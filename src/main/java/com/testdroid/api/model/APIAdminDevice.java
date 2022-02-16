package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;
import org.apache.commons.lang3.tuple.Pair;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APIAdminDevice extends APIEntity {

    @XmlType(namespace = "APIAdminDevice")
    public enum InitStep {
        SKIP,
        REBOOT,
        REBOOT_WITH_UNLOCK,
        UNLOCK_ONLY
    }

    @XmlType(namespace = "APIAdminDevice")
    public enum State {
        ONLINE,
        OFFLINE
    }

    @XmlType(namespace = "APIAdminDevice")
    public enum SubState {
        CLEANING,
        DIRTY,
        FREE,
        TESTING
    }

    @XmlType(namespace = "APIAdminDevice")
    public enum ComplexState {
        OFFLINE_CLEANING(State.OFFLINE, SubState.CLEANING),
        OFFLINE_DIRTY(State.OFFLINE, SubState.DIRTY),
        OFFLINE_FREE(State.OFFLINE, SubState.FREE),
        OFFLINE_TESTING(State.OFFLINE, SubState.TESTING),
        ONLINE_CLEANING(State.ONLINE, SubState.CLEANING),
        ONLINE_DIRTY(State.ONLINE, SubState.DIRTY),
        ONLINE_FREE(State.ONLINE, SubState.FREE),
        ONLINE_TESTING(State.ONLINE, SubState.TESTING);

        private final State state;

        private final SubState subState;

        private static final Map<Pair<State, SubState>, ComplexState> MAP = new HashMap<>(values().length, 1);

        static {
            Arrays.stream(values()).forEach(c -> MAP.put(Pair.of(c.state, c.subState), c));
        }

        ComplexState(State state, SubState subState) {
            this.state = state;
            this.subState = subState;
        }

        public State getState() {
            return state;
        }

        public SubState getSubState() {
            return subState;
        }

        public ComplexState compute(State state) {
            return MAP.get(Pair.of(state, subState));
        }

        public ComplexState compute(SubState subState) {
            return MAP.get(Pair.of(state, subState));
        }

    }

    private APICluster cluster;

    private Long deviceModelId;

    private String deviceModelName;

    private boolean enabled;

    private String fingerprint;

    private InitStep initStep;

    private String manufacturer;

    private String name;

    private String serialId;

    private APISoftwareVersion softwareVersion;

    private ComplexState state;

    private Date stateTime;

    private Date stateChangeTime;

    private Date lastOnlineTime;

    private String unlockGesture;

    private String ipAddress;

    private Long accountId;

    private String mainUserEmail;

    private String location;

    private Long testTimeLimit;

    private boolean locked;

    private APIDevice.OsType osType;

    private APIDevice.Platform platform;

    public APIAdminDevice() {
    }

    // Stub when additional data is not needed
    public APIAdminDevice(Long id) {
        super(id);
    }

    public APIAdminDevice(
            Long id, String name, String manufacturer, boolean enabled, String serialId, String fingerprint,
            String unlockGesture, String releaseVersion, Integer apiLevel, Long deviceModelId,
            String deviceModelName, ComplexState state, LocalDateTime stateTime,  LocalDateTime stateChangeTime,
            InitStep initStep, String ipAddress, Long clusterId, String clusterName, String clusterUrl,
            String jenkinsUrl, String pluginVersion, APICluster.State clusterState, LocalDateTime clusterStateTime,
            LocalDateTime clusterStateChangeTime, Boolean clusterEnabled, APICluster.Type clusterType,
            String clusterIpAddress, String clusterRegion, String clusterLocation, LocalDateTime lastOnlineTime,
            Long accountId, String mainUserEmail, String location, Long testTimeLimit, Boolean locked,
            APIDevice.OsType osType, APIDevice.Platform platform) {
        super(id);
        this.name = name;
        this.manufacturer = manufacturer;
        this.enabled = enabled;
        this.serialId = serialId;
        this.fingerprint = fingerprint;
        this.unlockGesture = unlockGesture;
        this.softwareVersion = new APISoftwareVersion(releaseVersion, apiLevel);
        this.deviceModelId = deviceModelId;
        this.deviceModelName = deviceModelName;
        this.state = state;
        this.stateTime = TimeConverter.toDate(stateTime);
        this.stateChangeTime = TimeConverter.toDate(stateChangeTime);
        this.initStep = initStep;
        this.ipAddress = ipAddress;
        this.cluster = new APICluster(clusterId, clusterName, clusterUrl, jenkinsUrl, pluginVersion, clusterState,
                clusterStateTime, clusterStateChangeTime, clusterEnabled, clusterType, clusterIpAddress, clusterRegion,
                clusterLocation);
        this.lastOnlineTime = TimeConverter.toDate(lastOnlineTime);
        this.accountId = accountId;
        this.testTimeLimit = testTimeLimit;
        this.mainUserEmail = mainUserEmail;
        this.location = location;
        this.locked = locked;
        this.osType = osType;
        this.platform = platform;
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

    public ComplexState getState() {
        return state;
    }

    public void setState(ComplexState state) {
        this.state = state;
    }

    public Date getStateTime() {
        return stateTime;
    }

    public void setStateTime(Date stateTime) {
        this.stateTime = stateTime;
    }

    public Date getStateChangeTime() {
        return stateChangeTime;
    }

    public void setStateChangeTime(Date stateChangeTime) {
        this.stateChangeTime = stateChangeTime;
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

    public APIAdminDevice setTestTimeLimit(Long testTimeLimit) {
        this.testTimeLimit = testTimeLimit;
        return this;
    }

    public Long getTestTimeLimit() {
        return testTimeLimit;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public APIDevice.OsType getOsType() {
        return osType;
    }

    public APIAdminDevice setOsType(APIDevice.OsType osType) {
        this.osType = osType;
        return this;
    }

    public APIDevice.Platform getPlatform() {
        return platform;
    }

    public APIAdminDevice setPlatform(APIDevice.Platform platform) {
        this.platform = platform;
        this.osType = platform.getOsType();
        return this;
    }

    public APIAdminDevice setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
        this.manufacturer = adminDevice.manufacturer;
        this.serialId = adminDevice.serialId;
        this.softwareVersion = adminDevice.softwareVersion;
        this.state = adminDevice.state;
        this.stateTime = adminDevice.stateTime;
        this.stateChangeTime = adminDevice.stateChangeTime;
        this.unlockGesture = adminDevice.unlockGesture;
        this.ipAddress = adminDevice.ipAddress;
        this.lastOnlineTime = adminDevice.lastOnlineTime;
        this.accountId = adminDevice.accountId;
        this.mainUserEmail = adminDevice.mainUserEmail;
        this.location = adminDevice.location;
        this.testTimeLimit = adminDevice.testTimeLimit;
        this.locked = adminDevice.locked;
        this.osType = adminDevice.osType;
        this.platform = adminDevice.platform;
    }
}
