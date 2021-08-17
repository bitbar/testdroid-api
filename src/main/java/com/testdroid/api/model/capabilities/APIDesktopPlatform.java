package com.testdroid.api.model.capabilities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIDesktopPlatform extends APIEntity {

    private String name;

    private String value;

    private Set<APIDesktopBrowser> browsers = new HashSet<>();

    private List<String> resolutions = new ArrayList<>();

    private APIDesktopPlatform() {
    }

    public APIDesktopPlatform(String name) {
        this.name = name;
        this.value = name.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Set<APIDesktopBrowser> getBrowsers() {
        return browsers;
    }

    public APIDesktopPlatform setBrowsers(Set<APIDesktopBrowser> browsers) {
        this.browsers = browsers;
        return this;
    }

    public List<String> getResolutions() {
        return resolutions;
    }

    public APIDesktopPlatform setResolutions(List<String> resolutions) {
        this.resolutions = resolutions;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        APIDesktopPlatform that = (APIDesktopPlatform) o;
        return name.equals(that.name) &&
                value.equals(that.value) &&
                browsers.equals(that.browsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, browsers);
    }
}
