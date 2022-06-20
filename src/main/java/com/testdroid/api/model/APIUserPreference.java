package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testdroid.api.APIEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@JsonIgnoreProperties(value = {"id"})
public class APIUserPreference extends APIEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long httpSessionMaxInactiveInterval;

    private Long testTimeout;

    private Long defaultHttpSessionMaxInactiveInterval;

    private Long defaultTestTimeout;

    public void setHttpSessionMaxInactiveInterval(Long httpSessionMaxInactiveInterval) {
        this.httpSessionMaxInactiveInterval = httpSessionMaxInactiveInterval;
    }

    public Long getHttpSessionMaxInactiveInterval() {
        return httpSessionMaxInactiveInterval;
    }

    public void setTestTimeout(Long testTimeout) {
        this.testTimeout = testTimeout;
    }

    public Long getTestTimeout() {
        return testTimeout;
    }

    public void setDefaultTestTimeout(Long defaultTestTimeout) {
        this.defaultTestTimeout = defaultTestTimeout;
    }

    public Long getDefaultTestTimeout() {
        return defaultTestTimeout;
    }

    public void setDefaultHttpSessionMaxInactiveInterval(Long defaultHttpSessionMaxInactiveInterval) {
        this.defaultHttpSessionMaxInactiveInterval = defaultHttpSessionMaxInactiveInterval;
    }

    public Long getDefaultHttpSessionMaxInactiveInterval() {
        return defaultHttpSessionMaxInactiveInterval;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIUserPreference prototype = (APIUserPreference) from;
        cloneBase(from);
        this.httpSessionMaxInactiveInterval = prototype.httpSessionMaxInactiveInterval;
        this.testTimeout = prototype.testTimeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        APIUserPreference that = (APIUserPreference) o;
        return Objects.equals(httpSessionMaxInactiveInterval, that.httpSessionMaxInactiveInterval) &&
                Objects.equals(testTimeout, that.testTimeout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpSessionMaxInactiveInterval, testTimeout);
    }
}
