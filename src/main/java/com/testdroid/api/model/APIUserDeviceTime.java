package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
@XmlRootElement
public class APIUserDeviceTime extends APIEntity {

    private Long totalDeviceTime;

    private Long inspectorDeviceTime;

    private Long automaticDeviceTime;

    private APIDeviceTimeEntry[] deviceTimeEntries;

    public APIUserDeviceTime() {

    }

    public APIUserDeviceTime(
            Long inspectorDeviceTime, Long automaticDeviceTime,
            APIDeviceTimeEntry... deviceTimeEntries) {
        this.totalDeviceTime = inspectorDeviceTime + automaticDeviceTime;
        this.inspectorDeviceTime = inspectorDeviceTime;
        this.automaticDeviceTime = automaticDeviceTime;
        this.deviceTimeEntries = deviceTimeEntries;
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

    public void setDeviceTimeEntries(APIDeviceTimeEntry[] deviceTimeEntries) {
        this.deviceTimeEntries = deviceTimeEntries;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIUserDeviceTime apiUserDeviceTime = (APIUserDeviceTime) from;
        cloneBase(from);
        this.totalDeviceTime = apiUserDeviceTime.totalDeviceTime;
        this.inspectorDeviceTime = apiUserDeviceTime.inspectorDeviceTime;
        this.automaticDeviceTime = apiUserDeviceTime.automaticDeviceTime;
        this.deviceTimeEntries = apiUserDeviceTime.deviceTimeEntries;
    }
}
