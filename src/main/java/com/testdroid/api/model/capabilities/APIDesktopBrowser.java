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
public class APIDesktopBrowser extends APIEntity {

    private String name;

    private String displayName;

    private String value;

    private List<String> versions = new ArrayList<>();

    private APIDesktopBrowser() {
    }

    public APIDesktopBrowser(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
        this.value = name.toLowerCase();
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

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public List<String> getVersions() {
        return versions;
    }

    public APIDesktopBrowser setVersions(List<String> versions) {
        this.versions = versions;
        return this;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDesktopBrowser original = (APIDesktopBrowser) from;
        cloneBase(from);
        this.name = original.name;
        this.displayName = original.displayName;
        this.value = original.value;
        this.versions = original.versions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        APIDesktopBrowser that = (APIDesktopBrowser) o;
        return name.equals(that.name) &&
                displayName.equals(that.displayName) &&
                value.equals(that.value) &&
                Objects.equals(versions, that.versions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, displayName, value, versions);
    }


}
