package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.model.APIProject;
import com.testdroid.api.model.APIUser;
import com.testdroid.api.sample.util.Common;

/**
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class CreateProjectSample {

    public static final APIClient CLIENT = Common.createApiClient();

    public static void main(String[] args) {
        APIUser me;
        APIProject project;
        APIProject project1;
        try {
            // Get authenticated user
            me = CLIENT.me();

            // Create project with selected type
            project = me.createProject(APIProject.Type.ANDROID);

            // Create project with selected type and specified name
            project1 = me.createProject(APIProject.Type.ANDROID, String.format("Another %s", project.getName()));

            // After project is created, update project
            project.setName(String.format("Different %s", project.getName()));
            project.setDescription("Description of project");
            project.update();

            // Deleting projects
            project.delete();
            project1.delete();

        } catch (APIException apie) {
            System.err.println(apie.getMessage());
        }
    }
}
