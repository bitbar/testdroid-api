package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import static com.testdroid.api.APIEntity.createUri;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author kajdus
 */
@XmlRootElement
public class APIDeviceProperty extends APIEntity {
    private String name;
    private String displayName;
    private Integer deviceCount;
    private Integer creditsPrice;

    public APIDeviceProperty() {}

    public APIDeviceProperty(Long id, String name, String displayName, Integer deviceCount, Integer creditsPrice) {
        super(id);
        this.name = name;
        this.displayName = displayName;
        this.deviceCount = deviceCount;
        this.creditsPrice = creditsPrice;
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

    public Integer getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    public Integer getCreditsPrice() {
        return creditsPrice;
    }

    public void setCreditsPrice(Integer creditsPrice) {
        this.creditsPrice = creditsPrice;
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
        this.creditsPrice = apiDeviceProperty.creditsPrice;
        this.deviceCount = apiDeviceProperty.deviceCount;
        this.displayName = apiDeviceProperty.displayName;
        this.name = apiDeviceProperty.name;
    }
}
