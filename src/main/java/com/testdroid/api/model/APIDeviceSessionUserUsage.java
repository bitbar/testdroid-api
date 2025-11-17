package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testdroid.api.APIEntity;

import static com.testdroid.api.dto.MappingKey.ID;
import static com.testdroid.api.dto.MappingKey.SELF_URI;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
@JsonIgnoreProperties(value = {ID, SELF_URI})
public class APIDeviceSessionUserUsage extends APIEntity {

    private long devices;

    private Long duration;

    private long osVersions;

    private double sessionShare;

    private long sessions;

    private Long userId;

    private String userName;

    private Long startedById;

    private String startedByName;

    public APIDeviceSessionUserUsage() {

    }

    public APIDeviceSessionUserUsage(
            Long userId, String userName, Long startedById, String startedByName, Long duration,
            long sessions, long devices, long osVersions, double sessionShare) {
        this.userId = userId;
        this.userName = userName;
        this.startedById = startedById;
        this.startedByName = startedByName;
        this.duration = duration;
        this.sessions = sessions;
        this.devices = devices;
        this.osVersions = osVersions;
        this.sessionShare = sessionShare;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceSessionUserUsage original = (APIDeviceSessionUserUsage) from;
        cloneBase(from);
        this.userId = original.userId;
        this.userName = original.userName;
        this.duration = original.duration;
        this.sessions = original.sessions;
        this.devices = original.devices;
        this.osVersions = original.osVersions;
        this.sessionShare = original.sessionShare;
        this.startedByName = original.startedByName;
        this.startedById = original.startedById;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public long getSessions() {
        return sessions;
    }

    public void setSessions(long sessions) {
        this.sessions = sessions;
    }

    public long getDevices() {
        return devices;
    }

    public void setDevices(long devices) {
        this.devices = devices;
    }

    public long getOsVersions() {
        return osVersions;
    }

    public void setOsVersions(long osVersions) {
        this.osVersions = osVersions;
    }

    public double getSessionShare() {
        return sessionShare;
    }

    public void setSessionShare(double sessionShare) {
        this.sessionShare = sessionShare;
    }

    public Long getStartedById() {
        return startedById;
    }

    public void setStartedById(Long startedById) {
        this.startedById = startedById;
    }

    public String getStartedByName() {
        return startedByName;
    }

    public void setStartedByName(String startedByName) {
        this.startedByName = startedByName;
    }
}
