package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import java.util.List;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIAdminDeviceProblem extends APIEntity {

    private Long clusterId;

    private String clusterName;

    private Long deviceId;

    private Long deviceModelId;

    private String deviceModelName;

    private String deviceName;

    private String location;

    private List<APIAdminDeviceProblemPair> problems;

    public APIAdminDeviceProblem() {
    }

    @SuppressWarnings("squid:S107")
    public APIAdminDeviceProblem(
            Long clusterId, String clusterName, Long deviceId, String deviceName, Long deviceModelId,
            String deviceModelName, String location, List<APIAdminDeviceProblemPair> problems) {
        this.clusterId = clusterId;
        this.clusterName = clusterName;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceModelId = deviceModelId;
        this.deviceModelName = deviceModelName;
        this.location = location;
        this.problems = problems;
    }

    public Long getClusterId() {
        return clusterId;
    }

    public void setClusterId(Long clusterId) {
        this.clusterId = clusterId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<APIAdminDeviceProblemPair> getProblems() {
        return problems;
    }

    public void setProblems(List<APIAdminDeviceProblemPair> problems) {
        this.problems = problems;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAdminDeviceProblem apiAdminDeviceProblem = (APIAdminDeviceProblem) from;
        cloneBase(from);
        this.clusterId = apiAdminDeviceProblem.clusterId;
        this.clusterName = apiAdminDeviceProblem.clusterName;
        this.deviceId = apiAdminDeviceProblem.deviceId;
        this.deviceName = apiAdminDeviceProblem.deviceName;
        this.deviceModelId = apiAdminDeviceProblem.deviceModelId;
        this.deviceModelName = apiAdminDeviceProblem.deviceModelName;
        this.location = apiAdminDeviceProblem.location;
        this.problems = apiAdminDeviceProblem.problems;
    }
}
