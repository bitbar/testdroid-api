package com.testdroid.api.um.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.formatter.CurrencyFormatter;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * @author kajdus
 */
@XmlRootElement(name = "umApiUserService", namespace = "cloud.testdroid.api.um.model")
@XmlType(name = "umApiUserService", namespace = "cloud.testdroid.api.um.model")
public class APIUserService extends APIEntity {

    private boolean active;

    private boolean autoRenew;

    private Date endTime;

    private boolean finished;

    private Integer payUnitCount;

    private String payUnitText;

    private APIPaymentMethod paymentMethod;

    private APIService service;

    private Date startTime;

    private Integer total;

    private String userEmail;

    private Long userId;

    private Integer vat;

    public APIUserService() {
    }

    public APIUserService(
            Long id, Long userId, String userEmail, APIService service, boolean active, boolean finished,
            boolean autoRenew,
            Integer total, Integer vat, APIPaymentMethod paymentMethod, Integer payUnitCount, String payUnitText,
            Date startTime, Date endTime) {
        this.id = id;
        this.userId = userId;
        this.userEmail = userEmail;
        this.service = service;
        this.active = active;
        this.finished = finished;
        this.autoRenew = autoRenew;
        this.total = total;
        this.vat = vat;
        this.paymentMethod = paymentMethod;
        this.payUnitCount = payUnitCount;
        this.payUnitText = payUnitText;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public APIService getService() {
        return service;
    }

    public void setService(APIService service) {
        this.service = service;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getTotalString() {
        return CurrencyFormatter.format(total);
    }

    public Integer getVat() {
        return vat;
    }

    public void setVat(Integer vat) {
        this.vat = vat;
    }

    public String getVatString() {
        return CurrencyFormatter.format(vat);
    }

    public Integer getNetPrice() {
        return total - vat;
    }

    public String getNetPriceString() {
        return CurrencyFormatter.format(getNetPrice());
    }

    public APIPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(APIPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getPayUnitCount() {
        return payUnitCount;
    }

    public void setPayUnitCount(Integer payUnitCount) {
        this.payUnitCount = payUnitCount;
    }

    public String getPayUnitText() {
        return payUnitText;
    }

    public void setPayUnitText(String payUnitText) {
        this.payUnitText = payUnitText;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @JsonIgnore
    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIUserService apiUserService = (APIUserService) from;
        cloneBase(from);
        this.service = apiUserService.service;
        this.active = apiUserService.active;
        this.finished = apiUserService.finished;
        this.autoRenew = apiUserService.autoRenew;
        this.total = apiUserService.total;
        this.vat = apiUserService.vat;
        this.userId = apiUserService.userId;
        this.userEmail = apiUserService.userEmail;
        this.paymentMethod = apiUserService.paymentMethod;
        this.payUnitCount = apiUserService.payUnitCount;
        this.payUnitText = apiUserService.payUnitText;
        this.startTime = apiUserService.startTime;
        this.endTime = apiUserService.endTime;
    }

    public boolean isActiveAt(Date date) {
        if (startTime != null && (startTime.equals(date) || startTime.before(date))) {
            if (endTime == null || (endTime != null && (endTime.after(date) || endTime.equals(date)))) {
                return true;
            }
        }
        return false;
    }

}
