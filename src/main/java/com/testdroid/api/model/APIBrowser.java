package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class APIBrowser extends APIEntity {

    private String name;

    private String displayName;

    private String version;

    private APIDevice.OsType osType;

    public APIBrowser() {
    }

    public APIBrowser(Long id, String name, String displayName, String version, APIDevice.OsType osType) {
        super(id);
        this.name = name;
        this.displayName = displayName;
        this.version = version;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public APIDevice.OsType getOsType() {
        return osType;
    }

    public void setOsType(APIDevice.OsType osType) {
        this.osType = osType;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIBrowser original = (APIBrowser) from;
        cloneBase(from);
        this.name = original.name;
        this.displayName = original.displayName;
        this.version = original.version;
        this.osType = original.osType;
    }
}
