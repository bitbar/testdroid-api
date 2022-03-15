package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testdroid.api.APIEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties(value = {"id", "selfURI"})
public class APIBrokerHub extends APIEntity {

    private String location;

    private String url;

    public APIBrokerHub() {
        // need for serialization/deserialization
    }

    public APIBrokerHub(String location, String url) {
        this.location = location;
        this.url = url;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIBrokerHub source = (APIBrokerHub) from;
        cloneBase(source);

        this.location = source.location;
        this.url = source.url;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("location", location)
                .append("url", url)
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

        return new EqualsBuilder().append(location, hub.location).append(url, hub.url).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(location).append(url).toHashCode();
    }
}
