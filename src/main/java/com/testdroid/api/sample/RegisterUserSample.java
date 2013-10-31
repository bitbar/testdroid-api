package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.DefaultAPIClient;
import com.testdroid.api.model.APIUser;
import com.testdroid.api.sample.util.Common;

/**
 *
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class RegisterUserSample {

    public static void main(String[] args) {
        try {
            // Create client without credentials
            APIClient client = new DefaultAPIClient(Common.SERVER_URL, null, null);
            
            // Register user passing email address to register method
            APIUser user = client.register("email@localhost");
            
            // Registered user have to recieve email, and fill registration form to activate account
            System.out.println(String.format("Registered user id %s", user.getId()));
        } catch(APIException apie) {
            System.err.println(apie.getMessage());
        }
    }
    
}
