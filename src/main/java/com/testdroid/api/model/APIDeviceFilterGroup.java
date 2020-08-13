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
public class APIDeviceFilterGroup extends APIEntity {

    private String name;

    private List<APIDeviceFilter> deviceFilters = new ArrayList<>();

    public APIDeviceFilterGroup() {
    }

    public APIDeviceFilterGroup(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<APIDeviceFilter> getDeviceFilters() {
        return deviceFilters;
    }

    public APIDeviceFilterGroup setDeviceFilters(List<APIDeviceFilter> deviceFilters) {
        this.deviceFilters = deviceFilters;
        return this;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceFilterGroup apiDeviceFilterGroup = (APIDeviceFilterGroup) from;
        cloneBase(from);
        this.name = apiDeviceFilterGroup.name;
        this.deviceFilters = apiDeviceFilterGroup.deviceFilters;
    }
}
