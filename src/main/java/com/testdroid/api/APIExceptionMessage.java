package com.testdroid.api;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class used to return body of API response with exception
 *
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
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

}
