package com.testdroid.api.model.notification;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public enum APINotificationScope {
    ALL,
    TEST_RUN,
    TEST_RUN_FAILURE,//Project
    TEST_RUN_SUCCEEDED,//Project
    SYSTEM,
    CHECK
}
