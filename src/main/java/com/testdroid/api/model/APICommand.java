package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
@XmlRootElement
public class APICommand extends APIEntity {

    private String httpMethod;

    private int responseCode;

    private long timestamp;

    private long duration;

    private String requestBody;

    private String responseBody;

    private String uri;

    public APICommand() {
    }

    public APICommand(
            String httpMethod, int responseCode, long timestamp, long duration, String requestBody,
            String responseBody, String uri) {
        this.httpMethod = httpMethod;
        this.responseCode = responseCode;
        this.timestamp = timestamp;
        this.duration = duration;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
        this.uri = uri;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
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
    protected <T extends APIEntity> void clone(T from) {
        throw new UnsupportedOperationException();
    }
}
