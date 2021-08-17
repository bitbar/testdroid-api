package com.testdroid.api.model.capabilities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIDesktopBrowserCapabilities extends APIEntity {

    private List<APIDesktopPlatform> platforms = new ArrayList<>();

    public APIDesktopBrowserCapabilities() {
    }

    public List<APIDesktopPlatform> getPlatforms() {
        return platforms;
    }

    public APIDesktopBrowserCapabilities setPlatforms(List<APIDesktopPlatform> platforms) {
        this.platforms = platforms;
        return this;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDesktopBrowserCapabilities original = (APIDesktopBrowserCapabilities) from;
        this.platforms = original.platforms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        APIDesktopBrowserCapabilities that = (APIDesktopBrowserCapabilities) o;
        return platforms.equals(that.platforms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(platforms);
    }
}
