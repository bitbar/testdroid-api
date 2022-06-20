package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import static java.lang.Boolean.FALSE;

/**
 * @author Damian Sniezek <damian.sniezek@smartbear.com>
 */
public class APIAccountConcurrencyStatus {

    private Integer accountConcurrency;

    private Long runningSessions;

    private Long sessions;

    private Boolean unlimitedConcurrency;

    private List<String> usedBy;

    private Long waitingSessions;

    APIAccountConcurrencyStatus() {
    }

    public Integer getAccountConcurrency() {
        return accountConcurrency;
    }

    public void setAccountConcurrency(Integer accountConcurrency) {
        this.accountConcurrency = accountConcurrency;
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

    public Boolean getUnlimitedConcurrency() {
        return unlimitedConcurrency;
    }

    public List<String> getUsedBy() {
        return usedBy;
    }

    public void setUnlimitedConcurrency(Boolean unlimitedConcurrency) {
        this.unlimitedConcurrency = unlimitedConcurrency;
    }

    public APIAccountConcurrencyStatus(List<String> usedBy, Long waitingSessions, Long runningSessions) {
        this.runningSessions = runningSessions;
        this.waitingSessions = waitingSessions;
        this.usedBy = usedBy;
        this.sessions = runningSessions + waitingSessions;
        this.unlimitedConcurrency = FALSE;
    }

    @JsonIgnore
    protected void clone(APIAccountConcurrencyStatus origin) {
        this.runningSessions = origin.runningSessions;
        this.waitingSessions = origin.waitingSessions;
        this.sessions = origin.sessions;
        this.accountConcurrency = origin.accountConcurrency;
        this.usedBy = origin.usedBy;
        this.unlimitedConcurrency = origin.unlimitedConcurrency;
    }
}
