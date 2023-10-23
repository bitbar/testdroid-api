package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import java.io.Serializable;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APIUserFileProperty extends APIEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Key {
        MAIN_ACTIVITY("main_activity"),
        MIN_SDK("min_sdk"),
        PACKAGE_NAME("package_name"),
        TARGET_PACKAGE_NAME("target_package_name"),
        INSTRUMENTATION_NAME("instrumentation_name"),
        BUNDLE_NAME("bundle_name"),
        BUNDLE_IDENTIFIER("bundle_identifier"),
        JAR_NAMES("jar_names"),
        CODE_SIGNERS("code_signers"),
        VIRUS_SCAN_STATUS("virus_scan_status");

        private final String value;

        Key(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    public enum VirusScanStatus {
        WAITING("waiting"),
        SCANNING("scanning"),
        INFECTED("infected"),
        SAFE("safe"),
        DISABLED("disabled");

        private final String value;

        VirusScanStatus(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    private String key;

    private String value;

    public APIUserFileProperty() {
    }

    public APIUserFileProperty(Long id, String key, String value) {
        super(id);
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIUserFileProperty apiUserFileProperty = (APIUserFileProperty) from;
        cloneBase(from);
        this.key = apiUserFileProperty.key;
        this.value = apiUserFileProperty.value;
    }
}
