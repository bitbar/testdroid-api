package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.dto.Context;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIDeviceSession extends APIEntity {

    @XmlType(namespace = "APIDeviceSession")
    public enum ExcludeReason {
        ADMIN("Test run was interrupted by user or administrator."),
        NO_DEVICE("Requested device does not exist or is busy at the moment."),
        API_LEVEL("The minimum API Level required for the application to run is higher than Device's API Level"),
        SINGLE_MODE("SINGLE mode test run - another device has started execution."),
        FRAMEWORK_NOT_SUPPORTED("The device does not support selected framework"),
        CONCURRENCY_LIMIT("Account's device sessions concurrency limit reached");

        private final String displayName;

        ExcludeReason(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    @XmlType(namespace = "APIDeviceSession")
    public enum Type {
        AUTOMATIC,
        MANUAL,
        REMOTE
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

    private Long deviceLogFirstTimestamp;

    private Date startTime;

    private Date installTime;

    private Long timeLimit;

    private Type type;

    private State state;

    @Deprecated
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

    private Long duration;

    private Long testRunId;

    private Long projectId;

    private Float successRatio;

    private String name;

    public APIDeviceSession() {
    }

    public APIDeviceSession(
            Long id, APIDeviceSession.Type type, LocalDateTime createTime, LocalDateTime startTime,
            LocalDateTime installTime, LocalDateTime endTime, Long timeLimit, Long launchAppDuration,
            Long deviceLogFirstTimestamp, APIDeviceSession.State state, Integer testCasePassedCount,
            Integer testCaseFailedCount, Integer testCaseSkippedCount, Boolean billable, Long deviceModelId,
            String displayName, Integer creditsPrice, String imagePrefix, Integer imageTop, Integer imageLeft,
            Integer imageWidth, Integer imageHeight, Integer frameExtraWidth, APIDevice.OsType osType, Boolean enabled,
            Long softwareVersionId, String releaseVersion, Integer apiLevel, ExcludeReason excludeReason,
            Long deviceInstanceId, RetryState retryState, Integer autoRetriesLeftCount, Long deviceTime, Long duration,
            Long projectId, Long testRunId, Float successRatio, String name) {
        super(id);
        this.type = type;
        this.createTime = TimeConverter.toDate(createTime);
        this.startTime = TimeConverter.toDate(startTime);
        this.installTime = TimeConverter.toDate(installTime);
        this.endTime = TimeConverter.toDate(endTime);
        this.device = new APIDevice(deviceModelId, displayName, softwareVersionId, releaseVersion, apiLevel,
                creditsPrice, imagePrefix, imageTop, imageLeft, imageWidth, imageHeight, frameExtraWidth, osType, null,
                null, enabled, null, null);
        this.timeLimit = timeLimit;
        this.launchAppDuration = launchAppDuration;
        this.deviceLogFirstTimestamp = deviceLogFirstTimestamp;
        this.state = state;
        this.deviceRunId = id;
        this.billable = billable;
        this.deviceTime = deviceTime;
        this.excludeReason = excludeReason != null ? excludeReason.getDisplayName() : null;
        this.testCaseAllCount = testCasePassedCount + testCaseFailedCount + testCaseSkippedCount;
        this.testCaseSuccessCount = testCasePassedCount + testCaseFailedCount;
        this.testCasePassedCount = testCasePassedCount;
        this.testCaseFailedCount = testCaseFailedCount;
        this.testCaseSkippedCount = testCaseSkippedCount;
        this.deviceInstanceId = deviceInstanceId;
        this.retryState = retryState;
        this.autoRetriesLeftCount = autoRetriesLeftCount;
        this.duration = duration;
        this.projectId = projectId;
        this.testRunId = testRunId;
        this.successRatio = successRatio;
        this.name = name;
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

    public Long getDeviceLogFirstTimestamp() {
        return deviceLogFirstTimestamp;
    }

    public void setDeviceLogFirstTimestamp(Long deviceLogFirstTimestamp) {
        this.deviceLogFirstTimestamp = deviceLogFirstTimestamp;
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

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(Long testRunId) {
        this.testRunId = testRunId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Float getSuccessRatio() {
        return successRatio;
    }

    public void setSuccessRatio(Float successRatio) {
        this.successRatio = successRatio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public APIListResource<APIDeviceSessionStep> getDeviceSessionStepsResource() throws APIException {
        return getListResource(createUri(selfURI, "/steps"), APIDeviceSessionStep.class);
    }

    @JsonIgnore
    public APIListResource<APIScreenshot> getScreenshotsResource() throws APIException {
        return getListResource(getScreenshotsURI(), APIScreenshot.class);
    }

    @JsonIgnore
    public APIListResource<APIScreenshot> getScreenshotsResource(Context<APIScreenshot> context) throws APIException {
        return getListResource(getScreenshotsURI(), context);
    }

    @JsonIgnore
    public InputStream getOutputFiles() throws APIException {
        return client.get(createUri(selfURI, "/output-file-set/files.zip"));
    }

    @JsonIgnore
    public APIDeviceSession release() throws APIException {
        return client.post(createUri(selfURI, "/release"), null, APIDeviceSession.class);
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
        this.deviceLogFirstTimestamp = apiDeviceSession.deviceLogFirstTimestamp;
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
        this.duration = apiDeviceSession.duration;
        this.testRunId = apiDeviceSession.testRunId;
        this.projectId = apiDeviceSession.projectId;
        this.successRatio = apiDeviceSession.successRatio;
        this.name = apiDeviceSession.name;
    }
}
