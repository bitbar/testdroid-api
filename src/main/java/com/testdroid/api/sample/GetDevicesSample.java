package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.APIQueryBuilder;
import com.testdroid.api.APISort;
import com.testdroid.api.model.APIDevice;
import com.testdroid.api.model.APIUser;
import com.testdroid.api.sample.util.Common;

/**
 *
 * @author Sławomir
 */
public class GetDevicesSample {

    public static final APIClient CLIENT = Common.createApiClient();

    public static void main(String[] args) {
        try {
            // Get all devices
            APIListResource<APIDevice> devicesResource = CLIENT.getDevices();
            System.out.println(String.format("Get %s devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);

            // Get new devices
            devicesResource = CLIENT.getDevices(APIDevice.Filter.NEW);
            System.out.println(String.format("Get %s new devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);

            // Get recomended devices
            devicesResource = CLIENT.getDevices(APIDevice.Filter.RECOMMENDED);
            System.out.println(String.format("Get %s recomended devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);

            // Get free devices
            devicesResource = CLIENT.getDevices(APIDevice.Filter.FREE);
            System.out.println(String.format("Get %s free devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);
            
            // Search device
            String deviceName = devicesResource.getEntity().get(0).getDisplayName();
            devicesResource = CLIENT.getDevices(new APIQueryBuilder().offset(0).limit(10).search(deviceName));
            System.out.println(String.format("Found %s devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);

            // Search device with filter
            devicesResource = CLIENT.getDevices(new APIQueryBuilder().offset(0).limit(10).search(deviceName), APIDevice.Filter.RECOMMENDED);
            System.out.println(String.format("Found %s recomended devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);

            // Get devices using sorting
            deviceName = "Samsung";
            devicesResource = CLIENT.getDevices(new APIQueryBuilder().offset(0).limit(10).search(deviceName)
                    .sort(APIDevice.class, new APISort.SortItem(APISort.Column.DEVICE_NAME, APISort.Type.ASC)));
            System.out.println(String.format("Found %s devices with name %s", devicesResource.getTotal(), deviceName));
            printDeviceNames(devicesResource);

        } catch (APIException apie) {
            System.err.println(apie.getMessage());
        }
    }

    private static void printDeviceNames(APIListResource<APIDevice> devicesResource) throws APIException {
        for (APIDevice device : devicesResource.getEntity().getData()) {
            System.out.println(device.getDisplayName());
        }
    }
}
