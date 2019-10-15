package com.testdroid.api.model.devicetime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.testdroid.api.APIEntity;
import com.testdroid.api.model.APIDevice;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class APIDeviceTimeReportEntry extends APIBasicDeviceTime {

    private Long userId;

    private String userEmail;

    private Long deviceModelId;

    private String deviceModelName;

    private String projectId;

    private String projectName;

    private APIDevice.OsType osType;

    private Date day;

    APIDeviceTimeReportEntry() {

    }

    APIDeviceTimeReportEntry(
            Date day,
            Long deviceTime, Long userId, String userEmail, Long deviceModelId, String deviceModelName,
            String projectId, String projectName, APIDevice.OsType osType) {
        super(deviceTime);
        this.day = day;
        this.userId = userId;
        this.userEmail = userEmail;
        this.deviceModelId = deviceModelId;
        this.deviceModelName = deviceModelName;
        this.projectId = projectId;
        this.projectName = projectName;
        this.osType = osType;
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

    public APIDevice.OsType getOsType() {
        return osType;
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

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceTimeReportEntry apiDeviceTimeReportEntry = (APIDeviceTimeReportEntry) from;
        super.clone(from);
        this.day = apiDeviceTimeReportEntry.day;
        this.userId = apiDeviceTimeReportEntry.userId;
        this.userEmail = apiDeviceTimeReportEntry.userEmail;
        this.deviceModelId = apiDeviceTimeReportEntry.deviceModelId;
        this.deviceModelName = apiDeviceTimeReportEntry.deviceModelName;
        this.projectId = apiDeviceTimeReportEntry.projectId;
        this.projectName = apiDeviceTimeReportEntry.projectName;
        this.osType = apiDeviceTimeReportEntry.osType;
    }
}
