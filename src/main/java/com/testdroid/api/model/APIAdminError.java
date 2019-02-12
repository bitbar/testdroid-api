package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIAdminError extends APIEntity {

    @XmlType(namespace = "APIAdminError")
    public enum Type {
        ABORT_REQUEST,
        DEVICE_OFFLINE,
        NO_NETWORK_CONNECTION,
        REBOOTING_TIMEOUT,
        REPACKAGING,
        INSTALL_FAILED,
        INTERNAL_INSTALL_FAILED,
        ADB_COMMAND_REJECTED,
        ADB_SHELL_COMMAND_FAILED,
        XCRUN_INSTRUMENTS_COMMAND_FAILED,
        XCRUN_INSTRUMENTS_JS_ERROR,
        TEST_RUN_INTERRUPTED,
        TEST_RUN_TIMEOUT,
        TEST_RUN_WARNED,
        TEST_RUN_FAILED,
        RESULTS_PROCESSING,
        INVALID_TEST_SESSION_FILE,
        LOCK_TIMEOUT,
        LOCK_EXPIRED,
        STEP_TIMEOUT,
        TEST_TIMEOUT,
        GRANT_FAILED,
        GAMEBENCH_NOT_SUPPORTED,
        GAMEBENCH_ERROR,
        APP_LOCK_NOT_INSTALLED,
        DEVICE_OUTPUT_NULL,
        SYSTEM_FAILURE,
        INTERACTIVE_SETUP_FAILED,
        VNC_CONNECTION_SETUP_FAILED,
        OTHER
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
