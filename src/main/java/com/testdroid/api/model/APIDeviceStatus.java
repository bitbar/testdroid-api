package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Roman Kierzkowski <roman.kierzkowski@bitbar.com>
 * @author Krzysztof Fonał <krzysztof.fonal@bitbar.com>
 */
@XmlRootElement
public class APIDeviceStatus extends APIEntity {

    private Boolean alarmOn;

    private Boolean aslOn;

    private Integer batteryLevel;

    private Boolean bluetoothOn;

    private Long clusterId;

    private String clusterName;

    private Long deviceId;

    private String deviceName;

    private Long deviceTime;

    private String deviceTimeZone;

    private String emailAccount;

    private Long externalStorage;

    private Boolean flashOn;

    private Long internalStorage;

    private Boolean internetAccess;

    private Boolean locationServiceOn;

    private Boolean mockLocationOn;

    private Boolean monitoringOn;

    private Boolean screenLocked;

    private Boolean sdcardPresent;

    private String ssid;

    private APIAdminDevice.ComplexState state;

    private String tdsVersion;

    private Boolean testExecuting;

    private Boolean tetheringOn;

    private Date updateTime;

    public APIDeviceStatus() {
    }

    public APIDeviceStatus(
            Long id, Long deviceId, String deviceName, APIAdminDevice.ComplexState state, Long clusterId,
            String clusterName, Date updateTime, Boolean internetAccess, Boolean monitoringOn, Boolean testExecuting,
            String ssid, Boolean flashOn, Boolean alarmOn, Boolean aslOn, Integer batteryLevel, Long deviceTime,
            String deviceTimeZone, Boolean screenLocked, Boolean mockLocationOn, Boolean locationServiceOn,
            Boolean bluetoothOn, Boolean sdcardPresent, Boolean tetheringOn, String tdsVersion, Long internalStorage,
            Long externalStorage, String emailAccount) {
        super(id);
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.state = state;
        this.clusterId = clusterId;
        this.clusterName = clusterName;
        this.updateTime = updateTime;
        this.internetAccess = internetAccess;
        this.monitoringOn = monitoringOn;
        this.ssid = ssid;
        this.testExecuting = testExecuting;
        this.flashOn = flashOn;
        this.alarmOn = alarmOn;
        this.aslOn = aslOn;
        this.batteryLevel = batteryLevel;
        this.deviceTime = deviceTime;
        this.deviceTimeZone = deviceTimeZone;
        this.screenLocked = screenLocked;
        this.mockLocationOn = mockLocationOn;
        this.locationServiceOn = locationServiceOn;
        this.bluetoothOn = bluetoothOn;
        this.sdcardPresent = sdcardPresent;
        this.tetheringOn = tetheringOn;
        this.tdsVersion = tdsVersion;
        this.internalStorage = internalStorage;
        this.externalStorage = externalStorage;
        this.emailAccount = emailAccount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date reportTime) {
        this.updateTime = reportTime;
    }

    public Boolean isInternetAccess() {
        return internetAccess;
    }

    public void setInternetAccess(Boolean internetAccess) {
        this.internetAccess = internetAccess;
    }

    public Boolean isMonitoringOn() {
        return monitoringOn;
    }

    public void setMonitoringOn(Boolean monitoringOn) {
        this.monitoringOn = monitoringOn;
    }

    public Boolean isTestExecuting() {
        return testExecuting;
    }

    public void setTestExecuting(Boolean executingTest) {
        this.testExecuting = executingTest;
    }

    public Boolean isFlashOn() {
        return flashOn;
    }

    public void setFlashOn(Boolean flashOn) {
        this.flashOn = flashOn;
    }

    public Boolean isAlarmOn() {
        return alarmOn;
    }

    public void setAlarmOn(Boolean alarmOn) {
        this.alarmOn = alarmOn;
    }

    public Boolean isAslOn() {
        return aslOn;
    }

