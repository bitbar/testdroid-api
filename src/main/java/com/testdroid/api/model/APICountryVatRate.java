package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APICountryVatRate extends APIEntity {

    private String country;

    private String countryCode;

    private Integer vatRate;

    public APICountryVatRate() {
    }

    public APICountryVatRate(Long id, String countryCode, String country, Integer vatRate) {
        super(id);
        this.countryCode = countryCode;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APICountryVatRate countryVatRate = (APICountryVatRate) from;
        cloneBase(from);
        this.country = countryVatRate.country;
        this.countryCode = countryVatRate.countryCode;
        this.vatRate = countryVatRate.vatRate;
    }
}
