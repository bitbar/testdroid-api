package com.testdroid.api.model.devicetime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
public class APIDeviceTimeStepTimeReportEntry extends APIBasicDeviceTime {

    private Long userId;

    private String userEmail;

    private Long deviceModelId;

    private String deviceModelName;

    private String projectId;

    private String projectName;

    private Long preparationTime;

    private Long waitingTime;

    private Date day;

    public APIDeviceTimeStepTimeReportEntry() {

    }

    public APIDeviceTimeStepTimeReportEntry(
            Date day,
            Long deviceTime, Long userId, String userEmail, Long deviceModelId, String deviceModelName,
            String projectId, String projectName, Long preparationTime, Long waitingTime) {
        super(deviceTime);
        this.day = day;
        this.userId = userId;
        this.userEmail = userEmail;
        this.deviceModelId = deviceModelId;
        this.deviceModelName = deviceModelName;
        this.projectId = projectId;
        this.projectName = projectName;
        this.preparationTime = preparationTime;
        this.waitingTime = waitingTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getDeviceModelName() {
        return deviceModelName;
    }

    public String getProjectName() {
        return projectName;
    }

    public Date getDay() {
        return day;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setDeviceModelName(String deviceModelName) {
        this.deviceModelName = deviceModelName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Long getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Long preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setWaitingTime(Long waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Long getWaitingTime() {
        return waitingTime;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceTimeStepTimeReportEntry apiDeviceTimeReportEntry = (APIDeviceTimeStepTimeReportEntry) from;
        super.clone(from);
        this.day = apiDeviceTimeReportEntry.day;
        this.userId = apiDeviceTimeReportEntry.userId;
        this.userEmail = apiDeviceTimeReportEntry.userEmail;
        this.deviceModelId = apiDeviceTimeReportEntry.deviceModelId;
        this.deviceModelName = apiDeviceTimeReportEntry.deviceModelName;
        this.projectId = apiDeviceTimeReportEntry.projectId;
        this.projectName = apiDeviceTimeReportEntry.projectName;
        this.preparationTime = apiDeviceTimeReportEntry.preparationTime;
        this.waitingTime = apiDeviceTimeReportEntry.waitingTime;
    }
}
