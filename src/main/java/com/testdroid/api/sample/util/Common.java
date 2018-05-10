package com.testdroid.api.sample.util;

import com.testdroid.api.APIClient;
import com.testdroid.api.APIException;
import com.testdroid.api.APIKeyClient;
import com.testdroid.api.DefaultAPIClient;
import com.testdroid.api.model.APIProject;

import java.io.File;

/**
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class Common {

    public static final String ANDROID_APPLICATION_RESOURCE_PATH = "src/main/resources/fixtures/BitbarSampleApp.apk";

    public static final String ANDROID_FILE_MIME_TYPE = "application/vnd.android.package-archive";

    public static final String ANDROID_TEST_RESOURCE_PATH = "/src/main/resources/fixtures/BitbarSampleAppTest.apk";

    public static final String DATA_FILE_RESOURCE_PATH = "src/main/resources/fixtures/testData.zip";

    public static final String JAR_FILE_MIME_TYPE = "application/java-archive";

    public static final String PASSWORD = "Fill with user password";

    public static final String SERVER_URL = "Fill with server URL";

    public static final String USERNAME = "Fill with username";

    public static final String API_KEY = "Fill with apiKey";

    public static final String ZIP_FILE_MIME_TYPE = "application/zip";

    public static APIClient createApiClient() {
        return new DefaultAPIClient(SERVER_URL, USERNAME, PASSWORD);
    }

    public static APIClient createApiClient(String url, String username, String password) {
        return new DefaultAPIClient(url, username, password);
    }

    public static APIClient createApiClientWithApiKey() {
        return new APIKeyClient(SERVER_URL, API_KEY);
    }
}
