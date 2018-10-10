package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIAdminInteractiveDeviceSession extends APIEntity {

    private Boolean billable;

    private Long deviceId;

    private Long deviceModelId;

    private String deviceModelName;

    private String deviceName;

    private Long duration;

    private Date endTime;

    private String errorMessage;

    private Date createTime;

    private Date startTime;

    private APIDeviceSession.State state;

    private String userEmail;

    private Long userId;

    private Long deviceTime;

    private APIDeviceSession.Type type;

    public APIAdminInteractiveDeviceSession() {
    }

    public APIAdminInteractiveDeviceSession(
            Long id, Date createTime, Date startTime, Date endTime, String userEmail, Long userId, Long duration,
            String deviceModelName, Long deviceModelId, String deviceName, Long deviceId,
            APIDeviceSession.State state, Boolean billable, Long deviceTime, APIDeviceSession.Type type) {
        super(id);
        this.createTime = createTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userEmail = userEmail;
        this.userId = userId;
        this.duration = duration;
        this.deviceModelName = deviceModelName;
        this.deviceModelId = deviceModelId;
        this.deviceName = deviceName;
        this.deviceId = deviceId;
        this.state = state;
        this.billable = billable;
        this.deviceTime = deviceTime;
        this.type = type;
    }

    public Boolean getBillable() {
        return billable;
    }

    public void setBillable(Boolean billable) {
        this.billable = billable;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(Long deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    public String getDeviceModelName() {
        return deviceModelName;
    }

    public void setDeviceModelName(String deviceModelName) {
        this.deviceModelName = deviceModelName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public APIDeviceSession.State getState() {
        return state;
    }

    public void setState(APIDeviceSession.State state) {
        this.state = state;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(Long deviceTime) {
        this.deviceTime = deviceTime;
    }

    public APIDeviceSession.Type getType() {
        return type;
    }

    public void setType(APIDeviceSession.Type type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAdminInteractiveDeviceSession interactiveDeviceSession = (APIAdminInteractiveDeviceSession) from;
        cloneBase(from);
        this.createTime = interactiveDeviceSession.createTime;
        this.startTime = interactiveDeviceSession.startTime;
        this.endTime = interactiveDeviceSession.endTime;
        this.userEmail = interactiveDeviceSession.userEmail;
        this.userId = interactiveDeviceSession.userId;
        this.duration = interactiveDeviceSession.duration;
        this.deviceModelName = interactiveDeviceSession.deviceModelName;
        this.deviceModelId = interactiveDeviceSession.deviceModelId;
        this.deviceName = interactiveDeviceSession.deviceName;
        this.deviceId = interactiveDeviceSession.deviceId;
        this.state = interactiveDeviceSession.state;
        this.errorMessage = interactiveDeviceSession.errorMessage;
        this.billable = interactiveDeviceSession.billable;
        this.deviceTime = interactiveDeviceSession.deviceTime;
        this.type = interactiveDeviceSession.type;
    }
}
