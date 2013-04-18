package com.testdroid.api.model;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author kajdus
 */
@XmlType
public enum DeviceRunStateType {
        PREPARING(1), WAITING(2), DEVICE_WAITING(3), DEVICE_DOWNLOAD_TESTSESSION(4), DEVICE_UNINSTALLING_ALL(5),
        DEVICE_REBOOTING(5), DEVICE_WIFI_CHECKING(5), DEVICE_ADD_PERMISSIONS(5),
        DEVICE_REPACKAGING(5), DEVICE_TARGET_INSTALLING(5), DEVICE_TEST_INSTALLING(5),DEVICE_REMOTECONTROL_RUNNING(5),
        DEVICE_RUNNING(5), DEVICE_SDCARD_COPYING(5), DEVICE_TARGET_UNINSTALLING(5), DEVICE_UIAUTOMATOR_RUNNING(5),
        DEVICE_TEST_UNINSTALLING(5), RESULTS_WAITING(6), RESULTS_PROCESSING(7), PARSE_LOGCAT(5);
        
        private int orderNumber;
        
        private DeviceRunStateType(int orderNumber) { 
        	this.orderNumber = orderNumber; 
        	}
        
        public boolean canBeFollowedBy(DeviceRunStateType type) { 
        	return this.orderNumber <= type.orderNumber;  
        	
        }
        
        public String getDisplayName() {
            switch(this) {
            	case PARSE_LOGCAT: return "Launching application";
                case DEVICE_REBOOTING: return "Rebooting device";
                case DEVICE_REPACKAGING: return "Repackaging APKs";
                case DEVICE_RUNNING: return "Running tests";
                case DEVICE_SDCARD_COPYING: return "Retrieving data from SD card";
                case DEVICE_TARGET_INSTALLING: return "Installing application";
                case DEVICE_TARGET_UNINSTALLING: return "Uninstalling application";
                case DEVICE_TEST_INSTALLING: return "Installing test APK";
                case DEVICE_TEST_UNINSTALLING: return "Uninstalling test APK";
                case DEVICE_UNINSTALLING_ALL: return "Cleaning device for testing";
                case DEVICE_WAITING: return "Uploading test data to the device";
                case DEVICE_WIFI_CHECKING: return "WI-FI verification";
                case DEVICE_ADD_PERMISSIONS: return "Added permissions in APK's manifest";
                case DEVICE_DOWNLOAD_TESTSESSION: return "Downloading test session";
                case PREPARING: return "Preparing test run";
                case RESULTS_PROCESSING: return "Processing results";
                case RESULTS_WAITING: return "Downloading results from the device";
                case WAITING: return "Waiting in queue for device";
                case DEVICE_UIAUTOMATOR_RUNNING: return "Running UIAutomator tests";
                case DEVICE_REMOTECONTROL_RUNNING: return "Remote control";
                default: return "Unknown state";
            }
        }

}
