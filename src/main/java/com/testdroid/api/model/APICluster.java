package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIList;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APICluster extends APIEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlType(namespace = "APICluster", name = "APIClusterState")
    public enum State {
        OFFLINE,
        ONLINE
    }

    @XmlType(namespace = "APICluster", name = "APIClusterType")
    public enum Type {
        BARE_METAL,
        EC2,
        VM
    }

    private APIList<APIAdminDevice> devices;

    private Boolean enabled = true;

    private String name;

    private State state = State.OFFLINE;

    private Date stateChangeTime = new Date();

    private Date stateTime = new Date();

    private String url;

    private String pluginVersion;

    private Type type = Type.BARE_METAL;

    private String ipAddress;

    private String region;

    private String location;

    public APICluster() {
    }

    public APICluster(
            Long id, String name, String url, String pluginVersion, State state, LocalDateTime stateTime,
            LocalDateTime stateChangeTime, Boolean enabled, Type type, String ipAddress, String region,
            String location) {
        super(id);
        this.name = name;
        this.url = url;
        this.state = state;
        this.stateTime = TimeConverter.toDate(stateTime);
        this.stateChangeTime = TimeConverter.toDate(stateChangeTime);
        this.enabled = enabled;
        this.pluginVersion = pluginVersion;
        this.type = type;
        this.ipAddress = ipAddress;
        this.region = region;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public APICluster setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public APICluster setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPluginVersion() {
        return pluginVersion;
    }

    public APICluster setPluginVersion(String pluginVersion) {
        this.pluginVersion = pluginVersion;
        return this;
    }

    public State getState() {
        return state;
    }

    public APICluster setState(State state) {
        this.state = state;
        return this;
    }

    public Date getStateTime() {
        return stateTime;
    }

    public APICluster setStateTime(Date stateTime) {
        this.stateTime = stateTime;
        return this;
    }

    public Date getStateChangeTime() {
        return stateChangeTime;
    }

    public APICluster setStateChangeTime(Date stateChangeTime) {
        this.stateChangeTime = stateChangeTime;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public APICluster setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public APIList<APIAdminDevice> getDevices() {
        return devices;
    }

    public APICluster setDevices(APIList<APIAdminDevice> devices) {
        this.devices = devices;
        return this;
    }

    public Type getType() {
        return type;
    }

    public APICluster setType(Type type) {
        this.type = type;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public APICluster setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public APICluster setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public APICluster setLocation(String location) {
        this.location = location;
        return this;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APICluster apiCluster = (APICluster) from;
        cloneBase(from);
        this.devices = apiCluster.devices;
        this.enabled = apiCluster.enabled;
        this.name = apiCluster.name;
        this.state = apiCluster.state;
        this.stateChangeTime = apiCluster.stateChangeTime;
        this.stateTime = apiCluster.stateTime;
        this.url = apiCluster.url;
        this.pluginVersion = apiCluster.pluginVersion;
        this.type = apiCluster.type;
        this.ipAddress = apiCluster.ipAddress;
        this.region = apiCluster.region;
        this.location = apiCluster.location;
    }

}
