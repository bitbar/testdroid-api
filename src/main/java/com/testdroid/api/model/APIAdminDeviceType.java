package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
@XmlRootElement
public class APIAdminDeviceType extends APIEntity {

    private Integer frameExtraWidth;

    private Integer imageHeight;

    private Integer imageLeft;

    private String imagePrefix;

    private Integer imageTop;

    private Integer imageWidth;

    public APIAdminDeviceType() {

    }

    public APIAdminDeviceType(
            Long id, Integer frameExtraWidth, Integer imageHeight, Integer imageLeft, String imagePrefix,
            Integer imageTop, Integer imageWidth) {
        super(id);
        this.frameExtraWidth = frameExtraWidth;
        this.imageHeight = imageHeight;
        this.imageLeft = imageLeft;
        this.imagePrefix = imagePrefix;
        this.imageTop = imageTop;
        this.imageWidth = imageWidth;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAdminDeviceType deviceType = (APIAdminDeviceType) from;
        cloneBase(from);
        this.frameExtraWidth = deviceType.frameExtraWidth;
        this.imageHeight = deviceType.imageHeight;
        this.imageLeft = deviceType.imageLeft;
        this.imagePrefix = deviceType.imagePrefix;
        this.imageTop = deviceType.imageTop;
        this.imageWidth = deviceType.imageWidth;
    }

    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }

    public Integer getFrameExtraWidth() {
        return frameExtraWidth;
    }

    public void setFrameExtraWidth(Integer frameExtraWidth) {
        this.frameExtraWidth = frameExtraWidth;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    public Integer getImageLeft() {
        return imageLeft;
    }

    public void setImageLeft(Integer imageLeft) {
        this.imageLeft = imageLeft;
    }

    public Integer getImageTop() {
        return imageTop;
    }

    public void setImageTop(Integer imageTop) {
        this.imageTop = imageTop;
    }

    public Integer getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }
}
