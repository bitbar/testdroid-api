package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIView;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.map.annotate.JsonView;

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

    @JsonView(value = {APIView.AdminView.class, APIView.MonitorView.class})
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonView(APIView.AdminView.class)
    public APISoftwareVersion getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(APISoftwareVersion softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    @JsonView(APIView.AdminView.class)
    public Integer getCreditsPrice() {
        return creditsPrice;
    }

    public void setCreditsPrice(Integer creditsPrice) {
        this.creditsPrice = creditsPrice;
    }

    @JsonView(APIView.AdminView.class)
    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }

    @JsonView(APIView.AdminView.class)
    public Integer getImageTop() {
        return imageTop;
    }

    public void setImageTop(Integer imageTop) {
        this.imageTop = imageTop;
    }

    @JsonView(APIView.AdminView.class)
    public Integer getImageLeft() {
        return imageLeft;
    }

    public void setImageLeft(Integer imageLeft) {
        this.imageLeft = imageLeft;
    }

    @JsonView(APIView.AdminView.class)
    public Integer getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    @JsonView(APIView.AdminView.class)
    public Integer getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    @JsonView(APIView.AdminView.class)
    public Integer getFrameExtraWidth() {
        return frameExtraWidth;
    }

    public void setFrameExtraWidth(Integer frameExtraWidth) {
        this.frameExtraWidth = frameExtraWidth;
    }

    @JsonView(value = {APIView.AdminView.class, APIView.MonitorView.class})
    public OsType getOsType() {
        return osType;
    }

    public void setOsType(OsType osType) {
        this.osType = osType;
    }
    
}
