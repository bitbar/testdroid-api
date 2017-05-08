package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
public class APIEnum extends APIEntity {

    private String value;

    public APIEnum() {
    }

    public APIEnum(Enum value) {
        super(Long.valueOf(value.ordinal()));
        this.value = value.name();
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
        APIEnum apiString = (APIEnum) from;
        cloneBase(from);
        this.value = apiString.value;
    }
}
