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
public class APIDesktopPlatform extends APIEntity {

    private String name;

    private String value;

    private List<APIDesktopBrowser> browsers = new ArrayList<>();

    private List<String> resolutions = new ArrayList<>();

    public APIDesktopPlatform() {
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

    public List<APIDesktopBrowser> getBrowsers() {
        return browsers;
    }

    public void setBrowsers(List<APIDesktopBrowser> browsers) {
        this.browsers = browsers;
    }

    public List<String> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<String> resolutions) {
        this.resolutions = resolutions;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDesktopPlatform apiDeviceFilterGroup = (APIDesktopPlatform) from;
        cloneBase(from);
        this.name = apiDeviceFilterGroup.name;
        this.value = apiDeviceFilterGroup.value;
        this.browsers = apiDeviceFilterGroup.browsers;
        this.resolutions = apiDeviceFilterGroup.resolutions;
    }
}
