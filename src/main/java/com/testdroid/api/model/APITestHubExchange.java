package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testdroid.api.APIEntity;

import static com.testdroid.api.dto.MappingKey.ID;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
@JsonIgnoreProperties(ID)
public class APITestHubExchange extends APIEntity {

    private String integration;

    @JsonProperty("user_api_key")
    private String apiKey;

    public APITestHubExchange() {
    }

    public APITestHubExchange(String integration, String apiKey) {
        this.integration = integration;
        this.apiKey = apiKey;
    }

    public String getIntegration() {
        return integration;
    }

    public void setIntegration(String integration) {
        this.integration = integration;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        throw new UnsupportedOperationException();
    }
}
