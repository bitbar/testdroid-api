package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import java.time.LocalDateTime;
import java.util.Date;

public class APIAdminTrialStatistics extends APIEntity {

    private String company;

    private String country;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String state;

    private Date trialEndDate;

    private Date trialStartDate;

    public APIAdminTrialStatistics() {
    }

    @SuppressWarnings("squid:S107")
    public APIAdminTrialStatistics(String email, String firstName, String lastName, String company, String phone,
            String country, String state, LocalDateTime trialStartDate, LocalDateTime trialEndDate) {
        this.company = company;
        this.country = country;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.state = state;
        this.trialEndDate = TimeConverter.toDate(trialEndDate);
        this.trialStartDate = TimeConverter.toDate(trialStartDate);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getTrialEndDate() {
        return trialEndDate;
    }

    public void setTrialEndDate(Date trialEndDate) {
        this.trialEndDate = trialEndDate;
    }

    public Date getTrialStartDate() {
        return trialStartDate;
    }

    public void setTrialStartDate(Date trialStartDate) {
        this.trialStartDate = trialStartDate;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAdminTrialStatistics original = (APIAdminTrialStatistics) from;
        cloneBase(from);
        this.company = original.company;
        this.country = original.country;
        this.email = original.email;
        this.firstName = original.firstName;
        this.lastName = original.lastName;
        this.phone = original.phone;
        this.state = original.state;
        this.trialEndDate = original.trialEndDate;
        this.trialStartDate = original.trialStartDate;
    }
}
