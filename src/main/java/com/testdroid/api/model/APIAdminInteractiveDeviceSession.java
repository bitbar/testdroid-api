package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIAdminInteractiveDeviceSession extends APIEntity {

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

    private APIDeviceSession.Type type;

    public APIAdminInteractiveDeviceSession() {
    }

    @SuppressWarnings("squid:S107")
    public APIAdminInteractiveDeviceSession(
            Long id, LocalDateTime createTime, LocalDateTime startTime, LocalDateTime endTime, String userEmail,
            Long userId, Long duration, String deviceModelName, Long deviceModelId, String deviceName, Long deviceId,
            APIDeviceSession.State state, APIDeviceSession.Type type) {
        super(id);
        this.createTime = TimeConverter.toDate(createTime);
        this.startTime = TimeConverter.toDate(startTime);
        this.endTime = TimeConverter.toDate(endTime);
        this.userEmail = userEmail;
        this.userId = userId;
        this.duration = duration;
        this.deviceModelName = deviceModelName;
        this.deviceModelId = deviceModelId;
        this.deviceName = deviceName;
        this.deviceId = deviceId;
        this.state = state;
        this.type = type;
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
        this.type = interactiveDeviceSession.type;
    }
}
