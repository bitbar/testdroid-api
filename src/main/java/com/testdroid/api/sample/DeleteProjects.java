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
 * Created with IntelliJ IDEA.
 * User: zabkiewi
 * Date: 23.01.14
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public class DeleteProjects {

    public static final APIClient CLIENT = Common.createApiClient("http://testdroid:9080","svc_quickbuild","qU1ckbU1ld");
    //public static final String HOOK_URL = "some URL";

    public static void main(String[] args) {
        try {
            // Get authenticated user
            APIUser me = CLIENT.me();
            List<APIProject> plist = null;

            while( (plist = me.getProjectsResource().getEntity().getData())!=null ) {
                for(APIProject p : plist) {
                    p.delete();
                    System.out.println(p.getId());
                }
            }

        } catch(APIException apie) {
            System.err.println(apie.getMessage());
        }
    }
}
