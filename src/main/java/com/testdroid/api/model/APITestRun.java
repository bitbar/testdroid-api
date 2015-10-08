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

    protected Float executionRatio;

    protected APIFiles files;

    protected Integer number;

    protected Long projectId;

    protected String startedByDisplayName;

    protected State state;

    protected Float successRatio;

    protected Long screenshotsFileId;

    protected Long logsFileId;

    public APITestRun() {
    }

    public APITestRun(
            Long id, Integer number, Date createTime, String displayName, Float executionRatio,
            Float successRatio, String startedByDisplayName, State state, Long projectId, Long screenshotsFileId,
            Long logsFileId) {
        super(id);
        this.number = number;
        this.createTime = createTime;
        this.displayName = displayName;
        this.executionRatio = executionRatio;
        this.successRatio = successRatio;
        this.startedByDisplayName = startedByDisplayName;
        this.state = state;
        this.projectId = projectId;
        this.screenshotsFileId = screenshotsFileId;
        this.logsFileId = logsFileId;
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

    private String getRequestScreenshotsZipURI() {
        return createUri(selfURI, "/screenshots.zip");
    }

    private String getRequestLogsZipURI() {
        return createUri(selfURI, "/logs.zip");
    }

    private String getAbortURI() {
        return createUri(selfURI, "/abort");
    }

    private String getLogsZipURI() { return String.format("/files/%s", logsFileId); }

    private String getScreenshotsZipURI() { return String.format("/files/%s", screenshotsFileId); }

    /**
     * Returns APIFiles entity about files uploaded to this project.
     * Depending on <code>type</code> it may be any subclass of <code>APIFiles</code> returned.
     */
    @JsonIgnore
    public <T extends APIFiles> T getFiles(Class<T> clazz) throws APIException {
        if (files == null) {
            files = getResource(getFilesURI(), clazz).getEntity();
        }
        return (T) files;
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
        this.files = apiTestRun.files;
        this.number = apiTestRun.number;
        this.startedByDisplayName = apiTestRun.startedByDisplayName;
        this.state = apiTestRun.state;
        this.successRatio = apiTestRun.successRatio;
        this.projectId = apiTestRun.projectId;
        this.screenshotsFileId = apiTestRun.screenshotsFileId;
        this.logsFileId = apiTestRun.logsFileId;
    }
}
