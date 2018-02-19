package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlType;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIMarketShare extends APIEntity {

    @XmlType(namespace = "APIMarketShare")
    public enum Type {
        ANDROID_VERSION,
        IOS_VERSION,
        OPEN_GL_VERSION
    }

    private String name;

    private Type type;

    private Float value;

    public APIMarketShare() {
    }

    public APIMarketShare(Long id, Type type, String name, Float value) {
        super(id);
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIMarketShare apiMarketShare = (APIMarketShare) from;
        cloneBase(from);
        this.type = apiMarketShare.type;
        this.name = apiMarketShare.name;
        this.value = apiMarketShare.value;
    }
}
