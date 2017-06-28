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
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.ChallengeState;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;

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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 * @author Krzysztof Fonał <krzysztof.fonal@bitbar.com>
 */
public class DefaultAPIClient implements APIClient {

    public static final int HTTP_CONNECT_TIMEOUT = 60000;

    public static final int HTTP_READ_TIMEOUT = 60000;

    protected static final String API_URI = "/api/v2";

    static final JAXBContext context = initContext();

    private static final int DEFAULT_CLIENT_CONNECT_TIMEOUT = 20000;

    private int clientConnectTimeout = DEFAULT_CLIENT_CONNECT_TIMEOUT;

    private static final int DEFAULT_CLIENT_REQUEST_TIMEOUT = 60000;

    private int clientRequestTimeout = DEFAULT_CLIENT_REQUEST_TIMEOUT;

    private static final String DEVICES_URI = "/devices";

    private static final String LABEL_GROUPS_URI = "/label-groups";

    private static final String TESTDROID_API_PACKAGES = "com.testdroid.api:com.testdroid.api.model";

    protected final HttpTransport httpTransport;

    protected String accessToken;

    protected long accessTokenExpireTime = 0;

    protected String apiURL;

    protected String cloudURL;

    protected String password;

    protected String refreshToken;

    protected String username;

    public DefaultAPIClient(String cloudURL, String username, String password) {
        this(cloudURL, username, password, false);
    }

    public DefaultAPIClient(String cloudURL, String username, String password, boolean skipCheckCertificate) {
        NetHttpTransport.Builder netHttpBuilder;
        if (skipCheckCertificate) {
            try {
                netHttpBuilder = new NetHttpTransport.Builder().doNotValidateCertificate();
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(DefaultAPIClient.class.getName())
                        .log(Level.WARNING, "Cannot set not-validating certificate. Certificate will be validating.",
                                ex);
                netHttpBuilder = new NetHttpTransport.Builder();
            }
        } else {
            netHttpBuilder = new NetHttpTransport.Builder();
        }

        httpTransport = netHttpBuilder.build();
        initializeDefaultAPIClient(cloudURL, username, password);
    }

    public DefaultAPIClient(
            String cloudURL, String username, String password, HttpHost proxy,
            boolean skipCheckCertificate) {
        ApacheHttpTransport.Builder apacheBuilder;
        if (skipCheckCertificate) {
            try {
                apacheBuilder = new ApacheHttpTransport.Builder().setProxy(proxy).doNotValidateCertificate();
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(DefaultAPIClient.class.getName())
                        .log(Level.WARNING, "Cannot set not-validating certificate. Certificate will be validating.",
                                ex);
                apacheBuilder = new ApacheHttpTransport.Builder().setProxy(proxy);
            }
        } else {
            apacheBuilder = new ApacheHttpTransport.Builder().setProxy(proxy);
        }

        httpTransport = apacheBuilder.build();
        initializeDefaultAPIClient(cloudURL, username, password);
    }

