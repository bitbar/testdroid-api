package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APICountryVatRate extends APIEntity {

    private String countryCode;

    private Integer vatRate;

    public APICountryVatRate() {
    }

    public APICountryVatRate(Long id, String countryCode, Integer vatRate) {
        super(id);
        this.countryCode = countryCode;
        this.vatRate = vatRate;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getVatRate() {
        return vatRate;
    }

    public void setVatRate(Integer vatRate) {
        this.vatRate = vatRate;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APICountryVatRate countryVatRate = (APICountryVatRate) from;
        cloneBase(from);
        this.countryCode = countryVatRate.countryCode;
        this.vatRate = countryVatRate.vatRate;
    }
}
