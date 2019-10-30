package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.formatter.CurrencyFormatter;
import com.testdroid.api.model.enums.Unit;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APIService extends APIEntity {

    private boolean autoRenew;

    private String externalId;

    private Integer centPrice;

    private Integer includedHours;

    private Integer pricePerHour;

    private String description;

    private String name;

    private Date archiveTime;

    private Date activateTime;

    private boolean activated;

    private boolean customPlan;

    private ChargeType chargeType;

    private List<APIRole> roles = new ArrayList<>();

    private Unit unit;

    @XmlType(namespace = "APIService")
    public enum ChargeType {
        USAGE,
        CONCURRENCY;

        public static ChargeType fromValue(String value) {
            return "metered".equals(value) ? ChargeType.USAGE : ChargeType.CONCURRENCY;
        }
    }

    public APIService() {
    }

    public APIService(
            Long id, String name, String description, boolean autoRenew, Integer centPrice, Integer includedHours,
            Integer pricePerHour, String externalId, LocalDateTime archiveTime, LocalDateTime activateTime,
            boolean activated, boolean customPlan, ChargeType chargeType, Unit unit) {
        super(id);
        this.name = name;
        this.description = description;
        this.autoRenew = autoRenew;
        this.centPrice = centPrice;
        this.includedHours = includedHours;
        this.pricePerHour = pricePerHour;
        this.externalId = externalId;
        this.archiveTime = TimeConverter.toDate(archiveTime);
        this.activateTime = TimeConverter.toDate(activateTime);
        this.activated = activated;
        this.customPlan = customPlan;
        this.chargeType = chargeType;
        this.unit = unit;
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
        this.externalId = apiService.externalId;
        this.includedHours = apiService.includedHours;
        this.pricePerHour = apiService.pricePerHour;
        this.archiveTime = apiService.archiveTime;
        this.activateTime = apiService.activateTime;
        this.activated = apiService.activated;
        this.customPlan = apiService.customPlan;
        this.chargeType = apiService.chargeType;
        this.unit = apiService.unit;
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

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getPriceString() {
        return CurrencyFormatter.format(centPrice.longValue());
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

    public ChargeType getChargeType() {
        return chargeType;
    }

    public void setChargeType(ChargeType chargeType) {
        this.chargeType = chargeType;
    }

    public List<APIRole> getRoles() {
        return roles;
    }

    public void setRoles(List<APIRole> roles) {
        this.roles = roles;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }
}
