package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIBillingPeriodUsage extends APIEntity {

    private APIDeviceSession.Type type;

    private APIDevice.OsType osType;

    private long duration;

    public APIBillingPeriodUsage() {
    }

    public APIBillingPeriodUsage(
            APIDeviceSession.Type type, APIDevice.OsType osType, long duration) {
        this.type = type;
        this.osType = osType;
        this.duration = duration;
    }

    public APIDeviceSession.Type getType() {
        return type;
    }

    public APIDevice.OsType getOsType() {
        return osType;
    }

    public void setType(APIDeviceSession.Type type) {
        this.type = type;
    }

    public void setOsType(APIDevice.OsType osType) {
        this.osType = osType;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIBillingPeriodUsage original = (APIBillingPeriodUsage) from;
        cloneBase(from);
        this.type = original.type;
        this.osType = original.osType;
        this.duration = original.duration;
    }
}
