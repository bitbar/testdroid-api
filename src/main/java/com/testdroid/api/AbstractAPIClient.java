package com.testdroid.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.api.client.http.*;
import com.testdroid.api.dto.Context;
import com.testdroid.api.dto.MappingKey;
import com.testdroid.api.dto.Operand;
import com.testdroid.api.filter.FilterEntry;
import com.testdroid.api.http.MultipartFormDataContent;
import com.testdroid.api.model.APIDevice;
import com.testdroid.api.model.APIDeviceProperty;
import com.testdroid.api.model.APILabelGroup;
import com.testdroid.api.model.APIUser;
import com.testdroid.api.util.TypeReferenceFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.*;

import static com.testdroid.api.APIEntity.OBJECT_MAPPER;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.http.HttpStatus.*;

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

    protected String apiURL;

    private static final List<Integer> POSSIBLE_DELETE_STATUSES = Arrays.asList(SC_OK, SC_ACCEPTED, SC_NO_CONTENT);

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
        HttpResponse response = null;
        //Fix for https://jira.bitbar.com/browse/TD-12086
        //caused by https://github.com/googleapis/google-http-java-client/issues/398
        //We should used pure Apache Http Client
        uri = uri.replaceAll("\\+", "%2B");
        try {
            // Call request and parse result
            request = factory.buildGetRequest(new GenericUrl(buildUrl(apiURL + uri, context)));
            request.setHeaders(getHttpHeaders());
            request.setConnectTimeout(clientConnectTimeout);
            request.setReadTimeout(clientRequestTimeout);

            response = request.execute();
            if (!Arrays
                    .asList(SC_OK, HttpStatus.SC_ACCEPTED, HttpStatus.SC_CREATED, SC_NO_CONTENT)
                    .contains(response.getStatusCode())) {
                throw new APIException(response.getStatusCode(), String.format("Failed to execute api call: %s", uri));
            }

            T result = fromJson(response.getContent(), type);
            result.client = this;
            if (result.selfURI == null) {
                result.selfURI = uri;
            }
            return result;
        } catch (HttpResponseException ex) {
            throw getAPIException(ex);
        } catch (IOException ex) {
            throw new APIException(String
                    .format("Failed to execute API call: %s. Reason: %s", uri, ex.getMessage()), ex);
        } finally {
            disconnectQuietly(response);
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
            if (!Arrays.asList(SC_OK, HttpStatus.SC_ACCEPTED, HttpStatus.SC_CREATED)
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
        HttpResponse response = null;
        String resourceUrl = apiURL + uri;
        try {
            HttpContent content;
            HttpHeaders headers = getHttpHeaders();
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
                content = new InputStreamContent(contentType, IOUtils
                        .toInputStream(((APIEntity) body).toJson(), UTF_8));
            } else if (body instanceof HttpContent) {
                content = (HttpContent) body;
            } else if (body instanceof Map) {
                // Set empty strings for nulls - otherwise it is not passed at all to server and parameters is ignored
                content = new UrlEncodedContent(fixMapParameters((Map<String, Object>) body));
            } else if (body == null) {
                content = null;
            } else {
                content = new ByteArrayContent("text/plain", body.toString().getBytes());
            }
            request = factory.buildPostRequest(new GenericUrl(resourceUrl), content);
            request.setHeaders(headers);
            request.setConnectTimeout(clientConnectTimeout);
            request.setReadTimeout(clientRequestTimeout);

            // Call request and parse result
            response = request.execute();

            if (response == null) {
                throw new APIException("No response from API");
            }

            if (response.getStatusCode() < SC_OK || response.getStatusCode() >= 300) {
                throw new APIException(response.getStatusCode(), "Failed to post resource: " + response
                        .getStatusMessage());
            }

            if (type != null) {
                T result = fromJson(response.getContent(), type);
                result.client = this;
                if (result.selfURI == null) {
                    result.selfURI = uri;
                    // In case of entity creation, we need to update its url
                    if (response.getStatusCode() == HttpStatus.SC_CREATED && result.getId() != null) {
                        result.selfURI += String.format("/%s", result.getId());
                    }
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
        } finally {
            disconnectQuietly(response);
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
        HttpResponse response = null;
        try {
            request = factory.buildDeleteRequest(new GenericUrl(apiURL + uri));
            request.setHeaders(getHttpHeaders());
            request.setConnectTimeout(clientConnectTimeout);
            request.setReadTimeout(clientRequestTimeout);

            response = request.execute();
            if (response == null) {
                throw new APIException("No response from API");
            }

            if (!POSSIBLE_DELETE_STATUSES.contains(response.getStatusCode())) {
                throw new APIException(response.getStatusCode(), "Failed to delete resource: " + response
                        .getStatusMessage());
            }
        } catch (HttpResponseException ex) {
            throw getAPIException(ex);
        } catch (IOException ex) {
            throw new APIException(String
                    .format("Failed to execute API call: %s. Reason: %s", uri, ex.getMessage()), ex);
        } finally {
            disconnectQuietly(response);
        }
    }

    @Override
    public APIUser me() throws APIException {
        return get("/me", APIUser.class);
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

    @Override
    public Optional<APIDeviceProperty> findDevicePropertyInLabelGroup(String groupName, String labelName)
            throws APIException {
        Optional<APIDeviceProperty> result = Optional.empty();
        Context<APILabelGroup> ctx = new Context<>(APILabelGroup.class);
        ctx.addFilter(new FilterEntry(MappingKey.NAME, Operand.EQ, groupName));
        List<APILabelGroup> labelGroups = this.getLabelGroups(ctx).getEntity().getData();
        if (CollectionUtils.isNotEmpty(labelGroups)) {
            Context<APIDeviceProperty> lCtx = new Context<>(APIDeviceProperty.class);
            lCtx.addFilter(new FilterEntry(MappingKey.NAME, Operand.EQ, labelName));
            result = labelGroups.get(0).getDevicePropertiesResource(lCtx).getEntity().getData().stream().findFirst();
        }
        return result;
    }

    protected <T> T fromJson(InputStream inputStream, TypeReference<T> type) throws APIException {
        try {
            return OBJECT_MAPPER.readValue(inputStream, type);
        } catch (IOException e) {
            throw new APIException(String.format("Failed to parse response as %s: %s", type.getType().getTypeName(),
                    getAPIResponceContent(inputStream, type)));
        }
    }

    private String getAPIResponceContent(InputStream inputStream, TypeReference<?> type) throws APIException {
        try {
            return IOUtils.toString(inputStream, UTF_8.name());
        } catch (IOException e) {
            throw new APIException(String.format("Failed to parse response as %s", type.getType().getTypeName()));
        }
    }

    protected <T> T fromJson(String content, TypeReference<T> type) throws APIException {
        try {
            return OBJECT_MAPPER.readValue(content, type);
        } catch (IOException e) {
            throw new APIException(String.format("Failed to parse response as %s: %s", type.getType().getTypeName(),
                    content));
        }
    }

    protected <T extends APIEntity> String buildUrl(String url, Context<T> context) throws APIException {
        try {
            URIBuilder builder = new URIBuilder(url);
            if (context != null) {
                for (Map.Entry<String, Collection<Object>> entry : context.build().asMap().entrySet()) {
                    for (Object value : entry.getValue()) {
                        builder.addParameter(entry.getKey(), value == null ? EMPTY : value.toString());
                    }
                }
            }
            return builder.build().toString();
        } catch (URISyntaxException e) {
            throw new APIException(String.format("Bad URL: %s", e.getMessage()));
        }
    }

    protected Map<String, Object> fixMapParameters(Map<String, Object> map) {
        return map.entrySet().stream().collect(toMap(Map.Entry::getKey, p -> p.getValue() == null ? EMPTY :
                p.getValue() instanceof Enum<?> ? p.getValue().toString() : p.getValue()));
    }

    protected APIException getAPIException(HttpResponseException ex) {
        if (ex.getContent() == null) {
            return getNoContentAPIException(ex);
        }
        try {
            APIExceptionMessage exceptionMessage = fromJson(ex.getContent(),
                    TypeReferenceFactory.getTypeRef(APIExceptionMessage.class));
            return new APIException(ex.getStatusCode(), exceptionMessage.getMessage(), ex);
        } catch (APIException e) {
            return new APIException(ex.getStatusCode(), ex.getMessage());
        }
    }

    private APIException getNoContentAPIException(HttpResponseException ex) {
        String message;
        switch (ex.getStatusCode()) {
            case 401:
                message = "Unauthenticated access";
                break;
            case 403:
                message = "Unauthorized access";
                break;
            default:
                message = String.format("Unknown exception: %s - %s", ex.getStatusCode(), ex.getStatusMessage());
        }
        return new APIException(ex.getStatusCode(), message);
    }

    protected void disconnectQuietly(HttpResponse httpResponse) {
        if (httpResponse != null) {
            try {
                httpResponse.disconnect();
            } catch (IOException exc) {
                //ignore
            }
        }
    }
}
