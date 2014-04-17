package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIDeviceRunState extends APIEntity {

    @XmlType(namespace = "APIDeviceRunState")
    public static enum Status {
        STARTED, EXCLUDED, FAILED, SUCCEEDED, NOT_AVAILABLE
    }

    private Long deviceRunId;

    private String failReason;

    private Long finishTimeMS;

    private Date retryTime;

    private Long startTimeMS;

    private Status status;

    private DeviceRunStateType type;

    public APIDeviceRunState() {
    }

    public APIDeviceRunState(Long id, Long deviceRunId, Long startTimeMS, Long finishTimeMS, Date retryTime,
            String failReason, Status status, DeviceRunStateType type) {
        super(id);
        this.deviceRunId = deviceRunId;
        this.startTimeMS = startTimeMS;
        this.finishTimeMS = finishTimeMS;
        this.retryTime = retryTime;
        this.failReason = failReason;
        this.status = status;
        this.type = type;
    }

    public Long getDeviceRunId() {
        return deviceRunId;
    }

    public void setDeviceRunId(Long deviceRunId) {
        this.deviceRunId = deviceRunId;
    }

    public Long getStartTimeMS() {
        return startTimeMS;
    }

    public void setStartTimeMS(Long startTimeMS) {
        this.startTimeMS = startTimeMS;
    }

    public Long getFinishTimeMS() {
        return finishTimeMS;
    }

    public void setFinishTimeMS(Long finishTimeMS) {
        this.finishTimeMS = finishTimeMS;
    }

    public Date getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(Date retryTime) {
        this.retryTime = retryTime;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DeviceRunStateType getDeviceRunStateType() {
        return type;
    }

    public void setDeviceRunStateType(DeviceRunStateType type) {
        this.type = type;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceRunState apiDeviceRunState = (APIDeviceRunState) from;
        cloneBase(from);
        this.deviceRunId = apiDeviceRunState.deviceRunId;
        this.failReason = apiDeviceRunState.failReason;
        this.finishTimeMS = apiDeviceRunState.finishTimeMS;
        this.retryTime = apiDeviceRunState.retryTime;
        this.startTimeMS = apiDeviceRunState.startTimeMS;
        this.status = apiDeviceRunState.status;
        this.type = apiDeviceRunState.type;
    }

}
