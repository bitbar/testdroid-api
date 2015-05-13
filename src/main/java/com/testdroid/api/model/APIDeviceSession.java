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
        INTERACTIVE,
        AUTOMATIC
    }

    @XmlType(namespace = "APIDeviceSession")
    public static enum State {
        WAITING,
        RUNNING,
        EXCLUDED,
        WARNING,
        FAILED,
        SUCCEEDED;

        public boolean isFinished() {
            switch (this) {
                case WAITING:
                case RUNNING:
                    return false;
                default:
                    return true;
            }
        }
    }

    private Date createTime;

    private APIDevice device;

    private Date endTime;

    private Long launchAppDuration;

    private Date startTime;

    private Date installTime;

    private Long timeLimit;

    private Type type;

    private State state;

    private Long deviceRunId;

    private Integer testCaseAllCount;

    private Integer testCaseSuccessCount;

    private Integer testCasePassedCount;

    private Integer testCaseFailedCount;

    private Integer testCaseSkippedCount;

    public APIDeviceSession() {
    }

    public APIDeviceSession(Long id, Type type, Date createTime, Date startTime, Date installTime, Date endTime,
            APIDevice device, Long timeLimit, Long launchAppDuration, State state, Long deviceRunId) {
        super(id);
        this.type = type;
        this.createTime = createTime;
        this.startTime = startTime;
        this.installTime = installTime;
        this.endTime = endTime;
        this.device = device;
        this.timeLimit = timeLimit;
        this.launchAppDuration = launchAppDuration;
        this.state = state;
        this.deviceRunId = deviceRunId;
    }

    public APIDeviceSession(Long id, Type type, Date createTime, Date startTime, Date installTime, Date endTime,
            APIDevice device, Long timeLimit, Long launchAppDuration, State state, Long deviceRunId,
            Integer testCaseAllCount, Integer testCaseSuccessCount, Integer testCasePassedCount,
            Integer testCaseFailedCount, Integer testCaseSkippedCount) {
        this(id, type, createTime, startTime, installTime, endTime, device, timeLimit, launchAppDuration, state,
                deviceRunId);
        this.testCaseAllCount = testCaseAllCount;
        this.testCaseSuccessCount = testCaseSuccessCount;
        this.testCasePassedCount = testCasePassedCount;
        this.testCaseFailedCount = testCaseFailedCount;
        this.testCaseSkippedCount = testCaseSkippedCount;
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

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public Long getLaunchAppDuration() {
        return launchAppDuration;
    }

    public void setLaunchAppDuration(Long launchAppDuration) {
        this.launchAppDuration = launchAppDuration;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getDeviceRunId() {
        return deviceRunId;
    }

    public void setDeviceRunId(Long deviceRunId) {
        this.deviceRunId = deviceRunId;
    }

    public Integer getTestCaseAllCount() {
        return testCaseAllCount;
    }

    public void setTestCaseAllCount(Integer testCaseAllCount) {
        this.testCaseAllCount = testCaseAllCount;
    }

    public Integer getTestCaseSuccessCount() {
        return testCaseSuccessCount;
    }

    public void setTestCaseSuccessCount(Integer testCaseSuccessCount) {
        this.testCaseSuccessCount = testCaseSuccessCount;
    }

    public Integer getTestCasePassedCount() {
        return testCasePassedCount;
    }

    public void setTestCasePassedCount(Integer testCasePassedCount) {
        this.testCasePassedCount = testCasePassedCount;
    }

    public Integer getTestCaseFailedCount() {
        return testCaseFailedCount;
    }

    public void setTestCaseFailedCount(Integer testCaseFailedCount) {
        this.testCaseFailedCount = testCaseFailedCount;
    }

    public Integer getTestCaseSkippedCount() {
        return testCaseSkippedCount;
    }

    public void setTestCaseSkippedCount(Integer testCaseSkippedCount) {
        this.testCaseSkippedCount = testCaseSkippedCount;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceSession apiDeviceSession = (APIDeviceSession) from;
        cloneBase(from);
        this.createTime = apiDeviceSession.createTime;
        this.startTime = apiDeviceSession.startTime;
        this.installTime = apiDeviceSession.installTime;
        this.endTime = apiDeviceSession.endTime;
        this.type = apiDeviceSession.type;
        this.device = apiDeviceSession.device;
        this.timeLimit = apiDeviceSession.timeLimit;
        this.launchAppDuration = apiDeviceSession.launchAppDuration;
        this.state = apiDeviceSession.state;
        this.deviceRunId = apiDeviceSession.deviceRunId;
        this.testCaseAllCount = apiDeviceSession.testCaseAllCount;
        this.testCaseSuccessCount = apiDeviceSession.testCaseSuccessCount;
        this.testCasePassedCount = apiDeviceSession.testCasePassedCount;
        this.testCaseFailedCount = apiDeviceSession.testCaseFailedCount;
        this.testCaseSkippedCount = apiDeviceSession.testCaseSkippedCount;
    }
}
