package com.testdroid.api.model;

import com.testdroid.api.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.InputStream;
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

    @XmlType(namespace = "APITestRun", name = "APITestRunZipState")
    public static enum ZipState {
        BLANK,
        PROGRESS,
        READY
    }

    private APITestRunConfig config;

    private Date createTime;

    private String displayName;

    private Float executionRatio;

    private APIFiles files;

    private ZipState logZipState;

    private Integer number;

    private Long projectId;

    private ZipState screenshotZipState;

    private String startedByDisplayName;

    private State state;

    private Float successRatio;

    public APITestRun() {
    }

    public APITestRun(
            Long id, Integer number, Date createTime, String displayName, Float executionRatio,
            Float successRatio, String startedByDisplayName,
            State state, ZipState screenshotZipState, ZipState logZipState, Long projectId) {
        super(id);
        this.number = number;
        this.createTime = createTime;
        this.displayName = displayName;
        this.executionRatio = executionRatio;
        this.successRatio = successRatio;
        this.startedByDisplayName = startedByDisplayName;
        this.state = state;
        this.screenshotZipState = screenshotZipState;
        this.logZipState = logZipState;
        this.projectId = projectId;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ZipState getScreenshotZipState() {
        return screenshotZipState;
    }

    public void setScreenshotZipState(ZipState screenshotZipState) {
        this.screenshotZipState = screenshotZipState;
    }

    public ZipState getLogZipState() {
        return logZipState;
    }

    public void setLogZipState(ZipState logZipState) {
        this.logZipState = logZipState;
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

    private String getScreenshotsZipURI() {
        return createUri(selfURI, "/screenshots.zip");
    }

    private String getLogsZipURI() {
        return createUri(selfURI, "/logs.zip");
    }

    private String getAbortURI() {
        return createUri(selfURI, "/abort");
    }

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
        postResource(getScreenshotsZipURI(), null, null);
    }

    @JsonIgnore
    public InputStream getScreenshotsZip() throws APIException {
        return getFile(getScreenshotsZipURI());
    }

    @JsonIgnore
    public void requestLogsZip() throws APIException {
        postResource(getLogsZipURI(), null, null);
    }

    @JsonIgnore
    public InputStream getLogsZip() throws APIException {
        return getFile(getLogsZipURI());
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
        this.screenshotZipState = apiTestRun.screenshotZipState;
        this.startedByDisplayName = apiTestRun.startedByDisplayName;
        this.state = apiTestRun.state;
        this.successRatio = apiTestRun.successRatio;
        this.projectId = apiTestRun.projectId;
    }
}
