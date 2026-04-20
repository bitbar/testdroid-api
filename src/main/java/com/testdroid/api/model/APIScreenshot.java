package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;

import java.io.InputStream;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APIScreenshot extends APIEntity {

    private String originalName;

    private Long takeTimestamp;

    public APIScreenshot() {
    }

    public APIScreenshot(String originalName) {
        this.originalName = originalName;
    }

    public APIScreenshot(Long id, String originalName, Long takeTimestamp) {
        super(id);
        this.originalName = originalName;
        this.takeTimestamp = takeTimestamp;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
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
        this.originalName = apiScreenshot.originalName;
        this.takeTimestamp = apiScreenshot.takeTimestamp;
    }

}
