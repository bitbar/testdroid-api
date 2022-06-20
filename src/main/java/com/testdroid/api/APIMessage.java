package com.testdroid.api;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIMessage {

    private String message;

    public APIMessage() {
    }

    public APIMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
