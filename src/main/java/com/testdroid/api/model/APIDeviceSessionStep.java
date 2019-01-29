package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIDeviceSessionStep extends APIEntity {

    @XmlType(namespace = "APIDeviceSessionStep")
    public enum Type {
        WAITING("Waiting for device"),
        PREPARING("Preparing device"),
        UNINSTALL("Uninstall apps"),
        INSTALL("Install apps"),
        RUNNING("Running session"),
        SENDING_RESULTS("Sending results"),
        PROCESSING_RESULTS("Processing results");

        private final String displayName;

        Type(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    private Long deviceSessionId;

    private String failReason;

    private Long finishTimeMS;

    private Type type;

    private Long startTimeMS;

    private String excludeReason;

    public APIDeviceSessionStep() {
    }

    public APIDeviceSessionStep(
            Long id, Long deviceSessionId, String failReason, Long finishTimeMS, Long startTimeMS,
            Type type) {
        super(id);
        this.deviceSessionId = deviceSessionId;
        this.failReason = failReason;
        this.finishTimeMS = finishTimeMS;
        this.startTimeMS = startTimeMS;
        this.type = type;
    }

    public APIDeviceSessionStep(
            Long id, Long deviceSessionId, String failReason, Long finishTimeMS, Long startTimeMS,
            Type type, APIDeviceSession.ExcludeReason excludeReason) {
        this(id, deviceSessionId, failReason, finishTimeMS, startTimeMS, type);
        this.excludeReason = excludeReason != null ? excludeReason.getDisplayName() : null;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getStartTimeMS() {
        return startTimeMS;
    }

    public void setStartTimeMS(Long startTimeMS) {
        this.startTimeMS = startTimeMS;
    }

    public String getExcludeReason() {
        return excludeReason;
    }

    public void setExcludeReason(String excludeReason) {
        this.excludeReason = excludeReason;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceSessionStep apiDeviceSessionStep = (APIDeviceSessionStep) from;
        cloneBase(from);
        this.deviceSessionId = apiDeviceSessionStep.deviceSessionId;
        this.failReason = apiDeviceSessionStep.failReason;
        this.startTimeMS = apiDeviceSessionStep.startTimeMS;
        this.finishTimeMS = apiDeviceSessionStep.finishTimeMS;
        this.type = apiDeviceSessionStep.type;
        this.excludeReason = apiDeviceSessionStep.excludeReason;
    }
}
