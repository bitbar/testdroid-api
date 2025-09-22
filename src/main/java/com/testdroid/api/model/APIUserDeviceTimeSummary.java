package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testdroid.api.APIEntity;

import static com.testdroid.api.dto.MappingKey.ID;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
@JsonIgnoreProperties(ID)
public class APIUserDeviceTimeSummary extends APIEntity {

    private Long totalDeviceTime;

    private Long inspectorDeviceTime;

    private Long automaticDeviceTime;

    private Long periodStart;

    private Long periodEnd;

    public APIUserDeviceTimeSummary() {

    }

    public APIUserDeviceTimeSummary(Long inspectorDeviceTime, Long automaticDeviceTime) {
        this.totalDeviceTime = inspectorDeviceTime + automaticDeviceTime;
        this.inspectorDeviceTime = inspectorDeviceTime;
        this.automaticDeviceTime = automaticDeviceTime;
    }

    public Long getTotalDeviceTime() {
        return totalDeviceTime;
    }

    public void setTotalDeviceTime(Long totalDeviceTime) {
        this.totalDeviceTime = totalDeviceTime;
    }

    public Long getInspectorDeviceTime() {
        return inspectorDeviceTime;
    }

    public void setInspectorDeviceTime(Long inspectorDeviceTime) {
        this.inspectorDeviceTime = inspectorDeviceTime;
    }

    public Long getAutomaticDeviceTime() {
        return automaticDeviceTime;
    }

    public void setAutomaticDeviceTime(Long automaticDeviceTime) {
        this.automaticDeviceTime = automaticDeviceTime;
    }

    public Long getPeriodStart() {
        return periodStart;
    }

    public Long getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Long periodEnd) {
        this.periodEnd = periodEnd;
    }

    public void setPeriodStart(Long periodStart) {
        this.periodStart = periodStart;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIUserDeviceTimeSummary apiUserDeviceTimeSummary = (APIUserDeviceTimeSummary) from;
        cloneBase(from);
        this.totalDeviceTime = apiUserDeviceTimeSummary.totalDeviceTime;
        this.inspectorDeviceTime = apiUserDeviceTimeSummary.inspectorDeviceTime;
        this.automaticDeviceTime = apiUserDeviceTimeSummary.automaticDeviceTime;
        this.periodStart = apiUserDeviceTimeSummary.periodStart;
        this.periodEnd = apiUserDeviceTimeSummary.periodEnd;
    }
}
