package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
@XmlRootElement
public class APIAdminDeviceEvent extends APIEntity {

    public enum DeviceEventType {
        SESSION_START,
        SESSION_END,
        SESSION_TIMEOUT,
        STATE_CHANGE,
        CLUSTER_CHANGE
    }

    private Date time;

    private DeviceEventType type;

    private APIAdminDevice.ComplexState state;

    private APIAdminDevice device;

    private APICluster cluster;

    private APIAdminDeviceSession deviceSession;

    public APIAdminDeviceEvent() {

    }

    public APIAdminDeviceEvent(Long id, LocalDateTime time, APIAdminDeviceEvent.DeviceEventType type,
            APIAdminDevice.ComplexState state, Long deviceId,
            Long clusterId, String name, String url, APICluster.State clusterState, Date stateTime,
            Date stateChangeTime, Boolean enabled,
            Long deviceSessionId, Date createTime, Date startTime, Date endTime, String startedByDisplayName,
            Long projectId, String projectName, Long testRunId, String testRunName,
            APIDeviceSession.State deviceSessionState, Integer priority, Boolean billable, Long deviceTime,
            APIDeviceSessionStep.Type currentStepType, String retriedFailReason) {
        super(id);
        this.time = TimeConverter.toDate(time);
        this.type = type;
        this.state = state;
        this.device = new APIAdminDevice(deviceId);
        this.cluster = new APICluster(clusterId, name, url, clusterState, stateTime, stateChangeTime, enabled);
        this.deviceSession = new APIAdminDeviceSession(deviceSessionId, createTime, startTime, endTime,
                startedByDisplayName, projectId, projectName, testRunId, testRunName, deviceSessionState, priority,
                billable, deviceTime, currentStepType, retriedFailReason);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public DeviceEventType getType() {
        return type;
    }

    public void setType(DeviceEventType type) {
        this.type = type;
    }

    public APIAdminDevice getDevice() {
        return device;
    }

    public void setDevice(APIAdminDevice device) {
        this.device = device;
    }

    public APICluster getCluster() {
        return cluster;
    }

    public void setCluster(APICluster cluster) {
        this.cluster = cluster;
    }

    public APIAdminDeviceSession getDeviceSession() {
        return deviceSession;
    }

    public void setDeviceSession(APIAdminDeviceSession deviceSession) {
        this.deviceSession = deviceSession;
    }

    public APIAdminDevice.ComplexState getState() {
        return state;
    }

    public void setState(APIAdminDevice.ComplexState state) {
        this.state = state;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAdminDeviceEvent deviceEvent = (APIAdminDeviceEvent) from;
        cloneBase(from);
        this.time = deviceEvent.time;
        this.state = deviceEvent.state;
        this.device = deviceEvent.device;
        this.cluster = deviceEvent.cluster;
        this.deviceSession = deviceEvent.deviceSession;
    }
}
