package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIDeviceGroup extends APIEntity {

    private Long creditsPrice;

    private Long deviceCount;

    private String displayName;

    private String name;

    private APIDevice.OsType osType;

    private Long userId;

    public APIDeviceGroup() {
    }

    public APIDeviceGroup(
            Long id, String name, String displayName, APIDevice.OsType osType, Long deviceCount,
            Long creditsPrice, Long userId) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.creditsPrice = creditsPrice;
        this.deviceCount = deviceCount;
        this.userId = userId;
        this.osType = osType;
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

    public Long getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Long deviceCount) {
        this.deviceCount = deviceCount;
    }

    public Long getCreditsPrice() {
        return creditsPrice;
    }

    public void setCreditsPrice(Long creditsPrice) {
        this.creditsPrice = creditsPrice;
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

    private String getIncludedDevicesURI() {
        return createUri(selfURI, "/devices");
    }

    private String getIncludedDevicesURI(Long deviceId) {
        return createUri(selfURI, "/devices/" + deviceId);
    }

    @JsonIgnore
    public APIListResource<APIDevice> getIncludedDevicesResource() throws APIException {
        return getListResource(getIncludedDevicesURI());
    }

    /**
     * @param queryBuilder
     * @return
     * @throws APIException
     * @since 1.3.34
     */
    @JsonIgnore
    public APIListResource<APIDevice> getIncludedDevicesResource(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getIncludedDevicesURI(), queryBuilder);
    }

    /**
     * @param offset
     * @param limit
     * @param search
     * @param sort
     * @return
     * @throws APIException
     * @deprecated
     */
    @JsonIgnore
    public APIListResource<APIDevice> getIncludedDevicesResource(long offset, long limit, String search, APISort sort)
            throws APIException {
        return getListResource(getIncludedDevicesURI(), offset, limit, search, sort, APIDevice.class);
    }

    public void delete() throws APIException {
        deleteResource(selfURI);
    }

    public void addDevice(APIDevice device) throws APIException {
        postResource(getIncludedDevicesURI(), Collections.singletonMap("deviceId", device.getId()), null);
    }

    public void deleteDevice(APIDevice device) throws APIException {
        deleteResource(getIncludedDevicesURI(device.getId()));
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceGroup apiDeviceGroup = (APIDeviceGroup) from;
        cloneBase(from);
        this.creditsPrice = apiDeviceGroup.creditsPrice;
        this.deviceCount = apiDeviceGroup.deviceCount;
        this.name = apiDeviceGroup.name;
        this.displayName = apiDeviceGroup.displayName;
        this.userId = apiDeviceGroup.userId;
        this.osType = apiDeviceGroup.osType;
    }
}
