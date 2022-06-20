package com.testdroid.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class used to return body of API response with exception
 *
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
public class APIExceptionMessage extends APIMessage {

    private Integer statusCode;

    public APIExceptionMessage() {
    }

    public APIExceptionMessage(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("message", getMessage())
                .append("statusCode", statusCode)
                .toString();
    }

}
