package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

/**
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APITestCaseRunStep extends APIEntity {

    private String description;

    private Long duration;

    private String httpMethod;

    private int responseCode;

    private long timestamp;

    private String requestBody;

    private String responseBody;

    private String uri;

    public APITestCaseRunStep() {
    }

    @SuppressWarnings("squid:S107")
    public APITestCaseRunStep(
            Long id, Long duration, String httpMethod, int responseCode, long timestamp, String requestBody,
            String responseBody, String uri) {
        super(id);
        this.description = String.format("%s - %s", httpMethod, uri);
        this.duration = duration;
        this.httpMethod = httpMethod;
        this.responseCode = responseCode;
        this.timestamp = timestamp;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APITestCaseRunStep apiTestCaseRunStep = (APITestCaseRunStep) from;
        cloneBase(from);
        this.description = apiTestCaseRunStep.description;
        this.duration = apiTestCaseRunStep.duration;
        this.httpMethod = apiTestCaseRunStep.httpMethod;
        this.responseCode = apiTestCaseRunStep.responseCode;
        this.timestamp = apiTestCaseRunStep.timestamp;
        this.requestBody = apiTestCaseRunStep.requestBody;
        this.responseBody = apiTestCaseRunStep.responseBody;
        this.uri = apiTestCaseRunStep.uri;
    }

}
