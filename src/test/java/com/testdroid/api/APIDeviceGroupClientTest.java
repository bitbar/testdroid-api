package com.testdroid.api;

import com.testdroid.api.dto.Context;
import com.testdroid.api.filter.StringFilterEntry;
import com.testdroid.api.model.APIDevice;
import com.testdroid.api.model.APIDeviceGroup;
import com.testdroid.api.model.APIDeviceProperty;
import com.testdroid.api.model.APILabelGroup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.testdroid.api.dto.Operand.EQ;
import static com.testdroid.api.model.APIDevice.OsType.ANDROID;
import static com.testdroid.api.model.APIDevice.OsType.IOS;
import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static com.testdroid.dao.repository.dto.MappingKey.DISPLAY_NAME;
import static com.testdroid.dao.repository.dto.MappingKey.OS_TYPE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
public class APIDeviceGroupClientTest extends APIClientTest {

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    public void createDeviceGroupTest(AbstractAPIClient apiKeyClient) throws APIException {
        APIDeviceGroup deviceGroup = apiKeyClient.me().createDeviceGroup("testDeviceGroup", ANDROID);
        assertThat(deviceGroup.getDisplayName(), is("testDeviceGroup"));
        assertThat(deviceGroup.getOsType(), is(ANDROID));
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    public void addDeviceTest(AbstractAPIClient apiKeyClient) throws APIException {
        APIDeviceGroup deviceGroup = apiKeyClient.me().createDeviceGroup("iosDevicesGroup", IOS);
        APIList<APIDevice> devicesList = apiKeyClient.getDevices(new Context<>(APIDevice.class)
                .addFilter(new StringFilterEntry(OS_TYPE, EQ, IOS.name()))).getEntity();
        for (APIDevice device : devicesList.getData()) {
            deviceGroup.addDevice(device);
        }
        deviceGroup.refresh();
        assertThat(deviceGroup.getDeviceCount(), is(Long.valueOf(devicesList.getTotal())));
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    public void addSelectorTest(AbstractAPIClient apiKeyClient) throws APIException {
        APIList<APILabelGroup> labelGroups = apiKeyClient.getLabelGroups(new Context<>(APILabelGroup.class)
                .addFilter(new StringFilterEntry(DISPLAY_NAME, EQ, "Device groups"))).getEntity();
        APILabelGroup deviceGroupLabelGroup = labelGroups.getData().stream().findFirst().get();
        APIList<APIDeviceProperty> apiDeviceProperties = deviceGroupLabelGroup.getDevicePropertiesResource(new
                Context<>(APIDeviceProperty.class)
                .addFilter(new StringFilterEntry(DISPLAY_NAME, EQ, "Android devices")))
                .getEntity();
        APIDeviceProperty androidDevicesLabel = apiDeviceProperties.getData().stream().findFirst().get();
        APIDeviceGroup dynamicAndroidDeviceGroup = apiKeyClient.me()
                .createDeviceGroup("dynamicAndroidDeviceGroup", ANDROID);
        dynamicAndroidDeviceGroup = dynamicAndroidDeviceGroup.addSelector(androidDevicesLabel);
        APIList<APIDevice> devicesList = apiKeyClient.getDevices(new Context<>(APIDevice.class)
                .addFilter(new StringFilterEntry(OS_TYPE, EQ, ANDROID.name()))).getEntity();
        assertThat(dynamicAndroidDeviceGroup.getDeviceCount(), is(Long.valueOf(devicesList.getTotal())));
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    public void getIncludedDevicesTest(AbstractAPIClient apiKeyClient) throws APIException {
        APIDeviceGroup deviceGroup = apiKeyClient.me().createDeviceGroup("iosDevicesGroup", IOS);
        APIList<APIDevice> devicesList = apiKeyClient.getDevices(new Context<>(APIDevice.class)
                .addFilter(new StringFilterEntry(OS_TYPE, EQ, IOS.name()))).getEntity();
        for (APIDevice device : devicesList.getData()) {
            deviceGroup.addDevice(device);
        }
        deviceGroup.refresh();
        APIList<APIDevice> allDevices = deviceGroup.getIncludedDevicesResource().getEntity();
        APIList<APIDevice> samsungDevices = deviceGroup.getIncludedDevicesResource(new Context<>(APIDevice.class)
                .addFilter(new StringFilterEntry(DISPLAY_NAME, EQ, "%Samsung%"))).getEntity();

        assertThat(allDevices.getTotal(), is(greaterThanOrEqualTo(samsungDevices.getTotal())));
    }

}
