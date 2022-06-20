package com.testdroid.api;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class APIMaintenance extends APIEntity {

    private Boolean enabled;

    public APIMaintenance() {
    }

    public APIMaintenance(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        throw new UnsupportedOperationException();
    }
}