    public DefaultAPIClient(
            String cloudURL, String username, String password, HttpHost proxy, final String proxyUser,
            final String proxyPassword, boolean skipCheckCertificate) {
        this(cloudURL, username, password, proxy, skipCheckCertificate);

        DefaultHttpClient apacheClient = (DefaultHttpClient) ((ApacheHttpTransport) httpTransport).getHttpClient();
        apacheClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        apacheClient.getCredentialsProvider().setCredentials(
                new AuthScope(proxy.getHostName(), proxy.getPort()),
                new UsernamePasswordCredentials(proxyUser, proxyPassword));

        final AuthCache authCache = new BasicAuthCache();
        final BasicScheme basicAuth = new BasicScheme(ChallengeState.PROXY);
        authCache.put(proxy, basicAuth);

        apacheClient.addRequestInterceptor((hr, hc) -> hc.setAttribute(HttpClientContext.AUTH_CACHE, authCache), 0);
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

    private void initializeDefaultAPIClient(String cloudURL, String username, String password) {
        if (cloudURL.endsWith("/")) {
            cloudURL = cloudURL.substring(0, cloudURL.length() - 1);
        }
        this.cloudURL = cloudURL;
        this.apiURL = cloudURL + API_URI;
        this.username = username;
        this.password = password;
    }

    protected HttpRequestFactory getRequestFactory(String accessToken) {
        final Credential credential = getCredential();
        if (StringUtils.isNotBlank(accessToken)) {
            credential.setAccessToken(accessToken);
        }
        return httpTransport.createRequestFactory(credential::initialize);
    }

    protected String getAccessToken() throws APIException {
        if (accessToken == null) {
            accessToken = acquireAccessToken();
        } else if (System.currentTimeMillis() > (accessTokenExpireTime - 10 * 1000)) {
            try {
                accessToken = refreshAccessToken();
            } catch (APIException ex) {
                accessToken = null; // if refreshing failed, then we are not authorized
                accessToken = acquireAccessToken();
            }
        }
        return accessToken;
    }

    protected String acquireAccessToken() throws APIException {
        try {
            if (username == null && password == null) {
                return "";
            }

            GenericUrl url = new GenericUrl(String.format("%s/oauth/token", cloudURL));
            HashMap data = new HashMap();
            data.put("client_id", "testdroid-cloud-api");
            data.put("grant_type", "password");
            data.put("username", username);
            data.put("password", password);
            HttpContent content = new UrlEncodedContent(data);

            HttpRequest request = httpTransport.createRequestFactory().buildPostRequest(url, content);
            request.setConnectTimeout(HTTP_CONNECT_TIMEOUT); // one minute
            request.setReadTimeout(HTTP_READ_TIMEOUT); // one minute            
            request.setHeaders(new HttpHeaders().setAccept("application/json"));

            HttpResponse response = request.execute();
            if (response.getStatusCode() != 200) {
                throw new APIException(response.getStatusCode(), "Failed to acquire access token");
            }

            String responseJson = StringUtils.join(IOUtils.readLines(response.getContent(), UTF_8), "\n");
            JSONObject json = JSONObject.fromObject(responseJson);
            accessTokenExpireTime = System.currentTimeMillis() + (Long.parseLong(json.optString("expires_in")) * 1000);
            refreshToken = json.optString("refresh_token");
            return json.optString("access_token");
        } catch (HttpResponseException ex) {
            throw new APIException(String
                    .format("Failed to acquire access token. Reason: %s", ex.getStatusMessage()), ex);
        } catch (IOException ex) {
            throw new APIException(String.format("Failed to acquire access token. Reason: %s", ex.getMessage()), ex);
        }
    }

    protected String refreshAccessToken() throws APIException {
        try {
            if (refreshToken == null) {
                return null;
            }

            GenericUrl url = new GenericUrl(String.format("%s/oauth/token", cloudURL));
            HttpContent content = new UrlEncodedContent(new HashMap() {{
                put("client_id", "testdroid-cloud-api");
                put("grant_type", "refresh_token");
                put("refresh_token", refreshToken);
            }});

            HttpRequest request = httpTransport.createRequestFactory().buildPostRequest(url, content);
            request.setConnectTimeout(HTTP_CONNECT_TIMEOUT); // one minute
            request.setReadTimeout(HTTP_READ_TIMEOUT); // one minute
            request.setHeaders(new HttpHeaders().setAccept("application/json"));
            HttpResponse response = request.execute();

            if (response.getStatusCode() != 200) {
                throw new APIException(response.getStatusCode(), "Failed to refresh access token");
            }

            String jsonContent = StringUtils.join(IOUtils.readLines(response.getContent(), UTF_8), "\n");
            JSONObject json = JSONObject.fromObject(jsonContent);
            accessTokenExpireTime = System.currentTimeMillis() + (Long.parseLong(json.optString("expires_in")) * 1000);
            refreshToken = json.optString("refresh_token");
            return json.optString("access_token");
        } catch (IOException ex) {
            throw new APIException(String.format("Failed to refresh access token. Reason: %s", ex.getMessage()), ex);
        }
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
        try {
            return getOnce(uri, null, type);
        } catch (APIException ex) {
            if (ex.getStatus() != null && HttpStatus.SC_UNAUTHORIZED == ex.getStatus()) {
                // Access token may have expired. Clean and try again.
                accessToken = null;
                return getOnce(uri, null, type);
            } else {
                throw ex;
            }
        }
    }

    @Override
    public <T extends APIEntity> T get(String uri, APIQueryBuilder queryBuilder, Class<T> type) throws APIException {
        try {
            return getOnce(uri, queryBuilder, type);
        } catch (APIException ex) {
            if (ex.getStatus() != null && HttpStatus.SC_UNAUTHORIZED == ex.getStatus()) {
                // Access token may have expired. Clean and try again.
                accessToken = null;
                return getOnce(uri, queryBuilder, type);
            } else {
                throw ex;
            }
        }
    }

    @Override
    public InputStream get(String uri) throws APIException {
        try {
            return getStream(uri);
        } catch (APIException ex) {
            if (ex.getStatus() != null && HttpStatus.SC_UNAUTHORIZED == ex.getStatus()) {
                // Access token may have expired. Clean and try again.
                accessToken = null;
                return getStream(uri);
            } else {
                throw ex;
            }
        }
    }

    /**
     * Tries to call API once. Returns expected entity or throws exception.
     */
    private <T extends APIEntity> T getOnce(
            String uri, APIQueryBuilder queryBuilder,
            Class<T> type) throws APIException {
        // Build request
        HttpRequestFactory factory = getRequestFactory(getAccessToken());
        HttpRequest request;
        HttpResponse response;
        try {
            // Call request and parse result
            request = factory.buildGetRequest(new GenericUrl(buildUrl(apiURL + uri, queryBuilder)));
            request.setHeaders(new HttpHeaders().setAccept("application/xml"));
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
        HttpRequestFactory factory = getRequestFactory(getAccessToken());
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
        try {
            return postOnce(uri, body, null, type);
        } catch (APIException ex) {
            if (ex.getStatus() != null && HttpStatus.SC_UNAUTHORIZED == ex.getStatus()) {
                // Access token may have expired. Clean and try again.
                accessToken = null;
                return postOnce(uri, body, null, type);
            } else {
                throw ex;
            }
        }
    }

    protected <T extends APIEntity> T postOnce(String uri, Object body, String contentType, Class<T> type)
            throws APIException {
        if (contentType == null) {
            contentType = "application/xml";
        }
        HttpRequestFactory factory = getRequestFactory(getAccessToken());
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
                content = new InputStreamContent(contentType, IOUtils.toInputStream(((APIEntity) body).toXML(), UTF_8));
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
            request.setHeaders(headers);
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
        try {
            return postOnce(uri, file, contentType, type);
        } catch (APIException ex) {
            if (ex.getStatus() != null && HttpStatus.SC_UNAUTHORIZED == ex.getStatus()) {
                // Access token may have expired. Clean and try again.
                accessToken = null;
                return postOnce(uri, file, contentType, type);
            } else {
                throw ex;
            }
        }
    }

    @Override
    public void delete(String uri) throws APIException {
        try {
            deleteOnce(uri);
        } catch (APIException ex) {
            if (ex.getStatus() != null && HttpStatus.SC_UNAUTHORIZED == ex.getStatus()) {
                // Access token may have expired. Clean and try again.
                accessToken = null;
                deleteOnce(uri);
            } else {
                throw ex;
            }
        }
    }

    private void deleteOnce(String uri) throws APIException {
        HttpRequestFactory factory = getRequestFactory(getAccessToken());
        HttpRequest request;
        HttpResponse response;
        try {
            request = factory.buildDeleteRequest(new GenericUrl(apiURL + uri));
            request.setHeaders(new HttpHeaders().setAccept("application/xml"));
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
        return new APIListResource<>(this, DEVICES_URI, new APIDeviceQueryBuilder()
                .filterWithDeviceFilters(filters));
    }

    @Override
    public APIListResource<APIDevice> getDevices(APIDeviceQueryBuilder queryBuilder) {
        return new APIListResource<>(this, DEVICES_URI, queryBuilder);
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
        return new APIListResource<>(this, DEVICES_URI, builder);
    }

    @Override
    public APIListResource<APILabelGroup> getLabelGroups() {
        return new APIListResource<>(this, LABEL_GROUPS_URI);
    }

    @Override
    public APIListResource<APILabelGroup> getLabelGroups(APIQueryBuilder queryBuilder) {
        return new APIListResource<>(this, LABEL_GROUPS_URI, queryBuilder);
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

    private Map fixMapParameters(Map<String, Object> map) {
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

}
