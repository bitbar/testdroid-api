package com.testdroid.api;

import com.testdroid.api.dto.Context;
import com.testdroid.api.filter.BooleanFilterEntry;
import com.testdroid.api.filter.StringFilterEntry;
import com.testdroid.api.model.APIFramework;
import com.testdroid.api.model.APIUser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.testdroid.api.dto.Operand.EQ;
import static com.testdroid.api.model.APIDevice.OsType.ANDROID;
import static com.testdroid.dao.repository.dto.MappingKey.*;
import static java.lang.Boolean.TRUE;
import static java.lang.Integer.MAX_VALUE;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public abstract class APIClientTest {

    protected static final String APP_PATH = "/fixtures/BitbarSampleApp.apk";

    private static final String CLOUD_URL = System.getenv("CLOUD_URL");

    private static final String ADMIN_API_KEY = System.getenv("ADMIN_API_KEY");

    private static final String EMAIL_PATTERN = System.getenv("EMAIL_PATTERN");

    protected static APIKeyClient ADMIN_API_CLIENT = new APIKeyClient(CLOUD_URL, ADMIN_API_KEY, true);

    private static APIKeyClient USER_API_KEY_CLIENT;

    private static DefaultAPIClient USER_DEFAULT_CLIENT;

    protected static final String TEST_PATH = "/fixtures/BitbarSampleAppTest.apk";

    private static final String USER_PASSWORD = "TSV3ma2n)c3~L/96hQTw";

    @BeforeAll
    public static void beforeAll() throws APIException {
        APIUser apiUser1 = create(ADMIN_API_CLIENT);
        USER_API_KEY_CLIENT = new APIKeyClient(CLOUD_URL, apiUser1.getApiKey());
        APIUser apiUser2 = create(ADMIN_API_CLIENT);
        USER_DEFAULT_CLIENT = new DefaultAPIClient(CLOUD_URL, apiUser2.getEmail(), USER_PASSWORD);
    }

    @AfterAll
    public static void afterAll() throws APIException {
        String deleteUrl = "%s/delete";
        Map<String, Object> map = new HashMap<>();
        map.put(PASSWORD, USER_PASSWORD);
        APIUser apiUser1 = USER_API_KEY_CLIENT.me();
        ADMIN_API_CLIENT.post(String.format(deleteUrl, apiUser1.getSelfURI()), map, APIUser.class);
        APIUser apiUser2 = USER_API_KEY_CLIENT.me();
        ADMIN_API_CLIENT.post(String.format(deleteUrl, apiUser2.getSelfURI()), map, APIUser.class);
    }

    public static class APIClientProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(USER_API_KEY_CLIENT, USER_DEFAULT_CLIENT).map(Arguments::of);
        }
    }

    protected APIFramework getApiFramework(AbstractAPIClient apiKeyClient, String frameworkName) throws APIException {
        StringFilterEntry osTypeFilter = new StringFilterEntry(OS_TYPE, EQ, ANDROID.name());
        BooleanFilterEntry forProject = new BooleanFilterEntry(FOR_PROJECTS, EQ, TRUE);
        BooleanFilterEntry canRunFromUI = new BooleanFilterEntry(CAN_RUN_FROM_UI, EQ, TRUE);
        StringFilterEntry defaultFrameworkName = new StringFilterEntry(TYPE, EQ, frameworkName);
        Context<APIFramework> context = new Context(APIFramework.class, 0, MAX_VALUE, EMPTY, EMPTY);
        context.addFilter(osTypeFilter);
        context.addFilter(forProject);
        context.addFilter(canRunFromUI);
        context.addFilter(defaultFrameworkName);
        return apiKeyClient.me().getAvailableFrameworksResource(context).getEntity().get(0);
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

    public static String generateUnique(String prefix) {
        return String.format("%s%d",prefix, System.currentTimeMillis());
    }

}
