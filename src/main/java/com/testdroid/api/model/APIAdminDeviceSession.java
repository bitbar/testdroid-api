package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
public class APIAdminDeviceSession extends APIEntity {

    private Date createTime;

    private APIDeviceSessionStep.Type currentStepType;

    @Deprecated
    private Long deviceTime;

    private Date endTime;

    private String errorMessage;

    private Integer priority;

    private Long projectId;

    private String projectName;

    private Date startTime;

    private String startedByDisplayName;

    private APIDeviceSession.State state;

    private Long testRunId;

    private String testRunName;

    private String retriedFailReason;

    private Long duration;

    public APIAdminDeviceSession() {

    }

    @SuppressWarnings("squid:S107")
    public APIAdminDeviceSession(
            Long id, LocalDateTime createTime, LocalDateTime startTime, LocalDateTime endTime,
            String startedByDisplayName, Long projectId, String projectName, Long testRunId, String testRunName,
            APIDeviceSession.State state, Integer priority, Long duration, APIDeviceSessionStep.Type currentStepType,
            String retriedFailReason) {
        super(id);
        this.createTime = TimeConverter.toDate(createTime);
        this.startTime = TimeConverter.toDate(startTime);
        this.endTime = TimeConverter.toDate(endTime);
        this.startedByDisplayName = startedByDisplayName;
        this.projectId = projectId;
        this.projectName = projectName;
        this.testRunId = testRunId;
        this.testRunName = testRunName;
        this.state = state;
        this.priority = priority;
        this.deviceTime = duration;
        this.duration = duration;
        this.currentStepType = currentStepType;
        this.retriedFailReason = retriedFailReason;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public APIDeviceSessionStep.Type getCurrentStepType() {
        return currentStepType;
    }

    public void setCurrentStepType(APIDeviceSessionStep.Type currentStepType) {
        this.currentStepType = currentStepType;
    }

    public String getRetriedFailReason() {
        return retriedFailReason;
    }

    public void setRetriedFailReason(String retriedFailReason) {
        this.retriedFailReason = retriedFailReason;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAdminDeviceSession apiAdminDeviceSession = (APIAdminDeviceSession) from;
        cloneBase(from);
        this.createTime = apiAdminDeviceSession.createTime;
        this.startTime = apiAdminDeviceSession.startTime;
        this.endTime = apiAdminDeviceSession.endTime;
        this.startedByDisplayName = apiAdminDeviceSession.startedByDisplayName;
        this.projectName = apiAdminDeviceSession.projectName;
        this.testRunName = apiAdminDeviceSession.testRunName;
        this.errorMessage = apiAdminDeviceSession.errorMessage;
        this.state = apiAdminDeviceSession.state;
        this.priority = apiAdminDeviceSession.priority;
        this.deviceTime = apiAdminDeviceSession.deviceTime;
        this.duration = apiAdminDeviceSession.duration;
        this.currentStepType = apiAdminDeviceSession.currentStepType;
        this.retriedFailReason = apiAdminDeviceSession.retriedFailReason;
    }
}
