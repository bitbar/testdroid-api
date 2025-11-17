package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIList;
import jakarta.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APIDevice extends APIEntity {

    public enum DeviceGroupOrigin {
        STATIC,
        DYNAMIC,
        HYBRID
    }

    public enum Creator {
        MANUAL,
        AUTOMATIC
    }

    @XmlType(namespace = "APIDevice")
    public enum Platform {
        IOS(OsType.IOS),
        ANDROID(OsType.ANDROID),
        WINDOWS(OsType.DESKTOP, "Windows"),
        MAC(OsType.DESKTOP, "macOS"),
        LINUX(OsType.DESKTOP, "Linux"),
        UNDEFINED(OsType.UNDEFINED);

        private final OsType osType;

        private final String displayName;

        Platform(OsType osType) {
            this(osType, osType.getDisplayName());
        }

        Platform(OsType osType, String displayName) {
            this.osType = osType;
            this.displayName = displayName;
        }

        public OsType getOsType() {
            return osType;
        }

        public String getDisplayName() {
            return displayName;
        }

        public static APIDevice.Platform getByDisplayName(String displayName) {
            return Arrays.stream(APIDevice.Platform.values())
                    .filter(p -> StringUtils.equalsIgnoreCase(p.getDisplayName(), displayName))
                    .findAny().orElse(UNDEFINED);
        }
    }

    @XmlType(namespace = "APIDevice")
    public enum OsType {
        IOS("iOS"),
        ANDROID("Android"),
        DESKTOP("Desktop"),
        UNDEFINED("Undefined");

        private final String displayName;

        OsType(String displayName) {
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

        public boolean isDesktop() {
            return this == DESKTOP;
        }

        public List<OsType> getFamily() {
            return this.isDesktop() ? singletonList(this) : asList(IOS, ANDROID);
        }

        public static final List<APIDevice.OsType> MOBILES = List.of(APIDevice.OsType.ANDROID, APIDevice.OsType.IOS);

        public static final List<APIDevice.OsType> DESKTOPS = Collections.singletonList(APIDevice.OsType.DESKTOP);
    }

    public enum LockReason {
        TESTING,
        CLEANING,
        NOT_OPERATIONAL
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

    private Integer imageCornerRadius;

    private String manufacturer;

    private Boolean locked;

    private Boolean online;

    private APIDevice.OsType osType;

    private APIDevice.Platform platform;

    private APISoftwareVersion softwareVersion;

    private Boolean enabled;

    private Long accountId;

    private String accountName;

    private APIList<APIDeviceProperty> properties;

    private APIList<APIBrowser> browsers;

    private DeviceGroupOrigin deviceGroupOrigin;

    private boolean available;

    private Set<Creator> supportedCreators;

    private APIDeviceAvailability availability;

    private String location;

    @JsonIgnore
    private Integer onlineDevices;

    @JsonIgnore
    private Integer availableDevices;

    /**
     * @deprecated with support for multi account owners it does not make sense anymore
     */
    @Deprecated
    private String mainUserEmail;

    private LockReason lockReason;

    public APIDevice() {
    }

    @SuppressWarnings("squid:S107")
    public APIDevice(
            Long id, String displayName, String manufacturer, String releaseVersion, Integer apiLevel,
            Integer creditsPrice, String imagePrefix, Integer imageTop, Integer imageLeft, Integer imageWidth,
            Integer imageHeight, Integer imageCornerRadius, Integer frameExtraWidth, OsType osType, Platform platform,
            Boolean online, Boolean locked, Boolean enabled, String location, Long accountId, String accountName,
            Integer onlineDevices, Integer availableDevices) {
        super(id);
        this.displayName = displayName;
        this.manufacturer = manufacturer;
        this.softwareVersion = new APISoftwareVersion(releaseVersion, apiLevel);
        this.creditsPrice = creditsPrice;
        this.imagePrefix = imagePrefix;
        this.imageTop = imageTop;
        this.imageLeft = imageLeft;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.imageCornerRadius = imageCornerRadius;
        this.frameExtraWidth = frameExtraWidth;
        this.osType = osType;
        this.platform = platform;
        this.locked = locked;
        this.online = online;
        this.enabled = enabled;
        this.location = location;
        this.accountId = accountId;
        this.accountName = accountName;
        this.onlineDevices = onlineDevices;
        this.availableDevices = availableDevices;
        if (Objects.nonNull(accountId)) {
            this.mainUserEmail = "TheFieldIsDeprecated@smartbear.com";
        }
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

    public Integer getImageCornerRadius() {
        return imageCornerRadius;
    }

    public void setImageCornerRadius(Integer imageCornerRadius) {
        this.imageCornerRadius = imageCornerRadius;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public APIList<APIDeviceProperty> getProperties() {
        return properties;
    }

    public void setProperties(APIList<APIDeviceProperty> properties) {
        this.properties = properties;
    }

    public APIList<APIBrowser> getBrowsers() {
        return browsers;
    }

    public void setBrowsers(APIList<APIBrowser> browsers) {
        this.browsers = browsers;
    }

    public DeviceGroupOrigin getDeviceGroupOrigin() {
        return deviceGroupOrigin;
    }

    public void setDeviceGroupOrigin(DeviceGroupOrigin deviceGroupOrigin) {
        this.deviceGroupOrigin = deviceGroupOrigin;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setSupportedCreators(Set<Creator> supportedCreators) {
        this.supportedCreators = supportedCreators;
    }

    public Set<Creator> getSupportedCreators() {
        return supportedCreators;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public APIDeviceAvailability getAvailability() {
        return availability;
    }

    public void setAvailability(APIDeviceAvailability availability) {
        this.availability = availability;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getOnlineDevices() {
        return onlineDevices;
    }

    public Integer getAvailableDevices() {
        return availableDevices;
    }

    public String getMainUserEmail() {
        return mainUserEmail;
    }

    public void setMainUserEmail(String mainUserEmail) {
        this.mainUserEmail = mainUserEmail;
    }

    public LockReason getLockReason() {
        return lockReason;
    }

    public void setLockReason(LockReason lockReason) {
        this.lockReason = lockReason;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDevice apiDevice = (APIDevice) from;
        cloneBase(from);
        this.creditsPrice = apiDevice.creditsPrice;
        this.displayName = apiDevice.displayName;
        this.manufacturer = apiDevice.manufacturer;
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
        this.imageCornerRadius = apiDevice.imageCornerRadius;
        this.osType = apiDevice.osType;
        this.platform = apiDevice.platform;
        this.softwareVersion = apiDevice.softwareVersion;
        this.online = apiDevice.online;
        this.locked = apiDevice.locked;
        this.enabled = apiDevice.enabled;
        this.accountId = apiDevice.accountId;
        this.accountName = apiDevice.accountName;
        this.mainUserEmail = apiDevice.mainUserEmail;
        this.deviceGroupOrigin = apiDevice.deviceGroupOrigin;
        this.properties = apiDevice.properties;
        this.browsers = apiDevice.browsers;
        this.available = apiDevice.available;
        this.supportedCreators = apiDevice.supportedCreators;
        this.availability = apiDevice.availability;
        this.onlineDevices = apiDevice.onlineDevices;
        this.availableDevices = apiDevice.availableDevices;
        this.location = apiDevice.location;
        this.lockReason = apiDevice.lockReason;
    }
}
