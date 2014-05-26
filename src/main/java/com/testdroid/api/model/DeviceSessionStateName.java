package com.testdroid.api.model;

import javax.xml.bind.annotation.XmlType;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */

@XmlType
public enum DeviceSessionStateName {
    PREPARING,
    WAITING,
    WAITING_FOR_DEVICE,
    FINISHED,
    EXCLUDED;

    public String getDisplayName() {
        switch (this) {
            case PREPARING:
                return "";
            case WAITING:
                return "";
            case WAITING_FOR_DEVICE:
                return "";
            default:
                return "";
        }
    }
}
