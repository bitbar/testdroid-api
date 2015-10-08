package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
@XmlRootElement
public class APIUserDeviceTime extends APIEntity {

    private Long totalDeviceTime;

    private Long inspectorDeviceTime;

    private Long automaticDeviceTime;

    private Long freeDeviceTime;

    private Long periodStart;

    private Long periodEnd;

    private APIDeviceTimeEntry[] deviceTimeEntries;

    public APIUserDeviceTime() {

    }

    public APIUserDeviceTime(
            Long inspectorDeviceTime, Long automaticDeviceTime, Long freeDeviceTime, Long periodStart, 
            Long periodEnd, APIDeviceTimeEntry... deviceTimeEntries) {
        this.totalDeviceTime = inspectorDeviceTime + automaticDeviceTime + freeDeviceTime;
        this.inspectorDeviceTime = inspectorDeviceTime;
        this.automaticDeviceTime = automaticDeviceTime;
        this.freeDeviceTime = freeDeviceTime;
        this.deviceTimeEntries = deviceTimeEntries;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
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

    public APIDeviceTimeEntry[] getDeviceTimeEntries() {
        return deviceTimeEntries;
    }

    public Long getPeriodStart() {
        return periodStart;
    }

    public Long getPeriodEnd() {
        return periodEnd;
    }

    public void setDeviceTimeEntries(APIDeviceTimeEntry[] deviceTimeEntries) {
        this.deviceTimeEntries = deviceTimeEntries;
    }

    public Long getFreeDeviceTime() {
        return freeDeviceTime;
    }

    public void setFreeDeviceTime(Long freeDeviceTime) {
        this.freeDeviceTime = freeDeviceTime;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIUserDeviceTime apiUserDeviceTime = (APIUserDeviceTime) from;
        cloneBase(from);
        this.totalDeviceTime = apiUserDeviceTime.totalDeviceTime;
        this.inspectorDeviceTime = apiUserDeviceTime.inspectorDeviceTime;
        this.automaticDeviceTime = apiUserDeviceTime.automaticDeviceTime;
        this.freeDeviceTime = apiUserDeviceTime.freeDeviceTime;
        this.deviceTimeEntries = apiUserDeviceTime.deviceTimeEntries;
        this.periodStart = apiUserDeviceTime.periodStart;
        this.periodEnd = apiUserDeviceTime.periodEnd;
    }
}
