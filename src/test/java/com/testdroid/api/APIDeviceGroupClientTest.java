package com.testdroid.api;

import com.testdroid.api.dto.Context;
import com.testdroid.api.filter.FilterEntry;
import com.testdroid.api.model.APIDevice;
import com.testdroid.api.model.APIDeviceGroup;
import com.testdroid.api.model.APIDeviceProperty;
import com.testdroid.api.model.APILabelGroup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.testdroid.api.dto.MappingKey.DISPLAY_NAME;
import static com.testdroid.api.dto.MappingKey.OS_TYPE;
import static com.testdroid.api.dto.Operand.EQ;
import static com.testdroid.api.model.APIDevice.OsType.ANDROID;
import static com.testdroid.api.model.APIDevice.OsType.IOS;
import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
class APIDeviceGroupClientTest extends BaseAPIClientTest {

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void createDeviceGroupTest(APIClient apiClient) throws APIException {
        APIDeviceGroup deviceGroup = apiClient.me().createDeviceGroup("testDeviceGroup", ANDROID);
        assertThat(deviceGroup.getDisplayName()).isEqualTo("testDeviceGroup");
        assertThat(deviceGroup.getOsType()).isSameAs(ANDROID);
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void addDeviceTest(APIClient apiClient) throws APIException {
        APIDeviceGroup deviceGroup = apiClient.me().createDeviceGroup("iosDevicesGroup", IOS);
        APIList<APIDevice> devicesList = apiClient.getDevices(new Context<>(APIDevice.class)
                .addFilter(new FilterEntry(OS_TYPE, EQ, IOS.name()))).getEntity();
        for (APIDevice device : devicesList.getData()) {
            deviceGroup.addDevice(device);
        }
        deviceGroup.refresh();
        assertThat(deviceGroup.getDeviceCount()).isEqualTo(Long.valueOf(devicesList.getTotal()));
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void addSelectorTest(APIClient apiClient) throws APIException {
        APIList<APILabelGroup> labelGroups = apiClient.getLabelGroups(new Context<>(APILabelGroup.class)
                .addFilter(new FilterEntry(DISPLAY_NAME, EQ, "Device groups"))).getEntity();
        APILabelGroup deviceGroupLabelGroup = labelGroups.getData().stream().findFirst().orElseThrow(APIException::new);
        APIList<APIDeviceProperty> apiDeviceProperties = deviceGroupLabelGroup.getDevicePropertiesResource(new
                Context<>(APIDeviceProperty.class)
                .addFilter(new FilterEntry(DISPLAY_NAME, EQ, "Android devices")))
                .getEntity();
        APIDeviceProperty androidDevicesLabel = apiDeviceProperties.getData().stream().findFirst()
                .orElseThrow(APIException::new);
        APIDeviceGroup dynamicAndroidDeviceGroup = apiClient.me()
                .createDeviceGroup("dynamicAndroidDeviceGroup", ANDROID);
        dynamicAndroidDeviceGroup = dynamicAndroidDeviceGroup.addSelector(androidDevicesLabel);
        APIList<APIDevice> devicesList = apiClient.getDevices(new Context<>(APIDevice.class)
                .addFilter(new FilterEntry(OS_TYPE, EQ, ANDROID.name()))).getEntity();
        assertThat(dynamicAndroidDeviceGroup.getDeviceCount()).isEqualTo(Long.valueOf(devicesList.getTotal()));
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void getIncludedDevicesTest(APIClient apiClient) throws APIException {
        APIDeviceGroup deviceGroup = apiClient.me().createDeviceGroup("iosDevicesGroup", IOS);
        APIList<APIDevice> devicesList = apiClient.getDevices(new Context<>(APIDevice.class)
                .addFilter(new FilterEntry(OS_TYPE, EQ, IOS.name()))).getEntity();
        for (APIDevice device : devicesList.getData()) {
            deviceGroup.addDevice(device);
        }
        deviceGroup.refresh();
        APIList<APIDevice> allDevices = deviceGroup.getIncludedDevicesResource().getEntity();
        APIList<APIDevice> samsungDevices = deviceGroup.getIncludedDevicesResource(new Context<>(APIDevice.class)
                .addFilter(new FilterEntry(DISPLAY_NAME, EQ, "%Samsung%"))).getEntity();

        assertThat(allDevices.getTotal()).isGreaterThanOrEqualTo(samsungDevices.getTotal());
    }

}
