package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.model.enums.APIPaymentMethod;
import com.testdroid.api.model.enums.Unit;
import com.testdroid.api.util.TimeConverter;
import jakarta.xml.bind.annotation.XmlType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIAccountService extends APIEntity {

    @XmlType(namespace = "APIAccountService")
    public enum DeactivateReason {
        INITIAL_FAILURE,
        SCA_FAILURE,
        CHARGE_FAILURE,
        CANCEL,
        SUSPENDED,
        ANOTHER_PURCHASE
    }

    private Long accountId;

    private String accountName;

    private Long activatedById;

    private String activatedByName;

    private boolean active;

    private String braintreeId;

    private Date createTime;

    private Long deactivatedById;

    private String deactivatedByName;

    private Date endTime;

    private boolean finished;

    private Date lastPaymentTime;

    private APIPaymentMethod paymentMethod;

    private Integer price;

    private Integer serviceCount;

    private Long serviceId;

    private String serviceName;

    private Date startTime;

    private Unit unit;

    private Integer unitCount;

    private Integer vatRate;

    private DeactivateReason deactivateReason;

    private String subscriptionManagementURL;

    private String slmLicenseId;

    public APIAccountService() {
    }

    @SuppressWarnings("squid:S107")
    public APIAccountService(
            Long accountId, String accountName, Long activatedById, String activatedByName, boolean active,
            String braintreeId, LocalDateTime createTime, Long deactivatedById,
            String deactivatedByName, LocalDateTime endTime, boolean finished, Long id, LocalDateTime lastPaymentTime,
            APIPaymentMethod paymentMethod, Integer price, Long serviceId, LocalDateTime startTime, Integer vatRate,
            Unit unit, Integer unitCount, Integer serviceCount, String serviceName, DeactivateReason deactivateReason,
            String slmLicenseId) {
        super(id);
        this.accountId = accountId;
        this.accountName = accountName;
        this.activatedById = activatedById;
        this.activatedByName = activatedByName;
        this.active = active;
        this.braintreeId = braintreeId;
        this.createTime = TimeConverter.toDate(createTime);
        this.deactivatedById = deactivatedById;
        this.deactivatedByName = deactivatedByName;
        this.endTime = TimeConverter.toDate(endTime);
        this.finished = finished;
        this.lastPaymentTime = TimeConverter.toDate(lastPaymentTime);
        this.paymentMethod = paymentMethod;
        this.price = price;
        this.serviceId = serviceId;
        this.startTime = TimeConverter.toDate(startTime);
        this.vatRate = vatRate;
        this.unit = unit;
        this.unitCount = unitCount;
        this.serviceCount = serviceCount;
        this.serviceName = serviceName;
        this.deactivateReason = deactivateReason;
        this.slmLicenseId = slmLicenseId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getActivatedById() {
        return activatedById;
    }

    public void setActivatedById(Long activatedById) {
        this.activatedById = activatedById;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getDeactivatedById() {
        return deactivatedById;
    }

    public void setDeactivatedById(Long deactivatedById) {
        this.deactivatedById = deactivatedById;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getLastPaymentTime() {
        return lastPaymentTime;
    }

    public void setLastPaymentTime(Date lastPaymentTime) {
        this.lastPaymentTime = lastPaymentTime;
    }

    public APIPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(APIPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getVatRate() {
        return vatRate;
    }

    public void setVatRate(Integer vatRate) {
        this.vatRate = vatRate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getTotal() {
        return (price * (100 + vatRate)) / 100;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBraintreeId() {
        return braintreeId;
    }

    public void setBraintreeId(String braintreeId) {
        this.braintreeId = braintreeId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Integer getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(Integer unitCount) {
        this.unitCount = unitCount;
    }

    public Integer getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(Integer serviceCount) {
        this.serviceCount = serviceCount;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getActivatedByName() {
        return activatedByName;
    }

    public void setActivatedByName(String activatedByName) {
        this.activatedByName = activatedByName;
    }

    public String getDeactivatedByName() {
        return deactivatedByName;
    }

    public void setDeactivatedByName(String deactivatedByName) {
        this.deactivatedByName = deactivatedByName;
    }

    public DeactivateReason getDeactivateReason() {
        return deactivateReason;
    }

    public void setDeactivateReason(DeactivateReason deactivateReason) {
        this.deactivateReason = deactivateReason;
    }

    public void setSubscriptionManagementURL(String subscriptionManagementURL) {
        this.subscriptionManagementURL = subscriptionManagementURL;
    }

    public String getSubscriptionManagementURL() {
        return subscriptionManagementURL;
    }

    public String getSlmLicenseId() {
        return slmLicenseId;
    }

    public void setSlmLicenseId(String slmLicenseId) {
        this.slmLicenseId = slmLicenseId;
    }

    @JsonIgnore
    public String getVatPriceString() {
        float vatPrice = (getPrice() * getVatRate()) / 100f;
        return String.format(Locale.ENGLISH, "%.2f", vatPrice / 100);
    }

    @JsonIgnore
    public String getNetPriceString() {
        return String.format(Locale.ENGLISH, "%.2f", ((float) getPrice()) / 100);
    }

    @JsonIgnore
    public String getTotalPriceString() {
        float totalPrice = (getPrice() * (100 + getVatRate())) / 100f;
        return String.format(Locale.ENGLISH, "%.2f", totalPrice / 100);
    }

    @JsonIgnore
    @Override
    public <T extends APIEntity> void clone(T from) {
        APIAccountService accountService = (APIAccountService) from;
        cloneBase(from);
        this.accountId = accountService.accountId;
        this.accountName = accountService.accountName;
        this.active = accountService.active;
        this.finished = accountService.finished;
        this.price = accountService.price;
        this.activatedById = accountService.activatedById;
        this.activatedByName = accountService.activatedByName;
        this.createTime = accountService.createTime;
        this.deactivatedById = accountService.deactivatedById;
        this.deactivatedByName = accountService.deactivatedByName;
        this.endTime = accountService.endTime;
        this.lastPaymentTime = accountService.lastPaymentTime;
        this.paymentMethod = accountService.paymentMethod;
        this.serviceId = accountService.serviceId;
        this.startTime = accountService.startTime;
        this.vatRate = accountService.vatRate;
        this.unit = accountService.unit;
        this.unitCount = accountService.unitCount;
        this.serviceCount = accountService.serviceCount;
        this.serviceName = accountService.serviceName;
        this.braintreeId = accountService.braintreeId;
        this.deactivateReason = accountService.deactivateReason;
        this.subscriptionManagementURL = accountService.subscriptionManagementURL;
        this.slmLicenseId = accountService.slmLicenseId;
    }

    public boolean isActiveAt(Date date) {
        if (date == null) {
            return false;
        }
        if (startTime != null && (startTime.equals(date) || startTime.before(date))) {
            return endTime == null || (endTime.after(date) || endTime.equals(date));
        }
        return false;
    }
}
