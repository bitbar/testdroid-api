package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import static com.testdroid.api.dto.MappingKey.ID;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
@XmlRootElement
@JsonIgnoreProperties(value = {ID})
public class APIDeviceStatistic extends APIEntity {

    @XmlType(namespace = "APIDeviceStatistic")
    public enum Mode {
        DEVICE_NAME,
        DEVICE_OS
    }

    private String deviceName;

    private APIDevice.OsType osType;

    private String releaseVersion;

    private Long usageMillis;

    private Long usageCount;

    private Long totalTests;

    private Long passedTests;

    private Long failedTests;

    private Float passedRatio;

    private Float failedRatio;

    private Long failedDevices;

    public APIDeviceStatistic() {
    }

    public APIDeviceStatistic(
            String deviceName, Long usageMillis, Long usageCount, Long totalTests, Long passedTests, Long failedTests,
            Float passedRatio, Float failedRatio, Long failedDevices) {
        this(deviceName, null, null, usageMillis, usageCount, totalTests, passedTests, failedTests,
                passedRatio, failedRatio, failedDevices);
    }

    public APIDeviceStatistic(
            APIDevice.OsType osType, String releaseVersion, Long usageMillis, Long usageCount,
            Long totalTests, Long passedTests, Long failedTests, Float passedRatio, Float failedRatio,
            Long failedDevices) {
        this(null, osType, releaseVersion, usageMillis, usageCount, totalTests, passedTests, failedTests,
                passedRatio, failedRatio, failedDevices);
    }

    private APIDeviceStatistic(
            String deviceName, APIDevice.OsType osType, String releaseVersion, Long usageMillis, Long usageCount,
            Long totalTests, Long passedTests, Long failedTests, Float passedRatio, Float failedRatio,
            Long failedDevices) {
        this.deviceName = deviceName;
        this.osType = osType;
        this.releaseVersion = releaseVersion;
        this.usageMillis = usageMillis;
        this.usageCount = usageCount;
        this.totalTests = totalTests;
        this.passedTests = passedTests;
        this.failedTests = failedTests;
        this.passedRatio = passedRatio;
        this.failedRatio = failedRatio;
        this.failedDevices = failedDevices;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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

    public Long getUsageMillis() {
        return usageMillis;
    }

    public void setUsageMillis(Long usageMillis) {
        this.usageMillis = usageMillis;
    }

    public Long getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(Long usageCount) {
        this.usageCount = usageCount;
    }

    public Long getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(Long totalTests) {
        this.totalTests = totalTests;
    }

    public Long getPassedTests() {
        return passedTests;
    }

    public void setPassedTests(Long passedTests) {
        this.passedTests = passedTests;
    }

    public Long getFailedTests() {
        return failedTests;
    }

    public void setFailedTests(Long failedTests) {
        this.failedTests = failedTests;
    }

    public Long getFailedDevices() {
        return failedDevices;
    }

    public void setFailedDevices(Long failedDevices) {
        this.failedDevices = failedDevices;
    }

    public Float getPassedRatio() {
        return passedRatio;
    }

    public void setPassedRatio(Float passedRatio) {
        this.passedRatio = passedRatio;
    }

    public Float getFailedRatio() {
        return failedRatio;
    }

    public void setFailedRatio(Float failedRatio) {
        this.failedRatio = failedRatio;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        throw new UnsupportedOperationException();
    }
}
