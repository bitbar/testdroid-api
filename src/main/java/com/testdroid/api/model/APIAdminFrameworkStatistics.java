package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIAdminFrameworkStatistics extends APIEntity {

    private String cloudName;

    private Date currentDate;

    private Long frameworkId;

    private APIDevice.OsType osType;

    private String releaseVersion;

    private String frameworkName;

    private APIDeviceSession.State state;

    private Long count;

    private APIDeviceSession.Type type;

    public APIAdminFrameworkStatistics() {
    }

    public APIAdminFrameworkStatistics(
            Date currentDate, Long frameworkId, APIDevice.OsType osType, String releaseVersion,
            String frameworkName, APIDeviceSession.State state, Long count, APIDeviceSession.Type type) {
        this.currentDate = currentDate;
        this.frameworkId = frameworkId;
        this.osType = osType;
        this.releaseVersion = releaseVersion;
        this.frameworkName = frameworkName;
        this.state = state;
        this.count = count;
        this.type = type;
    }

    public String getCloudName() {
        return cloudName;
    }

    public void setCloudName(String cloudName) {
        this.cloudName = cloudName;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
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

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAdminFrameworkStatistics original = (APIAdminFrameworkStatistics) from;
        cloneBase(from);
        this.cloudName = original.cloudName;
        this.currentDate = original.currentDate;
        this.frameworkId = original.frameworkId;
        this.osType = original.osType;
        this.releaseVersion = original.releaseVersion;
        this.frameworkName = original.frameworkName;
        this.state = original.state;
        this.count = original.count;
        this.type = original.type;
    }
}
