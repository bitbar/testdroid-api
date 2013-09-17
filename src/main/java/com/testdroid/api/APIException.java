package com.testdroid.api;

/**
 *
 * @author kajdus
 */
public class APIException extends Exception {

    private Integer status;

    public APIException() {
        super();
    }

    public APIException(String message) {
        this(null, message);
    }

    public APIException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public APIException(Throwable t) {
        super(t);
    }

    public APIException(String message, Throwable t) {
        this(null, message, t);
    }

    public APIException(Integer status, String message, Throwable t) {
        super(message, t);
        this.status = status;
    }

    /**
     * HTTP status returned from API call or null if call hasn't been executed
     * yet.
     */
    public Integer getStatus() {
        return status;
    }
}
