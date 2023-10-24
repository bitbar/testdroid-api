package com.testdroid.api;

import com.google.api.client.http.HttpResponse;
import com.testdroid.api.model.APIEnum;
import com.testdroid.api.model.APIUser;
import org.assertj.core.api.Condition;
import org.apache.http.HttpHost;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
class APIClientTest extends BaseAPIClientTest {

    private static final String UNKNOWN_HOST_EXCEPTION_MESSAGE = "Failed to execute API call: /me. Reason: %s: %s";

    @Tag("TD-12086")
    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void td12086(APIClient apiClient) throws APIException {
        APIUser user = apiClient.me();
        APIResource<APIEnum> resource = user.getResource(user.selfURI + "/notifications/channels/SLACK+/scopes",
                APIEnum.class);
        Exception exception = assertThrows(APIException.class, resource::getEntity);
        assertThat(exception.getMessage()).isEqualTo("Invalid Notification Channel: SLACK+");
    }

    @Tag("TD-14615")
    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void td14615(APIClient apiClient) throws APIException {
        HttpResponse httpResponse = apiClient.getHttpResponse("/me", null);
        httpResponse.getHeaders().getHeaderStringValues("set-cookie").forEach(
                s -> assertThat(s).doesNotContainIgnoringCase("SESSION"));
    }

    private static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                // @formatter:off
                {"127.0.0.a", 8080, APIException.class, getUnknownHostExceptionMessage("127.0.0.a")},
                {"127.0.0.1", 0, APIException.class, startsWith("Failed to execute API call")},
                // @formatter:on
        };
        return Arrays.asList(data);
    }

    @ParameterizedTest
    @MethodSource("data")
    void testWrongProxy(String host, Integer port, Class<Exception> exc, Condition<String> condition)
            throws APIException {
        APIKeyClient apiKeyClient = createDefaultApiClientWithProxy(new HttpHost(host, port));
        Throwable throwable = assertThrows(exc, apiKeyClient::me);
        assertThat(throwable.getMessage()).is(condition);
    }

    private static Condition<String> getUnknownHostExceptionMessage(String host) {
        return anyOf(
                isEqual(String
                        .format(UNKNOWN_HOST_EXCEPTION_MESSAGE, host, "nodename nor servname provided, or not known")),
                isEqual(String.format(UNKNOWN_HOST_EXCEPTION_MESSAGE, host, "Name or service not known")),
                isEqual(String.format(UNKNOWN_HOST_EXCEPTION_MESSAGE, host, "Temporary failure in name resolution"))
        );
    }

    private static <T> Condition<T> isEqual(T expected) {
        return new Condition<>(v -> v.equals(expected), "equals" + expected);
    }

    private static Condition<String> startsWith(String expected) {
        return new Condition<>(v -> v.startsWith(expected), "startsWith" + expected);
    }

}
