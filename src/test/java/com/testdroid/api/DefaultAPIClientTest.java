package com.testdroid.api;

import org.apache.http.HttpHost;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
class DefaultAPIClientTest extends BaseAPIClientTest {

    private static final String UNKNOW_HOST_EXCEPTION_MESSAGE = "Failed to acquire access token. Reason: %s: %s";

    private static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                // @formatter:off
                {"127.0.0.a", 8080, APIException.class, getUnknownHostExceptionMessage("127.0.0.a")},
                {"127.0.0.1", 0, APIException.class, is("Failed to acquire access token. Reason: Connection refused (Connection refused)")},
                // @formatter:on
        };
        return Arrays.asList(data);
    }

    @ParameterizedTest
    @MethodSource("data")
    void testWrongProxy(String host, Integer port, Class<Exception> exc, Matcher<String> matcher) throws APIException {
        DefaultAPIClient defaultAPIClient = createDefaultApiClientWithProxy(new HttpHost(host, port));
        Throwable throwable = assertThrows(exc, defaultAPIClient::me);
        assertThat(throwable.getMessage(), matcher);
    }

    private static Matcher<String> getUnknownHostExceptionMessage(String host) {
        return anyOf(
                is(String.format(UNKNOW_HOST_EXCEPTION_MESSAGE, host, "nodename nor servname provided, or not known")),
                is(String.format(UNKNOW_HOST_EXCEPTION_MESSAGE, host, "Name or service not known")),
                is(String.format(UNKNOW_HOST_EXCEPTION_MESSAGE, host, "Temporary failure in name resolution"))
        );
    }

}
