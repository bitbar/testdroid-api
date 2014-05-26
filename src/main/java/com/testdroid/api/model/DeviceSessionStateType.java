package com.testdroid.api.model;

import javax.xml.bind.annotation.XmlType;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */

@XmlType
public enum DeviceSessionStateType {
    WAITING,
    PREPARING,
    UNINSTALL,
    INSTALL,
    RUNNING,
    SENDING_RESULTS,
    PROCESSING_RESULTS;

    public String getDisplayName() {
        switch (this) {
            case PREPARING:
                return "Preparing device";
            case WAITING:
                return "waiting for device";
            case UNINSTALL:
                return "Uninstall apps";
            case INSTALL:
                return "Install apps";
            case RUNNING:
                return "Running session";
            case SENDING_RESULTS:
                return "Sending results";
            case PROCESSING_RESULTS:
                return "Processing results";
            default:
                return "";
        }
    }

    public boolean isChargeable() {
        switch (this) {
            case INSTALL:
            case RUNNING:
                return true;
            default:
                return false;
        }
    }
}