    public void setAslOn(Boolean aslOn) {
        this.aslOn = aslOn;
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Long getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(Long deviceTime) {
        this.deviceTime = deviceTime;
    }

    public String getDeviceTimeZone() {
        return deviceTimeZone;
    }

    public void setDeviceTimeZone(String deviceTimeZone) {
        this.deviceTimeZone = deviceTimeZone;
    }

    public Boolean isScreenLocked() {
        return screenLocked;
    }

    public void setScreenLocked(Boolean screenLocked) {
        this.screenLocked = screenLocked;
    }

    public Boolean isMockLocationOn() {
        return mockLocationOn;
    }

    public void setMockLocationOn(Boolean mockLocationOn) {
        this.mockLocationOn = mockLocationOn;
    }

    public Boolean isLocationServiceOn() {
        return locationServiceOn;
    }

    public void setLocationServiceOn(Boolean locationServiceOn) {
        this.locationServiceOn = locationServiceOn;
    }

    public Boolean isBluetoothOn() {
        return bluetoothOn;
    }

    public void setBluetoothOn(Boolean bluetoothOn) {
        this.bluetoothOn = bluetoothOn;
    }

    public Boolean isSdcardPresent() {
        return sdcardPresent;
    }

    public void setSdcardPresent(Boolean sdcardPresent) {
        this.sdcardPresent = sdcardPresent;
    }

    public Boolean isTetheringOn() {
        return tetheringOn;
    }

    public void setTetheringOn(Boolean tetheringOn) {
        this.tetheringOn = tetheringOn;
    }

    public String getTdsVersion() {
        return tdsVersion;
    }

    public void setTdsVersion(String tdsVersion) {
        this.tdsVersion = tdsVersion;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public Long getExternalStorage() {
        return externalStorage;
    }

    public void setExternalStorage(Long externalStorage) {
        this.externalStorage = externalStorage;
    }

    public Long getInternalStorage() {
        return internalStorage;
    }

    public void setInternalStorage(Long internalStorage) {
        this.internalStorage = internalStorage;
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getClusterId() {
        return clusterId;
    }

    public void setClusterId(Long clusterId) {
        this.clusterId = clusterId;
    }

    public APIAdminDevice.ComplexState getState() {
        return state;
    }

    public void setState(APIAdminDevice.ComplexState state) {
        this.state = state;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceStatus apiDeviceStatus = (APIDeviceStatus) from;
        cloneBase(from);
        this.deviceId = apiDeviceStatus.deviceId;
        this.deviceName = apiDeviceStatus.deviceName;
        this.state = apiDeviceStatus.state;
        this.clusterId = apiDeviceStatus.clusterId;
        this.clusterName = apiDeviceStatus.clusterName;
        this.updateTime = apiDeviceStatus.updateTime;
        this.internetAccess = apiDeviceStatus.internetAccess;
        this.monitoringOn = apiDeviceStatus.monitoringOn;
        this.testExecuting = apiDeviceStatus.testExecuting;
        this.ssid = apiDeviceStatus.ssid;
        this.flashOn = apiDeviceStatus.flashOn;
        this.alarmOn = apiDeviceStatus.alarmOn;
        this.aslOn = apiDeviceStatus.aslOn;
        this.batteryLevel = apiDeviceStatus.batteryLevel;
        this.deviceTime = apiDeviceStatus.deviceTime;
        this.deviceTimeZone = apiDeviceStatus.deviceTimeZone;
        this.screenLocked = apiDeviceStatus.screenLocked;
        this.mockLocationOn = apiDeviceStatus.mockLocationOn;
        this.locationServiceOn = apiDeviceStatus.locationServiceOn;
        this.bluetoothOn = apiDeviceStatus.bluetoothOn;
        this.sdcardPresent = apiDeviceStatus.sdcardPresent;
        this.tetheringOn = apiDeviceStatus.tetheringOn;
        this.tdsVersion = apiDeviceStatus.tdsVersion;
        this.internalStorage = apiDeviceStatus.internalStorage;
        this.externalStorage = apiDeviceStatus.externalStorage;
        this.emailAccount = apiDeviceStatus.emailAccount;
    }
}
