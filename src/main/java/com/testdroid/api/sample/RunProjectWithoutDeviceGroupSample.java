package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIDeviceQueryBuilder;
import com.testdroid.api.APIException;
import com.testdroid.api.APIList;
import com.testdroid.api.model.*;
import com.testdroid.api.sample.util.Common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class RunProjectWithoutDeviceGroupSample {

    public static final APIClient CLIENT = Common.createApiClient();

    public static void main(String[] args) {
        try {
            // Get authenticated user
            APIUser me = CLIENT.me();

            // Create project
            APIProject project = me.createProject(APIProject.Type.ANDROID);

            // Get test run config from project
            APITestRunConfig testRunConfig = project.getTestRunConfig();

            // Configure test run config
            testRunConfig.setApplicationPassword("applicationPassword");
            testRunConfig.setApplicationUsername("applicationUsername");
            testRunConfig.setAutoScreenshots(false);
            testRunConfig.setCheckApp(true);
            testRunConfig.setDeviceLanguageCode("EN");
            testRunConfig.setMode(APITestRunConfig.Mode.FULL_RUN);

            // Update changes
            testRunConfig.update();

            // Upload application
            project.uploadApplication(new File(RunProjectWithoutDeviceGroupSample.class.getResource(Common.ANDROID_APPLICATION_RESOURCE_PATH).getPath()), Common.ANDROID_FILE_MIME_TYPE);

            // Upload test
            project.uploadTest(new File(RunProjectWithoutDeviceGroupSample.class.getResource(Common.ANDROID_TEST_RESOURCE_PATH).getPath()), Common.ANDROID_FILE_MIME_TYPE);

            // Upload data
            project.uploadData(new File(RunProjectWithoutDeviceGroupSample.class.getResource(Common.DATA_FILE_RESOURCE_PATH).getPath()), Common.ZIP_FILE_MIME_TYPE);

            // Run test run
            APITestRun testRun = project.run("My test run", getDeviceIds());

            System.out.println(String.format("Created test run with name: %s", testRun.getDisplayName()));

        } catch (APIException apie) {
            System.err.println(apie.getMessage());
        }
    }

    private static List<Long> getDeviceIds() throws APIException {
        APIList<APIDevice> devices = CLIENT.getDevices(new APIDeviceQueryBuilder().search("samsung")).getEntity();
        List<Long> result = new ArrayList<>();

        for (APIDevice device : devices.getData()) {
            result.add(device.getId());
        }

        return result;
    }
}
