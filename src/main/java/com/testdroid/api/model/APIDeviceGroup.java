package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import static com.testdroid.api.APIEntity.createUri;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.APIQueryBuilder;
import com.testdroid.api.APISort;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author kajdus
 */
@XmlRootElement
public class APIDeviceGroup extends APIDeviceProperty {
    private Long userId;
    private APIDevice.OsType osType;

    public APIDeviceGroup() {}

    public APIDeviceGroup(Long id, String name, String displayName, APIDevice.OsType osType, Integer deviceCount, Integer creditsPrice, Long userId) {
        super(id, name, displayName, deviceCount, creditsPrice);
        this.userId = userId;
        this.osType = osType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public APIDevice.OsType getOsType() {
        return osType;
    }

    public void setOsType(APIDevice.OsType osType) {
        this.osType = osType;
    }
    
    @JsonIgnore
    public boolean isPublic() {
        return userId == null;
    }
    
    private String getIncludedDevicesURI() { return createUri(selfURI, "/devices"); };
    private String getIncludedDevicesURI(Long deviceId) { return createUri(selfURI, "/devices/" + deviceId); };
    
    @JsonIgnore
    public APIListResource<APIDevice> getIncludedDevicesResource() throws APIException {
        return getListResource(getIncludedDevicesURI(), APIDevice.class);
    }
    
    /**
     * @since 1.3.34
     * @param queryBuilder
     * @return
     * @throws APIException 
     */
    @JsonIgnore
    public APIListResource<APIDevice> getIncludedDevicesResource(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getIncludedDevicesURI(), queryBuilder, APIDevice.class);
    }
    
    /**
     * @deprecated 
     * @param offset
     * @param limit
     * @param search
     * @param sort
     * @return
     * @throws APIException 
     */
    @JsonIgnore
    public APIListResource<APIDevice> getIncludedDevicesResource(long offset, long limit, String search, APISort sort) throws APIException {
        return getListResource(getIncludedDevicesURI(), offset, limit, search, sort, APIDevice.class);
    }
    
    public void addDevice(APIDevice device) throws APIException {
        postResource(getIncludedDevicesURI(), String.format("id=%s", device.getId()), null);
    }
    
    public void deleteDevice(APIDevice device) throws APIException {
        deleteResource(getIncludedDevicesURI(device.getId()));
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        APIDeviceGroup apiDeviceGroup = (APIDeviceGroup) from;
        this.userId = apiDeviceGroup.userId;
    }
}
