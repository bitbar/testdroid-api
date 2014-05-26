package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIDeviceSessionState extends APIEntity {

    private Long deviceSessionId;

    private String failReason;

    private Long finishTimeMS;

    private DeviceSessionStateType type;

    private Long startTimeMS;

    public APIDeviceSessionState() {
    }

    public APIDeviceSessionState(
            Long id, Long deviceSessionId, String failReason, Long finishTimeMS, Long startTimeMS,
            DeviceSessionStateType type) {
        super(id);
        this.deviceSessionId = deviceSessionId;
        this.failReason = failReason;
        this.finishTimeMS = finishTimeMS;
        this.startTimeMS = startTimeMS;
        this.type = type;
    }

    public Long getDeviceSessionId() {
        return deviceSessionId;
    }

    public void setDeviceSessionId(Long deviceSessionId) {
        this.deviceSessionId = deviceSessionId;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public Long getFinishTimeMS() {
        return finishTimeMS;
    }

    public void setFinishTimeMS(Long finishTimeMS) {
        this.finishTimeMS = finishTimeMS;
    }

    public DeviceSessionStateType getType() {
        return type;
    }

    public void setType(DeviceSessionStateType type) {
        this.type = type;
    }

    public Long getStartTimeMS() {
        return startTimeMS;
    }

    public void setStartTimeMS(Long startTimeMS) {
        this.startTimeMS = startTimeMS;
    }

    @Override protected <T extends APIEntity> void clone(T from) {
        APIDeviceSessionState apiDeviceSessionStep = (APIDeviceSessionState) from;
        cloneBase(from);
        this.deviceSessionId = apiDeviceSessionStep.deviceSessionId;
        this.failReason = apiDeviceSessionStep.failReason;
        this.startTimeMS = apiDeviceSessionStep.startTimeMS;
        this.finishTimeMS = apiDeviceSessionStep.finishTimeMS;
        this.type = apiDeviceSessionStep.type;
    }
}
