package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

/**
 * @author Artur Ćwikliński <artur.cwiklinski@smartbear.com>
 */
public class APIVisualTestAccess extends APIEntity {

    private boolean enabled;

    private String apiKey;

    public APIVisualTestAccess() {
    }

    public APIVisualTestAccess(Long id, boolean status, String apiKey) {
        super(id);
        this.enabled = status;
        this.apiKey = apiKey;
    }

    public APIVisualTestAccess(boolean status, String apiKey) {
        this.enabled = status;
        this.apiKey = apiKey;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIVisualTestAccess visualTestAccess = (APIVisualTestAccess) from;
        cloneBase(from);
        this.enabled = visualTestAccess.enabled;
        this.apiKey = visualTestAccess.apiKey;
    }
}
