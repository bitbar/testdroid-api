package com.testdroid.api;

import com.testdroid.api.APISort.SortItem;
import com.testdroid.api.model.APIFiles.AndroidFiles;
import com.testdroid.api.model.APIProject;
import com.testdroid.api.model.APIUser;
import java.io.File;


/**
 *
 * @author kajdus
 * For development testing. To be removed in final version.
 */
public class Test {
    
    public static void main(String[] args) {
        APIClient client = new DefaultAPIClient("http://localhost:8080/testdroid-cloud", "admin@localhost", "admin");
        try {
            APIUser user = client.me();
            System.out.println("SUCCEEDED WITH USER: " + user.getId());
            APIListResource<APIUser> resource = new APIListResource<APIUser>(client, "/users", 0L, 10L, null, 
                    APISort.create(APIUser.class, new SortItem(APISort.Column.USER_EMAIL, APISort.Type.DESC)), APIUser.class);
            System.out.println("USERS COUNT: " + resource.getEntity().getTotal());
            APIProject p = user.getProject(120731L);
            System.out.println(p.getName());
            AndroidFiles files = p.getFiles(AndroidFiles.class);
            files.uploadTest(new File("C:\\Users\\Lukasz\\Desktop\\apks\\MoviesTest.apk"));
            System.out.println(files.getAndroidApp().getOriginalName());
        } catch (APIException ex) {
            ex.printStackTrace();
        }
    }
}
