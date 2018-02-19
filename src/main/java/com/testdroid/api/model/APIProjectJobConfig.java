package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import org.apache.commons.lang3.EnumUtils;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIProjectJobConfig extends APIEntity {

    @XmlType(namespace = "APIProjectJobConfig")
    public enum Type {
        DEFAULT(APIDevice.OsType.ANDROID, "Android instrumentation"),
        INSTATEST(APIDevice.OsType.ANDROID, "Android App Crawler"),
        INTERACTIVE(APIDevice.OsType.ANDROID, "Testdroid Interactive"),
        INTERACTIVE_IOS(APIDevice.OsType.IOS, "Testdroid iOS Interactive"),
        UIAUTOMATOR(APIDevice.OsType.ANDROID, "UI Automator"),
        CALABASH(APIDevice.OsType.ANDROID, "Android Calabash"),
        CALABASH_IOS(APIDevice.OsType.IOS, "iOS Calabash"),
        APPIUM_ANDROID_SERVER_SIDE(APIDevice.OsType.ANDROID, "Android Appium server side"),
        APPIUM_ANDROID(APIDevice.OsType.ANDROID, "Android Appium"),
        APPIUM_IOS(APIDevice.OsType.IOS, "iOS Appium"),
        APPIUM_IOS_SERVER_SIDE(APIDevice.OsType.IOS, "iOS Appium server side"),
        APPCRAWLER_IOS(APIDevice.OsType.IOS, "AppCrawler iOS"),
        XCTEST(APIDevice.OsType.IOS, "iOS XCTest"),
        XCUITEST(APIDevice.OsType.IOS, "iOS XCUITest"),
        GENERIC(APIDevice.OsType.UNDEFINED, "Undefined Framework");

        private String name;

        private APIDevice.OsType osType;

        private Type(APIDevice.OsType osType, String name) {
            this.osType = osType;
            this.name = name;
        }

        public APIDevice.OsType getOsType() {
            return osType;
        }

        public String getName() {
            return name;
        }
    }

    private String content;

    private Long frameworkId;

    private boolean global;

    private Date lastModificationTime;

    private String lastModifiedBy;

    private Type type;

    private Integer version;

    public APIProjectJobConfig() {
    }

    public APIProjectJobConfig(
            Long id, String type, String content, Integer version, boolean global,
            Date lastModificationTime, String lastModifiedBy, Long frameworkId) {
        this(id, EnumUtils.getEnum(Type.class, type), content, version, global,
                lastModificationTime, lastModifiedBy, frameworkId);
    }

    public APIProjectJobConfig(
            Long id, Type type, String content, Integer version, boolean global,
            Date lastModificationTime, String lastModifiedBy, Long frameworkId) {
        super(id);
        this.type = type;
        this.content = content;
        this.version = version;
        this.global = global;
        this.lastModificationTime = lastModificationTime;
        this.lastModifiedBy = lastModifiedBy;
        this.frameworkId = frameworkId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(Date lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public Long getFrameworkId() {
        return frameworkId;
    }

    public void setFrameworkId(Long frameworkId) {
        this.frameworkId = frameworkId;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIProjectJobConfig apiProjectJobConfig = (APIProjectJobConfig) from;
        cloneBase(from);
        this.content = apiProjectJobConfig.content;
        this.type = apiProjectJobConfig.type;
        this.version = apiProjectJobConfig.version;
        this.global = apiProjectJobConfig.global;
        this.lastModificationTime = apiProjectJobConfig.lastModificationTime;
        this.lastModifiedBy = apiProjectJobConfig.lastModifiedBy;
        this.frameworkId = apiProjectJobConfig.frameworkId;
    }
}
