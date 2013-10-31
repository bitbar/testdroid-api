package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import static com.testdroid.api.APIEntity.createUri;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APIDeviceProperty extends APIEntity {
    private String name;
    private String displayName;
    private Long propertyGroupId;
    private String propertyGroupName;

    public APIDeviceProperty() {}

    public APIDeviceProperty(Long id, String name, String displayName, Long propertyGroupId, String propertyGroupName) {
        super(id);
        this.name = name;
        this.displayName = displayName;
        this.propertyGroupId = propertyGroupId;
        this.propertyGroupName = propertyGroupName;
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
    
    private String getDevicesURI() { return createUri(selfURI, "/devices"); };
    
    @JsonIgnore
    public APIListResource<APIDevice> getDevicesResource() throws APIException {
        return getListResource(getDevicesURI(), APIDevice.class);
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
    }
}
