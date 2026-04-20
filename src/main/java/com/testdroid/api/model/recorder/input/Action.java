package com.testdroid.api.model.recorder.input;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class Action {

    private Long createTime;

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

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

        private Long createTime;

        public Builder type(ActionType type) {
            this.type = type;
            return this;
        }

        public Builder point(Point point) {
            this.point = point;
            return this;
        }

        public Builder startPoint(Point startPoint) {
            this.startPoint = startPoint;
            return this;
        }

        public Builder endPoint(Point endPoint) {
            this.endPoint = endPoint;
            return this;
        }

        public Builder startPoint1(Point startPoint1) {
            this.startPoint1 = startPoint1;
            return this;
        }

        public Builder endPoint1(Point endPoint1) {
            this.endPoint1 = endPoint1;
            return this;
        }

        public Builder startPoint2(Point startPoint2) {
            this.startPoint2 = startPoint2;
            return this;
        }

        public Builder endPoint2(Point endPoint2) {
            this.endPoint2 = endPoint2;
            return this;
        }

        public Builder originPoint(Point originPoint) {
            this.originPoint = originPoint;
            return this;
        }

        public Builder delta(String delta) {
            this.delta = delta;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder button(String button) {
            this.button = button;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder createTime(Long createTime) {
            this.createTime = createTime;
            return this;
        }

        public Action build() {
            Action action = new Action();
            action.type = this.type;
            action.point = this.point;
            action.startPoint = this.startPoint;
            action.endPoint = this.endPoint;
            action.startPoint1 = this.startPoint1;
            action.endPoint1 = this.endPoint1;
            action.startPoint2 = this.startPoint2;
            action.endPoint2 = this.endPoint2;
            action.originPoint = this.originPoint;
            action.delta = this.delta;
            action.key = this.key;
            action.text = this.text;
            action.button = this.button;
            action.id = this.id;
            action.createTime = this.createTime;
            return action;
        }
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
                .append("createTime", createTime)
                .toString();
    }
}
