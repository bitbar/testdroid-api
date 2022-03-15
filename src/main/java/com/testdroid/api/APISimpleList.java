package com.testdroid.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@JsonIgnoreProperties(value = {"id", "selfURI"})
public class APISimpleList<T extends APIEntity> extends APIEntity {

    private List<T> data;

    public APISimpleList() {
        // need for serialization
    }

    public APISimpleList(List<T> data) {
        this.data = data;
    }

    @Override
    @JsonIgnore
    protected <V extends APIEntity> void clone(V from) {
        APISimpleList<T> source = (APISimpleList<T>) from;
        cloneBase(from);
        this.data = source.data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
