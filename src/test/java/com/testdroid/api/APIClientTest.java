package com.testdroid.api;

import com.testdroid.api.model.APIEnum;
import com.testdroid.api.model.APIUser;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
class APIClientTest extends BaseAPIClientTest {

    @Tag("TD-12086")
    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void td12086(AbstractAPIClient apiKeyClient) throws APIException {
        APIUser user = apiKeyClient.me();
        Exception exception = assertThrows(APIException.class, () ->
                user.getResource(user.selfURI + "/notifications/channels/SLACK+/scopes", APIEnum.class).getEntity());
        assertThat(exception.getMessage(), is("Invalid Notification Channel: SLACK+"));
    }

}
