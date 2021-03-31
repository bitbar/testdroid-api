package com.testdroid.api;

import com.testdroid.api.dto.Context;
import com.testdroid.api.filter.FilterEntry;
import com.testdroid.api.model.APIFramework;
import com.testdroid.api.model.APIUser;
import org.apache.http.HttpHost;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static com.testdroid.api.dto.MappingKey.*;
import static com.testdroid.api.dto.Operand.EQ;
import static com.testdroid.api.filter.FilterEntry.trueFilterEntry;
import static com.testdroid.api.model.APIDevice.OsType.ANDROID;
import static java.lang.Integer.MAX_VALUE;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNoneBlank;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
abstract class BaseAPIClientTest {

    static final String APP_PATH = "/fixtures/BitbarSampleApp.apk";

    private static final String CLOUD_URL = System.getenv("CLOUD_URL");

    private static final String ADMIN_API_KEY = System.getenv("ADMIN_API_KEY");

    private static final String EMAIL_PATTERN = System.getenv("EMAIL_PATTERN");

    private static final String PROXY_HOST = System.getenv("API_CLIENT_TEST_PROXY_HOST");

    private static final String PROXY_PORT = System.getenv("API_CLIENT_TEST_PROXY_PORT");

    static final APIKeyClient ADMIN_API_CLIENT = new APIKeyClient(CLOUD_URL, ADMIN_API_KEY, true);

    private static APIKeyClient USER_API_KEY_CLIENT;

    private static DefaultAPIClient USER_DEFAULT_CLIENT;

    private static DefaultAPIClient USER_DEFAULT_CLIENT_WITH_PROXY;

    static final String TEST_PATH = "/fixtures/BitbarSampleAppTest.apk";

    private static final String USER_PASSWORD = "TSV3ma2n)c3~L/96hQTw";

    @BeforeAll
    static void beforeAll() throws APIException {
        APIUser apiUser1 = create(ADMIN_API_CLIENT);
        USER_API_KEY_CLIENT = new APIKeyClient(CLOUD_URL, apiUser1.getApiKey());
        APIUser apiUser2 = create(ADMIN_API_CLIENT);
        USER_DEFAULT_CLIENT = new DefaultAPIClient(CLOUD_URL, apiUser2.getEmail(), USER_PASSWORD);
        if (isNoneBlank(PROXY_HOST, PROXY_PORT)) {
            USER_DEFAULT_CLIENT_WITH_PROXY = createDefaultApiClientWithProxy(new HttpHost(PROXY_HOST, Integer
                    .parseInt(PROXY_PORT)));
        }
    }

    @AfterAll
    static void afterAll() throws APIException {
        String deleteUrl = "%s/delete";
        Map<String, Object> map = new HashMap<>();
        map.put(PASSWORD, USER_PASSWORD);
        ADMIN_API_CLIENT.post(String.format(deleteUrl, USER_API_KEY_CLIENT.me().getSelfURI()), map, APIUser.class);
        ADMIN_API_CLIENT.post(String.format(deleteUrl, USER_DEFAULT_CLIENT.me().getSelfURI()), map, APIUser.class);
        if (USER_DEFAULT_CLIENT_WITH_PROXY != null) {
            ADMIN_API_CLIENT.post(String
                    .format(deleteUrl, USER_DEFAULT_CLIENT_WITH_PROXY.me().getSelfURI()), map, APIUser.class);
        }
    }

    static class APIClientProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(USER_API_KEY_CLIENT, USER_DEFAULT_CLIENT, USER_DEFAULT_CLIENT_WITH_PROXY).filter
                    (Objects::nonNull).map(Arguments::of);
        }
    }

    APIFramework getApiFramework(APIClient apiClient, String frameworkName) throws APIException {
        Context<APIFramework> context = new Context<>(APIFramework.class, 0, MAX_VALUE, EMPTY, EMPTY);
        context.addFilter(new FilterEntry(OS_TYPE, EQ, ANDROID.name()));
        context.addFilter(trueFilterEntry(FOR_PROJECTS));
        context.addFilter(trueFilterEntry(CAN_RUN_FROM_UI));
        context.addFilter(new FilterEntry(NAME, EQ, frameworkName));
        return apiClient.me().getAvailableFrameworksResource(context).getEntity().get(0);
    }

    private static APIUser create(APIKeyClient adminApiClient) throws APIException {
        Map<String, Object> map = new HashMap<>();
        map.put(EMAIL, generateUniqueEmail(EMAIL_PATTERN));
        APIUser userForTest = adminApiClient.post("/admin/users", map, APIUser.class);
        map.clear();
        map.put(EMAIL, userForTest.getEmail());
        map.put(NEW_PASSWORD, USER_PASSWORD);
        map.put(CONFIRM_PASSWORD, USER_PASSWORD);
        userForTest = adminApiClient.post(userForTest.selfURI, map, APIUser.class);
        return userForTest;
    }

    private static String generateUniqueEmail(String emailPattern) {
        return String.format(emailPattern, System.currentTimeMillis());
    }

    static String generateUnique(String prefix) {
        return String.format("%s%d", prefix, System.currentTimeMillis());
    }

    static DefaultAPIClient createDefaultApiClientWithProxy(HttpHost proxy) throws APIException {
        return new DefaultAPIClient(CLOUD_URL, create(ADMIN_API_CLIENT).getEmail(), USER_PASSWORD, proxy, false);
    }

}
