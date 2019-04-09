package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIDesktopBrowser extends APIEntity {

    private String name;

    private String value;

    private List<String> versions = new ArrayList<>();

    public APIDesktopBrowser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getVersions() {
        return versions;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDesktopBrowser apiDeviceFilterGroup = (APIDesktopBrowser) from;
        cloneBase(from);
        this.name = apiDeviceFilterGroup.name;
        this.value = apiDeviceFilterGroup.value;
        this.versions = apiDeviceFilterGroup.versions;
    }
}
