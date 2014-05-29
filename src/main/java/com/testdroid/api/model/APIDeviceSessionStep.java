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
                    return "Waiting for device";
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
    }

    private Long deviceSessionId;

    private String failReason;

    private Long finishTimeMS;

    private Type type;

    private Long startTimeMS;

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

    @Override protected <T extends APIEntity> void clone(T from) {
        APIDeviceSessionStep apiDeviceSessionStep = (APIDeviceSessionStep) from;
        cloneBase(from);
        this.deviceSessionId = apiDeviceSessionStep.deviceSessionId;
        this.failReason = apiDeviceSessionStep.failReason;
        this.startTimeMS = apiDeviceSessionStep.startTimeMS;
        this.finishTimeMS = apiDeviceSessionStep.finishTimeMS;
        this.type = apiDeviceSessionStep.type;
    }
}
