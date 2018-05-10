package com.testdroid.api;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.testdroid.api.dto.Context;
import com.testdroid.api.model.APIDevice;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.ChallengeState;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;

import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIKeyClient extends AbstractAPIClient {

    private String apiKey;

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

        apacheClient.addRequestInterceptor((hr, hc) -> hc.setAttribute(HttpClientContext.AUTH_CACHE, authCache), 0);
    }

    private void initializeDefaultAPIClient(String cloudURL, String apiKey) {
        this.apiURL = StringUtils.removeEnd(cloudURL, "/") + API_URI;
        this.apiKey = apiKey;
    }

    @Override
    protected HttpHeaders getHttpHeaders() {
        return new HttpHeaders().setAccept(ACCEPT_HEADER).setBasicAuthentication(apiKey, "");
    }
}
