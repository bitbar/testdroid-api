package com.testdroid.api.model;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class APIInvoiceDetails {

    private String address;

    private String city;

    private String code;

    private String country;

    private String state;

    public APIInvoiceDetails() {

    }

    public APIInvoiceDetails(String address, String city, String code, String country, String state) {
        this.address = address;
        this.city = city;
        this.code = code;
        this.country = country;
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isUpdate() {
        return ObjectUtils.anyNotNull(address, city, state, country, code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof APIInvoiceDetails that)) {
            return false;
        }

        return new EqualsBuilder().append(address, that.address).append(city, that.city)
                .append(code, that.code).append(country, that.country).append(state, that.state).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(address).append(city).append(code).append(country).append(state)
                .toHashCode();
    }
}
