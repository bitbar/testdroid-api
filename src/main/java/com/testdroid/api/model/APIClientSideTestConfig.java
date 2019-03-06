package com.testdroid.api.model;

import org.apache.commons.lang3.EnumUtils;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Optional;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIClientSideTestConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private String browserName;

    private String platform;

    private String version;

    private Target target;

    private String screenResolution;

    private String appiumVersion;

    @XmlType(namespace = "APIClientSideTestConfig")
    public enum Target {
        ANDROID,
        IOS,
        SELENDROID,
        SAFARI,
        CHROME,
        XCUITEST,
        DESKTOP;

        public static Optional<Target> fromString(String name) {
            return Optional.ofNullable(EnumUtils.getEnum(Target.class, name.toUpperCase()));
        }
    }

    public APIClientSideTestConfig() {
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getAppiumVersion() {
        return appiumVersion;
    }

    public void setAppiumVersion(String appiumVersion) {
        this.appiumVersion = appiumVersion;
    }
}
