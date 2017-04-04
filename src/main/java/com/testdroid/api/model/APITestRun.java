package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Collections;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APITestRun extends APIEntity {

    @XmlType(namespace = "APITestRun", name = "APITestRunState")
    public static enum State {
        WAITING,
        RUNNING,
        FINISHED
    }

    protected APITestRunConfig config;

    protected Date createTime;

    protected String displayName;

    protected Integer errorsDeviceCount;

    protected Integer excludedDeviceCount;

    protected Float executionRatio;

    protected Integer finishedDeviceCount;

    protected Long logsFileId;

    protected Integer number;

    protected Long projectId;

    protected Long screenshotsFileId;

    protected String startedByDisplayName;

    protected Long startedById;

    protected State state;

    protected Float successRatio;

    protected Integer successfulTestCaseCount;

    protected Integer testCaseCount;

    protected Integer totalDeviceCount;

    protected Integer warningDeviceCount;

    protected Integer runningDeviceCount;

    protected Integer succeededDeviceCount;

    protected Integer waitingDeviceCount;

    protected Integer abortedDeviceCount;

    protected Long userId;

    protected String gamebenchResultsUrl;

    protected Long frameworkId;

    protected String frameworkName;

    public APITestRun() {
    }

    public APITestRun(
            Long id, Integer number, Date createTime, String displayName, Float executionRatio,
            Float successRatio, Long startedById, String startedByDisplayName, State state, Long userId, Long projectId,
            Long screenshotsFileId, Long logsFileId, Integer testCaseCount, Integer successfulTestCaseCount,
            Integer totalDeviceCount, Integer finishedDeviceCount, Integer excludedDeviceCount,
            Integer errorsDeviceCount, Integer succeededDeviceCount, Integer runningDeviceCount,
            Integer warningDeviceCount, Integer waitingDeviceCount, Integer abortedDeviceCount,
            String gamebenchResultsUrl, Long frameworkId, String frameworkName) {
        super(id);
        this.number = number;
        this.createTime = createTime;
        this.displayName = displayName;
        this.executionRatio = executionRatio;
        this.successRatio = successRatio;
        this.startedById = startedById;
        this.startedByDisplayName = startedByDisplayName;
        this.state = state;
        this.userId = userId;
        this.projectId = projectId;
        this.screenshotsFileId = screenshotsFileId;
        this.logsFileId = logsFileId;
        this.testCaseCount = testCaseCount;
        this.successfulTestCaseCount = successfulTestCaseCount;
        this.totalDeviceCount = totalDeviceCount;
        this.finishedDeviceCount = finishedDeviceCount;
        this.excludedDeviceCount = excludedDeviceCount;
        this.errorsDeviceCount = errorsDeviceCount;
        this.succeededDeviceCount = succeededDeviceCount;
        this.runningDeviceCount = runningDeviceCount;
        this.warningDeviceCount = warningDeviceCount;
        this.waitingDeviceCount = waitingDeviceCount;
        this.abortedDeviceCount = abortedDeviceCount;
        this.gamebenchResultsUrl = gamebenchResultsUrl;
        this.frameworkId = frameworkId;
        this.frameworkName = frameworkName;
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

    private String getFilesURI() {
        return createUri(selfURI, "/files");
    }

    private String getConfigURI() {
        return createUri(selfURI, "/config");
    }

    private String getTagsURI() {
        return createUri(selfURI, "/tags");
    }

    private String getDeviceRunsURI() {
        return createUri(selfURI, "/device-runs");
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
        return String.format("/files/%s", screenshotsFileId);
    }

    public Integer getWaitingDeviceCount() {
        return waitingDeviceCount;
    }

    public void setWaitingDeviceCount(Integer waitingDeviceCount) {
        this.waitingDeviceCount = waitingDeviceCount;
    }

    @JsonIgnore
    public APIListResource<APIUserFile> getFilesResource() throws APIException {
        return getListResource(getFilesURI());
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

    public Integer getTotalDeviceCount() {
        return totalDeviceCount;
    }

    public void setTotalDeviceCount(Integer totalDeviceCount) {
        this.totalDeviceCount = totalDeviceCount;
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

    public String getGamebenchResultsUrl() {
        return gamebenchResultsUrl;
    }

    public void setGamebenchResultsUrl(String gamebenchResultsUrl) {
        this.gamebenchResultsUrl = gamebenchResultsUrl;
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

    public void delete() throws APIException {
        deleteResource(selfURI);
    }

    @JsonIgnore
    public APITestRunConfig getConfig() throws APIException {
        if (config == null) {
            config = getResource(getConfigURI(), APITestRunConfig.class).getEntity();
        }
        return config;
    }

    public APITag addTag(String name) throws APIException {
        return postResource(getTagsURI(), Collections.singletonMap("name", name), APITag.class);
    }

    @JsonIgnore
    public APIListResource<APITag> getTagsResource() throws APIException {
        return getListResource(getTagsURI());
    }

    /**
     * @param queryBuilder
     * @return
     * @throws APIException
     * @since 1.3.34
     */
    @JsonIgnore
    public APIListResource<APITag> getTagsResource(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getTagsURI(), queryBuilder);
    }

    /**
     * @param offset
     * @param limit
     * @param search
     * @param sort
     * @return
     * @throws APIException
     * @deprecated
     */
    @JsonIgnore
    public APIListResource<APITag> getTagsResource(long offset, long limit, String search, APISort sort)
            throws APIException {
        return getListResource(getTagsURI(), offset, limit, search, sort, APITag.class);
    }

    @JsonIgnore
    public APIListResource<APIDeviceRun> getDeviceRunsResource() throws APIException {
        return getListResource(getDeviceRunsURI());
    }

    @JsonIgnore
    public APIListResource<APIDeviceSession> getDeviceSessionsResource() throws APIException {
        return getListResource(getDeviceSessionsURI());
    }

    /**
     * @param queryBuilder
     * @return
     * @throws APIException
     * @since 1.3.34
     */
    @JsonIgnore
    public APIListResource<APIDeviceRun> getDeviceRunsResource(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getDeviceRunsURI(), queryBuilder);
    }

    /**
     * @param offset
     * @param limit
     * @param search
     * @param sort
     * @return
     * @throws APIException
     * @deprecated
     */
    @JsonIgnore
    public APIListResource<APIDeviceRun> getDeviceRunsResource(long offset, long limit, String search, APISort sort)
            throws APIException {
        return getListResource(getDeviceRunsURI(), offset, limit, search, sort, APIDeviceRun.class);
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

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APITestRun apiTestRun = (APITestRun) from;
        cloneBase(from);
        this.config = apiTestRun.config;
        this.createTime = apiTestRun.createTime;
        this.displayName = apiTestRun.displayName;
        this.executionRatio = apiTestRun.executionRatio;
        this.number = apiTestRun.number;
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
        this.totalDeviceCount = apiTestRun.totalDeviceCount;
        this.finishedDeviceCount = apiTestRun.finishedDeviceCount;
        this.excludedDeviceCount = apiTestRun.excludedDeviceCount;
        this.errorsDeviceCount = apiTestRun.errorsDeviceCount;
        this.warningDeviceCount = apiTestRun.warningDeviceCount;
        this.runningDeviceCount = apiTestRun.runningDeviceCount;
        this.succeededDeviceCount = apiTestRun.succeededDeviceCount;
        this.waitingDeviceCount = apiTestRun.waitingDeviceCount;
        this.abortedDeviceCount = apiTestRun.abortedDeviceCount;
        this.gamebenchResultsUrl = apiTestRun.gamebenchResultsUrl;
        this.frameworkId = apiTestRun.frameworkId;
        this.frameworkName = apiTestRun.frameworkName;
    }
}
