package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
public class APIAdminDeviceModel extends APIEntity {

    private String name;

    private Integer online;

    private Integer total;

    private Long running;

    private Long queueSize;

    private Long avgWaitingTime;

    private Boolean enabled;

    private Integer creditsPrice;

    private APIDevice.OsType osType;

    private APIDevice.Platform platform;

    private String location;

    private Boolean dedicated;

    private String releaseVersion;

    public APIAdminDeviceModel() {
    }

    @SuppressWarnings("squid:S107")
    public APIAdminDeviceModel(
            Long id, String name, APIDevice.OsType osType, APIDevice.Platform platform, String location,
            Boolean dedicated, String releaseVersion, Boolean enabled, Integer creditsPrice, Integer online,
            Integer total, Long running, Long queueSize, Double avgWaitingTime) {
        super(id);
        this.name = name;
        this.osType = osType;
        this.platform = platform;
        this.location = location;
        this.dedicated = dedicated;
        this.releaseVersion = releaseVersion;
        this.enabled = enabled;
        this.creditsPrice = creditsPrice;
        this.online = online;
        this.total = total;
        this.running = running;
        this.queueSize = queueSize;
        this.avgWaitingTime = avgWaitingTime != null ? avgWaitingTime.longValue() : 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getRunning() {
        return running;
    }

    public void setRunning(Long running) {
        this.running = running;
    }

    public Long getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(Long queueSize) {
        this.queueSize = queueSize;
    }

    public Long getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public void setAvgWaitingTime(Long avgWaitingTime) {
        this.avgWaitingTime = avgWaitingTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public APIDevice.OsType getOsType() {
        return osType;
    }

    public void setOsType(APIDevice.OsType osType) {
        this.osType = osType;
    }

    public APIDevice.Platform getPlatform() {
        return platform;
    }

    public void setPlatform(APIDevice.Platform platform) {
        this.platform = platform;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getDedicated() {
        return dedicated;
    }

    public void setDedicated(Boolean dedicated) {
        this.dedicated = dedicated;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public Integer getCreditsPrice() {
        return creditsPrice;
    }

    public void setCreditsPrice(Integer creditsPrice) {
        this.creditsPrice = creditsPrice;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAdminDeviceModel model = (APIAdminDeviceModel) from;
        cloneBase(from);
        this.name = model.name;
        this.osType = model.osType;
        this.platform = model.platform;
        this.location = model.location;
        this.dedicated = model.dedicated;
        this.releaseVersion = model.releaseVersion;
        this.enabled = model.enabled;
        this.creditsPrice = model.creditsPrice;
        this.online = model.online;
        this.total = model.total;
        this.running = model.running;
        this.queueSize = model.queueSize;
        this.avgWaitingTime = model.avgWaitingTime;
    }
}
