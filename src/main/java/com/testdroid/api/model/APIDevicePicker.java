package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIDevicePicker extends APIEntity {

    private List<APIDeviceFilterGroup> deviceFilterGroups = new ArrayList<>();

    public APIDevicePicker() {
    }

    public APIDevicePicker(Long id, List<APIDeviceFilterGroup> deviceFilterGroups) {
        super(id);
        this.deviceFilterGroups = deviceFilterGroups;
    }

    public List<APIDeviceFilterGroup> getDeviceFilterGroups() {
        return deviceFilterGroups;
    }

    public void setDeviceFilterGroups(List<APIDeviceFilterGroup> deviceFilterGroups) {
        this.deviceFilterGroups = deviceFilterGroups;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDevicePicker apiDevicePicker = (APIDevicePicker) from;
        this.deviceFilterGroups = apiDevicePicker.deviceFilterGroups;
        cloneBase(from);
    }
}
