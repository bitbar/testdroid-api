package com.testdroid.api;

import com.testdroid.api.dto.Context;
import com.testdroid.api.filter.BooleanFilterEntry;
import com.testdroid.api.filter.StringFilterEntry;
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
import static com.testdroid.api.model.APIDevice.OsType.ANDROID;
import static com.testdroid.api.model.APIDeviceSession.State.WAITING;
import static com.testdroid.api.model.APIDeviceSession.Type.REMOTE;
import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static java.util.Collections.singletonMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
        config.setType(REMOTE);
        config.setDeviceModelId(freeAndroidDevices.get(0).id);
        config.setAdbVersion("1.2.3");
        APIDeviceSession deviceSession = apiClient.post("/me/device-sessions", config, APIDeviceSession.class);
        assertThat(deviceSession.getState(), is(WAITING));
        deviceSession.release();
    }

    private List<APIDevice> getFreeTrialAndroidDevices(APIClient apiClient) throws APIException {
        Context<APIDevice> ctx = new Context<>(APIDevice.class);
        ctx.getFilters().add(new StringFilterEntry(OS_TYPE, EQ, ANDROID.getDisplayName()));
        ctx.getFilters().add(BooleanFilterEntry.trueFilterEntry(ONLINE));
        ctx.getFilters().add(BooleanFilterEntry.falseFilterEntry(LOCKED));
        ctx.getFilters().add(BooleanFilterEntry.trueFilterEntry(ENABLED));
        apiClient.findDevicePropertyInLabelGroup("device-groups", "trial-devices").ifPresent(val -> ctx.setExtraParams(
                new HashSetValuedHashMap<>(singletonMap(LABEL_IDS_ARR, val.getId()))
        ));
        ctx.setLimit(0);
        return apiClient.getDevices(ctx).getEntity().getData();
    }
}
