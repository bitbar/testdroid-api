package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
@XmlRootElement
public class APIAdminDeviceModel extends APIEntity {

    private String name;

    private Integer online;

    private Integer total;

    private Integer running;

    private Integer queueSize;

    private Long avgWaitingTime;

    private Boolean enabled;

    private Boolean vncSupported;

    public APIAdminDeviceModel() {

    }

    public APIAdminDeviceModel(Long id, String name, Boolean enabled, Boolean vncSupported, Integer online,
            Integer total, Integer running, Integer queueSize, Double avgWaitingTime) {
        super(id);
        this.name = name;
        this.enabled = enabled;
        this.vncSupported = vncSupported;
        this.online = online;
        this.total = total;
        this.running = running;
        this.queueSize = queueSize;
        this.avgWaitingTime = avgWaitingTime != null ? avgWaitingTime.longValue() : 0;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAdminDeviceModel model = (APIAdminDeviceModel) from;
        cloneBase(from);
        this.name = model.name;
        this.enabled = model.enabled;
        this.vncSupported = model.vncSupported;
        this.online = model.online;
        this.total = model.total;
        this.running = model.running;
        this.queueSize = model.queueSize;
        this.avgWaitingTime = model.avgWaitingTime;
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

    public Integer getRunning() {
        return running;
    }

    public void setRunning(Integer running) {
        this.running = running;
    }

    public Integer getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(Integer queueSize) {
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

    public Boolean getVncSupported() {
        return vncSupported;
    }

    public void setVncSupported(Boolean vncSupported) {
        this.vncSupported = vncSupported;
    }
}
