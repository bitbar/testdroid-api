package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class APIAccountPreference extends APIEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<String> allowedFileExtensions = new ArrayList<>();

    private Long testTimeout;

    private Long dedicatedDevicesTestTimeout;

    private Long defaultTestTimeout;

    public List<String> getAllowedFileExtensions() {
        return allowedFileExtensions;
    }

    public void setAllowedFileExtensions(List<String> allowedFileExtensions) {
        this.allowedFileExtensions = allowedFileExtensions;
    }

    public Long getTestTimeout() {
        return testTimeout;
    }

    public void setTestTimeout(Long testTimeout) {
        this.testTimeout = testTimeout;
    }

    public Long getDedicatedDevicesTestTimeout() {
        return dedicatedDevicesTestTimeout;
    }

    public void setDedicatedDevicesTestTimeout(Long dedicatedDevicesTestTimeout) {
        this.dedicatedDevicesTestTimeout = dedicatedDevicesTestTimeout;
    }

    public Long getDefaultTestTimeout() {
        return defaultTestTimeout;
    }

    public void setDefaultTestTimeout(Long defaultTestTimeout) {
        this.defaultTestTimeout = defaultTestTimeout;
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
        this.selfURI = String.format("/accounts/%s/preferences", id);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAccountPreference prototype = (APIAccountPreference) from;
        cloneBase(from);
        this.allowedFileExtensions = prototype.allowedFileExtensions;
        this.testTimeout = prototype.testTimeout;
        this.dedicatedDevicesTestTimeout = prototype.dedicatedDevicesTestTimeout;
        this.defaultTestTimeout = prototype.defaultTestTimeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof APIAccountPreference that)) {
            return false;
        }
        return Objects.equals(allowedFileExtensions, that.allowedFileExtensions)
                && Objects.equals(testTimeout, that.testTimeout)
                && Objects.equals(dedicatedDevicesTestTimeout, that.dedicatedDevicesTestTimeout)
                && Objects.equals(defaultTestTimeout, that.defaultTestTimeout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allowedFileExtensions, testTimeout, dedicatedDevicesTestTimeout, defaultTestTimeout);
    }
}
