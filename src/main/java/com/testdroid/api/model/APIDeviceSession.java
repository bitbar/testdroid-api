package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.UiPresentable;
import com.testdroid.api.dto.Context;
import com.testdroid.api.util.TimeConverter;
import jakarta.xml.bind.annotation.XmlType;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APIDeviceSession extends APIEntity implements UiPresentable {

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
    public enum FinishReason {
        CHANGE_DEVICE,
        EXCLUDED,
        GRANT_REJECTED,
        INACTIVITY,
        LOGOUT,
        PLAN_CHANGE,
        SUCCESS,
        TEST_ERROR,
        TIMEOUT,
        UNKNOWN,
        USER_ACTION,
        WEBSOCKET_CLOSED,
    }

    @XmlType(namespace = "APIDeviceSession")
    public enum Type {
        AUTOMATIC,
        MANUAL_APP,
        MANUAL_WEB;

        public boolean isManual() {
            return this == MANUAL_APP || this == MANUAL_WEB;
        }
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
            return switch (this) {
                case WAITING, RUNNING -> false;
                default -> true;
            };
        }

        public boolean isSettable() {
            return switch (this) {
                case WAITING, RUNNING, EXCLUDED -> false;
                default -> true;
            };
        }
    }

    private Long accountId;

    private Integer autoRetriesLeftCount;

    private Boolean biometricInstrumentation;

    private String clientSideId;

    private APIDeviceSessionConfig config;

    private Date createTime;

    private APIDevice device;

    private Long deviceInstanceId;

    private Long duration;

    private Date endTime;

    private Date abortTime;

    private String excludeReason;

    private String externalId;

    private Date installTime;

    private String name;

    private Long projectId;

    private String projectName;

    private Boolean retryable;

    private RetryState retryState;

    private Date startTime;

    private State state;

    private Float successRatio;

    private Integer testCaseAllCount;

    private Integer testCaseFailedCount;

    private Integer testCasePassedCount;

    private Integer testCaseSkippedCount;

    private Integer testCaseSuccessCount;

    private Long testRunId;

    private String testRunName;

    private Long timeLimit;

    private Type type;

    private String uiLink;

    private String userEmail;

    private Long userId;

    public APIDeviceSession() {
    }

    @SuppressWarnings("squid:S107")
    public APIDeviceSession(
            Long id, String externalId, String clientSideId, Long userId, String userEmail, Long accountId,
            APIDeviceSession.Type type, LocalDateTime createTime, LocalDateTime startTime, LocalDateTime installTime,
            LocalDateTime endTime, LocalDateTime abortTime, Long timeLimit, APIDeviceSession.State state,
            Integer testCasePassedCount, Integer testCaseFailedCount, Integer testCaseSkippedCount, Long deviceModelId,
            String displayName, String deviceManufacturer, Integer creditsPrice, String imagePrefix, Integer imageTop,
            Integer imageLeft, Integer imageWidth, Integer imageHeight, Integer imageCornerRadius,
            Integer frameExtraWidth, APIDevice.OsType osType, APIDevice.Platform platform, Boolean locked,
            Boolean enabled, String releaseVersion, Integer apiLevel, ExcludeReason excludeReason,
            Long deviceInstanceId, RetryState retryState, Integer autoRetriesLeftCount, Long duration, Long projectId,
            String projectName, Long testRunId, String testRunName, Float successRatio, String name,
            APIDeviceSessionConfig config, Boolean biometricInstrumentation, String location, Boolean retryable) {
        super(id);
        this.externalId = externalId;
        this.clientSideId = clientSideId;
        this.userId = userId;
        this.userEmail = userEmail;
        this.accountId = accountId;
        this.type = type;
        this.createTime = TimeConverter.toDate(createTime);
        this.startTime = TimeConverter.toDate(startTime);
        this.installTime = TimeConverter.toDate(installTime);
        this.endTime = TimeConverter.toDate(endTime);
        this.abortTime = TimeConverter.toDate(abortTime);
        this.device = new APIDevice(deviceModelId, displayName, deviceManufacturer, releaseVersion,
                apiLevel, creditsPrice, imagePrefix, imageTop, imageLeft, imageWidth, imageHeight, imageCornerRadius,
                frameExtraWidth, osType, platform, null, locked, enabled, location, null, null, null, null);
        this.timeLimit = timeLimit;
        this.state = state;
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
        this.projectName = projectName;
        this.testRunId = testRunId;
        this.testRunName = testRunName;
        this.successRatio = successRatio;
        this.name = name;
        this.config = config;
        this.biometricInstrumentation = biometricInstrumentation;
        this.retryable = retryable;
    }

    @Override
    public String getUiLink() {
        return uiLink;
    }

    @Override
    public void setUiLink(String uiLink) {
        this.uiLink = uiLink;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getAbortTime() {
        return abortTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setAbortTime(Date abortTime) {
        this.abortTime = abortTime;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTestRunName() {
        return testRunName;
    }

    public void setTestRunName(String testRunName) {
        this.testRunName = testRunName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public APIDeviceSessionConfig getConfig() {
        return config;
    }

    public void setConfig(APIDeviceSessionConfig config) {
        this.config = config;
    }

    public String getClientSideId() {
        return clientSideId;
    }

    public void setClientSideId(String clientSideId) {
        this.clientSideId = clientSideId;
    }

    public Boolean getBiometricInstrumentation() {
        return biometricInstrumentation;
    }

    public void setBiometricInstrumentation(Boolean biometricInstrumentation) {
        this.biometricInstrumentation = biometricInstrumentation;
    }

    public Boolean isRetryable() {
        return retryable;
    }

    public void setRetryable(Boolean retryable) {
        this.retryable = retryable;
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

    @JsonIgnore
    public APIDeviceSession abort() throws APIException {
        return client.post(createUri(selfURI, "/abort"), null, APIDeviceSession.class);
    }

    private String getScreenshotsURI() {
        return createUri(selfURI, "/screenshots");
    }

    @JsonIgnore
    public APIUserFile uploadResultsFile(File file, String contentType, Map<String, String> fileParams)
            throws APIException {
        return postFile(createUri(selfURI, "/output-file-set/files"), file, fileParams, contentType, APIUserFile.class);
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceSession apiDeviceSession = (APIDeviceSession) from;
        cloneBase(from);
        this.externalId = apiDeviceSession.externalId;
        this.clientSideId = apiDeviceSession.clientSideId;
        this.userId = apiDeviceSession.userId;
        this.userEmail = apiDeviceSession.userEmail;
        this.accountId = apiDeviceSession.accountId;
        this.createTime = apiDeviceSession.createTime;
        this.startTime = apiDeviceSession.startTime;
        this.installTime = apiDeviceSession.installTime;
        this.endTime = apiDeviceSession.endTime;
        this.abortTime = apiDeviceSession.abortTime;
        this.type = apiDeviceSession.type;
        this.device = apiDeviceSession.device;
        this.timeLimit = apiDeviceSession.timeLimit;
        this.state = apiDeviceSession.state;
        this.testCaseAllCount = apiDeviceSession.testCaseAllCount;
        this.testCaseSuccessCount = apiDeviceSession.testCaseSuccessCount;
        this.testCasePassedCount = apiDeviceSession.testCasePassedCount;
        this.testCaseFailedCount = apiDeviceSession.testCaseFailedCount;
        this.testCaseSkippedCount = apiDeviceSession.testCaseSkippedCount;
        this.excludeReason = apiDeviceSession.excludeReason;
        this.deviceInstanceId = apiDeviceSession.deviceInstanceId;
        this.retryState = apiDeviceSession.retryState;
        this.autoRetriesLeftCount = apiDeviceSession.autoRetriesLeftCount;
        this.duration = apiDeviceSession.duration;
        this.testRunId = apiDeviceSession.testRunId;
        this.testRunName = apiDeviceSession.testRunName;
        this.projectId = apiDeviceSession.projectId;
        this.projectName = apiDeviceSession.projectName;
        this.successRatio = apiDeviceSession.successRatio;
        this.name = apiDeviceSession.name;
        this.config = apiDeviceSession.config;
        this.biometricInstrumentation = apiDeviceSession.biometricInstrumentation;
        this.retryable = apiDeviceSession.retryable;
    }
}
