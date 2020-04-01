package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@JsonIgnoreProperties(value = {"id"})
@XmlRootElement
public class APIUserPreference extends APIEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long httpSessionMaxInactiveInterval;

    public void setHttpSessionMaxInactiveInterval(Long httpSessionMaxInactiveInterval) {
        this.httpSessionMaxInactiveInterval = httpSessionMaxInactiveInterval;
    }

    public Long getHttpSessionMaxInactiveInterval() {
        return httpSessionMaxInactiveInterval;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIUserPreference prototype = (APIUserPreference) from;
        cloneBase(from);
        this.httpSessionMaxInactiveInterval = prototype.httpSessionMaxInactiveInterval;
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
        return Objects.equals(httpSessionMaxInactiveInterval, that.httpSessionMaxInactiveInterval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpSessionMaxInactiveInterval);
    }
}
