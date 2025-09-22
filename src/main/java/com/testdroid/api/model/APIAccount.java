package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
public class APIAccount extends APIEntity {

    private String comment;

    private Date createTime;

    private String name;

    private String userName;

    @JsonInclude(Include.NON_NULL)
    private APIInvoiceDetails invoiceDetails;

    @JsonInclude(Include.NON_NULL)
    private String activeServiceName;

    @JsonInclude(Include.NON_NULL)
    private Long dedicatedDevicesCount;

    private String slmOrganizationId;

    @JsonInclude(Include.NON_NULL)
    private Long publicDevicesUsage;

    @JsonInclude(Include.NON_NULL)
    private Long publicDevicesConcurrency;

    @JsonInclude(Include.NON_NULL)
    private Float publicDevicesRatio;

    @JsonInclude(Include.NON_NULL)
    private Long dedicatedDevicesUsage;

    @JsonInclude(Include.NON_NULL)
    private Long dedicatedDevicesConcurrency;

    @JsonInclude(Include.NON_NULL)
    private Float dedicatedDevicesRatio;

    public APIAccount() {
    }

    @SuppressWarnings("squid:S107")
    public APIAccount(
            Long id, String slmOrganizationId, String name, String userName, String comment, LocalDateTime createTime) {
        super(id);
        this.slmOrganizationId = slmOrganizationId;
        this.name = name;
        this.userName = userName;
        this.comment = comment;
        this.createTime = TimeConverter.toDate(createTime);
    }

    @SuppressWarnings("squid:S107")
    public APIAccount(
            Long id, String slmOrganizationId, String name, String userName, String comment, LocalDateTime createTime,
            Long publicDevicesUsage, Long publicDevicesConcurrency, Float publicDevicesRatio,
            Long dedicatedDevicesUsage, Long dedicatedDevicesConcurrency, Float dedicatedDevicesRatio) {
        this(id, slmOrganizationId, name, userName, comment, createTime);
        this.publicDevicesUsage = publicDevicesUsage;
        this.publicDevicesConcurrency = publicDevicesConcurrency;
        this.publicDevicesRatio = publicDevicesRatio;
        this.dedicatedDevicesUsage = dedicatedDevicesUsage;
        this.dedicatedDevicesConcurrency = dedicatedDevicesConcurrency;
        this.dedicatedDevicesRatio = dedicatedDevicesRatio;
    }

    @SuppressWarnings("squid:S107")
    public APIAccount(
            Long id, String slmOrganizationId, String name, String userName, String comment, LocalDateTime createTime,
            Long publicDevicesUsage, Long publicDevicesConcurrency, Float publicDevicesRatio,
            Long dedicatedDevicesUsage, Long dedicatedDevicesConcurrency, Float dedicatedDevicesRatio,
            long dedicatedDevicesCount, String activeServiceName) {
        this(id, slmOrganizationId, name, userName, comment, createTime, publicDevicesUsage, publicDevicesConcurrency,
                publicDevicesRatio, dedicatedDevicesUsage, dedicatedDevicesConcurrency, dedicatedDevicesRatio);
        this.dedicatedDevicesCount = dedicatedDevicesCount;
        this.activeServiceName = activeServiceName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActiveServiceName() {
        return activeServiceName;
    }

    public void setActiveServiceName(String activeServiceName) {
        this.activeServiceName = activeServiceName;
    }

    public Long getDedicatedDevicesCount() {
        return dedicatedDevicesCount;
    }

    public void setDedicatedDevicesCount(Long dedicatedDevicesCount) {
        this.dedicatedDevicesCount = dedicatedDevicesCount;
    }

    public APIInvoiceDetails getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(APIInvoiceDetails invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    public String getSlmOrganizationId() {
        return slmOrganizationId;
    }

    public void setSlmOrganizationId(String slmOrganizationId) {
        this.slmOrganizationId = slmOrganizationId;
    }

    public Long getPublicDevicesUsage() {
        return publicDevicesUsage;
    }

    public void setPublicDevicesUsage(Long publicDevicesUsage) {
        this.publicDevicesUsage = publicDevicesUsage;
    }

    public Long getPublicDevicesConcurrency() {
        return publicDevicesConcurrency;
    }

    public void setPublicDevicesConcurrency(Long publicDevicesConcurrency) {
        this.publicDevicesConcurrency = publicDevicesConcurrency;
    }

    public Float getPublicDevicesRatio() {
        return publicDevicesRatio;
    }

    public void setPublicDevicesRatio(Float publicDevicesRatio) {
        this.publicDevicesRatio = publicDevicesRatio;
    }

    public Long getDedicatedDevicesUsage() {
        return dedicatedDevicesUsage;
    }

    public void setDedicatedDevicesUsage(Long dedicatedDevicesUsage) {
        this.dedicatedDevicesUsage = dedicatedDevicesUsage;
    }

    public Long getDedicatedDevicesConcurrency() {
        return dedicatedDevicesConcurrency;
    }

    public void setDedicatedDevicesConcurrency(Long dedicatedDevicesConcurrency) {
        this.dedicatedDevicesConcurrency = dedicatedDevicesConcurrency;
    }

    public Float getDedicatedDevicesRatio() {
        return dedicatedDevicesRatio;
    }

    public void setDedicatedDevicesRatio(Float dedicatedDevicesRatio) {
        this.dedicatedDevicesRatio = dedicatedDevicesRatio;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAccount account = (APIAccount) from;
        cloneBase(from);
        this.createTime = account.createTime;
        this.activeServiceName = account.activeServiceName;
        this.dedicatedDevicesCount = account.dedicatedDevicesCount;
        this.comment = account.comment;
        this.name = account.name;
        this.userName = account.userName;
        this.invoiceDetails = account.invoiceDetails;
        this.slmOrganizationId = account.slmOrganizationId;
        this.publicDevicesUsage = account.publicDevicesUsage;
        this.publicDevicesConcurrency = account.publicDevicesConcurrency;
        this.publicDevicesRatio = account.publicDevicesRatio;
        this.dedicatedDevicesUsage = account.dedicatedDevicesUsage;
        this.dedicatedDevicesConcurrency = account.dedicatedDevicesConcurrency;
        this.dedicatedDevicesRatio = account.dedicatedDevicesRatio;
    }
}
