package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;

import javax.xml.bind.annotation.XmlType;
import java.io.InputStream;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APIScreenshot extends APIEntity {

    @XmlType(namespace = "APIScreenshot")
    public enum Type {
        LANDSCAPE,
        PORTRAIT
    }

    private Boolean fail;

    private String originalName;

    private Long takeTimestamp;

    private Type type;

    public APIScreenshot() {
    }

    public APIScreenshot(String originalName) {
        this.originalName = originalName;
    }

    public APIScreenshot(Long id, String originalName, Boolean fail, Type type, Long takeTimestamp) {
        super(id);
        this.originalName = originalName;
        this.fail = fail;
        this.type = type;
        this.takeTimestamp = takeTimestamp;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Boolean isFail() {
        return fail;
    }

    public void setFail(Boolean fail) {
        this.fail = fail;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getTakeTimestamp() {
        return takeTimestamp;
    }

    public void setTakeTimestamp(Long takeTimestamp) {
        this.takeTimestamp = takeTimestamp;
    }

    @JsonIgnore
    public InputStream getContent() throws APIException {
        return getFile(selfURI);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIScreenshot apiScreenshot = (APIScreenshot) from;
        cloneBase(from);
        this.fail = apiScreenshot.fail;
        this.originalName = apiScreenshot.originalName;
        this.type = apiScreenshot.type;
        this.takeTimestamp = apiScreenshot.takeTimestamp;
    }

}
