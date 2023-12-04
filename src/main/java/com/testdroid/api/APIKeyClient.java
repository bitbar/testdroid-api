package com.testdroid.api;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import org.apache.commons.lang3.StringUtils;

import java.net.Proxy;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIKeyClient extends AbstractAPIClient {

    private final String apiKey;

    public APIKeyClient(String cloudURL, String apiKey) {
        this(cloudURL, apiKey, false);
    }

    public APIKeyClient(String cloudURL, String apiKey, boolean skipCheckCertificate) {
        this.apiURL = StringUtils.removeEnd(cloudURL, "/") + API_URI;
        this.apiKey = apiKey;
        this.skipCheckCertificate = skipCheckCertificate;
    }

    public APIKeyClient(String cloudURL, String apiKey, Proxy proxy, boolean skipCheckCertificate) {
        this.apiURL = StringUtils.removeEnd(cloudURL, "/") + API_URI;
        this.apiKey = apiKey;
        this.skipCheckCertificate = skipCheckCertificate;
        this.proxy = proxy;
    }

    public APIKeyClient(
            String cloudURL, String apiKey, Proxy proxy, final String proxyUser,
            final String proxyPassword, boolean skipCheckCertificate) {
        this(cloudURL, apiKey, proxy, skipCheckCertificate);
        this.proxyUser = proxyUser;
        this.proxyPassword = proxyPassword;
    }

    protected Interceptor getInterceptor() {
        return chain -> chain.proceed(chain.request().newBuilder()
                .addHeader("Authorization", Credentials.basic(apiKey, EMPTY))
                .build());
    }
}
