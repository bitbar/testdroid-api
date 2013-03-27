package com.testdroid.api;

import com.testdroid.api.model.APIProject;
import com.testdroid.api.model.APIUser;


/**
 *
 * @author kajdus
 * For development testing. To be removed in final version.
 */
public class Test {
    
    public static void main(String[] args) {
        APIClient client = new DefaultAPIClient("http://localhost:8080/testdroid-cloud", "admin@localhost", "admin");
        try {
            APIUser user = client.get("/me", APIUser.class);
            System.out.println("SUCCEEDED WITH USER: " + user.getId());
            Long projectCount = user.getProjectsResource().getEntity().getTotal();
            System.out.println("PROJECT COUNT: " + projectCount);
            APIProject project = user.getProjectsResource().getEntity().getData().get(0);
        } catch (APIException ex) {
            ex.printStackTrace();
        }
    }
}
