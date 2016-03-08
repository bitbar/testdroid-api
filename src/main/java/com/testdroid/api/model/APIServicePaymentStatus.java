package com.testdroid.api.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APIServicePaymentStatus {

    @XmlType(name = "APIServicePaymentStatus")
    public static enum Status {
        SUCCEEDED,
        FAILED,
        REDIRECTED
    }

    private String message;

    private String redirectionPostData;

    private String redirectionUrl;

    private Status status;

    private APIAccountService accountService;

    public static APIServicePaymentStatus createSucceededStatus(APIAccountService accountService) {
        return createSucceededStatus(accountService, null);
    }

    public static APIServicePaymentStatus createSucceededStatus(APIAccountService accountService, String message) {
        APIServicePaymentStatus result = new APIServicePaymentStatus();
        result.accountService = accountService;
        result.status = Status.SUCCEEDED;
        result.message = message;
        return result;
    }

    public static APIServicePaymentStatus createFailedStatus(APIAccountService accountService, String message) {
        APIServicePaymentStatus result = new APIServicePaymentStatus();
        result.accountService = accountService;
        result.status = Status.FAILED;
        result.message = message;
        return result;
    }

    public static APIServicePaymentStatus createRedirectionStatus(
            APIAccountService accountService, String redirectionUrl, String redirectionPostData) {
        APIServicePaymentStatus result = new APIServicePaymentStatus();
        result.accountService = accountService;
        result.status = Status.REDIRECTED;
        result.redirectionUrl = redirectionUrl;
        result.redirectionPostData = redirectionPostData;
        return result;
    }

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

    public String getRedirectionUrl() {
        return redirectionUrl;
    }

    public void setRedirectionUrl(String redirectionUrl) {
        this.redirectionUrl = redirectionUrl;
    }

    public String getRedirectionPostData() {
        return redirectionPostData;
    }

    public void setRedirectionPostData(String redirectionPostData) {
        this.redirectionPostData = redirectionPostData;
    }

}
