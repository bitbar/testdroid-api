package com.testdroid.api.sample;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.model.APIUser;
import com.testdroid.api.sample.util.Common;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIKeyAuthenticationSample {

    public static final APIClient CLIENT = Common.createApiClientWithApiKey();

    public static void main(String[] args) {
        try {
            // Get authenticated user
            APIUser me = CLIENT.me();
            printUser(me);
        } catch (APIException apie) {
            System.err.println(apie.getMessage());
        }
    }

    private static void printUser(APIUser user) {
        System.out.println(String.format("Address: %s", user.getAddress()));
        System.out.println(String.format("City: %s", user.getCity()));
        System.out.println(String.format("Code: %s", user.getCode()));
        System.out.println(String.format("Country: %s", user.getCountry()));
        System.out.println(String.format("Email: %s", user.getEmail()));
        System.out.println(String.format("Name: %s", user.getName()));
        System.out.println(String.format("Organization: %s", user.getOrganization()));
        System.out.println(String.format("Phone: %s", user.getPhone()));
        System.out.println(String.format("State: %s", user.getState()));
        System.out.println(String.format("Time zone: %s", user.getTimeZone()));
        System.out.println(String.format("Vat ID: %s", user.getVatId()));
    }
}
