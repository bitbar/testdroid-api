package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIDevice extends APIEntity {

    @XmlType
    @Deprecated
    public enum DeviceFilter {
        FREE,
        RECOMMENDED,
        NEW
    }

    @XmlType
    public enum DeviceGroupOrigin {
        STATIC,
        DYNAMIC,
        HYBRID
    }

    @XmlType(namespace = "APIDevice")
    public enum OsType {
        IOS("iOS"),
        ANDROID("Android"),
        UNDEFINED("Undefined");

        private String displayName;

        private OsType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public boolean isAndroid() {
            return this == ANDROID;
        }

        public boolean isIos() {
            return this == IOS;
        }
    }

    private Integer creditsPrice;

    private String displayName;

    private String frame100Url;

    private String frame160Url;

    private String frame400Url;

    private String frame80Url;

    private Integer frameExtraWidth;

    private Integer imageHeight;

    private Integer imageLeft;

    private String imagePrefix;

    private Integer imageTop;

    private Integer imageWidth;

    private Boolean locked;

    private Boolean online;

    private APIDevice.OsType osType;

    private APISoftwareVersion softwareVersion;

    private Boolean vncSupported;

    private Boolean enabled;

    private Long accountId;

    private String mainUserEmail;

    private APIList<APIDeviceProperty> properties;

    private DeviceGroupOrigin deviceGroupOrigin;

    public APIDevice() {
    }

    public APIDevice(
            Long id, String displayName, APISoftwareVersion softwareVersion, Integer creditsPrice,
            String imagePrefix, Integer imageTop, Integer imageLeft, Integer imageWidth, Integer imageHeight,
            Integer frameExtraWidth, OsType osType, String frame80Url, String frame100Url, String frame160Url,
            String frame400Url, Boolean online, Boolean locked, Boolean vncSupported, Boolean enabled,
            Long accountId, String mainUserEmail) {
        this(id, displayName, softwareVersion, creditsPrice, imagePrefix, imageTop, imageLeft, imageWidth,
                imageHeight, frameExtraWidth, osType, frame80Url, frame100Url, frame160Url, frame400Url, online,
                locked, vncSupported, enabled);
        this.accountId = accountId;
        this.mainUserEmail = mainUserEmail;
    }

    public APIDevice(
            Long id, String displayName, APISoftwareVersion softwareVersion, Integer creditsPrice,
            String imagePrefix, Integer imageTop, Integer imageLeft, Integer imageWidth, Integer imageHeight,
            Integer frameExtraWidth, OsType osType, String frame80Url, String frame100Url, String frame160Url,
            String frame400Url, Boolean online, Boolean locked, Boolean vncSupported, Boolean enabled) {
        super(id);
        this.displayName = displayName;
        this.softwareVersion = softwareVersion;
        this.creditsPrice = creditsPrice;
        this.imagePrefix = imagePrefix;
        this.imageTop = imageTop;
        this.imageLeft = imageLeft;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.frameExtraWidth = frameExtraWidth;
        this.osType = osType;
        this.frame80Url = frame80Url;
        this.frame100Url = frame100Url;
        this.frame160Url = frame160Url;
        this.frame400Url = frame400Url;
        this.locked = locked;
        this.online = online;
        this.vncSupported = vncSupported;
        this.enabled = enabled;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public APISoftwareVersion getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(APISoftwareVersion softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public Integer getCreditsPrice() {
        return creditsPrice;
    }

    public void setCreditsPrice(Integer creditsPrice) {
        this.creditsPrice = creditsPrice;
    }

    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }

    public Integer getImageTop() {
        return imageTop;
    }

    public void setImageTop(Integer imageTop) {
        this.imageTop = imageTop;
    }

    public Integer getImageLeft() {
        return imageLeft;
    }

    public void setImageLeft(Integer imageLeft) {
        this.imageLeft = imageLeft;
    }

    public Integer getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    public Integer getFrameExtraWidth() {
        return frameExtraWidth;
    }

    public void setFrameExtraWidth(Integer frameExtraWidth) {
        this.frameExtraWidth = frameExtraWidth;
    }

    public OsType getOsType() {
        return osType;
    }

    public void setOsType(OsType osType) {
        this.osType = osType;
    }

    public String getFrame80Url() {
        return frame80Url;
    }

    public void setFrame80Url(String frame80Url) {
        this.frame80Url = frame80Url;
    }

    public String getFrame100Url() {
        return frame100Url;
    }

    public void setFrame100Url(String frame100Url) {
        this.frame100Url = frame100Url;
    }

    public String getFrame160Url() {
        return frame160Url;
    }

    public void setFrame160Url(String frame160Url) {
        this.frame160Url = frame160Url;
    }

    public String getFrame400Url() {
        return frame400Url;
    }

    public void setFrame400Url(String frame400Url) {
        this.frame400Url = frame400Url;
    }

    public Boolean isLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean isOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean isVncSupported() {
        return vncSupported;
    }

    public void setVncSupported(Boolean vncSupported) {
        this.vncSupported = vncSupported;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getMainUserEmail() {
        return mainUserEmail;
    }

    public void setMainUserEmail(String mainUserEmail) {
        this.mainUserEmail = mainUserEmail;
    }

    public APIList<APIDeviceProperty> getProperties() {
        return properties;
    }

    public void setProperties(APIList<APIDeviceProperty> properties) {
        this.properties = properties;
    }

    public DeviceGroupOrigin getDeviceGroupOrigin() {
        return deviceGroupOrigin;
    }

    public void setDeviceGroupOrigin(DeviceGroupOrigin deviceGroupOrigin) {
        this.deviceGroupOrigin = deviceGroupOrigin;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDevice apiDevice = (APIDevice) from;
        cloneBase(from);
        this.creditsPrice = apiDevice.creditsPrice;
        this.displayName = apiDevice.displayName;
        this.frame80Url = apiDevice.frame80Url;
        this.frame100Url = apiDevice.frame100Url;
        this.frame160Url = apiDevice.frame160Url;
        this.frame400Url = apiDevice.frame400Url;
        this.frameExtraWidth = apiDevice.frameExtraWidth;
        this.imageHeight = apiDevice.imageHeight;
        this.imageLeft = apiDevice.imageLeft;
        this.imagePrefix = apiDevice.imagePrefix;
        this.imageTop = apiDevice.imageTop;
        this.imageWidth = apiDevice.imageWidth;
        this.osType = apiDevice.osType;
        this.softwareVersion = apiDevice.softwareVersion;
        this.online = apiDevice.online;
        this.locked = apiDevice.locked;
        this.vncSupported = apiDevice.vncSupported;
        this.enabled = apiDevice.enabled;
        this.accountId = apiDevice.accountId;
        this.mainUserEmail = apiDevice.mainUserEmail;
        this.deviceGroupOrigin = apiDevice.deviceGroupOrigin;
        this.properties = apiDevice.properties;
    }

}
