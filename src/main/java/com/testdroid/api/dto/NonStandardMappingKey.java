package com.testdroid.api.dto;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class NonStandardMappingKey {

    private NonStandardMappingKey() {
        throw new IllegalStateException("Utility class");
    }

    // @formatter:off
    public static final String SBID_ID_TOKEN = "sbid_id_token";
    public static final String SLM_LICENSE_TOKEN = "slm_license_token";
    // @formatter:on

}
