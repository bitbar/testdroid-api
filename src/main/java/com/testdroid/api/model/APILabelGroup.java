package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIListResource;
import com.testdroid.api.dto.Context;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APILabelGroup extends APIEntity {

    private String displayName;

    private String name;

    public APILabelGroup() {
    }

    public APILabelGroup(Long id, String name, String displayName) {
        super(id);
        this.name = name;
        this.displayName = displayName;
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

    private String getDevicePropertiesURI() {
        return createUri(selfURI, "/labels");
    }

    @JsonIgnore
    public APIListResource<APIDeviceProperty> getDevicePropertiesResource() {
        return new APIListResource<>(client, getDevicePropertiesURI(), APIDeviceProperty.class);
    }

    @JsonIgnore
    public APIListResource<APIDeviceProperty> getDevicePropertiesResource(Context<APIDeviceProperty> context) {
        return new APIListResource<>(client, getDevicePropertiesURI(), context);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APILabelGroup apiLabelGroup = (APILabelGroup) from;
        cloneBase(from);
        this.displayName = apiLabelGroup.displayName;
        this.name = apiLabelGroup.name;
    }
}
