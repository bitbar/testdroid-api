package com.testdroid.api.model.recorder.processed;

import com.testdroid.api.model.recorder.input.ActionType;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class TextAction implements RecordedAction {

    private String text;

    private String selectorValue;

    private String selectorType;

    private long createTime;

    @Override
    public ActionType getType() {
        return ActionType.TYPE;
    }

    @Override
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public long getCreateTime() {
        return createTime;
    }

    @Override
    public boolean isSelectorBased() {
        return selectorValue != null;
    }

    @Override
    public String getSelectorValue() {
        return selectorValue;
    }

    @Override
    public String getSelectorType() {
        return selectorType;
    }

    public TextAction withSelector(String selector, String selectorType) {
        this.selectorValue = selector;
        this.selectorType = selectorType;
        return this;
    }

    public String getText() {
        return text;
    }

    public TextAction withText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return "Executed text action";
    }
}
