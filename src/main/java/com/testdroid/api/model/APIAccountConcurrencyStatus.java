package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * @author Damian Sniezek <damian.sniezek@smartbear.com>
 */
@XmlRootElement
public class APIAccountConcurrencyStatus extends APIEntity {

    private Long runningSessions;

    private Long waitingSessions;

    private Long sessions;

    private Integer accountConcurrency;

    private String mainUserEmail;

    private Boolean unlimitedConcurrency;

    APIAccountConcurrencyStatus(){

    }

    public Integer getAccountConcurrency() {
        return accountConcurrency;
    }

    public Long getRunningSessions() {
        return runningSessions;
    }

    public Long getWaitingSessions() {
        return waitingSessions;
    }

    public Long getSessions() {
        return sessions;
    }

    public String getMainUserEmail() {
        return mainUserEmail;
    }

    public Boolean getUnlimitedConcurrency() {
        return unlimitedConcurrency;
    }

    public APIAccountConcurrencyStatus(
            Long accountId, String mainUserEmail, Long waitingSessions, Long runningSessions,
            Integer accountConcurrency, Boolean hasMobileConcurrencyRole) {
        super(accountId);
        this.runningSessions = runningSessions;
        this.waitingSessions = waitingSessions;
        this.sessions = runningSessions + waitingSessions;
        this.mainUserEmail = mainUserEmail;
        this.accountConcurrency = accountConcurrency;
        this.unlimitedConcurrency = hasMobileConcurrencyRole && Objects.isNull(accountConcurrency);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAccountConcurrencyStatus origin = (APIAccountConcurrencyStatus) from;
        cloneBase(from);
        this.runningSessions = origin.runningSessions;
        this.waitingSessions = origin.waitingSessions;
        this.sessions = origin.sessions;
        this.accountConcurrency = origin.accountConcurrency;
        this.mainUserEmail = origin.mainUserEmail;
    }
}
