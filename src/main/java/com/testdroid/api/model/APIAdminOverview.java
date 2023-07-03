package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIAdminOverview extends APIEntity {

    private Long activeUsersCount;

    private Long offlineClusterCount;

    private Long offlineDeviceCount;

    private Long offlineDeviceModelCount;

    private Long runningInspectorSessionsCount;

    private Long runningTestRunsCount;

    private Long totalClusterCount;

    private Long totalDeviceCount;

    private Long totalDeviceModelCount;

    private Long totalInspectorSessionsCount;

    private Long totalTestRunCount;

    private Long totalUserCount;

    private Long waitingTestRunsCount;

    public APIAdminOverview() {
    }

    public APIAdminOverview(
            Long activeUsersCount, Long runningInspectorSessionsCount,
            Long runningTestRunsCount, Long waitingTestRunsCount) {
        this.activeUsersCount = activeUsersCount;
        this.runningInspectorSessionsCount = runningInspectorSessionsCount;
        this.runningTestRunsCount = runningTestRunsCount;
        this.waitingTestRunsCount = waitingTestRunsCount;
    }

    public Long getActiveUsersCount() {
        return activeUsersCount;
    }

    public APIAdminOverview withActiveUsersCount(Long activeUsersCount) {
        this.activeUsersCount = activeUsersCount;
        return this;
    }

    public Long getOfflineClusterCount() {
        return offlineClusterCount;
    }

    public APIAdminOverview withOfflineClusterCount(Long offlineClusterCount) {
        this.offlineClusterCount = offlineClusterCount;
        return this;
    }

    public Long getOfflineDeviceCount() {
        return offlineDeviceCount;
    }

    public APIAdminOverview withOfflineDeviceCount(Long offlineDeviceCount) {
        this.offlineDeviceCount = offlineDeviceCount;
        return this;
    }

    public Long getOfflineDeviceModelCount() {
        return offlineDeviceModelCount;
    }

    public APIAdminOverview withOfflineDeviceModelCount(Long offlineDeviceModelCount) {
        this.offlineDeviceModelCount = offlineDeviceModelCount;
        return this;
    }

    public Long getRunningInspectorSessionsCount() {
        return runningInspectorSessionsCount;
    }

    public APIAdminOverview withRunningInspectorSessionsCount(Long runningInspectorSessionsCount) {
        this.runningInspectorSessionsCount = runningInspectorSessionsCount;
        return this;
    }

    public Long getRunningTestRunsCount() {
        return runningTestRunsCount;
    }

    public APIAdminOverview withRunningTestRunsCount(Long runningTestRunsCount) {
        this.runningTestRunsCount = runningTestRunsCount;
        return this;
    }

    public Long getTotalClusterCount() {
        return totalClusterCount;
    }

    public APIAdminOverview withTotalClusterCount(Long totalClusterCount) {
        this.totalClusterCount = totalClusterCount;
        return this;
    }

    public Long getTotalDeviceCount() {
        return totalDeviceCount;
    }

    public APIAdminOverview withTotalDeviceCount(Long totalDeviceCount) {
        this.totalDeviceCount = totalDeviceCount;
        return this;
    }

    public Long getTotalDeviceModelCount() {
        return totalDeviceModelCount;
    }

    public APIAdminOverview withTotalDeviceModelCount(Long totalDeviceModelCount) {
        this.totalDeviceModelCount = totalDeviceModelCount;
        return this;
    }

    public Long getTotalInspectorSessionsCount() {
        return totalInspectorSessionsCount;
    }

    public APIAdminOverview withTotalInspectorSessionsCount(Long totalInspectorSessionsCount) {
        this.totalInspectorSessionsCount = totalInspectorSessionsCount;
        return this;
    }

    public Long getTotalTestRunCount() {
        return totalTestRunCount;
    }

    public APIAdminOverview withTotalTestRunCount(Long totalTestRunCount) {
        this.totalTestRunCount = totalTestRunCount;
        return this;
    }

    public Long getTotalUserCount() {
        return totalUserCount;
    }

    public APIAdminOverview withTotalUserCount(Long totalUserCount) {
        this.totalUserCount = totalUserCount;
        return this;
    }

    public Long getWaitingTestRunsCount() {
        return waitingTestRunsCount;
    }

    public APIAdminOverview withWaitingTestRunsCount(Long waitingTestRunsCount) {
        this.waitingTestRunsCount = waitingTestRunsCount;
        return this;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAdminOverview apiAdminTestRun = (APIAdminOverview) from;
        cloneBase(from);
        this.activeUsersCount = apiAdminTestRun.activeUsersCount;
        this.offlineClusterCount = apiAdminTestRun.offlineClusterCount;
        this.offlineDeviceCount = apiAdminTestRun.offlineDeviceCount;
        this.offlineDeviceModelCount = apiAdminTestRun.offlineDeviceModelCount;
        this.runningInspectorSessionsCount = apiAdminTestRun.runningInspectorSessionsCount;
        this.runningTestRunsCount = apiAdminTestRun.runningTestRunsCount;
        this.totalClusterCount = apiAdminTestRun.totalClusterCount;
        this.totalDeviceCount = apiAdminTestRun.totalDeviceCount;
        this.totalDeviceModelCount = apiAdminTestRun.totalDeviceModelCount;
        this.totalInspectorSessionsCount = apiAdminTestRun.totalInspectorSessionsCount;
        this.totalTestRunCount = apiAdminTestRun.totalTestRunCount;
        this.totalUserCount = apiAdminTestRun.totalUserCount;
        this.waitingTestRunsCount = apiAdminTestRun.waitingTestRunsCount;
    }
}
