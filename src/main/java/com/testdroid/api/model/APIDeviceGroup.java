package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.dto.Context;

import static java.util.Collections.singletonMap;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APIDeviceGroup extends APIEntity {

    private Long deviceCount;

    private String displayName;

    private String name;

    private APIDevice.OsType osType;

    private Long userId;

    private String userEmail;

    private boolean isShared;

    public APIDeviceGroup() {
    }

    public APIDeviceGroup(
            Long id, String name, String displayName, APIDevice.OsType osType, Long deviceCount, Long userId,
            String userEmail, boolean isShared) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.deviceCount = deviceCount;
        this.userId = userId;
        this.osType = osType;
        this.userEmail = userEmail;
        this.isShared = isShared;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
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
        return getListResource(getIncludedDevicesURI(), APIDevice.class);
    }

    @JsonIgnore
    public APIListResource<APIDevice> getIncludedDevicesResource(Context<APIDevice> context) throws APIException {
        return getListResource(getIncludedDevicesURI(), context);
    }

    public void delete() throws APIException {
        deleteResource(selfURI);
    }

    public void addDevice(APIDevice device) throws APIException {
        postResource(getIncludedDevicesURI(), singletonMap("deviceId", device.getId()), null);
    }

    public APIDeviceGroup addSelector(APIDeviceProperty deviceProperty) throws APIException {
        return postResource(createUri(selfURI, "/selectors"), singletonMap("selectorIds[]", deviceProperty
                .getId()), APIDeviceGroup.class);
    }

    public void deleteDevice(APIDevice device) throws APIException {
        deleteResource(getIncludedDevicesURI(device.getId()));
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceGroup apiDeviceGroup = (APIDeviceGroup) from;
        cloneBase(from);
        this.deviceCount = apiDeviceGroup.deviceCount;
        this.name = apiDeviceGroup.name;
        this.displayName = apiDeviceGroup.displayName;
        this.userId = apiDeviceGroup.userId;
        this.osType = apiDeviceGroup.osType;
        this.userEmail = apiDeviceGroup.userEmail;
        this.isShared = apiDeviceGroup.isShared;
    }
}
