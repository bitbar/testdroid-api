package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlType;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIAdminError extends APIEntity {

    @XmlType(namespace = "APIAdminError")
    public enum Type {
        ABORT_REQUEST,
        ADB_COMMAND_REJECTED,
        ADB_SHELL_COMMAND_FAILED,
        APP_LOCK_NOT_INSTALLED,
        DEVICE_OFFLINE,
        DEVICE_OUTPUT_NULL,
        GAMEBENCH_ERROR,
        GAMEBENCH_NOT_SUPPORTED,
        GRANT_FAILED,
        INSTALL_FAILED,
        INTERACTIVE_SETUP_FAILED,
        INTERNAL_INSTALL_FAILED,
        INVALID_TEST_SESSION_FILE,
        LOCK_EXPIRED,
        LOCK_TIMEOUT,
        NO_NETWORK_CONNECTION,
        OTHER,
        REBOOTING_TIMEOUT,
        REPACKAGING,
        RESULTS_PREPARING,
        RESULTS_PROCESSING,
        RESULTS_SENDING,
        STEP_TIMEOUT,
        SYSTEM_FAILURE,
        TEST_RUN_FAILED,
        TEST_RUN_INTERRUPTED,
        TEST_RUN_TIMEOUT,
        TEST_RUN_WARNED,
        TEST_TIMEOUT,
        VNC_CONNECTION_SETUP_FAILED,
        XCRUN_INSTRUMENTS_COMMAND_FAILED,
        XCRUN_INSTRUMENTS_JS_ERROR
    }

    private Long quantity;

    private Type type;

    public APIAdminError() {
    }

    public APIAdminError(Type type, Long quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAdminError apiAdminError = (APIAdminError) from;
        cloneBase(from);
        this.type = apiAdminError.type;
        this.quantity = apiAdminError.quantity;
    }
}
