package com.testdroid.api.model.recorder.processed;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.testdroid.api.model.recorder.input.ActionType;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SwipeAction.class, name = "swipe"),
        @JsonSubTypes.Type(value = ClickAction.class, name = "click"),
        @JsonSubTypes.Type(value = TextAction.class, name = "type"),
})
public interface RecordedAction {

    ActionType getType();

    default boolean isSelectorBased() {
        return false;
    }

    default String getSelectorValue() {
        return null;
    }

    default String getSelectorType() {
        return null;
    }
}
