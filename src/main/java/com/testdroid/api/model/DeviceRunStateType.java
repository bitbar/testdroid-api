package com.testdroid.api.model;

import javax.xml.bind.annotation.XmlType;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlType
public enum DeviceRunStateType {
    PREPARING,
    WAITING,
    DEVICE_WAITING,
    DEVICE_DOWNLOAD_TESTSESSION,
    DEVICE_UNINSTALLING_ALL,
    DEVICE_REBOOTING,
    DEVICE_WIFI_CHECKING,
    DEVICE_ADD_PERMISSIONS,
    DEVICE_REPACKAGING,
    DEVICE_TARGET_INSTALLING,
    DEVICE_TEST_INSTALLING,
    DEVICE_REMOTECONTROL_RUNNING,
    DEVICE_RUNNING,
    DEVICE_SDCARD_COPYING,
    DEVICE_TARGET_UNINSTALLING,
    DEVICE_UIAUTOMATOR_RUNNING,
    DEVICE_TEST_UNINSTALLING,
    RESULTS_WAITING,
    RESULTS_PROCESSING,
    PARSE_LOGCAT,
    ADB_SHELL_COMMAND;

    public String getDisplayName() {
        switch (this) {
            case PARSE_LOGCAT:
                return "Launching application";
            case DEVICE_REBOOTING:
                return "Rebooting device";
            case DEVICE_REPACKAGING:
                return "Repackaging APKs";
            case DEVICE_RUNNING:
                return "Running tests";
            case DEVICE_SDCARD_COPYING:
                return "Retrieving data from SD card";
            case DEVICE_TARGET_INSTALLING:
                return "Installing application";
            case DEVICE_TARGET_UNINSTALLING:
                return "Uninstalling application";
            case DEVICE_TEST_INSTALLING:
                return "Installing test APK";
            case DEVICE_TEST_UNINSTALLING:
                return "Uninstalling test APK";
            case DEVICE_UNINSTALLING_ALL:
                return "Cleaning device for testing";
            case DEVICE_WAITING:
                return "Uploading test data to the device";
            case DEVICE_WIFI_CHECKING:
                return "WI-FI verification";
            case DEVICE_ADD_PERMISSIONS:
                return "Added permissions in APK's manifest";
            case DEVICE_DOWNLOAD_TESTSESSION:
                return "Downloading test session";
            case PREPARING:
                return "Preparing test run";
            case RESULTS_PROCESSING:
                return "Processing results";
            case RESULTS_WAITING:
                return "Downloading results from the device";
            case WAITING:
                return "Waiting in queue for device";
            case DEVICE_UIAUTOMATOR_RUNNING:
                return "Running UIAutomator tests";
            case DEVICE_REMOTECONTROL_RUNNING:
                return "Remote control";
            case ADB_SHELL_COMMAND:
                return "ADB shell command";
            default:
                return "Unknown state";
        }
    }

}
