package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
public class APIUserDeviceTime extends APIEntity {

    private Date createTime;

    private Date endTime;

    private String userName;

    private Long userId;

    @Deprecated
    private Long freeTime;

    @Deprecated
    private Long billableTime;

    @Deprecated
    private Long deviceTime;

    private Long duration;

    private APIDeviceSession.Type type;

    public APIUserDeviceTime() {

    }

    public APIUserDeviceTime(
            LocalDateTime createTime, LocalDateTime endTime, String userName, Long userId, Long duration,
            APIDeviceSession.Type type) {
        this.createTime = TimeConverter.toDate(createTime);
        this.endTime = TimeConverter.toDate(endTime);
        this.userName = userName;
        this.userId = userId;
        this.freeTime = 0L;
        this.billableTime = duration;
        this.deviceTime = duration;
        this.duration = duration;
        this.type = type;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIUserDeviceTime apiUserDeviceTime = (APIUserDeviceTime) from;
        cloneBase(from);
        this.createTime = apiUserDeviceTime.createTime;
        this.endTime = apiUserDeviceTime.endTime;
        this.userName = apiUserDeviceTime.userName;
        this.userId = apiUserDeviceTime.userId;
        this.deviceTime = apiUserDeviceTime.deviceTime;
        this.type = apiUserDeviceTime.type;
        this.freeTime = apiUserDeviceTime.freeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Long getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Long freeTime) {
        this.freeTime = freeTime;
    }

    public Long getBillableTime() {
        return billableTime;
    }

    public void setBillableTime(Long billableTime) {
        this.billableTime = billableTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
