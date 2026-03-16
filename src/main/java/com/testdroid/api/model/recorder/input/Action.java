package com.testdroid.api.model.recorder.input;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class Action {

    private ActionType type;

    private Point point;

    private Point startPoint;

    private Point endPoint;

    private Point startPoint1;

    private Point endPoint1;

    private Point startPoint2;

    private Point endPoint2;

    private Point originPoint;

    private String delta;

    private String key;

    private String text;

    private String button;

    private String id;

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Point getStartPoint1() {
        return startPoint1;
    }

    public void setStartPoint1(Point startPoint1) {
        this.startPoint1 = startPoint1;
    }

    public Point getEndPoint1() {
        return endPoint1;
    }

    public void setEndPoint1(Point endPoint1) {
        this.endPoint1 = endPoint1;
    }

    public Point getStartPoint2() {
        return startPoint2;
    }

    public void setStartPoint2(Point startPoint2) {
        this.startPoint2 = startPoint2;
    }

    public Point getEndPoint2() {
        return endPoint2;
    }

    public void setEndPoint2(Point endPoint2) {
        this.endPoint2 = endPoint2;
    }

    public Point getOriginPoint() {
        return originPoint;
    }

    public void setOriginPoint(Point originPoint) {
        this.originPoint = originPoint;
    }

    public String getDelta() {
        return delta;
    }

    public void setDelta(String delta) {
        this.delta = delta;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("type", type)
                .append("point", point)
                .append("startPoint", startPoint)
                .append("endPoint", endPoint)
                .append("startPoint1", startPoint1)
                .append("endPoint1", endPoint1)
                .append("startPoint2", startPoint2)
                .append("endPoint2", endPoint2)
                .append("originPoint", originPoint)
                .append("delta", delta)
                .append("key", key)
                .append("text", text)
                .append("button", button)
                .append("id", id)
                .toString();
    }
}
