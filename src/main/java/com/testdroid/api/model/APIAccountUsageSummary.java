package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class APIAccountUsageSummary extends APIEntity {

    private Long deviceModelsCount;

    private Long duration;

    private Long osVersionsCount;

    private Long projectsCount;

    private Long sessionsCount;

    private Long tunnelEnabledCount;

    private Long usersCount;

    public APIAccountUsageSummary() {
    }

    public APIAccountUsageSummary(
            Long sessionsCount, Long duration, Long deviceModelsCount, Long tunnelEnabledCount, Long osVersionsCount,
            Long projectsCount, Long usersCount) {
        this.sessionsCount = sessionsCount;
        this.duration = duration;
        this.deviceModelsCount = deviceModelsCount;
        this.tunnelEnabledCount = tunnelEnabledCount;
        this.osVersionsCount = osVersionsCount;
        this.projectsCount = projectsCount;
        this.usersCount = usersCount;
    }

    public Long getSessionsCount() {
        return sessionsCount;
    }

    public void setSessionsCount(Long sessionsCount) {
        this.sessionsCount = sessionsCount;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getDeviceModelsCount() {
        return deviceModelsCount;
    }

    public void setDeviceModelsCount(Long deviceModelsCount) {
        this.deviceModelsCount = deviceModelsCount;
    }

    public Long getTunnelEnabledCount() {
        return tunnelEnabledCount;
    }

    public void setTunnelEnabledCount(Long tunnelEnabledCount) {
        this.tunnelEnabledCount = tunnelEnabledCount;
    }

    public Long getOsVersionsCount() {
        return osVersionsCount;
    }

    public void setOsVersionsCount(Long osVersionsCount) {
        this.osVersionsCount = osVersionsCount;
    }

    public Long getProjectsCount() {
        return projectsCount;
    }

    public void setProjectsCount(Long projectsCount) {
        this.projectsCount = projectsCount;
    }

    public Long getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Long usersCount) {
        this.usersCount = usersCount;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAccountUsageSummary original = (APIAccountUsageSummary) from;
        cloneBase(from);
        this.sessionsCount = original.sessionsCount;
        this.duration = original.duration;
        this.deviceModelsCount = original.deviceModelsCount;
        this.tunnelEnabledCount = original.tunnelEnabledCount;
        this.osVersionsCount = original.osVersionsCount;
        this.projectsCount = original.projectsCount;
        this.usersCount = original.usersCount;
    }
}
