package com.testdroid.api.um.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.formatter.CurrencyFormatter;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author kajdus
 */
@XmlRootElement(name = "umApiUserService", namespace = "cloud.testdroid.api.um.model")
@XmlType(name="umApiUserService", namespace = "cloud.testdroid.api.um.model")
public class APIUserService extends APIEntity {
    private APIService service;
    private boolean active;
    private boolean finished;
    private boolean autoRenew;
    private Integer total;
    private Integer vat;
    private Long userId;
    private String userEmail;
    private APIPaymentMethod paymentMethod;
    private Integer payUnitCount;
    private String payUnitText;
    private Date startTime;
    private Date endTime;

    public APIUserService() {}
    
    public APIUserService(Long id, Long userId, String userEmail, APIService service, boolean active, boolean finished, boolean autoRenew, 
            Integer total, Integer vat, APIPaymentMethod paymentMethod, Integer payUnitCount, String payUnitText, Date startTime, Date endTime) {
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

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public boolean isFinished() {
        return finished;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public APIService getService() {
        return service;
    }

    public Integer getTotal() {
        return total;
    }
    
    public String getTotalString() {
        return CurrencyFormatter.format(total);
    }

    public Integer getVat() {
        return vat;
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

    public Integer getPayUnitCount() {
        return payUnitCount;
    }

    public String getPayUnitText() {
        return payUnitText;
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }


    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setService(APIService service) {
        this.service = service;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setVat(Integer vat) {
        this.vat = vat;
    }

    public void setPaymentMethod(APIPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPayUnitCount(Integer payUnitCount) {
        this.payUnitCount = payUnitCount;
    }

    public void setPayUnitText(String payUnitText) {
        this.payUnitText = payUnitText;
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
    
}
