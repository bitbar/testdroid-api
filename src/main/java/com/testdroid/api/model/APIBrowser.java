package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class APIBrowser extends APIEntity {

    private String name;

    private String version;

    public APIBrowser() {
    }

    public APIBrowser(Long id, String name, String version) {
        super(id);
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIBrowser original = (APIBrowser) from;
        cloneBase(from);
        this.name = original.name;
        this.version = original.version;
    }
}
