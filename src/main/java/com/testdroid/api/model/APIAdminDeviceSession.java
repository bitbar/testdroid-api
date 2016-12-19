package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
@XmlRootElement
public class APIAdminDeviceSession extends APIEntity {

    private Date startTime;

    private Date endTime;

    private String startedByDisplayName;

    private Long projectId;

    private String projectName;

    private Long testRunId;

    private String testRunName;

    private String errorMessage;

    private APIDeviceSession.State state;

    private Integer priority;

    private Boolean billable;

    private Long deviceTime;

    public APIAdminDeviceSession() {

    }

    public APIAdminDeviceSession(Long id, Date startTime, Date endTime, String startedByDisplayName, Long projectId,
            String projectName, Long testRunId, String testRunName, String errorMessage, APIDeviceSession.State state,
            Integer priority, Boolean billable, Long deviceTime) {
        super(id);
        this.startTime = startTime;
        this.endTime = endTime;
        this.startedByDisplayName = startedByDisplayName;
        this.projectId = projectId;
        this.projectName = projectName;
        this.testRunId = testRunId;
        this.testRunName = testRunName;
        this.errorMessage = errorMessage;
        this.state = state;
        this.priority = priority;
        this.billable = billable;
        this.deviceTime = deviceTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStartedByDisplayName() {
        return startedByDisplayName;
    }

    public void setStartedByDisplayName(String startedByDisplayName) {
        this.startedByDisplayName = startedByDisplayName;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public APIDeviceSession.State getState() {
        return state;
    }

    public void setState(APIDeviceSession.State state) {
        this.state = state;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(Long testRunId) {
        this.testRunId = testRunId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getBillable() {
        return billable;
    }

    public void setBillable(Boolean billable) {
        this.billable = billable;
    }

    public Long getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(Long deviceTime) {
        this.deviceTime = deviceTime;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAdminDeviceSession apiAdminDeviceSession = (APIAdminDeviceSession) from;
        cloneBase(from);
        this.startTime = apiAdminDeviceSession.startTime;
        this.endTime = apiAdminDeviceSession.endTime;
        this.startedByDisplayName = apiAdminDeviceSession.startedByDisplayName;
        this.projectName = apiAdminDeviceSession.projectName;
        this.testRunName = apiAdminDeviceSession.testRunName;
        this.errorMessage = apiAdminDeviceSession.errorMessage;
        this.state = apiAdminDeviceSession.state;
        this.priority = apiAdminDeviceSession.priority;
        this.billable = apiAdminDeviceSession.billable;
        this.deviceTime = apiAdminDeviceSession.deviceTime;
    }
}
