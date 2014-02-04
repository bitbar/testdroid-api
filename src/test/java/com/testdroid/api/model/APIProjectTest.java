package com.testdroid.api.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class APIProjectTest {

    @Test
    public void testAPIProjectType() {
        assertEquals(AndroidFiles.class, APIProject.Type.ANDROID.getFilesClass());
        assertEquals(null, APIProject.Type.CTS.getFilesClass());
        assertEquals(IOSFiles.class, APIProject.Type.IOS.getFilesClass());
        assertEquals(UIAutomatorFiles.class, APIProject.Type.UIAUTOMATOR.getFilesClass());
        assertEquals(RemoteControlFiles.class, APIProject.Type.REMOTECONTROL.getFilesClass());
        assertEquals(RecorderOnlineFiles.class, APIProject.Type.RECORDERONLINE.getFilesClass());
        assertEquals(CalabashFiles.class, APIProject.Type.CALABASH.getFilesClass());
        assertEquals(CalabashIOSFiles.class, APIProject.Type.CALABASH_IOS.getFilesClass());
        assertEquals(AppiumAndroidFiles.class, APIProject.Type.APPIUM_ANDROID.getFilesClass());
        assertEquals(AppiumIOSFiles.class, APIProject.Type.APPIUM_IOS.getFilesClass());
    }

}
