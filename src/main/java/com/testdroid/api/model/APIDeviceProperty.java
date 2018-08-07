package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIDeviceProperty extends APIEntity {

    private String displayName;

    private String name;

    private Long propertyGroupId;

    private String propertyGroupName;

    private String labelGroupName;

    public APIDeviceProperty() {
    }

    public APIDeviceProperty(Long id, String name, String displayName, Long propertyGroupId,
            String propertyGroupName, String labelGroupName) {
        super(id);
        this.name = name;
        this.displayName = displayName;
        this.propertyGroupId = propertyGroupId;
        this.propertyGroupName = propertyGroupName;
        this.labelGroupName = labelGroupName;
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

    public Long getPropertyGroupId() {
        return propertyGroupId;
    }

    public void setPropertyGroupId(Long propertyGroupId) {
        this.propertyGroupId = propertyGroupId;
    }

    public String getPropertyGroupName() {
        return propertyGroupName;
    }

    public void setPropertyGroupName(String propertyGroupName) {
        this.propertyGroupName = propertyGroupName;
    }

    public String getLabelGroupName() {
        return labelGroupName;
    }

    public void setLabelGroupName(String labelGroupName) {
        this.labelGroupName = labelGroupName;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceProperty apiDeviceProperty = (APIDeviceProperty) from;
        cloneBase(from);
        this.displayName = apiDeviceProperty.displayName;
        this.name = apiDeviceProperty.name;
        this.propertyGroupId = apiDeviceProperty.propertyGroupId;
        this.propertyGroupName = apiDeviceProperty.propertyGroupName;
        this.labelGroupName = apiDeviceProperty.labelGroupName;
    }
}
