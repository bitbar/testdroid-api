package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author kajdus
 */
@XmlRootElement
public class APIDevice extends APIEntity {
    
    @XmlType
    public static enum Filter { FREE, RECOMMENDED, NEW }
    
    @XmlType
    public static enum OsType {
        IOS("iOS"),
        ANDROID("Android");
        
        private String displayName;

        private OsType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
    
    private String displayName;
    private APISoftwareVersion softwareVersion;
    private Integer creditsPrice;
    private String imagePrefix;
    private Integer imageTop;
    private Integer imageLeft;
    private Integer imageWidth;
    private Integer imageHeight;
    private Integer frameExtraWidth;
    private APIDevice.OsType osType;

    public APIDevice() {}

    public APIDevice(Long id, String displayName, APISoftwareVersion softwareVersion, Integer creditsPrice, String imagePrefix, Integer imageTop, Integer imageLeft, 
            Integer imageWidth, Integer imageHeight, Integer frameExtraWidth, OsType osType) {
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
    
}
