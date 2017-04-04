package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.model.*;
import com.testdroid.api.sample.util.Common;

/**
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class RunTestsWithoutAppUploadedSample {

    private static final APIClient CLIENT = Common.createApiClient();

    public static void main(String[] args) {
        try {
            APIUser me = CLIENT.me();

            APIProject project = me.createProject(APIProject.Type.ANDROID);
            APIDeviceGroup deviceGroup = me.getDeviceGroupsResource().getEntity().get(0);

            APITestRunConfig config = project.getTestRunConfig();
            config.setAppCrawlerRun(Boolean.FALSE);
            config.setAppRequired(false);
            config.setUsedDeviceGroupId(deviceGroup.getId());
            config.update();

            Common.uploadTest(project);

            APITestRun testRun = project.run();

        } catch (APIException apie) {
            System.out.println(apie.getMessage());
        }
    }

}
