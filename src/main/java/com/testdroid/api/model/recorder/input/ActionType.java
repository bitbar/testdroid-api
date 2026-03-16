package com.testdroid.api.model.recorder.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public enum ActionType {
    CLICK,
    SWIPE,
    SCROLL,
    PRESS,
    TYPE,
    HARDWARE_BUTTON,
    DOUBLE_SWIPE,
    PINCH,
    ROTATE;

    @JsonCreator
    public static ActionType fromString(String key) {
        return key == null ? null : ActionType.valueOf(key.toUpperCase().replace("-", "_"));
    }

    @JsonValue
    public String toValue() {
        return this.name().toLowerCase().replace("_", "-");
    }
}
