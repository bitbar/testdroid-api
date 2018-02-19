package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIAdminDeviceProblemPair extends APIEntity {

    @XmlType(namespace = "APIAdminDeviceProblemPair", name = "APIAdminDeviceProblemPairType")
    public enum Type {
        OFFLINE,
        LOW_BATTERY,
        HIGH_FAIL_RATE,
        NO_INTERNET_CONNECTION
    }

    private Type type;

    private Object value;

    public APIAdminDeviceProblemPair() {
    }

    public APIAdminDeviceProblemPair(Type type) {
        this.type = type;
    }

    public APIAdminDeviceProblemPair(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAdminDeviceProblemPair cloneFrom = (APIAdminDeviceProblemPair) from;
        cloneBase(from);
        this.type = cloneFrom.type;
        this.value = cloneFrom.value;
    }

}
