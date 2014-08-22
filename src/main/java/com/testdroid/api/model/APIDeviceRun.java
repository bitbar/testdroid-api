package com.testdroid.api.model;

import com.testdroid.api.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.InputStream;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIDeviceRun extends APIEntity {

    @XmlType(namespace = "APIDeviceRun")
    public static enum RunStatus {
        WAITING,
        RUNNING,
        EXCLUDED,
        WARNING,
        FAILED,
        SUCCEEDED
    }

    private Date createTime;

    private APIDeviceRunState currentState;

    private APIDevice device;

    private String deviceName;

    private String deviceSerialId;

    private Long deviceSessionId;

    private APIDeviceRunState interruptedByState;

    private Long launchAppDuration;

    private RunStatus runStatus;

    private APISoftwareVersion softwareVersion;

    private Date startTime;

    private Integer testCaseAllNo;

    private Integer testCaseCount;

    private Integer testCaseSuccessNo;

    public APIDeviceRun() {
    }

    public APIDeviceRun(
            Long id, Long deviceSessionId, Long launchAppDuration, APIDevice device, Integer testCaseSuccessNo,
            Integer testCaseAllNo, Integer testCaseCount, APISoftwareVersion softwareVersion, Date createTime,
            Date startTime, APIDeviceRunState currentState, APIDeviceRunState interruptedByState, RunStatus runStatus,
            String deviceName, String deviceSerialId) {
        super(id);
        this.deviceSessionId = deviceSessionId;
        this.launchAppDuration = launchAppDuration;
        this.device = device;
        this.testCaseSuccessNo = testCaseSuccessNo;
        this.testCaseAllNo = testCaseAllNo;
        this.testCaseCount = testCaseCount;
        this.softwareVersion = softwareVersion;
        this.createTime = createTime;
        this.startTime = startTime;
        this.currentState = currentState;
        this.interruptedByState = interruptedByState;
        this.runStatus = runStatus;
        this.deviceName = deviceName;
        this.deviceSerialId = deviceSerialId;
    }

    public Long getDeviceSessionId() {
        return deviceSessionId;
    }

    public void setDeviceSessionId(Long deviceSessionId) {
        this.deviceSessionId = deviceSessionId;
    }

    public Long getLaunchAppDuration() {
        return launchAppDuration;
    }

    public void setLaunchAppDuration(Long launchAppDuration) {
        this.launchAppDuration = launchAppDuration;
    }

    public APIDevice getDevice() {
        return device;
    }

    public void setDevice(APIDevice device) {
        this.device = device;
    }

    public Integer getTestCaseSuccessNo() {
        return testCaseSuccessNo;
    }

    public void setTestCaseSuccessNo(Integer testCaseSuccessNo) {
        this.testCaseSuccessNo = testCaseSuccessNo;
    }

    public Integer getTestCaseAllNo() {
        return testCaseAllNo;
    }

    public void setTestCaseAllNo(Integer testCaseAllNo) {
        this.testCaseAllNo = testCaseAllNo;
    }

    public Integer getTestCaseCount() {
        return testCaseCount;
    }

    public void setTestCaseCount(Integer testCaseCount) {
        this.testCaseCount = testCaseCount;
    }

    public APISoftwareVersion getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(APISoftwareVersion softwareVersion) {
        this.softwareVersion = softwareVersion;
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

    public APIDeviceRunState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(APIDeviceRunState currentState) {
        this.currentState = currentState;
    }

    public APIDeviceRunState getInterruptedByState() {
        return interruptedByState;
    }

    public void setInterruptedByState(APIDeviceRunState interruptedByState) {
        this.interruptedByState = interruptedByState;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceSerialId() {
        return deviceSerialId;
    }

    public void setDeviceSerialId(String deviceSerialId) {
        this.deviceSerialId = deviceSerialId;
    }

    private String getLogsURI() {
        return createUri(selfURI, "/logs");
    }

    private String getScreenshotsURI() {
        return createUri(selfURI, "/screenshots");
    }

    private String getJunitURI() {
        return createUri(selfURI, "/junit.xml");
    }

    private String getPerformanceURI() {
        return createUri(selfURI, "/performance");
    }

    private String getResultDataZipURI() {
        return createUri(selfURI, "/result-data.zip");
    }

    private String getStatesURI() {
        return createUri(selfURI, "/states");
    }

    @JsonIgnore
    public InputStream getLogs() throws APIException {
        return client.get(getLogsURI());
    }

    @JsonIgnore
    public InputStream getJunitXml() throws APIException {
        return client.get(getJunitURI());
    }

    @JsonIgnore
    public InputStream getPerformanceData() throws APIException {
        return client.get(getPerformanceURI());
    }

    @JsonIgnore
    public InputStream getResultDataZip() throws APIException {
        return client.get(getResultDataZipURI());
    }

    @JsonIgnore
    public APIListResource<APIDeviceRunState> getDeviceRunStatesResource() throws APIException {
        return getListResource(getStatesURI());
    }

    /**
     * @param queryBuilder
     * @return
     * @throws APIException
     * @since 1.3.34
     */
    @JsonIgnore
    public APIListResource<APIDeviceRunState> getDeviceRunStatesResource(APIQueryBuilder queryBuilder)
            throws APIException {
        return getListResource(getStatesURI(), queryBuilder);
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
    public APIListResource<APIDeviceRunState> getDeviceRunStatesResource(
            long offset, long limit, String search,
            APISort sort) throws APIException {
        return getListResource(getStatesURI(), offset, limit, search, sort, APIDeviceRunState.class);
    }

    @JsonIgnore
    public APIListResource<APIScreenshot> getScreenshotsResource() throws APIException {
        return getListResource(getScreenshotsURI());
    }

    /**
     * @param queryBuilder
     * @return
     * @throws APIException
     * @since 1.3.34
     */
    @JsonIgnore
    public APIListResource<APIScreenshot> getScreenshotsResource(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getScreenshotsURI(), queryBuilder);
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
    public APIListResource<APIScreenshot> getScreenshotsResource(long offset, long limit, String search, APISort sort)
            throws APIException {
        return getListResource(getScreenshotsURI(), offset, limit, search, sort, APIScreenshot.class);
    }

    public RunStatus getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(RunStatus runStatus) {
        this.runStatus = runStatus;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceRun apiDeviceRun = (APIDeviceRun) from;
        cloneBase(from);
        this.deviceSessionId = apiDeviceRun.deviceSessionId;
        this.launchAppDuration = apiDeviceRun.launchAppDuration;
        this.createTime = apiDeviceRun.createTime;
        this.currentState = apiDeviceRun.currentState;
        this.device = apiDeviceRun.device;
        this.interruptedByState = apiDeviceRun.interruptedByState;
        this.runStatus = apiDeviceRun.runStatus;
        this.softwareVersion = apiDeviceRun.softwareVersion;
        this.startTime = apiDeviceRun.startTime;
        this.testCaseAllNo = apiDeviceRun.testCaseAllNo;
        this.testCaseCount = apiDeviceRun.testCaseCount;
        this.testCaseSuccessNo = apiDeviceRun.testCaseSuccessNo;
    }

}
