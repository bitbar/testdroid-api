package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class APIAccountServicePayment extends APIEntity {

    private Long accountId;

    private Date startBillingPeriod;

    private Date endBillingPeriod;

    private String name;

    private Long totalPrice;

    public APIAccountServicePayment() {
    }

    public APIAccountServicePayment(
            Long id, Long accountId, LocalDateTime startBillingPeriod, LocalDateTime endBillingPeriod, String name,
            Long totalPrice) {
        super(id);
        this.accountId = accountId;
        this.startBillingPeriod = TimeConverter.toDate(startBillingPeriod);
        this.endBillingPeriod = TimeConverter.toDate(endBillingPeriod);
        this.name = name;
        this.totalPrice = totalPrice;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getStartBillingPeriod() {
        return startBillingPeriod;
    }

    public void setStartBillingPeriod(Date startBillingPeriod) {
        this.startBillingPeriod = startBillingPeriod;
    }

    public Date getEndBillingPeriod() {
        return endBillingPeriod;
    }

    public void setEndBillingPeriod(Date endBillingPeriod) {
        this.endBillingPeriod = endBillingPeriod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getAdditionalTime() {
        return 0L;
    }

    public Long getIncludedTime() {
        return 0L;
    }

    public Long getUsedTime() {
        return 0L;
    }

    @JsonIgnore
    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAccountServicePayment payment = (APIAccountServicePayment) from;
        cloneBase(from);
        this.accountId = payment.accountId;
        this.startBillingPeriod = payment.startBillingPeriod;
        this.endBillingPeriod = payment.endBillingPeriod;
        this.name = payment.name;
        this.totalPrice = payment.totalPrice;
    }
}
