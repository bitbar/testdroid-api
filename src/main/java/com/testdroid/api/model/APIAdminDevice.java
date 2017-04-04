package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APIAdminDevice extends APIEntity {

    @XmlType(namespace = "APIAdminDevice")
    public static enum InitStep {
        SKIP,
        REBOOT,
        REBOOT_WITH_UNLOCK,
        UNLOCK_ONLY
    }

    @XmlType(namespace = "APIAdminDevice")
    public static enum State {
        ONLINE,
        OFFLINE
    }

    private APICluster cluster;

    private Long deviceModelId;

    private String deviceModelName;

    private boolean enabled;

    private String fingerprint;

    private InitStep initStep;

    private String name;

    private String serialId;

    private APISoftwareVersion softwareVersion;

    private State state;

    private Date stateTime;

    private Date lastOnlineTime;

    private String unlockGesture;

    private String ipAddress;

    private Long accountId;

    private String mainUserEmail;

    private boolean locked;

    public APIAdminDevice() {
    }

    public APIAdminDevice(
            Long id, String name, boolean enabled, String serialId, String fingerprint, String unlockGesture,
            APISoftwareVersion softwareVersion, Long deviceModelId, String deviceModelName, State state,
            Date stateTime, InitStep initStep, String ipAddress, APICluster cluster, Date lastOnlineTime,
            Long accountId, String mainUserEmail, Boolean locked) {
        super(id);
        this.name = name;
        this.enabled = enabled;
        this.serialId = serialId;
        this.fingerprint = fingerprint;
        this.unlockGesture = unlockGesture;
        this.softwareVersion = softwareVersion;
        this.deviceModelId = deviceModelId;
        this.deviceModelName = deviceModelName;
        this.state = state;
        this.stateTime = stateTime;
        this.initStep = initStep;
        this.ipAddress = ipAddress;
        this.cluster = cluster;
        this.lastOnlineTime = lastOnlineTime;
        this.accountId = accountId;
        this.mainUserEmail = mainUserEmail;
        this.locked = locked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getUnlockGesture() {
        return unlockGesture;
    }

    public void setUnlockGesture(String unlockGesture) {
        this.unlockGesture = unlockGesture;
    }

    public APISoftwareVersion getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(APISoftwareVersion softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public Long getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(Long deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getStateTime() {
        return stateTime;
    }

    public void setStateTime(Date stateTime) {
        this.stateTime = stateTime;
    }

    public InitStep getInitStep() {
        return initStep;
    }

    public void setInitStep(InitStep initStep) {
        this.initStep = initStep;
    }

    public APICluster getCluster() {
        return cluster;
    }

    public void setCluster(APICluster cluster) {
        this.cluster = cluster;
    }

    public String getDeviceModelName() {
        return deviceModelName;
    }

    public void setDeviceModelName(String deviceModelName) {
        this.deviceModelName = deviceModelName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Date lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getMainUserEmail() {
        return mainUserEmail;
    }

    public void setMainUserEmail(String mainUserEmail) {
        this.mainUserEmail = mainUserEmail;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAdminDevice adminDevice = (APIAdminDevice) from;
        cloneBase(from);
        this.cluster = adminDevice.cluster;
        this.deviceModelId = adminDevice.deviceModelId;
        this.deviceModelName = adminDevice.deviceModelName;
        this.enabled = adminDevice.enabled;
        this.fingerprint = adminDevice.fingerprint;
        this.initStep = adminDevice.initStep;
        this.name = adminDevice.name;
        this.serialId = adminDevice.serialId;
        this.softwareVersion = adminDevice.softwareVersion;
        this.state = adminDevice.state;
        this.stateTime = adminDevice.stateTime;
        this.unlockGesture = adminDevice.unlockGesture;
        this.ipAddress = adminDevice.ipAddress;
        this.lastOnlineTime = adminDevice.lastOnlineTime;
        this.accountId = adminDevice.accountId;
        this.mainUserEmail = adminDevice.mainUserEmail;
    }
}
