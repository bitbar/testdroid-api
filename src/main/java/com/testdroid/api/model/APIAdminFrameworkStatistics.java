package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIAdminFrameworkStatistics extends APIEntity {

    private String cloudName;

    private Date day;

    private Long frameworkId;

    private APIDevice.OsType osType;

    private String releaseVersion;

    private String frameworkName;

    private APIDeviceSession.State state;

    private Long count;

    private APIDeviceSession.Type type;

    private Long userId;

    private String userEmail;

    private Long deviceModelId;

    private String deviceModelName;

    public APIAdminFrameworkStatistics() {
    }

    public APIAdminFrameworkStatistics(
            Date day, Long frameworkId, APIDevice.OsType osType, String releaseVersion, String frameworkName,
            APIDeviceSession.State state, Long count, APIDeviceSession.Type type, Long userId, String userEmail,
            Long deviceModelId, String deviceModelName) {
        this.day = day;
        this.frameworkId = frameworkId;
        this.osType = osType;
        this.releaseVersion = releaseVersion;
        this.frameworkName = frameworkName;
        this.state = state;
        this.count = count;
        this.type = type;
        this.userId = userId;
        this.userEmail = userEmail;
        this.deviceModelId = deviceModelId;
        this.deviceModelName = deviceModelName;
    }

    public String getCloudName() {
        return cloudName;
    }

    public void setCloudName(String cloudName) {
        this.cloudName = cloudName;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Long getFrameworkId() {
        return frameworkId;
    }

    public void setFrameworkId(Long frameworkId) {
        this.frameworkId = frameworkId;
    }

    public APIDevice.OsType getOsType() {
        return osType;
    }

    public void setOsType(APIDevice.OsType osType) {
        this.osType = osType;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public String getFrameworkName() {
        return frameworkName;
    }

    public void setFrameworkName(String frameworkName) {
        this.frameworkName = frameworkName;
    }

    public APIDeviceSession.State getState() {
        return state;
    }

    public void setState(APIDeviceSession.State state) {
        this.state = state;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public APIDeviceSession.Type getType() {
        return type;
    }

    public void setType(APIDeviceSession.Type type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(Long deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    public String getDeviceModelName() {
        return deviceModelName;
    }

    public void setDeviceModelName(String deviceModelName) {
        this.deviceModelName = deviceModelName;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAdminFrameworkStatistics original = (APIAdminFrameworkStatistics) from;
        cloneBase(from);
        this.cloudName = original.cloudName;
        this.day = original.day;
        this.frameworkId = original.frameworkId;
        this.osType = original.osType;
        this.releaseVersion = original.releaseVersion;
        this.frameworkName = original.frameworkName;
        this.state = original.state;
        this.count = original.count;
        this.type = original.type;
        this.userId = original.userId;
        this.userEmail = original.userEmail;
        this.deviceModelId = original.deviceModelId;
        this.deviceModelName = original.deviceModelName;
    }
}
