package com.testdroid.api;

import com.testdroid.api.APIList.UserList;
import com.testdroid.api.APISort.SortItem;
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
            APIUser user = client.me();
            System.out.println("SUCCEEDED WITH USER: " + user.getId());
            APIListResource<UserList> resource = new APIListResource<UserList>(client, "/users", 0L, 10L, null, 
                    APISort.create(APIUser.class, new SortItem(APISort.Column.USER_EMAIL, APISort.Type.DESC)), UserList.class);
            UserList users = resource.getEntity();
        } catch (APIException ex) {
            ex.printStackTrace();
        }
    }
}
