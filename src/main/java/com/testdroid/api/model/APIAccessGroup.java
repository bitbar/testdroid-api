package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIAccessGroup extends APIEntity {

    @XmlType(namespace = "APIAccessGroup")
    public enum Scope {
        USER,
        ACCOUNT,
        GLOBAL
    }

    private String name;

    private Long userId;

    private Scope scope;

    private String userEmail;

    public APIAccessGroup() {
    }

    public APIAccessGroup(Long id, String name, Scope scope, Long userId, String userEmail) {
        super(id);
        this.name = name;
        this.scope = scope;
        this.userId = userId;
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAccessGroup original = (APIAccessGroup) from;
        cloneBase(from);
        this.name = original.name;
        this.userId = original.userId;
        this.scope = original.scope;
        this.userEmail = original.userEmail;
    }
}
