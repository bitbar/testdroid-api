package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIDeviceSession extends APIEntity {

    @XmlType(namespace = "APIDeviceSession")
    public static enum Type {
        INSPECTOR,
        AUTOMATIC
    }

    private Date createTime;

    private APIDevice device;

    private Date endTime;

    private Long launchAppDuration;

    private Date startTime;

    private Long timeLimit;

    private Type type;

    public APIDeviceSession() {
    }

    public APIDeviceSession(
            Long id, Type type, Date createTime, Date startTime, Date endTime, APIDevice device, Long timeLimit,
            Long launchAppDuration) {
        super(id);
        this.type = type;
        this.createTime = createTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.device = device;
        this.timeLimit = timeLimit;
        this.launchAppDuration = launchAppDuration;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public APIDevice getDevice() {
        return device;
    }

    public void setDevice(APIDevice device) {
        this.device = device;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Long getLaunchAppDuration() {
        return launchAppDuration;
    }

    public void setLaunchAppDuration(Long launchAppDuration) {
        this.launchAppDuration = launchAppDuration;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceSession apiDeviceSession = (APIDeviceSession) from;
        cloneBase(from);
        this.createTime = apiDeviceSession.createTime;
        this.startTime = apiDeviceSession.startTime;
        this.endTime = apiDeviceSession.endTime;
        this.type = apiDeviceSession.type;
        this.device = apiDeviceSession.device;
        this.timeLimit = apiDeviceSession.timeLimit;
        this.launchAppDuration = apiDeviceSession.launchAppDuration;
    }

}
