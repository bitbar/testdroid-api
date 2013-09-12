package com.testdroid.api.sample.util;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIExceptionMessage;
import com.testdroid.api.DefaultAPIClient;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Sławomir
 */
public class Common {

    public static final String SERVER_URL = "http://devel";
    public static final String USERNAME = "user@localhost";
    public static final String PASSWORD = "user";
    
    public static APIClient createApiClient() {
        return new DefaultAPIClient(SERVER_URL, USERNAME, PASSWORD);
    }
    
}
