package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIListResource;
import com.testdroid.api.APIQueryBuilder;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APILabelGroup extends APIEntity {

    private String displayName;

    private boolean hiddenByDefault;

    private boolean labelsSortAscending;

    private String name;

    public APILabelGroup() {
    }

    public APILabelGroup(
            Long id, String name, String displayName, boolean hiddenByDefault, boolean labelsSortAscending) {
        super(id);
        this.name = name;
        this.displayName = displayName;
        this.hiddenByDefault = hiddenByDefault;
        this.labelsSortAscending = labelsSortAscending;
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

    public boolean isHiddenByDefault() {
        return hiddenByDefault;
    }

    public void setHiddenByDefault(boolean hiddenByDefault) {
        this.hiddenByDefault = hiddenByDefault;
    }

    public boolean isLabelsSortAscending() {
        return labelsSortAscending;
    }

    public void setLabelsSortAscending(boolean labelsSortAscending) {
        this.labelsSortAscending = labelsSortAscending;
    }

    private String getDevicePropertiesURI() {
        return createUri(selfURI, "/labels");
    }

    @JsonIgnore
    public APIListResource<APIDeviceProperty> getDevicePropertiesResource() {
        return new APIListResource(client, getDevicePropertiesURI());
    }

    @JsonIgnore
    public APIListResource<APIDeviceProperty> getDevicePropertiesResource(APIQueryBuilder queryBuilder) {
        return new APIListResource(client, getDevicePropertiesURI(), queryBuilder);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APILabelGroup apiLabelGroup = (APILabelGroup) from;
        cloneBase(from);
        this.displayName = apiLabelGroup.displayName;
        this.hiddenByDefault = apiLabelGroup.hiddenByDefault;
        this.labelsSortAscending = apiLabelGroup.labelsSortAscending;
        this.name = apiLabelGroup.name;
    }
}
