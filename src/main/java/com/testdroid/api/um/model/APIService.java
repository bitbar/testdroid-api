package com.testdroid.api.um.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.formatter.CurrencyFormatter;
import com.testdroid.api.model.enums.Unit;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement(name = "umApiService", namespace = "cloud.testdroid.api.um.model")
@XmlType(name = "umApiService", namespace = "cloud.testdroid.api.um.model")
public class APIService extends APIEntity {

    private boolean autoRenew;

    private String braintreeId;

    private Integer centPrice;

    private Integer includedHours;

    private Integer pricePerHour;

    private String description;

    private String name;

    private Unit unit;

    private Integer unitCount;

    private Date archiveTime;

    private Date activateTime;

    private boolean customPlan;

    public APIService() {
    }

    public APIService(Long id, String name, String description, boolean autoRenew, Integer centPrice,
            Integer includedHours, Integer pricePerHour, String braintreeId, Date archiveTime, Date activateTime,
            Unit unit, Integer unitCount, boolean customPlan) {
        super(id);
        this.name = name;
        this.description = description;
        this.autoRenew = autoRenew;
        this.centPrice = centPrice;
        this.includedHours = includedHours;
        this.pricePerHour = pricePerHour;
        this.braintreeId = braintreeId;
        this.archiveTime = archiveTime;
        this.activateTime = activateTime;
        this.unit = unit;
        this.unitCount = unitCount;
        this.customPlan = customPlan;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public Integer getCentPrice() {
        return centPrice;
    }

    public void setCentPrice(Integer centPrice) {
        this.centPrice = centPrice;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIService apiService = (APIService) from;
        cloneBase(from);
        this.name = apiService.name;
        this.description = apiService.description;
        this.autoRenew = apiService.autoRenew;
        this.centPrice = apiService.centPrice;
        this.unitCount = apiService.unitCount;
        this.unit = apiService.unit;
        this.braintreeId = apiService.braintreeId;
        this.includedHours = apiService.includedHours;
        this.pricePerHour = apiService.pricePerHour;
        this.archiveTime = apiService.archiveTime;
        this.activateTime = apiService.activateTime;
        this.customPlan = apiService.customPlan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getBraintreeId() {
        return braintreeId;
    }

    public void setBraintreeId(String braintreeId) {
        this.braintreeId = braintreeId;
    }

    public String getPriceString() {
        return CurrencyFormatter.format(centPrice);
    }

    public Integer getIncludedHours() {
        return includedHours;
    }

    public void setIncludedHours(Integer includedHours) {
        this.includedHours = includedHours;
    }

    public Integer getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Integer pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Date getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(Date archiveTime) {
        this.archiveTime = archiveTime;
    }

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    public boolean isCustomPlan() {
        return customPlan;
    }

    public void setCustomPlan(boolean customPlan) {
        this.customPlan = customPlan;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof APIService)) {
            return false;
        }
        APIService other = (APIService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
