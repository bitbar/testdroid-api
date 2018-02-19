package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.InputStream;
import java.util.Date;

/**
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIDeviceSession extends APIEntity {

    @XmlType(namespace = "APIDeviceSession")
    public enum Type {
        MANUAL,
        AUTOMATIC
    }

    @XmlType(namespace = "APIDeviceSession")
    public enum RetryState {
        NONE,
        MANUAL,
        AUTO
    }

    @XmlType(namespace = "APIDeviceSession")
    public enum State {
        ABORTED,
        EXCLUDED,
        FAILED,
        RUNNING,
        SUCCEEDED,
        TIMEOUT,
        WAITING,
        WARNING;

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

    private Boolean billable;

    private String excludeReason;

    private Long deviceInstanceId;

    private RetryState retryState;

    private Integer autoRetriesLeftCount;

    private Long deviceTime;

    public APIDeviceSession() {
    }

    public APIDeviceSession(Long id, Type type, Date createTime, Date startTime, Date installTime, Date endTime,
            APIDevice device, Long timeLimit, Long launchAppDuration, State state, Long deviceRunId,
            Boolean billable, Long deviceTime) {
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
        this.billable = billable;
        this.deviceTime = deviceTime;
    }

    public APIDeviceSession(Long id, Type type, Date createTime, Date startTime, Date installTime, Date endTime,
            APIDevice device, Long timeLimit, Long launchAppDuration, State state, Long deviceRunId,
            Boolean billable, String excludeReason, Long deviceTime) {
        this(id, type, createTime, startTime, installTime, endTime, device, timeLimit, launchAppDuration, state,
                deviceRunId, billable, deviceTime);
        this.excludeReason = excludeReason;
    }

    public APIDeviceSession(Long id, Type type, Date createTime, Date startTime, Date installTime, Date endTime,
            APIDevice device, Long timeLimit, Long launchAppDuration, State state, Long deviceRunId,
            Integer testCaseAllCount, Integer testCaseSuccessCount, Integer testCasePassedCount,
            Integer testCaseFailedCount, Integer testCaseSkippedCount, Boolean billable, String excludeReason,
            Long deviceInstanceId, RetryState retryState, Integer autoRetriesLeftCount, Long deviceTime) {
        this(id, type, createTime, startTime, installTime, endTime, device, timeLimit, launchAppDuration, state,
                deviceRunId, billable, excludeReason, deviceTime);
        this.testCaseAllCount = testCaseAllCount;
        this.testCaseSuccessCount = testCaseSuccessCount;
        this.testCasePassedCount = testCasePassedCount;
        this.testCaseFailedCount = testCaseFailedCount;
        this.testCaseSkippedCount = testCaseSkippedCount;
        this.deviceInstanceId = deviceInstanceId;
        this.retryState = retryState;
        this.autoRetriesLeftCount = autoRetriesLeftCount;
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

    public Boolean getBillable() {
        return billable;
    }

    public void setBillable(Boolean billable) {
        this.billable = billable;
    }

    public String getExcludeReason() {
        return excludeReason;
    }

    public void setExcludeReason(String excludeReason) {
        this.excludeReason = excludeReason;
    }

    public Long getDeviceInstanceId() {
        return deviceInstanceId;
    }

    public void setDeviceInstanceId(Long deviceInstanceId) {
        this.deviceInstanceId = deviceInstanceId;
    }

    public RetryState getRetryState() {
        return retryState;
    }

    public void setRetryState(RetryState retryState) {
        this.retryState = retryState;
    }

    public Integer getAutoRetriesLeftCount() {
        return autoRetriesLeftCount;
    }

    public void setAutoRetriesLeftCount(Integer autoRetriesLeftCount) {
        this.autoRetriesLeftCount = autoRetriesLeftCount;
    }

    public Long getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(Long deviceTime) {
        this.deviceTime = deviceTime;
    }

    @JsonIgnore
    public APIListResource<APIDeviceSessionStep> getDeviceSessionStepsResource() throws APIException {
        return getListResource(createUri(selfURI, "/steps"));
    }

    @JsonIgnore
    public APIListResource<APIScreenshot> getScreenshotsResource() throws APIException {
        return getListResource(getScreenshotsURI());
    }

    @JsonIgnore
    public APIListResource<APIScreenshot> getScreenshotsResource(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getScreenshotsURI(), queryBuilder);
    }

    @JsonIgnore
    public APIListResource<APIScreenshot> getScreenshotsResource(long offset, long limit, String search, APISort sort)
            throws APIException {
        return getListResource(getScreenshotsURI(), offset, limit, search, sort, APIScreenshot.class);
    }

    @JsonIgnore
    public InputStream getOutputFiles() throws APIException {
        return client.get(createUri(selfURI, "/output-file-set/files.zip"));
    }

    @JsonIgnore
    public APIDeviceSessionDataAvailability getDataAvailability() throws APIException {
        return getResource(createUri(selfURI, "/data-availability"), APIDeviceSessionDataAvailability.class)
                .getEntity();
    }

    private String getScreenshotsURI() {
        return createUri(selfURI, "/screenshots");
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
        this.billable = apiDeviceSession.billable;
        this.excludeReason = apiDeviceSession.excludeReason;
        this.deviceInstanceId = apiDeviceSession.deviceInstanceId;
        this.retryState = apiDeviceSession.retryState;
        this.autoRetriesLeftCount = apiDeviceSession.autoRetriesLeftCount;
        this.deviceTime = apiDeviceSession.deviceTime;
    }
}
