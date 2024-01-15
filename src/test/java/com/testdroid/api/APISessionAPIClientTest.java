package com.testdroid.api;

import com.testdroid.api.dto.Context;
import com.testdroid.api.filter.FilterEntry;
import com.testdroid.api.model.APIDevice;
import com.testdroid.api.model.APIDeviceSession;
import com.testdroid.api.model.APIDeviceSessionConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;

import static com.testdroid.api.dto.MappingKey.*;
import static com.testdroid.api.dto.Operand.EQ;
import static com.testdroid.api.filter.FilterEntry.falseFilterEntry;
import static com.testdroid.api.filter.FilterEntry.trueFilterEntry;
import static com.testdroid.api.model.APIDevice.OsType.ANDROID;
import static com.testdroid.api.model.APIDeviceSession.State.WAITING;
import static com.testdroid.api.model.APIDeviceSession.Type.MANUAL_APP;
import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
class APISessionAPIClientTest extends BaseAPIClientTest {

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void startTestRunTest(APIClient apiClient) throws APIException {
        List<APIDevice> freeAndroidDevices = getFreeTrialAndroidDevices(apiClient);
        assumeTrue(CollectionUtils.isNotEmpty(freeAndroidDevices));
        APIDeviceSessionConfig config = new APIDeviceSessionConfig();
        config.setType(MANUAL_APP);
        config.setDeviceModelId(freeAndroidDevices.get(0).id);
        config.setAdbVersion("1.2.3");
        APIDeviceSession deviceSession = apiClient.post("/me/device-sessions", config, APIDeviceSession.class);
        assertThat(deviceSession.getState()).isEqualTo(WAITING);
        deviceSession.release();
    }

    private List<APIDevice> getFreeTrialAndroidDevices(APIClient apiClient) throws APIException {
        Context<APIDevice> ctx = new Context<>(APIDevice.class)
                .addFilter(new FilterEntry(OS_TYPE, EQ, ANDROID.getDisplayName()))
                .addFilter(trueFilterEntry(ONLINE))
                .addFilter(falseFilterEntry(LOCKED))
                .addFilter(trueFilterEntry(ENABLED));
        apiClient.findDevicePropertyInLabelGroup("device-groups", "trial-devices").ifPresent(val -> ctx.setExtraParams(
                new HashSetValuedHashMap<>(singletonMap(LABEL_IDS_ARR, val.getId()))
        ));
        ctx.setLimit(0);
        return apiClient.getDevices(ctx).getEntity().getData();
    }
}
