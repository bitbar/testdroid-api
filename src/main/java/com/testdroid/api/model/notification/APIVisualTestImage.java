package com.testdroid.api.model.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.testdroid.api.APIEntity;

import java.util.Objects;

/**
 * @author Artur Ćwikliński <artur.cwiklinski@smartbear.com>
 */
public class APIVisualTestImage extends APIEntity {

    private enum ImageType {

        FULL_PAGE("fullpage"),
        VIEWPORT("viewport"),
        ELEMENT_SCREENSHOT("element screenshot");

        final String type;

        ImageType(String type) {
            this.type = type;
        }

        @JsonValue
        public String toValue() {
            return String.valueOf(type);
        }

        @JsonCreator
        public static ImageType fromValue(String value) {
            for (ImageType iT : ImageType.values()) {
                if (Objects.equals(String.valueOf(iT.type), value)) {
                    return iT;
                }
            }
            return null;
        }
    }

    public APIVisualTestImage() {
    }

    @SuppressWarnings("squid:S107")
    public APIVisualTestImage(
            String imageName, ImageType imageType, String imageUrl, String imageThumbnailUrl, String comparisonStatus,
            String appUrl, String sessionId, String testUrl, String createdAt) {
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageUrl = imageUrl;
        this.imageThumbnailUrl = imageThumbnailUrl;
        this.comparisonStatus = comparisonStatus;
        this.appUrl = appUrl;
        this.sessionId = sessionId;
        this.testUrl = testUrl;
        this.createdAt = createdAt;
    }

    private String imageName;

    private ImageType imageType;

    private String imageUrl;

    private String imageThumbnailUrl;

    private String comparisonStatus;

    private String appUrl;

    private String sessionId;

    private String testUrl;

    private String createdAt;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageThumbnailUrl() {
        return imageThumbnailUrl;
    }

    public void setImageThumbnailUrl(String imageThumbnailUrl) {
        this.imageThumbnailUrl = imageThumbnailUrl;
    }

    public String getComparisonStatus() {
        return comparisonStatus;
    }

    public void setComparisonStatus(String comparisonStatus) {
        this.comparisonStatus = comparisonStatus;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTestUrl() {
        return testUrl;
    }

    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIVisualTestImage visualTestImage = (APIVisualTestImage) from;
        cloneBase(from);
        this.imageName = visualTestImage.imageName;
        this.imageType = visualTestImage.imageType;
        this.imageUrl = visualTestImage.imageUrl;
        this.imageThumbnailUrl = visualTestImage.imageThumbnailUrl;
        this.comparisonStatus = visualTestImage.comparisonStatus;
        this.appUrl = visualTestImage.appUrl;
        this.sessionId = visualTestImage.sessionId;
        this.testUrl = visualTestImage.testUrl;
        this.createdAt = visualTestImage.createdAt;
    }
}
