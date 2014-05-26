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

    private DeviceSessionStateName name;

    private Date retryTime;

    private Long startTimeMS;

    public APIDeviceSessionState() {
    }

    public APIDeviceSessionState(
            Long id, Long deviceSessionId, String failReason, Long finishTimeMS, Date retryTime, Long startTimeMS,
            DeviceSessionStateName name) {
        super(id);
        this.deviceSessionId = deviceSessionId;
        this.failReason = failReason;
        this.finishTimeMS = finishTimeMS;
        this.retryTime = retryTime;
        this.startTimeMS = startTimeMS;
        this.name = name;
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

    public DeviceSessionStateName getName() {
        return name;
    }

    public void setName(DeviceSessionStateName name) {
        this.name = name;
    }

    public Date getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(Date retryTime) {
        this.retryTime = retryTime;
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
        this.retryTime = apiDeviceSessionStep.retryTime;
        this.name = apiDeviceSessionStep.name;
    }
}
