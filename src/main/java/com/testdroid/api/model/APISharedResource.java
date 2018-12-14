package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APISharedResource extends APIEntity {

    @XmlType(namespace = "APISharedResource")
    public enum Type {
        DEVICE_GROUP,
        FILE,
        FILE_SET,
        PROJECT
    }

    private Long resourceId;

    private String name;

    private Type type;

    public APISharedResource() {
    }

    public APISharedResource(Long id, Long resourceId, String name, Type type) {
        super(id);
        this.resourceId = resourceId;
        this.name = name;
        this.type = type;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APISharedResource original = (APISharedResource) from;
        cloneBase(from);
        this.resourceId = original.resourceId;
        this.name = original.name;
        this.type = original.type;
    }
}
