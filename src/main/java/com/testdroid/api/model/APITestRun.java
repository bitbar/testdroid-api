package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.UiPresentable;
import com.testdroid.api.dto.Context;
import com.testdroid.api.util.TimeConverter;
import jakarta.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APITestRun extends APIEntity implements UiPresentable {

    @XmlType(namespace = "APITestRun", name = "APITestRunState")
    public enum State {
        WAITING,
        RUNNING,
        FINISHED
    }

    private Integer abortedDeviceCount;

    private APITestRunConfig config;

    private Date createTime;

    private Integer deviceCount;

    private String displayName;

    private Integer errorsDeviceCount;

    private Integer excludedDeviceCount;

    private Integer executedTestCaseCount;

    private Float executionRatio;

    private Integer failedTestCaseCount;

    private Integer finishedDeviceCount;

    private Long frameworkId;

    private String frameworkName;

    private Long logsFileId;

    private String message;

    private Integer number;

    private Long projectId;

    private String projectName;

    private Boolean retryable;

    private Integer rowIndex;

    private Integer runningDeviceCount;

    private Long screenshotsFileId;

    private Date startTime;

    private String startedByDisplayName;

    private Long startedById;

    private State state;

    private Integer succeededDeviceCount;

    private Float successRatio;

    private Integer successfulTestCaseCount;

    private Integer testCaseCount;

    private Integer timeoutedDeviceCount;

    private String uiLink;

    private Long userId;

    private Integer waitingDeviceCount;

    private Integer warningDeviceCount;

    public APITestRun() {
    }

    @SuppressWarnings("squid:S107")
    public APITestRun(
            Long id, Integer number, LocalDateTime createTime, LocalDateTime startTime, String displayName,
            Float executionRatio, Float successRatio, Long startedById, String startedByDisplayName, State state,
            Long userId, Long projectId, String projectName, Long screenshotsFileId, Long logsFileId,
            Integer testCaseCount, Integer successfulTestCaseCount, Integer failedTestCaseCount, Integer deviceCount,
            Integer finishedDeviceCount, Integer excludedDeviceCount, Integer errorsDeviceCount,
            Integer succeededDeviceCount, Integer runningDeviceCount, Integer warningDeviceCount,
            Integer waitingDeviceCount, Integer abortedDeviceCount, Integer timeoutedDeviceCount, Long frameworkId,
            String frameworkName, Long frameworkQueueWait, Boolean retryable, String testRunConfigurationContent) {
        super(id);
        this.number = number;
        this.createTime = TimeConverter.toDate(createTime);
        this.startTime = TimeConverter.toDate(startTime);
        this.displayName = displayName;
        this.executionRatio = executionRatio;
        this.successRatio = successRatio;
        this.startedById = startedById;
        this.startedByDisplayName = startedByDisplayName;
        this.state = state;
        this.userId = userId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.screenshotsFileId = screenshotsFileId;
        this.logsFileId = logsFileId;
        this.testCaseCount = testCaseCount;
        this.successfulTestCaseCount = successfulTestCaseCount;
        this.failedTestCaseCount = failedTestCaseCount;
        if (Objects.nonNull(successfulTestCaseCount) && Objects.nonNull(failedTestCaseCount)) {
            this.executedTestCaseCount = successfulTestCaseCount + failedTestCaseCount;
        }
        this.finishedDeviceCount = finishedDeviceCount;
        this.excludedDeviceCount = excludedDeviceCount;
        this.errorsDeviceCount = errorsDeviceCount;
        this.succeededDeviceCount = succeededDeviceCount;
        this.runningDeviceCount = runningDeviceCount;
        this.warningDeviceCount = warningDeviceCount;
        this.waitingDeviceCount = waitingDeviceCount;
        this.abortedDeviceCount = abortedDeviceCount;
        this.timeoutedDeviceCount = timeoutedDeviceCount;
        this.frameworkId = frameworkId;
        this.frameworkName = frameworkName;
        this.deviceCount = deviceCount;
        this.retryable = retryable;
        mapConfig(testRunConfigurationContent, frameworkQueueWait);
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Float getExecutionRatio() {
        return executionRatio;
    }

    public void setExecutionRatio(Float executionRatio) {
        this.executionRatio = executionRatio;
    }

    public Float getSuccessRatio() {
        return successRatio;
    }

    public void setSuccessRatio(Float successRatio) {
        this.successRatio = successRatio;
    }

    public Long getStartedById() {
        return startedById;
    }

    public void setStartedById(Long startedById) {
        this.startedById = startedById;
    }

    public String getStartedByDisplayName() {
        return startedByDisplayName;
    }

    public void setStartedByDisplayName(String startedByDisplayName) {
        this.startedByDisplayName = startedByDisplayName;
    }

    public Long getScreenshotsFileId() {
        return screenshotsFileId;
    }

    public void setScreenshotsFileId(Long screenshotsFileId) {
        this.screenshotsFileId = screenshotsFileId;
    }

    public Long getLogsFileId() {
        return logsFileId;
    }

    public void setLogsFileId(Long logsFileId) {
        this.logsFileId = logsFileId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    private String getDeviceSessionsURI() {
        return createUri(selfURI, "/device-sessions");
    }

    private String getRequestScreenshotsZipURI() {
        return createUri(selfURI, "/screenshots.zip");
    }

    private String getRequestLogsZipURI() {
        return createUri(selfURI, "/logs.zip");
    }

    private String getAbortURI() {
        return createUri(selfURI, "/abort");
    }

    private String getLogsZipURI() {
        return String.format("/files/%s", logsFileId);
    }

    private String getScreenshotsZipURI() {
        return String.format("/users/%s/files/%s", userId, screenshotsFileId);
    }

    public Integer getWaitingDeviceCount() {
        return waitingDeviceCount;
    }

    public void setWaitingDeviceCount(Integer waitingDeviceCount) {
        this.waitingDeviceCount = waitingDeviceCount;
    }

    public Integer getWarningDeviceCount() {
        return warningDeviceCount;
    }

    public void setWarningDeviceCount(Integer warningDeviceCount) {
        this.warningDeviceCount = warningDeviceCount;
    }

    public Integer getRunningDeviceCount() {
        return runningDeviceCount;
    }

    public void setRunningDeviceCount(Integer runningDeviceCount) {
        this.runningDeviceCount = runningDeviceCount;
    }

    public Integer getSucceededDeviceCount() {
        return succeededDeviceCount;
    }

    public void setSucceededDeviceCount(Integer succeededDeviceCount) {
        this.succeededDeviceCount = succeededDeviceCount;
    }

    public Integer getTestCaseCount() {
        return testCaseCount;
    }

    public void setTestCaseCount(Integer testCaseCount) {
        this.testCaseCount = testCaseCount;
    }

    public Integer getSuccessfulTestCaseCount() {
        return successfulTestCaseCount;
    }

    public void setSuccessfulTestCaseCount(Integer successfulTestCaseCount) {
        this.successfulTestCaseCount = successfulTestCaseCount;
    }

    public Integer getFailedTestCaseCount() {
        return failedTestCaseCount;
    }

    public void setFailedTestCaseCount(Integer failedTestCaseCount) {
        this.failedTestCaseCount = failedTestCaseCount;
    }

    public Integer getExecutedTestCaseCount() {
        return executedTestCaseCount;
    }

    public void setExecutedTestCaseCount(Integer executedTestCaseCount) {
        this.executedTestCaseCount = executedTestCaseCount;
    }

    public Integer getFinishedDeviceCount() {
        return finishedDeviceCount;
    }

    public void setFinishedDeviceCount(Integer finishedDeviceCount) {
        this.finishedDeviceCount = finishedDeviceCount;
    }

    public Integer getExcludedDeviceCount() {
        return excludedDeviceCount;
    }

    public void setExcludedDeviceCount(Integer excludedDeviceCount) {
        this.excludedDeviceCount = excludedDeviceCount;
    }

    public Integer getErrorsDeviceCount() {
        return errorsDeviceCount;
    }

    public void setErrorsDeviceCount(Integer errorsDeviceCount) {
        this.errorsDeviceCount = errorsDeviceCount;
    }

    public Integer getAbortedDeviceCount() {
        return abortedDeviceCount;
    }

    public void setAbortedDeviceCount(Integer abortedDeviceCount) {
        this.abortedDeviceCount = abortedDeviceCount;
    }

    public Integer getTimeoutedDeviceCount() {
        return timeoutedDeviceCount;
    }

    public void setTimeoutedDeviceCount(Integer timeoutedDeviceCount) {
        this.timeoutedDeviceCount = timeoutedDeviceCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFrameworkId() {
        return frameworkId;
    }

    public void setFrameworkId(Long frameworkId) {
        this.frameworkId = frameworkId;
    }

    public String getFrameworkName() {
        return frameworkName;
    }

    public void setFrameworkName(String frameworkName) {
        this.frameworkName = frameworkName;
    }

    public void setConfig(APITestRunConfig config) {
        this.config = config;
    }

    public Integer getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void delete() throws APIException {
        deleteResource(selfURI);
    }

    public APITestRunConfig getConfig() {
        return config;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Boolean isRetryable() {
        return retryable;
    }

    public void setRetryable(Boolean retryable) {
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

    @JsonIgnore
    public APIListResource<APIDeviceSession> getDeviceSessionsResource() throws APIException {
        return getListResource(getDeviceSessionsURI(), APIDeviceSession.class);
    }

    @JsonIgnore
    public APIListResource<APIDeviceSession> getDeviceSessionsResource(Context<APIDeviceSession> context)
            throws APIException {
        return getListResource(getDeviceSessionsURI(), context);
    }

    @JsonIgnore
    public void requestScreenshotsZip() throws APIException {
        APIUserFile result = postResource(getRequestScreenshotsZipURI(), null, APIUserFile.class);
        this.screenshotsFileId = result.getId();
    }

    @JsonIgnore
    public void requestLogsZip() throws APIException {
        APIUserFile result = postResource(getRequestLogsZipURI(), null, APIUserFile.class);
        this.logsFileId = result.getId();
    }

    @JsonIgnore
    public APIUserFile getScreenshotsZip() throws APIException {
        return super.getResource(getScreenshotsZipURI(), APIUserFile.class).getEntity();
    }

    @JsonIgnore
    public APIUserFile getLogsZip() throws APIException {
        return super.getResource(getLogsZipURI(), APIUserFile.class).getEntity();
    }

    public void update() throws APIException {
        APITestRun testRun = postResource(selfURI, Collections
                .singletonMap("displayName", displayName), APITestRun.class);
        clone(testRun);
    }

    public void abort() throws APIException {
        postResource(getAbortURI(), null, null);
    }

    @JsonIgnore
    private void mapConfig(String content, Long frameworkQueueWait) {
        if (StringUtils.isBlank(content)) {
            return;
        }
        try {
            APITestRunConfig apiTestRunConfig = OBJECT_MAPPER.readValue(content, APITestRunConfig.class);
            apiTestRunConfig.setMaxWaitTime(
                    Optional.ofNullable(apiTestRunConfig.getMaxWaitTime()).orElse(frameworkQueueWait));
            setConfig(apiTestRunConfig);
        } catch (IOException ignore) {
            // ignored
        }
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APITestRun apiTestRun = (APITestRun) from;
        cloneBase(from);
        this.config = apiTestRun.config;
        this.createTime = apiTestRun.createTime;
        this.startTime = apiTestRun.startTime;
        this.displayName = apiTestRun.displayName;
        this.executionRatio = apiTestRun.executionRatio;
        this.number = apiTestRun.number;
        this.rowIndex = apiTestRun.rowIndex;
        this.startedById = apiTestRun.startedById;
        this.startedByDisplayName = apiTestRun.startedByDisplayName;
        this.state = apiTestRun.state;
        this.successRatio = apiTestRun.successRatio;
        this.userId = apiTestRun.userId;
        this.projectId = apiTestRun.projectId;
        this.screenshotsFileId = apiTestRun.screenshotsFileId;
        this.logsFileId = apiTestRun.logsFileId;
        this.testCaseCount = apiTestRun.testCaseCount;
        this.successfulTestCaseCount = apiTestRun.successfulTestCaseCount;
        this.failedTestCaseCount = apiTestRun.failedTestCaseCount;
        this.finishedDeviceCount = apiTestRun.finishedDeviceCount;
        this.excludedDeviceCount = apiTestRun.excludedDeviceCount;
        this.errorsDeviceCount = apiTestRun.errorsDeviceCount;
        this.warningDeviceCount = apiTestRun.warningDeviceCount;
        this.runningDeviceCount = apiTestRun.runningDeviceCount;
        this.succeededDeviceCount = apiTestRun.succeededDeviceCount;
        this.waitingDeviceCount = apiTestRun.waitingDeviceCount;
        this.abortedDeviceCount = apiTestRun.abortedDeviceCount;
        this.timeoutedDeviceCount = apiTestRun.timeoutedDeviceCount;
        this.frameworkId = apiTestRun.frameworkId;
        this.frameworkName = apiTestRun.frameworkName;
        this.projectName = apiTestRun.projectName;
        this.deviceCount = apiTestRun.deviceCount;
        this.message = apiTestRun.message;
        this.uiLink = apiTestRun.uiLink;
        this.retryable = apiTestRun.retryable;
    }
}
