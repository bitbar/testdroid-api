package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.APIList;
import com.testdroid.api.APIListResource;
import com.testdroid.api.model.APIDevice;
import com.testdroid.api.model.APIDeviceGroup;
import com.testdroid.api.model.APIUser;
import com.testdroid.api.sample.util.Common;

/**
 *
 * @author Sławomir
 */
public class CreateDeviceGroupSample {

    public static final APIClient CLIENT = Common.createApiClient();
    
    public static void main(String[] args) {
        try {
            // Get authenticated user
            APIUser me = CLIENT.me();
            
            // Create device group
            APIDeviceGroup deviceGroup = me.createDeviceGroup("My device group");
            
            // Get devices resource
            APIListResource<APIDevice> devicesResource = CLIENT.getDevices();
            
            // Get devices list
            APIList<APIDevice> devicesList = devicesResource.getEntity();
            System.out.println(String.format("Got %s devices", devicesList.getLimit()));
            
            int i = 0;
            for(APIDevice device: devicesList.getData()) {
                if(i++ % 2 == 0) {
                    deviceGroup.addDevice(device);
                }
            }
            
            deviceGroup.refresh();
            
            System.out.println(String.format("Device group have %s devices. Single run cost %s credits", deviceGroup.getDeviceCount(), deviceGroup.getCreditsPrice()));
            
        } catch(APIException apie) {
            System.err.println(apie.getMessage());
        }
    }
    
}
