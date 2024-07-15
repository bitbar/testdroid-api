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

    private Long defaultTestTimeout;

    public List<String> getAllowedFileExtensions() {
        return allowedFileExtensions;
    }

    public APIAccountPreference setAllowedFileExtensions(List<String> allowedFileExtensions) {
        this.allowedFileExtensions = allowedFileExtensions;
        return this;
    }

    public Long getTestTimeout() {
        return testTimeout;
    }

    public APIAccountPreference setTestTimeout(Long testTimeout) {
        this.testTimeout = testTimeout;
        return this;
    }

    public Long getDefaultTestTimeout() {
        return defaultTestTimeout;
    }

    public APIAccountPreference setDefaultTestTimeout(Long defaultTestTimeout) {
        this.defaultTestTimeout = defaultTestTimeout;
        return this;
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
                && Objects.equals(defaultTestTimeout, that.defaultTestTimeout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allowedFileExtensions, testTimeout, defaultTestTimeout);
    }
}
