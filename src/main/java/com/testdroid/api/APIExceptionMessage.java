package com.testdroid.api;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class used to return body of API response with exception
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APIExceptionMessage {
    private Integer statusCode;
    private String message;

    public APIExceptionMessage() {}
    public APIExceptionMessage(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}