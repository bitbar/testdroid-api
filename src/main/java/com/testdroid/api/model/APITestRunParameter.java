package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APITestRunParameter extends APIEntity {

    private String key;

    private String value;

    public APITestRunParameter() {
    }

    public APITestRunParameter(Long id, String key, String value) {
        super(id);
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APITestRunParameter apiTestRunParameter = (APITestRunParameter) from;
        cloneBase(from);
        this.key = apiTestRunParameter.key;
        this.value = apiTestRunParameter.value;
    }

}
