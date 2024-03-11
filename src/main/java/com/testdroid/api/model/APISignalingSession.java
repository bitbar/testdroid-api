package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

/**
 * @author Adrian Zybała <adrian.zybala@smartbear.com>
 */
public class APISignalingSession extends APIEntity {

    private String signalingId;

    private String password;

    public APISignalingSession() {
    }

    public APISignalingSession(String signalingId, String password) {
        this.signalingId = signalingId;
        this.password = password;
    }

    public String getSignalingId() {
        return signalingId;
    }

    public void setSignalingId(String signalingId) {
        this.signalingId = signalingId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APISignalingSession other = (APISignalingSession) from;
        cloneBase(from);
        this.signalingId = other.signalingId;
        this.password = other.password;
    }
}
