package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.model.enums.APIPaymentMethod;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
public class APIBillingPeriod extends APIEntity {

    private Long userId;
    private Long accountServiceId;
    private String mail;
    private Date startBillingPeriod;
    private Date endBillingPeriod;
    private Date subscriptionStart;
    private Date subscriptionEnd;
    private String plan;
    private Long additionalHours;
    private Long totalPrice;
    private Long servicePrice;
    private Long additionalHoursPrice;
    private Boolean paid;
    private Date lastPaymentDate;
    private Date createTime;
    private APIPaymentMethod paymentMethod;
    private APIBillingPeriodType apiBillingPeriodType;

    public APIBillingPeriod() {
    }

    public APIBillingPeriod(
            Long billingPeriodId, Long accountServiceId, Long userId, String mail, String plan,
            LocalDateTime startBillingPeriod, LocalDateTime endBillingPeriod,
            LocalDateTime subscriptionStart, LocalDateTime subscriptionEnd,
            Long additionalHours, Long servicePrice, Long additionalHoursPrice,
            LocalDateTime paymentDate, LocalDateTime additionalHoursPaymentDate,
            LocalDateTime lastPaymentDate, LocalDateTime createTime, APIPaymentMethod paymentMethod) {
        super(billingPeriodId);
        this.accountServiceId = accountServiceId;
        this.userId = userId;
        this.mail = mail;
        this.plan = plan;
        this.additionalHours = additionalHours;
        this.totalPrice = servicePrice + additionalHoursPrice;
        this.startBillingPeriod = TimeConverter.toDate(startBillingPeriod);
        this.endBillingPeriod = TimeConverter.toDate(endBillingPeriod);
        this.subscriptionStart = TimeConverter.toDate(subscriptionStart);
        this.subscriptionEnd = TimeConverter.toDate(subscriptionEnd);
        this.servicePrice = servicePrice;
        this.additionalHoursPrice = additionalHoursPrice;
        this.paid = paymentDate != null && additionalHoursPaymentDate != null;
        this.lastPaymentDate = TimeConverter.toDate(lastPaymentDate);
        this.paymentMethod = paymentMethod;
        this.createTime = TimeConverter.toDate(createTime);
        this.apiBillingPeriodType = type(startBillingPeriod, endBillingPeriod, subscriptionStart, subscriptionEnd);
    }

    private static APIBillingPeriodType type(
            LocalDateTime startBillingPeriod, LocalDateTime endBillingPeriod, LocalDateTime subscriptionStart,
            LocalDateTime subscriptionEnd) {
        if (startBillingPeriod == null && endBillingPeriod == null
                && subscriptionStart != null && subscriptionEnd != null) {
            return APIBillingPeriodType.BUY;
        }
        if (startBillingPeriod != null && endBillingPeriod != null
                && subscriptionStart == null && subscriptionEnd == null) {
            return APIBillingPeriodType.CANCEL;
        }
        return APIBillingPeriodType.CHARGE;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Long getAdditionalHours() {
        return additionalHours;
    }

    public void setAdditionalHours(Long additionalHours) {
        this.additionalHours = additionalHours;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getSubscriptionStart() {
        return subscriptionStart;
    }

    public void setSubscriptionStart(Date subscriptionStart) {
        this.subscriptionStart = subscriptionStart;
    }

    public Date getSubscriptionEnd() {
        return subscriptionEnd;
    }

    public void setSubscriptionEnd(Date subscriptionEnd) {
        this.subscriptionEnd = subscriptionEnd;
    }

    public Long getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Long servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Long getAdditionalHoursPrice() {
        return additionalHoursPrice;
    }

    public void setAdditionalHoursPrice(Long additionalHoursPrice) {
        this.additionalHoursPrice = additionalHoursPrice;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public APIPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(APIPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public APIBillingPeriodType getApiBillingPeriodType() {
        return apiBillingPeriodType;
    }

    public void setApiBillingPeriodType(APIBillingPeriodType apiBillingPeriodType) {
        this.apiBillingPeriodType = apiBillingPeriodType;
    }

    public Long getAccountServiceId() {
        return accountServiceId;
    }

    public void setAccountServiceId(Long accountServiceId) {
        this.accountServiceId = accountServiceId;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIBillingPeriod period = (APIBillingPeriod) from;
        cloneBase(from);
        this.userId = period.userId;
        this.mail = period.mail;
        this.startBillingPeriod = period.startBillingPeriod;
        this.endBillingPeriod = period.endBillingPeriod;
        this.plan = period.plan;
        this.additionalHours = period.additionalHours;
        this.totalPrice = period.totalPrice;
        this.servicePrice = period.servicePrice;
        this.additionalHoursPrice = period.additionalHoursPrice;
        this.paid = period.paid;
        this.lastPaymentDate = period.lastPaymentDate;
        this.paymentMethod = period.paymentMethod;
        this.createTime = period.createTime;
        this.apiBillingPeriodType = period.apiBillingPeriodType;
        this.accountServiceId = period.accountServiceId;
    }
}
