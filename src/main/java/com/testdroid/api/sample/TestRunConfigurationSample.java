package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.model.APIProject;
import com.testdroid.api.model.APITestRunConfig;
import com.testdroid.api.model.APIUser;
import com.testdroid.api.sample.util.Common;

/**
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class TestRunConfigurationSample {

    public static final APIClient CLIENT = Common
            .createApiClient("http://localhost:8080/testdroid-cloud", "admin@localhost", "admin");

    public static void main(String[] args) {
        try {
            // Get user
            APIUser me = CLIENT.me();

            // Create android project
            APIProject project = me.createProject(APIProject.Type.ANDROID);

            // You would like to configure project before running tests on it
            // Each test run have its configuration
            // First You need to get test run config
            APITestRunConfig testRunConfig = project.getTestRunConfig();

            // After that you can configure few things:
            // Scheduler
            // AutoScreenshots
            // ScreenshotDir
            // LimitationType
            // ApplicationUsername
            // ApplicationPassword
            // UsedDeviceGroupId
            // HookURL
            // UIAutomatorTestClasses

            testRunConfig.setAppCrawlerRun(true);
            testRunConfig.update();

            testRunConfig.setAppCrawlerRun(false);
            testRunConfig.update();

        } catch (APIException apie) {
            System.out.println(apie.getMessage());
        }
    }
}
