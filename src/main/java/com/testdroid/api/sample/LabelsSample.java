package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.model.APIDeviceProperty;
import com.testdroid.api.model.APILabelGroup;
import com.testdroid.api.sample.util.Common;

/**
 * @author Sławomir
 */
public class LabelsSample {

    private static APIClient CLIENT = Common.createApiClient();

    public static void main(String[] args) {
        try {
            APIListResource<APILabelGroup> labelGroups = CLIENT.getLabelGroups();

            System.out.println("Label groups");
            for (APILabelGroup labelGroup : labelGroups.getEntity().getData()) {
                System.out.println(labelGroup.getDisplayName());
            }

            APILabelGroup labelGroup = labelGroups.getEntity().get(0);

            APIListResource<APIDeviceProperty> deviceProperties = labelGroup.getDevicePropertiesResource();

            System.out.println("Device properties");
            for (APIDeviceProperty deviceProperty : deviceProperties.getEntity().getData()) {
                System.out.println(deviceProperty.getDisplayName());
            }

        } catch (APIException apie) {
            System.out.println(apie.getMessage());
        }
    }

}
