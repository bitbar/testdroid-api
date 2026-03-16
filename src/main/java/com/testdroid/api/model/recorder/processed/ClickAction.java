package com.testdroid.api.model.recorder.processed;

import com.testdroid.api.model.recorder.input.ActionType;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class ClickAction implements RecordedAction {

    private double x;

    private double y;

    private String selectorValue;

    private String selectorType;

    @Override
    public ActionType getType() {
        return ActionType.CLICK;
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

    public ClickAction withSelector(String selector, String selectorType) {
        this.selectorValue = selector;
        this.selectorType = selectorType;
        return this;
    }

    public double getX() {
        return x;
    }

    public ClickAction withX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public ClickAction withY(double y) {
        this.y = y;
        return this;
    }
}
