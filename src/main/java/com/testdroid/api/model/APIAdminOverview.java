package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIAdminOverview extends APIEntity {

    private Long activeUsersCount;

    private Long inspectorSessionsCount;

    private Long remoteSessionsCount;

    private Long runningDeviceModelsCount;

    private Long runningTestRunsCount;

    private Long waitingDeviceModelsCount;

    private Long waitingTestRunsCount;

    public APIAdminOverview() {
    }

    public APIAdminOverview(
            Long activeUsersCount, Long inspectorSessionsCount, Long remoteSessionsCount, Long runningDeviceModelsCount,
            Long runningTestRunsCount, Long waitingDeviceModelsCount, Long waitingTestRunsCount) {
        this.activeUsersCount = activeUsersCount;
        this.inspectorSessionsCount = inspectorSessionsCount;
        this.remoteSessionsCount = remoteSessionsCount;
        this.runningDeviceModelsCount = runningDeviceModelsCount;
        this.runningTestRunsCount = runningTestRunsCount;
        this.waitingDeviceModelsCount = waitingDeviceModelsCount;
        this.waitingTestRunsCount = waitingTestRunsCount;
    }

    public Long getActiveUsersCount() {
        return activeUsersCount;
    }

    public void setActiveUsersCount(Long activeUsersCount) {
        this.activeUsersCount = activeUsersCount;
    }

    public Long getInspectorSessionsCount() {
        return inspectorSessionsCount;
    }

    public void setInspectorSessionsCount(Long inspectorSessionsCount) {
        this.inspectorSessionsCount = inspectorSessionsCount;
    }

    public Long getRunningDeviceModelsCount() {
        return runningDeviceModelsCount;
    }

    public void setRunningDeviceModelsCount(Long runningDeviceModelsCount) {
        this.runningDeviceModelsCount = runningDeviceModelsCount;
    }

    public Long getRunningTestRunsCount() {
        return runningTestRunsCount;
    }

    public void setRunningTestRunsCount(Long runningTestRunsCount) {
        this.runningTestRunsCount = runningTestRunsCount;
    }

    public Long getWaitingDeviceModelsCount() {
        return waitingDeviceModelsCount;
    }

    public void setWaitingDeviceModelsCount(Long waitingDeviceModelsCount) {
        this.waitingDeviceModelsCount = waitingDeviceModelsCount;
    }

    public Long getWaitingTestRunsCount() {
        return waitingTestRunsCount;
    }

    public void setWaitingTestRunsCount(Long waitingTestRunsCount) {
        this.waitingTestRunsCount = waitingTestRunsCount;
    }

    public Long getRemoteSessionsCount() {
        return remoteSessionsCount;
    }

    public void setRemoteSessionsCount(Long remoteSessionsCount) {
        this.remoteSessionsCount = remoteSessionsCount;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAdminOverview apiAdminTestRun = (APIAdminOverview) from;
        cloneBase(from);
        this.activeUsersCount = apiAdminTestRun.activeUsersCount;
        this.inspectorSessionsCount = apiAdminTestRun.inspectorSessionsCount;
        this.remoteSessionsCount = apiAdminTestRun.remoteSessionsCount;
        this.runningTestRunsCount = apiAdminTestRun.runningTestRunsCount;
        this.waitingTestRunsCount = apiAdminTestRun.waitingTestRunsCount;
        this.runningDeviceModelsCount = apiAdminTestRun.runningDeviceModelsCount;
        this.waitingDeviceModelsCount = apiAdminTestRun.waitingDeviceModelsCount;
    }
}
