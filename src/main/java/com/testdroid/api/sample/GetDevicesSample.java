package com.testdroid.api.sample;

import com.testdroid.api.*;
import com.testdroid.api.model.APIDevice;
import com.testdroid.api.sample.util.Common;

import java.util.Arrays;

/**
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class GetDevicesSample {

    public static final APIClient CLIENT = Common.createApiClient();

    public static void main(String[] args) {
        try {
            // Get all devices
            APIListResource<APIDevice> devicesResource = CLIENT.getDevices();
            System.out.println(String.format("Get %s devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);

            while (devicesResource.isNextAvailable()) {
                devicesResource = devicesResource.getNext();
                printDeviceNames(devicesResource);
            }

            // Get new devices
            devicesResource = CLIENT.getDevices(new APIDeviceQueryBuilder()
                    .filterWithDeviceFilters(APIDevice.DeviceFilter.NEW));
            System.out.println(String.format("\nGet %s new devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);

            // Get recommended devices
            devicesResource = CLIENT.getDevices(new APIDeviceQueryBuilder()
                    .filterWithDeviceFilters(APIDevice.DeviceFilter.RECOMMENDED));
            System.out.println(String.format("\nGet %s recommended devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);

            // Get free devices
            devicesResource = CLIENT.getDevices(new APIDeviceQueryBuilder()
                    .filterWithDeviceFilters(APIDevice.DeviceFilter.FREE));
            System.out.println(String.format("\nGet %s free devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);

            // Search device
            String deviceName = devicesResource.getEntity().get(0).getDisplayName();
            devicesResource = CLIENT.getDevices(new APIDeviceQueryBuilder().offset(0).limit(10).search(deviceName));
            System.out.println(String.format("\nFound %s devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);

            // Search device with filter
            devicesResource = CLIENT.getDevices(new APIDeviceQueryBuilder().offset(0).limit(10).search(deviceName)
                    .filterWithDeviceFilters(APIDevice
                            .DeviceFilter.RECOMMENDED));
            System.out.println(String.format("\nFound %s recommended devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);

            // Get devices using sorting
            deviceName = "Samsung Galaxy";
            devicesResource = CLIENT.getDevices(new APIDeviceQueryBuilder().offset(0).limit(10).search(deviceName)
                    .sort(APIDevice.class, Arrays.asList(new APISort.SortItem("displayName", APISort.Type.ASC))
                    ));
            System.out.println(String.format("\nFound %s devices with name %s", devicesResource.getTotal(),
                    deviceName));
            printDeviceNames(devicesResource);

            devicesResource = CLIENT.getDevices(
                    new APIDeviceQueryBuilder()
                            .offset(0)
                            .limit(10)
                            .sort(APIDevice.class,
                                    Arrays.asList(
                                            new APISort.SortItem("displayName", APISort.Type.ASC)))
                            .filter(Arrays.asList(
                                            new APIFilter.APIFilterItem(APIFilter.APIFilterItem.Type.B,
                                                    "vncSupported", APIFilter.APIFilterItem.Operand.EQ, Boolean.TRUE),
                                            new APIFilter.APIFilterItem(APIFilter.APIFilterItem.Type.B,
                                                    "online", APIFilter.APIFilterItem.Operand.EQ, Boolean.FALSE)
                                    )
                            )
            );
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
