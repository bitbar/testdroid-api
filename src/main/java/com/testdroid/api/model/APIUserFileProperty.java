package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIUserFileProperty extends APIEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Key {
        main_activity,
        min_sdk,
        package_name,
        target_package_name,
        instrumentation_name,
        bundle_name,
        bundle_identifier,
        jar_names,
        code_signers,
        virus_scan_status
    }

    public enum VirusScanStatus {
        waiting,
        scanning,
        infected,
        safe,
        disabled
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
