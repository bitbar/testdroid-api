package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testdroid.api.APIEntity;

@JsonIgnoreProperties(value = "apiKey", allowSetters = true)
public class APIVisualTest extends APIEntity {

    private Boolean enabled;

    @JsonProperty("apiKey")
    private String apiKey;

    public APIVisualTest() {
    }

    public APIVisualTest(Long id, boolean status, String apiKey) {
        super(id);
        this.enabled = status;
        this.apiKey = apiKey;
    }

    public APIVisualTest(boolean status, String apiKey) {
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
        APIVisualTest visualTest = (APIVisualTest) from;
        cloneBase(from);
        this.enabled = visualTest.enabled;
    }
}
