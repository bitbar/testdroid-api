package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.APIList;
import com.testdroid.api.model.*;
import com.testdroid.api.sample.util.Common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: zabkiewi
 * Date: 08.01.14
 * Time: 14:03
 */
public class DeleteProjects {

    public static APIClient CLIENT;

    public static void main(String[] args) {
        try {
            // Get authenticated user
            if(args.length < 3) {
                System.out.println("usage: host login password");
                System.exit(0);
            }

            CLIENT =  Common.createApiClient( args[0] , args[1], args[2]);
            APIUser me = CLIENT.me();
            List<APIProject> plist = null;


            while( (plist = me.getProjectsResource().getEntity().getData())!=null && (plist.size() > 19) ) {
                for(APIProject p : plist) {
                    p.delete();
                    System.out.println("Deleted project ID: " + p.getId());
                }
            }


        } catch(APIException apie) {
            System.err.println(apie.getMessage());
        }

        System.exit(0);
    }
}
