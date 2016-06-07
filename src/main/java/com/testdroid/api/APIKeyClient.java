package com.testdroid.api;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.*;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.testdroid.api.http.MultipartFormDataContent;
import com.testdroid.api.model.APIDevice;
import com.testdroid.api.model.APILabelGroup;
import com.testdroid.api.model.APIUser;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.ChallengeState;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIKeyClient implements APIClient {

    protected static final String API_URI = "/api/v2";

    static final JAXBContext context = initContext();

    private final static int DEFAULT_CLIENT_CONNECT_TIMEOUT = 20000;

    private int clientConnectTimeout = DEFAULT_CLIENT_CONNECT_TIMEOUT;

    private final static int DEFAULT_CLIENT_REQUEST_TIMEOUT = 60000;

    private int clientRequestTimeout = DEFAULT_CLIENT_REQUEST_TIMEOUT;

    private static final String DEVICES_URI = "/devices";

    private static final String LABEL_GROUPS_URI = "/label-groups";

    private static final String TESTDROID_API_PACKAGES = "com.testdroid.api:com.testdroid.api.model";

    protected final HttpTransport httpTransport;

    protected String apiURL;

    protected String cloudURL;

    protected String apiKey;

    public APIKeyClient(String cloudURL, String apiKey) {
        this(cloudURL, apiKey, false);
    }

    public APIKeyClient(String cloudURL, String apiKey, boolean skipCheckCertificate) {
        NetHttpTransport.Builder netHttpBuilder;
        if (skipCheckCertificate) {
            try {
                netHttpBuilder = new NetHttpTransport.Builder().doNotValidateCertificate();
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(APIKeyClient.class.getName())
                        .log(Level.WARNING, "Cannot set not-validating certificate. Certificate will be validating.",
                                ex);
                netHttpBuilder = new NetHttpTransport.Builder();
            }
        } else {
            netHttpBuilder = new NetHttpTransport.Builder();
        }

        httpTransport = netHttpBuilder.build();
        initializeDefaultAPIClient(cloudURL, apiKey);
    }

    public APIKeyClient(String cloudURL, String username, HttpHost proxy, boolean skipCheckCertificate) {
        ApacheHttpTransport.Builder apacheBuilder;
        if (skipCheckCertificate) {
            try {
                apacheBuilder = new ApacheHttpTransport.Builder().setProxy(proxy).doNotValidateCertificate();
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(APIKeyClient.class.getName())
                        .log(Level.WARNING, "Cannot set not-validating certificate. Certificate will be validating.",
                                ex);
                apacheBuilder = new ApacheHttpTransport.Builder().setProxy(proxy);
            }
        } else {
            apacheBuilder = new ApacheHttpTransport.Builder().setProxy(proxy);
        }

        httpTransport = apacheBuilder.build();
        initializeDefaultAPIClient(cloudURL, username);
    }

    public APIKeyClient(
            String cloudURL, String apiKey, HttpHost proxy, final String proxyUser,
            final String proxyPassword, boolean skipCheckCertificate) {
        this(cloudURL, apiKey, proxy, skipCheckCertificate);

        DefaultHttpClient apacheClient = (DefaultHttpClient) ((ApacheHttpTransport) httpTransport).getHttpClient();
        apacheClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        apacheClient.getCredentialsProvider().setCredentials(
                new AuthScope(proxy.getHostName(), proxy.getPort()),
                new UsernamePasswordCredentials(proxyUser, proxyPassword));

        final AuthCache authCache = new BasicAuthCache();
        final BasicScheme basicAuth = new BasicScheme(ChallengeState.PROXY);
        authCache.put(proxy, basicAuth);

        apacheClient.addRequestInterceptor(new HttpRequestInterceptor() {
            @Override
            public void process(org.apache.http.HttpRequest hr, HttpContext hc) throws HttpException, IOException {
                hc.setAttribute(ClientContext.AUTH_CACHE, authCache);
            }
        }, 0);
    }

    protected static Credential getCredential() {
        return new Credential.Builder(BearerToken.queryParameterAccessMethod()).build();
    }

    private static JAXBContext initContext() {
        try {
            ClassLoader cl = APIEntity.class.getClassLoader();
            return JAXBContext.newInstance(TESTDROID_API_PACKAGES, cl);
        } catch (JAXBException e) {
            System.out.println("Failed initializing JAXBContext for DefaultAPIClient - API client will not work!");
            e.printStackTrace();
        }
        return null;
    }

    protected JAXBContext getContext() {
        return context;
    }

    private void initializeDefaultAPIClient(String cloudURL, String apiKey) {
        if (cloudURL.endsWith("/")) {
            cloudURL = cloudURL.substring(0, cloudURL.length() - 1);
        }
        this.cloudURL = cloudURL;
        this.apiURL = cloudURL + API_URI;
        this.apiKey = apiKey;
    }

    protected HttpRequestFactory getRequestFactory() {
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
        return getOnce(uri, null, type);
    }

    @Override
    public <T extends APIEntity> T get(String uri, APIQueryBuilder queryBuilder, Class<T> type) throws APIException {
        return getOnce(uri, queryBuilder, type);
    }

    @Override
    public InputStream get(String uri) throws APIException {
        return getStream(uri);
    }

    /**
     * Tries to call API once. Returns expected entity or throws exception.
     */
    private <T extends APIEntity> T getOnce(
            String uri, APIQueryBuilder queryBuilder,
            Class<T> type) throws APIException {
        // Build request
        HttpRequestFactory factory = getRequestFactory();
        HttpRequest request;
        HttpResponse response;
        try {
            // Call request and parse result
            request = factory.buildGetRequest(new GenericUrl(buildUrl(apiURL + uri, queryBuilder)));
            HttpHeaders httpHeaders = new HttpHeaders().setAccept("application/xml").setBasicAuthentication(apiKey, "");
            request.setHeaders(httpHeaders);
            request.setConnectTimeout(clientConnectTimeout);
            request.setReadTimeout(clientRequestTimeout);

            response = request.execute();
            if (!Arrays
                    .asList(HttpStatus.SC_OK, HttpStatus.SC_ACCEPTED, HttpStatus.SC_CREATED, HttpStatus.SC_NO_CONTENT)
                    .contains(response.getStatusCode())) {
                throw new APIException(response.getStatusCode(), String.format("Failed to execute api call: %s", uri));
            }

            T result = fromXML(response.getContent(), type);
            result.client = this;
            result.selfURI = uri;
            return result;
        } catch (HttpResponseException ex) {
            try {
                APIExceptionMessage exceptionMessage = fromXML(ex.getContent(), APIExceptionMessage.class);
                throw new APIException(ex.getStatusCode(), exceptionMessage.getMessage(), ex);
            } catch (APIException e) {
                throw new APIException(ex.getStatusCode(), ex.getMessage());
            }
        } catch (IOException ex) {
            throw new APIException(String
                    .format("Failed to execute API call: %s. Reason: %s", uri, ex.getMessage()), ex);
        }
    }

    private InputStream getStream(String uri) throws APIException {
        HttpRequestFactory factory = getRequestFactory();
        HttpRequest request;
        HttpResponse response;
        try {
            request = factory.buildGetRequest(new GenericUrl(apiURL + uri));
            request.setConnectTimeout(clientConnectTimeout);
            request.setReadTimeout(clientRequestTimeout);

            response = request.execute();
            if (!Arrays.asList(HttpStatus.SC_OK, HttpStatus.SC_ACCEPTED, HttpStatus.SC_CREATED)
                    .contains(response.getStatusCode())) {
                throw new APIException(response.getStatusCode(), String.format("Failed to execute api call: %s", uri));
            }

            return response.getContent();
        } catch (HttpResponseException ex) {
            try {
                APIExceptionMessage exceptionMessage = fromXML(ex.getContent(), APIExceptionMessage.class);
                throw new APIException(ex.getStatusCode(), exceptionMessage.getMessage(), ex);
            } catch (APIException e) {
                throw new APIException(ex.getStatusCode(), ex.getMessage());
            }
        } catch (IOException ex) {
            throw new APIException(String
                    .format("Failed to execute API call: %s. Reason: %s", uri, ex.getMessage()), ex);
        }
    }

    @Override
    public <T extends APIEntity> T post(String uri, Object body, Class<T> type) throws APIException {
        return postOnce(uri, body, null, type);
    }

    protected <T extends APIEntity> T postOnce(String uri, Object body, String contentType, Class<T> type)
            throws APIException {
        if (contentType == null) {
            contentType = "application/xml";
        }
        HttpRequestFactory factory = getRequestFactory();
        HttpRequest request;
        HttpResponse response;
        String resourceUrl = apiURL + uri;
        try {
            HttpContent content;
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept("application/xml");
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
                content = new InputStreamContent(contentType, IOUtils.toInputStream(((APIEntity) body).toXML()));
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
            HttpHeaders httpHeaders = new HttpHeaders().setAccept("application/xml").setBasicAuthentication(apiKey, "");
            request.setHeaders(httpHeaders);
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
                T result = fromXML(response.getContent(), type);
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
            try {
                try {
                    APIExceptionMessage exceptionMessage = fromXML(ex.getContent(), APIExceptionMessage.class);
                    throw new APIException(ex.getStatusCode(), exceptionMessage.getMessage(), ex);
                } catch (Exception e) {
                    // Catch exceptions related to xml unserialization. Those are usually internal server exceptions
                    // and are not properly serialized.
                    // In such case we just put pure response content as a message
                    throw new APIException(ex.getStatusCode(), ex.getContent(), ex);
                }
            } catch (APIException e) {
                throw new APIException(ex.getStatusCode(), ex.getMessage());
            }
        } catch (IOException ex) {
            throw new APIException(String
                    .format("Failed to execute API call: %s. Reason: %s", uri, ex.getMessage()), ex);
        }
    }

    @Override
    public <T extends APIEntity> T postFile(String uri, String contentType, File file, Class<T> type)
            throws APIException {
        return postOnce(uri, file, contentType, type);
    }

    @Override
    public void delete(String uri) throws APIException {
        deleteOnce(uri);
    }

    private void deleteOnce(String uri) throws APIException {
        HttpRequestFactory factory = getRequestFactory();
        HttpRequest request;
        HttpResponse response;
        try {
            request = factory.buildDeleteRequest(new GenericUrl(apiURL + uri));
            HttpHeaders httpHeaders = new HttpHeaders().setAccept("application/xml").setBasicAuthentication(apiKey, "");
            request.setHeaders(httpHeaders);
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
            try {
                APIExceptionMessage exceptionMessage = fromXML(ex.getContent(), APIExceptionMessage.class);
                throw new APIException(ex.getStatusCode(), exceptionMessage.getMessage(), ex);
            } catch (APIException e) {
                throw new APIException(ex.getStatusCode(), ex.getMessage());
            }
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
        return new APIListResource<APIDevice>(this, DEVICES_URI);
    }

    @Override
    public APIListResource<APIDevice> getDevices(APIDevice.DeviceFilter... filters) {
        return new APIListResource<APIDevice>(this, DEVICES_URI, new APIDeviceQueryBuilder()
                .filterWithDeviceFilters(filters));
    }

    @Override
    public APIListResource<APIDevice> getDevices(APIDeviceQueryBuilder queryBuilder) {
        return new APIListResource<APIDevice>(this, DEVICES_URI, queryBuilder);
    }

    @Override
    public APIListResource<APIDevice> getDevices(
            long offset, long limit, String search, APISort sort,
            APIDevice.DeviceFilter... filters) {
        if (limit <= 0) {
            limit = 10;
        }
        APIDeviceQueryBuilder builder = new APIDeviceQueryBuilder().offset((int) offset).limit((int) limit)
                .search(search).filterWithDeviceFilters(filters);
        if (sort != null) {
            builder.sort(APIDevice.class, sort.getSorts());
        }
        return new APIListResource<APIDevice>(this, DEVICES_URI, builder);
    }

    @Override
    public APIListResource<APILabelGroup> getLabelGroups() {
        return new APIListResource<APILabelGroup>(this, LABEL_GROUPS_URI);
    }

    @Override
    public APIListResource<APILabelGroup> getLabelGroups(APIQueryBuilder queryBuilder) {
        return new APIListResource<APILabelGroup>(this, LABEL_GROUPS_URI, queryBuilder);
    }

    private <T> T fromXML(String xml, Class<T> type) throws APIException {
        try {
            Unmarshaller unmarshaller = getContext().createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException ex) {
            throw new APIException(String.format("Failed to parse response as %s", type.getName()));
        }
    }

    private <T> T fromXML(InputStream inputStream, Class<T> type) throws APIException {
        try {
            Unmarshaller unmarshaller = getContext().createUnmarshaller();
            return (T) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException ex) {
            throw new APIException(String.format("Failed to parse response as %s", type.getName()));
        }
    }

    private String buildUrl(String url, APIQueryBuilder queryBuilder) throws APIException {
        try {
            URIBuilder builder = new URIBuilder(url);
            if (queryBuilder != null) {
                for (Map.Entry<String, Object> entry : queryBuilder.build().entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
                }
            }
            return builder.build().toString();
        } catch (URISyntaxException e) {
            throw new APIException(String.format("Bad URL: %s", e.getMessage()));
        }
    }

    private Map fixMapParameters(Map map) {
        for (Object key : map.keySet()) {
            if (map.get(key) == null) {
                map.put(key, "");
            }
            if (map.get(key) instanceof Enum<?>) {
                map.put(key, map.get(key).toString());
            }
        }
        return map;
    }
}
