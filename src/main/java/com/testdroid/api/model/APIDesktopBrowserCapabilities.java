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
public class APIDesktopBrowserCapabilities extends APIEntity {

    private List<APIDesktopPlatform> platforms = new ArrayList<>();

    public APIDesktopBrowserCapabilities() {
    }

    public List<APIDesktopPlatform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<APIDesktopPlatform> platforms) {
        this.platforms = platforms;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDesktopBrowserCapabilities original = (APIDesktopBrowserCapabilities) from;
        this.platforms = original.platforms;
    }
}
