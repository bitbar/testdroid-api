package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class APIAccountPreference extends APIEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> allowedFileExtensions = new ArrayList<>();

    public List<String> getAllowedFileExtensions() {
        return allowedFileExtensions;
    }

    public APIAccountPreference setAllowedFileExtensions(List<String> allowedFileExtensions) {
        this.allowedFileExtensions = allowedFileExtensions;
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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        APIAccountPreference that = (APIAccountPreference) o;
        return allowedFileExtensions.equals(that.allowedFileExtensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allowedFileExtensions);
    }
}
