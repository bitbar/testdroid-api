package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIDeviceFilter extends APIEntity {

    private String name;

    private String displayName;

    private boolean hidden;

    private APIDevice.OsType osType;

    //Technical field to allow group by it
    @JsonIgnore
    private String labelGroupDisplayName;

    public APIDeviceFilter() {
    }

    public APIDeviceFilter(
            Long labelId, String labelName, String labelDisplayName, Long labelGroupId, String labelGroupDisplayName,
            boolean hidden, APIDevice.OsType osType, Long order) {
        super(labelId);
        this.parentId = labelGroupId;
        this.name = labelName;
        this.displayName = labelDisplayName;
        this.hidden = hidden;
        this.osType = osType;
        this.labelGroupDisplayName = labelGroupDisplayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public APIDevice.OsType getOsType() {
        return osType;
    }

    public void setOsType(APIDevice.OsType osType) {
        this.osType = osType;
    }

    public String getLabelGroupDisplayName() {
        return labelGroupDisplayName;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceFilter apiDeviceFilter = (APIDeviceFilter) from;
        cloneBase(from);
        this.name = apiDeviceFilter.name;
        this.displayName = apiDeviceFilter.displayName;
        this.hidden = apiDeviceFilter.hidden;
        this.osType = apiDeviceFilter.osType;
    }

}
