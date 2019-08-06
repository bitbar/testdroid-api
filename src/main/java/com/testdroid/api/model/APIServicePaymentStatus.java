package com.testdroid.api.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APIServicePaymentStatus {

    @XmlType(name = "APIServicePaymentStatus")
    public enum Status {
        SUCCEEDED,
        FAILED,
        REDIRECTED
    }

    private String message;

    private String redirectUrl;

    private Status status;

    private APIAccountService accountService;

    public APIAccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(APIAccountService accountService) {
        this.accountService = accountService;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

}
