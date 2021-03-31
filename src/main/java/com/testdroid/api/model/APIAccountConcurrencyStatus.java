package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.FALSE;

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

    private List<String> usedBy;

    private Boolean unlimitedConcurrency;

    APIAccountConcurrencyStatus(){

    }

    public Integer getAccountConcurrency() {
        return accountConcurrency;
    }

    public APIAccountConcurrencyStatus setAccountConcurrency(Integer accountConcurrency) {
        this.accountConcurrency = accountConcurrency;
        return this;
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

    public List<String> getUsedBy() {
        return usedBy;
    }

    public APIAccountConcurrencyStatus setUnlimitedConcurrency(Boolean unlimitedConcurrency) {
        this.unlimitedConcurrency = unlimitedConcurrency;
        return this;
    }

    public APIAccountConcurrencyStatus(
            Long accountId, String mainUserEmail, String userEmails, Long waitingSessions, Long runningSessions) {
        super(accountId);
        this.runningSessions = runningSessions;
        this.waitingSessions = waitingSessions;
        this.usedBy = Arrays.asList(StringUtils.split(userEmails, ","));
        this.sessions = runningSessions + waitingSessions;
        this.mainUserEmail = mainUserEmail;
        this.unlimitedConcurrency = FALSE;
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
        this.usedBy = origin.usedBy;
    }
}
