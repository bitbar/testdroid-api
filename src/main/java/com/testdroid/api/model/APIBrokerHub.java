package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testdroid.api.APIEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import jakarta.xml.bind.annotation.XmlType;

@JsonIgnoreProperties(value = {"id", "selfURI"})
public class APIBrokerHub extends APIEntity {

    @XmlType(namespace = "APIBrokerHub")
    public enum Type {
        MOBILE,
        DESKTOP
    }

    private String location;

    private String url;

    private Type type;

    public APIBrokerHub() {
        // need for serialization/deserialization
    }

    public APIBrokerHub(String location, String url, Type type) {
        this.location = location;
        this.url = url;
        this.type = type;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIBrokerHub source = (APIBrokerHub) from;
        cloneBase(source);

        this.location = source.location;
        this.url = source.url;
        this.type = source.type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("location", location)
                .append("url", url)
                .append("type", type)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        APIBrokerHub hub = (APIBrokerHub) o;

        return new EqualsBuilder()
                .append(location, hub.location)
                .append(url, hub.url)
                .append(type, hub.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(location)
                .append(url)
                .append(String.valueOf(type))
                .toHashCode();
    }
}
