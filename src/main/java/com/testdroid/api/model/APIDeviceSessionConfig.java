package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
public class APIDeviceSessionConfig extends APIEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String adbVersion;

    private String browserName;

    private String browserVersion;

    private Long deviceModelId;

    private String screenResolution;

    private APITunnelSettings tunnelSettings;

    private APIDeviceSession.Type type = APIDeviceSession.Type.MANUAL;

    private String url;

    public String getAdbVersion() {
        return adbVersion;
    }

    public void setAdbVersion(String adbVersion) {
        this.adbVersion = adbVersion;
    }

    public Long getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(Long deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    public void setType(APIDeviceSession.Type type) {
        this.type = type;
    }

    public APIDeviceSession.Type getType() {
        return type;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public APITunnelSettings getTunnelSettings() {
        return tunnelSettings;
    }

    public void setTunnelSettings(APITunnelSettings tunnelSettings) {
        this.tunnelSettings = tunnelSettings;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceSessionConfig prototype = (APIDeviceSessionConfig) from;
        cloneBase(from);
        this.adbVersion = prototype.adbVersion;
        this.browserName = prototype.browserName;
        this.browserVersion = prototype.browserVersion;
        this.deviceModelId = prototype.deviceModelId;
        this.screenResolution = prototype.screenResolution;
        this.tunnelSettings = prototype.tunnelSettings;
        this.type = prototype.type;
        this.url = prototype.url;
    }
}
