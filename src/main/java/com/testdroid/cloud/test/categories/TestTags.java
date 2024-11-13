package com.testdroid.cloud.test.categories;

/**
 * @author Krzysztof Fonał <krzysztof.fonal@bitbar.com>
 */
public class TestTags {

    private TestTags() {
        throw new IllegalStateException("Utility class");
    }

    public static final String UNIT = "unit";

    public static final String API = "api";

    public static final String INTEGRATION = "integration";

    public static final String API_CLIENT = "api_client";

    public static final String LOCAL = "local";

}
