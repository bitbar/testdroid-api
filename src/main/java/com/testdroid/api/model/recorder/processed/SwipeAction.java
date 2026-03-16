package com.testdroid.api.model.recorder.processed;

import com.testdroid.api.model.recorder.input.ActionType;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class SwipeAction implements RecordedAction {

    private double startX;

    private double startY;

    private double endX;

    private double endY;

    @Override
    public ActionType getType() {
        return ActionType.SWIPE;
    }

    public double getStartX() {
        return startX;
    }

    public SwipeAction withStartX(double startX) {
        this.startX = startX;
        return this;
    }

    public double getStartY() {
        return startY;
    }

    public SwipeAction withStartY(double startY) {
        this.startY = startY;
        return this;
    }

    public double getEndX() {
        return endX;
    }

    public SwipeAction withEndX(double endX) {
        this.endX = endX;
        return this;
    }

    public double getEndY() {
        return endY;
    }

    public SwipeAction withEndY(double endY) {
        this.endY = endY;
        return this;
    }
}
