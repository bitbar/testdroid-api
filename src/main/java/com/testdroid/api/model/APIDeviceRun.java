package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import static com.testdroid.api.APIEntity.createUri;
import com.testdroid.api.APIException;
import com.testdroid.api.APIList;
import com.testdroid.api.APIListResource;
import com.testdroid.api.APIQueryBuilder;
import com.testdroid.api.APISort;
import java.io.InputStream;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APIDeviceRun extends APIEntity {
    
    @XmlType(namespace = "APIDeviceRun")
    public static enum RunStatus { WAITING, RUNNING, EXCLUDED, WARNING, FAILED, SUCCEEDED }
    
    private Date runTime;
    private APIDevice device;
    private Integer testCaseSuccessNo;
    private Integer testCaseAllNo;
    private Integer testCaseCount;
    private APISoftwareVersion softwareVersion;
    private Date createTime;
    private Date startTime; 
    private APIDeviceRunState currentState;
    private APIDeviceRunState interruptedByState;
    private RunStatus runStatus;

    public APIDeviceRun() {}
    public APIDeviceRun(Long id, Date runTime, APIDevice device, Integer testCaseSuccessNo, Integer testCaseAllNo, Integer testCaseCount, APISoftwareVersion softwareVersion, 
            Date createTime, Date startTime, APIDeviceRunState currentState, APIDeviceRunState interruptedByState, RunStatus runStatus) {
        super(id);
        this.runTime = runTime;
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
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
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
    
    private String getLogsURI() { return createUri(selfURI, "/logs"); };
    private String getScreenshotsURI() { return createUri(selfURI, "/screenshots"); };
    private String getJunitURI() { return createUri(selfURI, "/junit.xml"); };
    private String getPerformanceURI() { return createUri(selfURI, "/performance"); };
    private String getResultDataZipURI() { return createUri(selfURI, "/result-data.zip"); };
    private String getStatesURI() { return createUri(selfURI, "/states"); };
    
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
        return getListResource(getStatesURI(), APIDeviceRunState.class);
    }
    
    /**
     * @since 1.3.34
     * @param queryBuilder
     * @return
     * @throws APIException 
     */
    @JsonIgnore
    public APIListResource<APIDeviceRunState> getDeviceRunStatesResource(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getStatesURI(), queryBuilder, APIDeviceRunState.class);
    }
    
    /**
     * @deprecated 
     * @param offset
     * @param limit
     * @param search
     * @param sort
     * @return
     * @throws APIException 
     */
    @JsonIgnore
    public APIListResource<APIDeviceRunState> getDeviceRunStatesResource(long offset, long limit, String search, APISort sort) throws APIException {
        return getListResource(getStatesURI(), offset, limit, search, sort, APIDeviceRunState.class);
    }
    
    @JsonIgnore
    public APIListResource<APIScreenshot> getScreenshotsResource() throws APIException {
        return getListResource(getScreenshotsURI(), APIScreenshot.class);
    }
    
    /**
     * @since 1.3.34
     * @param queryBuilder
     * @return
     * @throws APIException 
     */
    @JsonIgnore
    public APIListResource<APIScreenshot> getScreenshotsResource(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getScreenshotsURI(), queryBuilder, APIScreenshot.class);
    }
    
    /**
     * @deprecated 
     * @param offset
     * @param limit
     * @param search
     * @param sort
     * @return
     * @throws APIException 
     */
    @JsonIgnore
    public APIListResource<APIScreenshot> getScreenshotsResource(long offset, long limit, String search, APISort sort) throws APIException {
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
        this.createTime = apiDeviceRun.createTime;
        this.currentState = apiDeviceRun.currentState;
        this.device = apiDeviceRun.device;
        this.interruptedByState = apiDeviceRun.interruptedByState;
        this.runStatus = apiDeviceRun.runStatus;
        this.runTime = apiDeviceRun.runTime;
        this.softwareVersion = apiDeviceRun.softwareVersion;
        this.startTime = apiDeviceRun.startTime;
        this.testCaseAllNo = apiDeviceRun.testCaseAllNo;
        this.testCaseCount = apiDeviceRun.testCaseCount;
        this.testCaseSuccessNo = apiDeviceRun.testCaseSuccessNo;
    }
    
}
