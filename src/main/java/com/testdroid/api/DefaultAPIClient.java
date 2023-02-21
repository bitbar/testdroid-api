package com.testdroid.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.*;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.testdroid.api.util.TypeReferenceFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.ChallengeState;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.params.HttpConnectionParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import static com.testdroid.api.APIEntity.OBJECT_MAPPER;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 * @author Krzysztof Fonał <krzysztof.fonal@bitbar.com>
 */
public class DefaultAPIClient extends AbstractAPIClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAPIClient.class);

    public static final int HTTP_CONNECT_TIMEOUT = 60000;

    public static final int HTTP_READ_TIMEOUT = 60000;

    public static final String BITBAR_API_OAUTH2_CLIENT_ID = "testdroid-cloud-api";

    private static final String FAILED_TO_ACQUIRE_ACCESS_TOKEN = "Failed to acquire access token";

    private static final String FAILED_TO_ACQUIRE_ACCESS_TOKEN_REASON = "Failed to acquire access token. Reason: %s";

    protected String accessToken;

    protected long accessTokenExpireTime = 0;

    protected String cloudURL;

    protected String password;

    protected String refreshToken;

    protected String username;

    public DefaultAPIClient(String cloudURL, String username, String password) {
        this(cloudURL, username, password, false);
    }

    public DefaultAPIClient(String cloudURL, String username, String password, boolean skipCheckCertificate) {
        ApacheHttpTransport.Builder apacheBuilder = new ApacheHttpTransport.Builder();
        if (skipCheckCertificate) {
            try {
                apacheBuilder.doNotValidateCertificate();
            } catch (GeneralSecurityException ex) {
                LOGGER.warn("Cannot set not-validating certificate. Certificate will be validating.", ex);
            }
        }
        httpTransport = apacheBuilder.build();
        DefaultHttpClient apacheClient = (DefaultHttpClient) ((ApacheHttpTransport) httpTransport).getHttpClient();
        apacheClient.setHttpRequestRetryHandler(new StandardHttpRequestRetryHandler());
        HttpConnectionParams.setStaleCheckingEnabled(apacheClient.getParams(), true);
        initializeDefaultAPIClient(cloudURL, username, password);
    }

    public DefaultAPIClient(
            String cloudURL, String username, String password, HttpHost proxy, boolean skipCheckCertificate) {
        this(cloudURL, username, password, skipCheckCertificate);
        DefaultHttpClient apacheClient = (DefaultHttpClient) ((ApacheHttpTransport) httpTransport).getHttpClient();
        apacheClient.setRoutePlanner(new DefaultProxyRoutePlanner(proxy));
    }

    public DefaultAPIClient(
            String cloudURL, String username, String password, HttpHost proxy, final String proxyUser,
            final String proxyPassword, boolean skipCheckCertificate) {
        this(cloudURL, username, password, proxy, skipCheckCertificate);

        DefaultHttpClient apacheClient = (DefaultHttpClient) ((ApacheHttpTransport) httpTransport).getHttpClient();
        apacheClient.getCredentialsProvider().setCredentials(
                new AuthScope(proxy.getHostName(), proxy.getPort()),
                new UsernamePasswordCredentials(proxyUser, proxyPassword));

        final AuthCache authCache = new BasicAuthCache();
        final BasicScheme basicAuth = new BasicScheme(ChallengeState.PROXY);
        authCache.put(proxy, basicAuth);

        apacheClient.addRequestInterceptor((hr, hc) -> hc.setAttribute(HttpClientContext.AUTH_CACHE, authCache), 0);
    }

    private void initializeDefaultAPIClient(String cloudURL, String username, String password) {
        this.cloudURL = StringUtils.removeEnd(cloudURL, "/");
        this.apiURL = cloudURL + API_URI;
        this.username = username;
        this.password = password;
    }

    @Override
    protected HttpRequestFactory getRequestFactory() throws APIException {
        String accessToken = getAccessToken();
        final Credential credential = new Credential.Builder(BearerToken.queryParameterAccessMethod()).build();
        if (StringUtils.isNotBlank(accessToken)) {
            credential.setAccessToken(accessToken);
        }
        return httpTransport.createRequestFactory(credential);
    }

    private String getAccessToken() throws APIException {
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
        HttpResponse response = null;
        try {
            if (username == null && password == null) {
                return StringUtils.EMPTY;
            }

            GenericUrl url = new GenericUrl(String.format("%s/oauth/token", cloudURL));
            HashMap<String, Object> data = new HashMap<>();
            data.put("client_id", BITBAR_API_OAUTH2_CLIENT_ID);
            data.put("grant_type", "password");
            data.put("username", username);
            data.put("password", password);
            HttpContent content = new UrlEncodedContent(data);

            HttpRequest request = httpTransport.createRequestFactory().buildPostRequest(url, content);
            request.setConnectTimeout(HTTP_CONNECT_TIMEOUT); // one minute
            request.setReadTimeout(HTTP_READ_TIMEOUT); // one minute
            request.setHeaders(new HttpHeaders().setAccept("application/json"));

            response = request.execute();
            if (response.getStatusCode() != 200) {
                throw new APIException(response.getStatusCode(), FAILED_TO_ACQUIRE_ACCESS_TOKEN);
            }

            String responseJson = StringUtils.join(IOUtils.readLines(response.getContent(), UTF_8), "\n");
            Map<String, String> json = fromJson(responseJson, TypeReferenceFactory.getMapTypeReference());
            accessTokenExpireTime = System.currentTimeMillis() + (Long.parseLong(json.get("expires_in")) * 1000);
            refreshToken = json.get("refresh_token");
            return json.get("access_token");
        } catch (HttpResponseException ex) {
            String message;
            if (StringUtils.isNotBlank(ex.getContent())) {
                try {
                    message = OBJECT_MAPPER.readValue(ex.getContent(), APIExceptionMessage.class).getMessage();
                } catch (JsonProcessingException e) {
                    message = FAILED_TO_ACQUIRE_ACCESS_TOKEN;
                }
            } else {
                message = String.format(FAILED_TO_ACQUIRE_ACCESS_TOKEN_REASON, ex.getStatusMessage());
            }
            throw new APIException(ex.getStatusCode(), message, ex);
        } catch (IOException ex) {
            throw new APIException(String.format(FAILED_TO_ACQUIRE_ACCESS_TOKEN_REASON, ex.getMessage()), ex);
        } finally {
            disconnectQuietly(response);
        }
    }

    protected String refreshAccessToken() throws APIException {
        HttpResponse response = null;
        try {
            if (refreshToken == null) {
                return null;
            }

            GenericUrl url = new GenericUrl(String.format("%s/oauth/token", cloudURL));
            HashMap<String, Object> data = new HashMap<>();
            data.put("client_id", BITBAR_API_OAUTH2_CLIENT_ID);
            data.put("grant_type", "refresh_token");
            data.put("refresh_token", refreshToken);
            HttpContent content = new UrlEncodedContent(data);

            HttpRequest request = httpTransport.createRequestFactory().buildPostRequest(url, content);
            request.setConnectTimeout(HTTP_CONNECT_TIMEOUT); // one minute
            request.setReadTimeout(HTTP_READ_TIMEOUT); // one minute
            request.setHeaders(getHttpHeaders());
            response = request.execute();

            if (response.getStatusCode() != 200) {
                throw new APIException(response.getStatusCode(), "Failed to refresh access token");
            }

            String jsonContent = StringUtils.join(IOUtils.readLines(response.getContent(), UTF_8), "\n");
            Map<String, String> json = fromJson(jsonContent, TypeReferenceFactory.getMapTypeReference());
            accessTokenExpireTime = System.currentTimeMillis() + (Long.parseLong(json.get("expires_in")) * 1000);
            refreshToken = json.get("refresh_token");
            return json.get("access_token");
        } catch (IOException ex) {
            throw new APIException(String.format("Failed to refresh access token. Reason: %s", ex.getMessage()), ex);
        } finally {
            disconnectQuietly(response);
        }
    }

    @Override
    public <T extends APIEntity> T get(String uri, Class<T> type) throws APIException {
        try {
            return super.get(uri, type);
        } catch (APIException ex) {
            if (ex.getStatus() != null && HttpStatus.SC_UNAUTHORIZED == ex.getStatus()) {
                // Access token may have expired. Clean and try again.
                accessToken = null;
                return super.get(uri, type);
            } else {
                throw ex;
            }
        }
    }

    @Override
    public InputStream get(String uri) throws APIException {
        try {
            return super.get(uri);
        } catch (APIException ex) {
            if (ex.getStatus() != null && HttpStatus.SC_UNAUTHORIZED == ex.getStatus()) {
                // Access token may have expired. Clean and try again.
                accessToken = null;
                return super.get(uri);
            } else {
                throw ex;
            }
        }
    }

    @Override
    public <T extends APIEntity> T post(String uri, Object body, Class<T> type) throws APIException {
        try {
            return super.post(uri, body, type);
        } catch (APIException ex) {
            if (ex.getStatus() != null && HttpStatus.SC_UNAUTHORIZED == ex.getStatus()) {
                // Access token may have expired. Clean and try again.
                accessToken = null;
                return super.post(uri, body, type);
            } else {
                throw ex;
            }
        }
    }

    @Override
    public <T extends APIEntity> T postFile(
            String uri, String contentType, File file, Map<String, String> fileExtraParams, Class<T> type)
            throws APIException {
        try {
            return super.postFile(uri, contentType, file, fileExtraParams, type);
        } catch (APIException ex) {
            if (ex.getStatus() != null && HttpStatus.SC_UNAUTHORIZED == ex.getStatus()) {
                // Access token may have expired. Clean and try again.
                accessToken = null;
                return super.postFile(uri, contentType, file, fileExtraParams, type);
            } else {
                throw ex;
            }
        }
    }

    @Override
    public void delete(String uri) throws APIException {
        try {
            super.delete(uri);
        } catch (APIException ex) {
            if (ex.getStatus() != null && HttpStatus.SC_UNAUTHORIZED == ex.getStatus()) {
                // Access token may have expired. Clean and try again.
                accessToken = null;
                super.delete(uri);
            } else {
                throw ex;
            }
        }
    }

    @Override
    protected HttpHeaders getHttpHeaders() {
        return new HttpHeaders().setAccept(ACCEPT_HEADER);
    }
}
