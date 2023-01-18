package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlType;
import java.util.EnumMap;
import java.util.Map;

public class APIAccountConcurrencyStatusMap extends APIEntity {

    @XmlType(namespace = "APIAccountConcurrencyStatusMap")
    public enum Type {
        AUTOMATIC,
        MANUAL
    }

    private String mainUserEmail;

    private Map<Type, APIAccountConcurrencyStatus> statusMap = new EnumMap<>(Type.class);

    public APIAccountConcurrencyStatusMap() {
    }

    public APIAccountConcurrencyStatusMap(Long accountId, String mainUserEmail) {
        super(accountId);
        this.mainUserEmail = mainUserEmail;
    }

    public String getMainUserEmail() {
        return mainUserEmail;
    }

    public void setMainUserEmail(String mainUserEmail) {
        this.mainUserEmail = mainUserEmail;
    }

    @JsonAnyGetter
    public Map<Type, APIAccountConcurrencyStatus> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<Type, APIAccountConcurrencyStatus> statusMap) {
        this.statusMap = statusMap;
    }

    @JsonAnySetter
    public void add(String type, APIAccountConcurrencyStatus status) {
        add(Type.valueOf(type), status);
    }

    public void add(Type type, APIAccountConcurrencyStatus status) {
        statusMap.put(type, status);
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAccountConcurrencyStatusMap origin = (APIAccountConcurrencyStatusMap) from;
        cloneBase(from);
        this.setStatusMap(origin.getStatusMap());
    }
}
