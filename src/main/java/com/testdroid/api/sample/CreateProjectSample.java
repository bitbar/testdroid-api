package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.APIExceptionMessage;
import com.testdroid.api.model.APIProject;
import com.testdroid.api.model.APIUser;
import com.testdroid.api.sample.util.Common;

/**
 *
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class CreateProjectSample {

    public static final APIClient CLIENT = Common.createApiClient("http://testdroid-db","zabkiewi","opek987123");
    
    public static void main(String[] args) {
        APIUser me;
        APIProject project;
        APIProject project1;
        try {
            // Get authenticated user
            me = CLIENT.me();
            
            // Create proejct with selected type
            for(int i=0; i<1000; i++) {
                me.createProject(APIProject.Type.ANDROID);
            }
            
        } catch (APIException apie) {
            System.err.println(apie.getMessage());
        }
    }
    
}
