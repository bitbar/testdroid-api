package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.formatter.CurrencyFormatter;
import com.testdroid.api.model.enums.Unit;
import com.testdroid.api.util.TimeConverter;
import jakarta.xml.bind.annotation.XmlType;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
public class APIService extends APIEntity {

    private String externalId;

    private String commonId;

    private Integer centPrice;

    private String description;

    private String features;

    private String name;

    private Date archiveTime;

    private Date activateTime;

    private boolean activated;

    private boolean customPlan;

    private List<APIRole> roles = new ArrayList<>();

    private Unit unit;

    private Set<Integer> defaultConcurrences = new HashSet<>();

    @XmlType(namespace = "APIService")
    public enum ChargeType {
        CONCURRENCY,
        USAGE
    }

    public APIService() {
    }

    @SuppressWarnings("squid:S107")
    public APIService(
            Long id, String name, String description, Integer centPrice, String externalId,
            String commonId, LocalDateTime archiveTime, LocalDateTime activateTime, boolean activated,
            boolean customPlan, Unit unit, String features) {
        super(id);
        this.name = name;
        this.description = description;
        this.centPrice = centPrice;
        this.externalId = externalId;
        this.archiveTime = TimeConverter.toDate(archiveTime);
        this.activateTime = TimeConverter.toDate(activateTime);
        this.activated = activated;
        this.customPlan = customPlan;
        this.unit = unit;
        this.commonId = commonId;
        this.features = features;
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
        this.features = apiService.features;
        this.centPrice = apiService.centPrice;
        this.externalId = apiService.externalId;
        this.commonId = apiService.commonId;
        this.archiveTime = apiService.archiveTime;
        this.activateTime = apiService.activateTime;
        this.activated = apiService.activated;
        this.customPlan = apiService.customPlan;
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

    public Set<Integer> getDefaultConcurrences() {
        return defaultConcurrences;
    }

    public String getCommonId() {
        return commonId;
    }

    public void setCommonId(String commonId) {
        this.commonId = commonId;
    }

    public void setDefaultConcurrences(Set<Integer> defaultAvailableConcurrences) {
        this.defaultConcurrences = defaultAvailableConcurrences;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
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
