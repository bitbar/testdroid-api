package com.testdroid.api.model.notification;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public enum APINotificationScope {
    ALL,
    TEST_RUN,
    TEST_RUN_FAILURE,//Project
    TEST_RUN_SUCCEEDED,//Project
    MAINTENANCE,
    MAINTENANCE_RELEASE,//All, User?
    CUSTOM,//User, Project, All
    NEWS,
    SYSTEM,
    CHECK
}
