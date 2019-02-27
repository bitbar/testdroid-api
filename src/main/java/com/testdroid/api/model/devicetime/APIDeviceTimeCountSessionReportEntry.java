package com.testdroid.api.model.devicetime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class APIDeviceTimeCountSessionReportEntry extends APIBasicDeviceTime {

    private Long userId;

    private String userEmail;

    private Long deviceModelId;

    private String deviceModelName;

    private String projectId;

    private String projectName;

    private Long countTestRuns;

    private Long countDeviceSessions;

    private Date day;

    public APIDeviceTimeCountSessionReportEntry() {

    }

    public APIDeviceTimeCountSessionReportEntry(
            Date day,
            Long deviceTime, Long userId, String userEmail, Long deviceModelId, String deviceModelName,
            String projectId, String projectName, Long countTestRuns, Long countDeviceSessions) {
        super(deviceTime);
        this.day = day;
        this.userId = userId;
        this.userEmail = userEmail;
        this.deviceModelId = deviceModelId;
        this.deviceModelName = deviceModelName;
        this.projectId = projectId;
        this.projectName = projectName;
        this.countTestRuns = countTestRuns;
        this.countDeviceSessions = countDeviceSessions;
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

    public Long getCountTestRuns() {
        return countTestRuns;
    }

    public Date getDay() {
        return day;
    }

    public Long getCountDeviceSessions() {
        return countDeviceSessions;
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

    public void setCountDeviceSessions(Long countDeviceSessions) {
        this.countDeviceSessions = countDeviceSessions;
    }

    public void setCountTestRuns(Long countTestRuns) {
        this.countTestRuns = countTestRuns;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceTimeCountSessionReportEntry apiDeviceTimeCountSessionReportEntry =
                (APIDeviceTimeCountSessionReportEntry) from;
        super.clone(from);
        this.day = apiDeviceTimeCountSessionReportEntry.day;
        this.userId = apiDeviceTimeCountSessionReportEntry.userId;
        this.userEmail = apiDeviceTimeCountSessionReportEntry.userEmail;
        this.deviceModelId = apiDeviceTimeCountSessionReportEntry.deviceModelId;
        this.deviceModelName = apiDeviceTimeCountSessionReportEntry.deviceModelName;
        this.projectId = apiDeviceTimeCountSessionReportEntry.projectId;
        this.projectName = apiDeviceTimeCountSessionReportEntry.projectName;
        this.countTestRuns = apiDeviceTimeCountSessionReportEntry.countTestRuns;
        this.countDeviceSessions = apiDeviceTimeCountSessionReportEntry.countDeviceSessions;
    }
}
