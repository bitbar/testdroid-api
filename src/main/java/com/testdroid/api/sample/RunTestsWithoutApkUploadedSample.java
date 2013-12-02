package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.model.APIDeviceGroup;
import com.testdroid.api.model.APIProject;
import com.testdroid.api.model.APITestRun;
import com.testdroid.api.model.APITestRunConfig;
import com.testdroid.api.model.APIUser;
import com.testdroid.api.sample.util.Common;

/**
 *
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class RunTestsWithoutApkUploadedSample {

    private static final APIClient CLIENT = Common.createApiClient();
    
    public static void main(String[] args) {
        try {
            APIUser me = CLIENT.me();
            
            APIProject project = me.createProject(APIProject.Type.ANDROID);
            APIDeviceGroup deviceGroup = me.getDeviceGroupsResource().getEntity().get(0);
            
            APITestRunConfig config = project.getTestRunConfig();
            config.setMode(APITestRunConfig.Mode.FULL_RUN);
            config.setAppRequired(false);
            config.setUsedDeviceGroupId(deviceGroup.getId());
            config.update();
            
            Common.uploadTest(project);
            
            APITestRun testRun = project.run();
            
        } catch(APIException apie) {
            System.out.println(apie.getMessage());
        }
    }
    
}
