package com.testdroid.api;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
@JsonIgnoreProperties(value = {"id", "hasId"})
public class APIArray<T> extends APIEntity {

    private T[] items;

    public APIArray() {
    }

    public APIArray(T[] values) {
        this.items = values;
    }

    @XmlElement(name = "item")
    public T[] getItems() {
        return items;
    }

    public void setItems(T[] items) {
        this.items = items;
    }

    @Override
    @JsonIgnore
    protected <S extends APIEntity> void clone(S from) {
        APIArray<T> apiArray = (APIArray<T>) from;
        cloneBase(from);
        this.items = apiArray.items;
    }

}
