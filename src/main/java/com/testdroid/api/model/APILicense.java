package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import org.apache.commons.lang3.EnumUtils;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APILicense extends APIEntity {


    @XmlType(namespace = "APILicense")
    public enum Status {
        ACTIVE,
        EXPIRED,
        INACTIVE,
        CLOSED
    }

    public static final String DISABLED_TEXT = "";

    public static final String ENABLED_TEXT = "on";

    private AndroidLicense android;

    private AppiumLicense appium;

    private CalabashLicense calabash;

    private Date expireTime;

    private Date activateTime;

    private Date closeTime;

    private InspectorLicense inspector;

    private IOSLicense ios;

    private boolean privateInstance;

    private boolean autoRenew;

    private Long userId;

    private RecorderLicense recorder;

    private RemoteControlLicense remoteControl;

    private SeleniumLicense selenium;

    private ServerLicense server;

    private XCTestLicense xcTest;

    private XCUITestLicense xcuiTest;

    private String userEmail;

    private Status status;

    public APILicense() {
    }

    public APILicense(
            Long id, Long userId, String userEmail, boolean autoRenew, boolean enterprise, Date activateTime,
            Date expireTime, Integer androidDeviceLimit, Boolean androidEnabled, Boolean seleniumEnabled,
            Boolean serverAndroidEnabled, Boolean calabashEnabled, Boolean uiautomatorEnabled, Boolean recorderEnabled,
            Integer iosProjectLimit, Boolean serverIosEnabled, Integer recorderLimit, Boolean remoteControlEnabled,
            Boolean xcTestEnabled, Boolean xcuiTestEnabled, Boolean ctsEnabled, Integer androidProjectLimit,
            Boolean appiumEnabled, Boolean serverEnabled, Boolean inspectorEnabled, Boolean iosEnabled,
            Integer iosDeviceLimit, Date closeTime, String status) {
        super(id);
        this.privateInstance = enterprise;
        this.activateTime = activateTime;
        this.expireTime = expireTime;
        this.userEmail = userEmail;
        this.userId = userId;
        this.autoRenew = autoRenew;
        this.android = new AndroidLicense(androidDeviceLimit, androidProjectLimit,
                new AndroidLicense.CTSLicense(ctsEnabled), new AndroidLicense.UIAutomatorLicense(uiautomatorEnabled),
                androidEnabled);
        this.ios = new IOSLicense(iosDeviceLimit, iosProjectLimit, iosEnabled);
        this.recorder = new RecorderLicense(recorderLimit, recorderEnabled);
        this.server = new ServerLicense(serverAndroidEnabled, serverIosEnabled, serverEnabled);
        this.calabash = new CalabashLicense(calabashEnabled);
        this.appium = new AppiumLicense(appiumEnabled);
        this.selenium = new SeleniumLicense(seleniumEnabled);
        this.inspector = new InspectorLicense(inspectorEnabled);
        this.remoteControl = new RemoteControlLicense(remoteControlEnabled);
        this.xcTest = new XCTestLicense(xcTestEnabled);
        this.xcuiTest = new XCUITestLicense(xcuiTestEnabled);
        this.closeTime = closeTime;
        this.status = EnumUtils.getEnum(Status.class, status);
    }

    public APILicense(
            boolean privateInstance, Date expireTime, String userEmail, AndroidLicense android, IOSLicense ios,
            RecorderLicense recorder, ServerLicense server, CalabashLicense calabash, AppiumLicense appium,
            SeleniumLicense selenium, InspectorLicense inspector, RemoteControlLicense remoteControl,
            XCTestLicense xcTest, XCUITestLicense xcuiTest) {
        this.privateInstance = privateInstance;
        this.expireTime = expireTime;
        this.userEmail = userEmail;
        this.android = android;
        this.ios = ios;
        this.recorder = recorder;
        this.server = server;
        this.calabash = calabash;
        this.appium = appium;
        this.selenium = selenium;
        this.inspector = inspector;
        this.remoteControl = remoteControl;
        this.xcTest = xcTest;
        this.xcuiTest = xcuiTest;
    }

    private static String getTextValue(Integer i) {
        return i != null ? Integer.toString(i) : "";
    }

    private static String getTextValue(boolean b) {
        return b ? ENABLED_TEXT : DISABLED_TEXT;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        throw new UnsupportedOperationException("Not supported yet."); // Not used for licenses
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

    public CalabashLicense getCalabash() {
        return calabash;
    }

    public void setCalabash(CalabashLicense calabash) {
        this.calabash = calabash;
    }

    public RemoteControlLicense getRemoteControl() {
        return remoteControl;
    }

    public void setRemoteControl(RemoteControlLicense remoteControl) {
        this.remoteControl = remoteControl;
    }

    public AppiumLicense getAppium() {
        return appium;
    }

    public void setAppium(AppiumLicense appium) {
        this.appium = appium;
    }

    public SeleniumLicense getSelenium() {
        return selenium;
    }

    public void setSelenium(SeleniumLicense selenium) {
        this.selenium = selenium;
    }

    public XCTestLicense getXcTest() {
        return xcTest;
    }

    public void setXcTest(XCTestLicense xcTest) {
        this.xcTest = xcTest;
    }

    public XCUITestLicense getXcuiTest() {
        return xcuiTest;
    }

    public void setXcuiTest(XCUITestLicense xcuiTest) {
        this.xcuiTest = xcuiTest;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
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

    public String generateSignContent() {
        return String.format("%s:%s:%s%s%s%s", getTextValue(privateInstance), getUserEmail(), getAndroid()
                .generateSignContent(), getIos().generateSignContent(),
                getRecorder().generateSignContent(), getServer().generateSignContent());
    }

    public static abstract class FeatureLicense {

        protected boolean enabled;

        public FeatureLicense() {
        }

        public FeatureLicense(boolean enabled) {
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
            return String
                    .format("%s%s%s%s%s", getTextValue(enabled), getTextValue(projectLimit),
                            getTextValue(deviceLimit), cts
                            .generateSignContent(), uiAutomator.generateSignContent());
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

    public static class IOSLicense extends FeatureLicense {

        private Integer deviceLimit;

        private Integer projectLimit;

        public IOSLicense() {
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
            return String
                    .format("%s%s%s", getTextValue(enabled), getTextValue(projectLimit), getTextValue(deviceLimit));
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
            return String.format("%s%s", getTextValue(enabled), getTextValue(limit));
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
            return String
                    .format("%s%s%s", getTextValue(enabled), getTextValue(androidEnabled), getTextValue(iosEnabled));
        }
    }

    public static class CalabashLicense extends FeatureLicense {

        public CalabashLicense() {
        }

        public CalabashLicense(boolean enabled) {
            super(enabled);
        }

        @Override
        public String generateSignContent() {
            return getTextValue(enabled);
        }

    }

    public static class RemoteControlLicense extends FeatureLicense {

        public RemoteControlLicense() {
        }

        public RemoteControlLicense(boolean enabled) {
            super(enabled);
        }

        @Override
        public String generateSignContent() {
            return getTextValue(enabled);
        }

    }

    public static class AppiumLicense extends FeatureLicense {

        public AppiumLicense() {
        }

        public AppiumLicense(boolean enabled) {
            super(enabled);
        }

        @Override
        public String generateSignContent() {
            return getTextValue(enabled);
        }

    }

    public static class SeleniumLicense extends FeatureLicense {

        public SeleniumLicense() {
        }

        public SeleniumLicense(boolean enabled) {
            super(enabled);
        }

        @Override
        public String generateSignContent() {
            return getTextValue(enabled);
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

    public static class XCTestLicense extends FeatureLicense {

        public XCTestLicense() {
        }

        public XCTestLicense(boolean enabled) {
            super(enabled);
        }

        @Override
        public String generateSignContent() {
            return getTextValue(enabled);
        }

    }

    public static class XCUITestLicense extends FeatureLicense {

        public XCUITestLicense() {
        }

        public XCUITestLicense(boolean enabled) {
            super(enabled);
        }

        @Override
        public String generateSignContent() {
            return getTextValue(enabled);
        }

    }

}
