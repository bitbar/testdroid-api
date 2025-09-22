package com.testdroid.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import static com.testdroid.api.dto.MappingKey.ID;
import static com.testdroid.api.dto.MappingKey.SELF_URI;

@JsonIgnoreProperties(value = {ID, SELF_URI})
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
