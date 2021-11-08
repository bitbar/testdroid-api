package com.testdroid.api;

import com.google.api.client.http.HttpResponse;
import com.testdroid.api.model.APIEnum;
import com.testdroid.api.model.APIUser;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
class APIClientTest extends BaseAPIClientTest {

    @Tag("TD-12086")
    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void td12086(APIClient apiClient) throws APIException {
        APIUser user = apiClient.me();
        Exception exception = assertThrows(APIException.class, () ->
                user.getResource(user.selfURI + "/notifications/channels/SLACK+/scopes", APIEnum.class).getEntity());
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

}
