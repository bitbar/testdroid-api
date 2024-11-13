package com.testdroid.api;

import com.testdroid.api.dto.Context;
import com.testdroid.api.filter.FilterEntry;
import com.testdroid.api.model.APIFramework;
import com.testdroid.api.model.APIUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;
import java.util.stream.Stream;

import static com.testdroid.api.dto.MappingKey.*;
import static com.testdroid.api.dto.Operand.EQ;
import static com.testdroid.api.dto.Operand.LIKE;
import static com.testdroid.api.filter.FilterEntry.trueFilterEntry;
import static com.testdroid.api.model.APIDevice.OsType.ANDROID;
import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static java.lang.Integer.MAX_VALUE;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNoneBlank;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
abstract class BaseAPIClientTest {

    static final String APP_PATH = "/fixtures/BitbarSampleApp.apk";

    protected static final String CLOUD_URL = System.getenv("CLOUD_URL");

    private static final String ADMIN_API_KEY = System.getenv("ADMIN_API_KEY");

    private static final String EMAIL_PATTERN = System.getenv("EMAIL_PATTERN");

    private static final String PROXY_HOST = System.getenv("API_CLIENT_TEST_PROXY_HOST");

    private static final String PROXY_PORT = System.getenv("API_CLIENT_TEST_PROXY_PORT");

    static final APIKeyClient ADMIN_API_CLIENT = new APIKeyClient(CLOUD_URL, ADMIN_API_KEY, true);

    private static APIKeyClient USER_API_KEY_CLIENT;

    private static APIKeyClient USER_API_KEY_CLIENT_WITH_PROXY;

    protected static List<APIUser> TO_BE_DELETED;

    static final String TEST_PATH = "/fixtures/BitbarSampleAppTest.apk";

    private static final String USER_PASSWORD = RandomStringUtils.randomAlphanumeric(20);

    @BeforeAll
    static void beforeAll() throws APIException {
        TO_BE_DELETED = new ArrayList<>();
        APIUser apiUser1 = create(ADMIN_API_CLIENT);
        TO_BE_DELETED.add(apiUser1);
        USER_API_KEY_CLIENT = new APIKeyClient(CLOUD_URL, apiUser1.getApiKey());
        if (isNoneBlank(PROXY_HOST, PROXY_PORT)) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, Integer.parseInt(PROXY_PORT)));
            USER_API_KEY_CLIENT_WITH_PROXY = createDefaultApiClientWithProxy(proxy);
        }
    }

    @AfterAll
    static void afterAll() throws APIException {
        String deleteUrl = "%s/delete";
        Map<String, Object> map = new HashMap<>();
        map.put(PASSWORD, USER_PASSWORD);
        for (APIUser toBeDeleted : TO_BE_DELETED) {
            ADMIN_API_CLIENT.post(String.format(deleteUrl, toBeDeleted.getSelfURI()), map, APIUser.class);
        }
    }

    static class APIClientProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(USER_API_KEY_CLIENT, USER_API_KEY_CLIENT_WITH_PROXY).filter
                    (Objects::nonNull).map(Arguments::of);
        }
    }

    APIFramework getApiFramework(APIClient apiClient, String frameworkNameLike) throws APIException {
        Context<APIFramework> context = new Context<>(APIFramework.class, 0, MAX_VALUE, EMPTY, EMPTY);
        context.addFilter(new FilterEntry(OS_TYPE, EQ, ANDROID.name()));
        context.addFilter(trueFilterEntry(FOR_PROJECTS));
        context.addFilter(trueFilterEntry(CAN_RUN_FROM_UI));
        context.addFilter(new FilterEntry(NAME, LIKE, frameworkNameLike));
        return apiClient.me().getAvailableFrameworksResource(context).getEntity().get(0);
    }

    protected static APIUser create(APIKeyClient adminApiClient) throws APIException {
        Map<String, Object> map = new HashMap<>();
        map.put(EMAIL, generateUniqueEmail());
        APIUser userForTest = adminApiClient.post("/admin/users", map, APIUser.class);
        map.clear();
        map.put(EMAIL, userForTest.getEmail());
        map.put(NEW_PASSWORD, USER_PASSWORD);
        map.put(CONFIRM_PASSWORD, USER_PASSWORD);
        userForTest = adminApiClient.post(userForTest.selfURI, map, APIUser.class);
        return userForTest;
    }

    private static String generateUniqueEmail() {
        return String.format(EMAIL_PATTERN, System.currentTimeMillis());
    }

    static String generateUnique(String prefix) {
        return String.format("%s%d", prefix, System.currentTimeMillis());
    }

    static APIKeyClient createDefaultApiClientWithProxy(Proxy proxy) throws APIException {
        APIUser user = create(ADMIN_API_CLIENT);
        TO_BE_DELETED.add(user);
        return new APIKeyClient(CLOUD_URL, user.getApiKey(), proxy, false);
    }

    static File loadFile(String name) {
        java.net.URL url = BaseAPIClientTest.class.getResource(name);
        return new File(Objects.requireNonNull(url).getFile());
    }
}
