package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;
import jakarta.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APILicense extends APIEntity {

    @XmlType(namespace = "APILicense")
    public enum Status {
        ACTIVE,
        EXPIRED,
        INACTIVE,
        CLOSED
    }

    public static final String DISABLED_TEXT = EMPTY;

    public static final String ENABLED_TEXT = "on";

    private AndroidLicense android;

    private Date expireTime;

    private Date activateTime;

    private Date closeTime;

    private DesktopLicense desktop;

    private InspectorLicense inspector;

    private IOSLicense ios;

    private boolean privateInstance;

    private boolean legacy;

    private Long userId;

    private RecorderLicense recorder;

    private ServerLicense server;

    private GlobalLicense global;

    private BuildLicense build;

    private String userEmail;

    private Status status;

    public APILicense() {
    }

    @SuppressWarnings("squid:S107")
    public APILicense(
            Long id, Long userId, String userEmail, boolean privateInstance, boolean legacy, LocalDateTime activateTime,
            LocalDateTime expireTime, Integer androidDeviceLimit, Boolean androidEnabled, Boolean iosEnabled,
            Integer iosDeviceLimit, LocalDateTime closeTime, String status, Boolean desktopEnabled,
            Integer desktopDeviceLimit) {
        super(id);
        this.privateInstance = privateInstance;
        this.legacy = legacy;
        this.activateTime = TimeConverter.toDate(activateTime);
        this.expireTime = TimeConverter.toDate(expireTime);
        this.userEmail = userEmail;
        this.userId = userId;
        this.android = new AndroidLicense(androidDeviceLimit, null,
                new AndroidLicense.CTSLicense(true), new AndroidLicense.UIAutomatorLicense(true), androidEnabled);
        this.ios = new IOSLicense(iosDeviceLimit, null, iosEnabled);
        this.build = new BuildLicense(null, true);
        this.recorder = new RecorderLicense(null, true);
        this.server = new ServerLicense(true, true, true);
        this.inspector = new InspectorLicense(true);
        this.desktop = new DesktopLicense(desktopDeviceLimit, null, desktopEnabled);
        this.global = new GlobalLicense(null, null);
        this.closeTime = TimeConverter.toDate(closeTime);
        this.status = EnumUtils.getEnum(Status.class, status);
    }

    @SuppressWarnings("squid:S107")
    public APILicense(
            boolean privateInstance, LocalDateTime expireTime, String userEmail, AndroidLicense android, IOSLicense ios,
            RecorderLicense recorder, ServerLicense server, InspectorLicense inspector, BuildLicense build,
            DesktopLicense desktop, GlobalLicense global) {
        this.privateInstance = privateInstance;
        this.expireTime = TimeConverter.toDate(expireTime);
        this.userEmail = userEmail;
        this.android = android;
        this.ios = ios;
        this.recorder = recorder;
        this.server = server;
        this.inspector = inspector;
        this.build = build;
        this.desktop = desktop;
        this.global = global;
    }

    public APILicense(
            boolean privateInstance, LocalDateTime expireTime, AndroidLicense android, IOSLicense ios,
            DesktopLicense desktop) {
        this.privateInstance = privateInstance;
        this.expireTime = TimeConverter.toDate(expireTime);
        this.android = android;
        this.ios = ios;
        this.desktop = desktop;
    }

    private static String getTextValue(Integer i) {
        return i != null ? Integer.toString(i) : EMPTY;
    }

    private static String getTextValue(boolean b) {
        return b ? ENABLED_TEXT : DISABLED_TEXT;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        throw new UnsupportedOperationException();
    }

    public boolean isPrivateInstance() {
        return privateInstance;
    }

    public void setPrivateInstance(boolean privateInstance) {
        this.privateInstance = privateInstance;
    }

    public AndroidLicense getAndroid() {
        return android;
    }

    public void setAndroid(AndroidLicense android) {
        this.android = android;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @JsonIgnore
    public boolean isExpired() {
        return this.expireTime == null || new Date().after(this.expireTime);
    }

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    public IOSLicense getIos() {
        return ios;
    }

    public void setIos(IOSLicense ios) {
        this.ios = ios;
    }

    public RecorderLicense getRecorder() {
        return recorder;
    }

    public void setRecorder(RecorderLicense recorder) {
        this.recorder = recorder;
    }

    public InspectorLicense getInspector() {
        return inspector;
    }

    public void setInspector(InspectorLicense inspector) {
        this.inspector = inspector;
    }

    public ServerLicense getServer() {
        return server;
    }

    public void setServer(ServerLicense server) {
        this.server = server;
    }

    public BuildLicense getBuild() {
        return build;
    }

    public void setBuild(BuildLicense build) {
        this.build = build;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public DesktopLicense getDesktop() {
        return desktop;
    }

    public void setDesktop(DesktopLicense desktop) {
        this.desktop = desktop;
    }

    public GlobalLicense getGlobal() {
        return global;
    }

    public void setGlobal(GlobalLicense global) {
        this.global = global;
    }

    public boolean isLegacy() {
        return legacy;
    }

    public void setLegacy(boolean legacy) {
        this.legacy = legacy;
    }

    public String generateSignContent() {
        return StringUtils.joinWith(":", getTextValue(privateInstance), userEmail,
                StringUtils.join(
                        Optional.ofNullable(android).map(AndroidLicense::generateSignContent).orElse(EMPTY),
                        Optional.ofNullable(ios).map(IOSLicense::generateSignContent).orElse(EMPTY),
                        Optional.ofNullable(recorder).map(RecorderLicense::generateSignContent).orElse(EMPTY),
                        Optional.ofNullable(server).map(ServerLicense::generateSignContent).orElse(EMPTY)
                )
        );
    }

    public static class GlobalLicense {

        private Integer deviceLimit;

        private Integer projectLimit;

        public GlobalLicense() {

        }

        public GlobalLicense(Integer deviceLimit, Integer projectLimit) {
            this.deviceLimit = deviceLimit;
            this.projectLimit = projectLimit;
        }

        public Integer getDeviceLimit() {
            return deviceLimit;
        }

        public void setDeviceLimit(Integer deviceLimit) {
            this.deviceLimit = deviceLimit;
        }

        public Integer getProjectLimit() {
            return projectLimit;
        }

        public void setProjectLimit(Integer projectLimit) {
            this.projectLimit = projectLimit;
        }

        public boolean isDeviceLimited() {
            return deviceLimit != null;
        }

        public boolean isProjectLimited() {
            return projectLimit != null;
        }
    }

    public abstract static class FeatureLicense {

        boolean enabled;

        FeatureLicense() {
        }

        FeatureLicense(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public abstract String generateSignContent();
    }

    public static class AndroidLicense extends FeatureLicense {

        private CTSLicense cts;

        private Integer deviceLimit;

        private Integer projectLimit;

        private UIAutomatorLicense uiAutomator;

        public AndroidLicense() {
        }

        public AndroidLicense(boolean enabled, Integer deviceLimit) {
            super(enabled);
            this.deviceLimit = deviceLimit;
        }

        public AndroidLicense(
                Integer deviceLimit, Integer projectLimit, CTSLicense cts, UIAutomatorLicense uiAutomator,
                boolean enabled) {
            super(enabled);
            this.deviceLimit = deviceLimit;
            this.projectLimit = projectLimit;
            this.cts = cts;
            this.uiAutomator = uiAutomator;
        }

        public CTSLicense getCts() {
            return cts;
        }

        public void setCts(CTSLicense cts) {
            this.cts = cts;
        }

        public Integer getDeviceLimit() {
            return deviceLimit;
        }

        public void setDeviceLimit(Integer deviceLimit) {
            this.deviceLimit = deviceLimit;
        }

        public Integer getProjectLimit() {
            return projectLimit;
        }

        public void setProjectLimit(Integer projectLimit) {
            this.projectLimit = projectLimit;
        }

        public UIAutomatorLicense getUiAutomator() {
            return uiAutomator;
        }

        public void setUiAutomator(UIAutomatorLicense uiAutomator) {
            this.uiAutomator = uiAutomator;
        }

        public boolean isDeviceLimited() {
            return deviceLimit != null;
        }

        public boolean isProjectLimited() {
            return projectLimit != null;
        }

        @Override
        public String generateSignContent() {
            return StringUtils.join(getTextValue(enabled), getTextValue(projectLimit), getTextValue(deviceLimit),
                    Optional.ofNullable(cts).map(CTSLicense::generateSignContent).orElse(EMPTY),
                    Optional.ofNullable(uiAutomator).map(UIAutomatorLicense::generateSignContent).orElse(EMPTY)
            );
        }

        public static class CTSLicense extends FeatureLicense {

            public CTSLicense() {
            }

            public CTSLicense(boolean enabled) {
                super(enabled);
            }

            @Override
            public String generateSignContent() {
                return getTextValue(enabled);
            }
        }

        public static class UIAutomatorLicense extends FeatureLicense {

            public UIAutomatorLicense() {
            }

            public UIAutomatorLicense(boolean enabled) {
                super(enabled);
            }

            @Override
            public String generateSignContent() {
                return getTextValue(enabled);
            }
        }

    }

    public static class BuildLicense extends FeatureLicense {

        private Integer limit;

        public BuildLicense() {
        }

        public BuildLicense(Integer limit, boolean enabled) {
            super(enabled);
            this.limit = limit;
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public boolean isLimited() {
            return limit != null;
        }

        @Override
        public String generateSignContent() {
            return StringUtils.join(getTextValue(enabled), getTextValue(limit));
        }

    }

    public static class IOSLicense extends FeatureLicense {

        private Integer deviceLimit;

        private Integer projectLimit;

        public IOSLicense() {
        }

        public IOSLicense(boolean enabled, Integer deviceLimit) {
            super(enabled);
            this.deviceLimit = deviceLimit;
        }

        public IOSLicense(Integer deviceLimit, Integer projectLimit, boolean enabled) {
            super(enabled);
            this.deviceLimit = deviceLimit;
            this.projectLimit = projectLimit;
        }

        public Integer getDeviceLimit() {
            return deviceLimit;
        }

        public void setDeviceLimit(Integer deviceLimit) {
            this.deviceLimit = deviceLimit;
        }

        public Integer getProjectLimit() {
            return projectLimit;
        }

        public void setProjectLimit(Integer projectLimit) {
            this.projectLimit = projectLimit;
        }

        public boolean isDeviceLimited() {
            return deviceLimit != null;
        }

        public boolean isProjectLimited() {
            return projectLimit != null;
        }

        @Override
        public String generateSignContent() {
            return StringUtils.join(getTextValue(enabled), getTextValue(projectLimit), getTextValue(deviceLimit));
        }

    }

    public static class DesktopLicense extends FeatureLicense {

        private Integer deviceLimit;

        private Integer projectLimit;

        public DesktopLicense() {
        }

        public DesktopLicense(boolean enabled, Integer deviceLimit) {
            super(enabled);
            this.deviceLimit = deviceLimit;
        }

        public DesktopLicense(Integer deviceLimit, Integer projectLimit, boolean enabled) {
            super(enabled);
            this.deviceLimit = deviceLimit;
            this.projectLimit = projectLimit;
        }

        public Integer getDeviceLimit() {
            return deviceLimit;
        }

        public void setDeviceLimit(Integer deviceLimit) {
            this.deviceLimit = deviceLimit;
        }

        public Integer getProjectLimit() {
            return projectLimit;
        }

        public void setProjectLimit(Integer projectLimit) {
            this.projectLimit = projectLimit;
        }

        public boolean isDeviceLimited() {
            return deviceLimit != null;
        }

        public boolean isProjectLimited() {
            return projectLimit != null;
        }

        @Override
        public String generateSignContent() {
            return StringUtils.join(getTextValue(enabled), getTextValue(projectLimit), getTextValue(deviceLimit));
        }

    }

    public static class RecorderLicense extends FeatureLicense {

        private Integer limit;

        public RecorderLicense() {
        }

        public RecorderLicense(Integer limit, boolean enabled) {
            super(enabled);
            this.limit = limit;
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public boolean isLimited() {
            return limit != null;
        }

        @Override
        public String generateSignContent() {
            return StringUtils.join(getTextValue(enabled), getTextValue(limit));
        }

    }

    public static class ServerLicense extends FeatureLicense {

        private boolean androidEnabled;

        private boolean iosEnabled;

        public ServerLicense() {
        }

        public ServerLicense(boolean androidEnabled, boolean iosEnabled, boolean enabled) {
            super(enabled);
            this.androidEnabled = androidEnabled;
            this.iosEnabled = iosEnabled;
        }

        public boolean isAndroidEnabled() {
            return androidEnabled;
        }

        public void setAndroidEnabled(boolean androidEnabled) {
            this.androidEnabled = androidEnabled;
        }

        public boolean isIosEnabled() {
            return iosEnabled;
        }

        public void setIosEnabled(boolean iosEnabled) {
            this.iosEnabled = iosEnabled;
        }

        @Override
        public String generateSignContent() {
            return StringUtils.join(getTextValue(enabled), getTextValue(androidEnabled), getTextValue(iosEnabled));
        }
    }

    public static class InspectorLicense extends FeatureLicense {

        public InspectorLicense() {
        }

        public InspectorLicense(boolean enabled) {
            super(enabled);
        }

        @Override
        public String generateSignContent() {
            return getTextValue(enabled);
        }
    }
}
