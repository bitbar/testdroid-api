package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author kajdus
 */
@XmlRootElement
public class APILabelGroup extends APIEntity {
    private String name;
    private String displayName;
    private boolean hiddenByDefault;

    public APILabelGroup() {}

    public APILabelGroup(Long id, String name, String displayName, boolean hiddenByDefault) {
        super(id);
        this.name = name;
        this.displayName = displayName;
        this.hiddenByDefault = hiddenByDefault;
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

    private String getDevicesURI() { return selfURI + "/devices"; };

    @JsonIgnore
    public APIListResource<APIDevice> getDevicesResource() throws APIException {
        return getListResource(getDevicesURI(), APIDevice.class);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APILabelGroup apiLabelGroup = (APILabelGroup) from;
        cloneBase(from);
        this.displayName = apiLabelGroup.displayName;
        this.hiddenByDefault = apiLabelGroup.hiddenByDefault;
        this.name = apiLabelGroup.name;
    }
}