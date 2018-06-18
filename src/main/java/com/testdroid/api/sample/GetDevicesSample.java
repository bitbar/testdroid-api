package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.APISort;
import com.testdroid.api.dto.Context;
import com.testdroid.api.dto.Operand;
import com.testdroid.api.filter.BooleanFilterEntry;
import com.testdroid.api.filter.StringFilterEntry;
import com.testdroid.api.model.APIDevice;
import com.testdroid.api.sample.util.Common;

import java.util.Collections;

/**
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class GetDevicesSample {

    public static final APIClient CLIENT = Common.createApiClient();

    public static void main(String[] args) {
        try {
            // Get all devices
            Context<APIDevice> context = new Context<>(APIDevice.class);
            APIListResource<APIDevice> devicesResource = CLIENT.getDevices(context.setLimit(0));
            System.out.println(String.format("Get %s devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);

            // Search device
            context = new Context<>(APIDevice.class);
            String name = devicesResource.getEntity().get(0).getDisplayName();
            devicesResource = CLIENT.getDevices(context.setSearch(name));
            System.out.println(String.format("\nFound %s devices", devicesResource.getTotal()));
            printDeviceNames(devicesResource);

            // Get devices using sorting
            name = "Samsung Galaxy";
            devicesResource = CLIENT.getDevices(context.setSearch(name)
                    .setSort(APISort.create(
                            Collections.singletonList(new APISort.SortItem("displayName", APISort.Type.ASC)))));
            System.out.println(String.format("\nFound %s devices with name %s", devicesResource.getTotal(), name));
            printDeviceNames(devicesResource);

            context = new Context<>(APIDevice.class)
                    .setSort(new APISort(Collections
                            .singletonList(new APISort.SortItem("displayName", APISort.Type.ASC))))
                    .addFilter(new StringFilterEntry("osType", Operand.EQ, APIDevice.OsType.IOS.name()))
                    .addFilter(new BooleanFilterEntry("online", Operand.EQ, Boolean.FALSE));
            devicesResource = CLIENT.getDevices(context);
            System.out.println(String.format("\nFound %s devices", devicesResource.getTotal()));
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
