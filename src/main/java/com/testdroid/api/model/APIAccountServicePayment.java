package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
public class APIAccountServicePayment extends APIEntity {

    private Long accountId;

    private Date startBillingPeriod;

    private Date endBillingPeriod;

    private String name;

    private Long totalPrice;

    private Long includedTime;

    private Long additionalTime;

    private Long usedTime;

    public APIAccountServicePayment() {
    }

    public APIAccountServicePayment(
            Long id, Long accountId, LocalDateTime startBillingPeriod, LocalDateTime endBillingPeriod, String name,
            Long totalPrice, Long includedTime, Long additionalTime, Long usedTime) {
        super(id);
        this.accountId = accountId;
        this.startBillingPeriod = TimeConverter.toDate(startBillingPeriod);
        this.endBillingPeriod = TimeConverter.toDate(endBillingPeriod);
        this.name = name;
        this.totalPrice = totalPrice;
        this.includedTime = includedTime;
        this.additionalTime = additionalTime;
        this.usedTime = usedTime;
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
        return additionalTime;
    }

    public void setAdditionalTime(Long additionalTime) {
        this.additionalTime = additionalTime;
    }

    public Long getIncludedTime() {
        return includedTime;
    }

    public void setIncludedTime(Long includedTime) {
        this.includedTime = includedTime;
    }

    public Long getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Long usedTime) {
        this.usedTime = usedTime;
    }

    @JsonIgnore
    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAccountServicePayment payment = (APIAccountServicePayment) from;
        cloneBase(from);
        this.accountId = payment.accountId;
        this.accountId = payment.accountId;
        this.startBillingPeriod = payment.startBillingPeriod;
        this.endBillingPeriod = payment.endBillingPeriod;
        this.name = payment.name;
        this.totalPrice = payment.totalPrice;
        this.includedTime = payment.includedTime;
        this.additionalTime = payment.additionalTime;
        this.usedTime = payment.usedTime;
    }
}
