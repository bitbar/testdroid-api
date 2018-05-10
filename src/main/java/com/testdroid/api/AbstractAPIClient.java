package com.testdroid.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.*;
import com.testdroid.api.dto.Context;
import com.testdroid.api.http.MultipartFormDataContent;
import com.testdroid.api.model.APIDevice;
import com.testdroid.api.model.APILabelGroup;
import com.testdroid.api.model.APIUser;
import com.testdroid.api.util.TypeReferenceFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public abstract class AbstractAPIClient implements APIClient {

    protected static final String API_URI = "/api/v2";

    protected static final String ACCEPT_HEADER = "application/json";

    protected static final String DEVICES_URI = "/devices";

    protected static final String LABEL_GROUPS_URI = "/label-groups";
    
    protected int clientConnectTimeout = 20000;

    protected int clientRequestTimeout = 60000;

    protected HttpTransport httpTransport;

    protected ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    protected String apiURL;

    protected HttpRequestFactory getRequestFactory() throws APIException {
        return httpTransport.createRequestFactory();
    }

    @Override
    public void setConnectTimeout(int timeout) {
        clientConnectTimeout = timeout;
    }

    @Override
    public void setRequestTimeout(int timeout) {
        clientRequestTimeout = timeout;
    }

    @Override
    public <T extends APIEntity> T get(String uri, Class<T> type) throws APIException {
        return getOnce(uri, null, TypeReferenceFactory.getTypeRef(type));
    }

    @Override
    public InputStream get(String uri) throws APIException {
        return getStream(uri);
    }

    @Override
    public <T extends APIEntity> APIList<T> get(String uri, Context<T> context) throws APIException {
        return getOnce(uri, context, TypeReferenceFactory.getListTypeRef(context.getType()));
    }

    protected abstract HttpHeaders getHttpHeaders();

    /**
     * Tries to call API once. Returns expected entity or throws exception.
     */
    protected <T extends APIEntity> T getOnce(String uri, Context<?> context, TypeReference<T> type)
            throws APIException {
        // Build request
        HttpRequestFactory factory = getRequestFactory();
        HttpRequest request;
        HttpResponse response;
        try {
            // Call request and parse result
            request = factory.buildGetRequest(new GenericUrl(buildUrl(apiURL + uri, context)));
            request.setHeaders(getHttpHeaders());
            request.setConnectTimeout(clientConnectTimeout);
            request.setReadTimeout(clientRequestTimeout);

            response = request.execute();
            if (!Arrays
                    .asList(HttpStatus.SC_OK, HttpStatus.SC_ACCEPTED, HttpStatus.SC_CREATED, HttpStatus.SC_NO_CONTENT)
                    .contains(response.getStatusCode())) {
                throw new APIException(response.getStatusCode(), String.format("Failed to execute api call: %s", uri));
            }

            T result = fromJson(response.getContent(), type);
            result.client = this;
            result.selfURI = uri;
            return result;
        } catch (HttpResponseException ex) {
            throw getAPIException(ex);
        } catch (IOException ex) {
            throw new APIException(String
                    .format("Failed to execute API call: %s. Reason: %s", uri, ex.getMessage()), ex);
        }
    }

    protected InputStream getStream(String uri) throws APIException {
        HttpRequestFactory factory = getRequestFactory();
        HttpRequest request;
        HttpResponse response;
        try {
            request = factory.buildGetRequest(new GenericUrl(apiURL + uri));
            request.setConnectTimeout(clientConnectTimeout);
            request.setReadTimeout(clientRequestTimeout);
            request.setHeaders(getHttpHeaders());
            response = request.execute();
            if (!Arrays.asList(HttpStatus.SC_OK, HttpStatus.SC_ACCEPTED, HttpStatus.SC_CREATED)
                    .contains(response.getStatusCode())) {
                throw new APIException(response.getStatusCode(), String.format("Failed to execute api call: %s", uri));
            }
            return response.getContent();
        } catch (HttpResponseException ex) {
            throw getAPIException(ex);
        } catch (IOException ex) {
            throw new APIException(String
                    .format("Failed to execute API call: %s. Reason: %s", uri, ex.getMessage()), ex);
        }
    }

    @Override
    public <T extends APIEntity> T post(String uri, Object body, Class<T> type) throws APIException {
        return postOnce(uri, body, null, TypeReferenceFactory.getTypeRef(type));
    }

    protected <T extends APIEntity> T postOnce(String uri, Object body, String contentType, TypeReference<T> type)
            throws APIException {
        if (contentType == null) {
            contentType = ACCEPT_HEADER;
        }
        HttpRequestFactory factory = getRequestFactory();
        HttpRequest request;
        HttpResponse response;
        String resourceUrl = apiURL + uri;
        try {
            HttpContent content;
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(ACCEPT_HEADER);
            if (body instanceof File) {
                MultipartFormDataContent multipartContent = new MultipartFormDataContent();
                FileContent fileContent = new FileContent(contentType, (File) body);

                MultipartFormDataContent.Part filePart = new MultipartFormDataContent.Part("file", fileContent);
                multipartContent.addPart(filePart);

                content = multipartContent;
            } else if (body instanceof InputStream) {
                headers.setContentType(contentType);
                content = new InputStreamContent(contentType, (InputStream) body);
            } else if (body instanceof APIEntity) {
                content = new InputStreamContent(contentType, IOUtils.toInputStream(((APIEntity) body).toJson(), UTF_8));
            } else if (body instanceof HttpContent) {
                content = (HttpContent) body;
            } else if (body instanceof Map) {
                Map map = (Map) body;
                // Set empty strings for nulls - otherwise it is not passed at all to server and parameters is ignored
                fixMapParameters(map);
                content = new UrlEncodedContent(map);
            } else if (body == null) {
                content = null;
            } else {
                content = new ByteArrayContent("text/plain", body.toString().getBytes());
            }
            request = factory.buildPostRequest(new GenericUrl(resourceUrl), content);
            request.setHeaders(getHttpHeaders());
            request.setConnectTimeout(clientConnectTimeout);
            request.setReadTimeout(clientRequestTimeout);

            // Call request and parse result
            response = request.execute();

            if (response == null) {
                throw new APIException("No response from API");
            }

            if (response.getStatusCode() < HttpStatus.SC_OK || response.getStatusCode() >= 300) {
                throw new APIException(response.getStatusCode(), "Failed to post resource: " + response
                        .getStatusMessage());
            }

            if (type != null) {
                T result = fromJson(response.getContent(), type);
                result.client = this;
                result.selfURI = uri;

                // In case of entity creation, we need to update its url
                if (response.getStatusCode() == HttpStatus.SC_CREATED && result.getId() != null) {
                    result.selfURI += String.format("/%s", result.getId());
                }
                return result;
            } else {
                return null;

            }
        } catch (HttpResponseException ex) {
            throw getAPIException(ex);
        } catch (IOException ex) {
            throw new APIException(String
                    .format("Failed to execute API call: %s. Reason: %s", uri, ex.getMessage()), ex);
        }
    }

    @Override
    public <T extends APIEntity> T postFile(String uri, String contentType, File file, Class<T> type)
            throws APIException {
        return postOnce(uri, file, contentType, TypeReferenceFactory.getTypeRef(type));
    }

    @Override
    public void delete(String uri) throws APIException {
        deleteOnce(uri);
    }

    protected void deleteOnce(String uri) throws APIException {
        HttpRequestFactory factory = getRequestFactory();
        HttpRequest request;
        HttpResponse response;
        try {
            request = factory.buildDeleteRequest(new GenericUrl(apiURL + uri));
            request.setHeaders(getHttpHeaders());
            request.setConnectTimeout(clientConnectTimeout);
            request.setReadTimeout(clientRequestTimeout);

            response = request.execute();
            if (response == null) {
                throw new APIException("No response from API");
            }

            if (response.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new APIException(response.getStatusCode(), "Failed to delete resource: " + response
                        .getStatusMessage());
            }
        } catch (HttpResponseException ex) {
            throw getAPIException(ex);
        } catch (IOException ex) {
            throw new APIException(String
                    .format("Failed to execute API call: %s. Reason: %s", uri, ex.getMessage()), ex);
        }
    }

    @Override
    public APIUser me() throws APIException {
        return get("/me", APIUser.class);
    }

    @Override
    public APIUser register(String email) throws APIException {
        APIUser result = post("/users", Collections.singletonMap("email", email), APIUser.class);
        result.selfURI = "/me";
        return result;
    }

    @Override
    public APIListResource<APIDevice> getDevices() {
        return new APIListResource<>(this, DEVICES_URI, APIDevice.class);
    }

    @Override
    public APIListResource<APIDevice> getDevices(Context<APIDevice> context) {
        return new APIListResource<>(this, DEVICES_URI, context);
    }

    @Override
    public APIListResource<APILabelGroup> getLabelGroups() {
        return new APIListResource<>(this, LABEL_GROUPS_URI, APILabelGroup.class);
    }

    @Override
    public APIListResource<APILabelGroup> getLabelGroups(Context<APILabelGroup> context) {
        return new APIListResource<>(this, LABEL_GROUPS_URI, context);
    }

    protected <T> T fromJson(InputStream inputStream, TypeReference<T> type) throws APIException {
        try {
            return objectMapper.readValue(inputStream, type);
        } catch (IOException e) {
            throw new APIException(String.format("Failed to parse response as %s", type.getType().getTypeName()));
        }
    }

    protected  <T> T fromJson(String content, TypeReference<T> type) throws APIException {
        try {
            return objectMapper.readValue(content, type);
        } catch (IOException e) {
            throw new APIException(String.format("Failed to parse response as %s", type.getType().getTypeName()));
        }
    }

    protected <T extends APIEntity> String buildUrl(String url, Context<T> context) throws APIException {
        try {
            URIBuilder builder = new URIBuilder(url);
            if (context != null) {
                for (Map.Entry<String, Object> entry : context.build().entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
                }
            }
            return builder.build().toString();
        } catch (URISyntaxException e) {
            throw new APIException(String.format("Bad URL: %s", e.getMessage()));
        }
    }

    protected Map fixMapParameters(Map<String, Object> map) {
        String key;
        Object value;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (value == null) {
                map.put(key, "");
            }
            if (value instanceof Enum<?>) {
                map.put(key, value.toString());
            }
        }
        return map;
    }

    protected APIException getAPIException(HttpResponseException ex) throws APIException {
        try {
            APIExceptionMessage exceptionMessage = fromJson(ex.getContent(),
                    TypeReferenceFactory.getTypeRef(APIExceptionMessage.class));
            return new APIException(ex.getStatusCode(), exceptionMessage.getMessage(), ex);
        } catch (APIException e) {
            return new APIException(ex.getStatusCode(), ex.getMessage());
        }
    }
}
