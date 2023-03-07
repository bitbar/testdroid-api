package com.testdroid.api.model;

import java.io.Serializable;

public class APITunnelSettings implements Serializable {

    private String domain;

    private String email;

    public APITunnelSettings() {
        // need for serialization/deserialization
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
