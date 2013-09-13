package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
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
            
            // Get new devices
            devicesResource = CLIENT.getDevices(APIDevice.Filter.NEW);
            System.out.println(String.format("Get %s new devices", devicesResource.getTotal()));
            
            // Get recomended devices
            devicesResource = CLIENT.getDevices(APIDevice.Filter.RECOMMENDED);
            System.out.println(String.format("Get %s recomended devices", devicesResource.getTotal()));
            
            // Get free devices
            devicesResource = CLIENT.getDevices(APIDevice.Filter.FREE);
            System.out.println(String.format("Get %s free devices", devicesResource.getTotal()));
            
            // Search device
            String deviceName = devicesResource.getEntity().get(0).getDisplayName();
            devicesResource = CLIENT.getDevices(0, 10, deviceName, null);
            System.out.println(String.format("Found %s devices", devicesResource.getTotal()));
            
            // Search device with filter
            devicesResource = CLIENT.getDevices(0, 10, deviceName, null, APIDevice.Filter.RECOMMENDED);
            System.out.println(String.format("Found %s recomended devices", devicesResource.getTotal()));
            
            // Get devices using sorting
            deviceName = "Samsung";
            devicesResource = CLIENT.getDevices(0, 10, deviceName, APISort.create(APIDevice.class, new APISort.SortItem(APISort.Column.DEVICE_NAME, APISort.Type.ASC)));
            System.out.println(String.format("Found %s devices with name %s", devicesResource.getTotal(), deviceName));
            for(APIDevice device: devicesResource.getEntity().getData()) {
                System.out.println(device.getDisplayName());
            }
            
        } catch(APIException apie) {
            System.err.println(apie.getMessage());
        }
    }
}
