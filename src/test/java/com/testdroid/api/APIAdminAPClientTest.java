package com.testdroid.api;

import com.google.api.client.http.HttpResponse;
import com.testdroid.api.dto.Context;
import com.testdroid.api.model.APIUserFile;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
class APIAdminAPClientTest extends BaseAPIClientTest {

    @Test
    void testAdminSamples() throws APIException {
        Context<APIUserFile> ctx = new Context<>(APIUserFile.class).setLimit(Integer.MAX_VALUE);
        APIList<APIUserFile> samples = ADMIN_API_CLIENT.get("/admin/samples", ctx);
        List<APIUserFile> files = samples.getData();
        for (APIUserFile file : files) {
            HttpResponse httpResponse = ADMIN_API_CLIENT.getHttpResponse("/files/" + file.id + "/file", null);
            assertThat(httpResponse.getHeaders().getFirstHeaderStringValue("x-amz-tagging-count")).isEqualTo("1");
            assertThat(httpResponse.getHeaders().getFirstHeaderStringValue("x-amz-expiration")).isNull();
        }
    }

}
