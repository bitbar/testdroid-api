package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

public class APIDeviceAvailability extends APIEntity {

    public enum PoolSize {
        XL,
        L,
        M,
        S,
    }

    public enum Code {
        HIGH,
        MODERATE,
        LOW,
        NONE,
    }

    private PoolSize poolSize;

    private Code code;

    public APIDeviceAvailability() {
    }

    public APIDeviceAvailability(PoolSize poolSize, Code code) {
        this.poolSize = poolSize;
        this.code = code;
    }

    public PoolSize getPoolSize() {
        return poolSize;
    }

    public APIDeviceAvailability.Code getCode() {
        return code;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceAvailability other = (APIDeviceAvailability) from;
        cloneBase(from);
        this.poolSize = other.poolSize;
        this.code = other.code;
    }
}
