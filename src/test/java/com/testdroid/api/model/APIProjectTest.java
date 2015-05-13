package com.testdroid.api.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Jarno Tuovinen <jarno.tuovinen@bitbar.com>
 */
public class APIProjectTest {

    /**
     * Check that APIProject.Type returns proper class.
     */
    @Test
    public void testAPIProjectType() {
        assertEquals(AndroidFiles.class, APIProject.Type.ANDROID.getFilesClass());
        assertEquals(null, APIProject.Type.CTS.getFilesClass());
        assertEquals(IOSFiles.class, APIProject.Type.IOS.getFilesClass());
        assertEquals(UIAutomatorFiles.class, APIProject.Type.UIAUTOMATOR.getFilesClass());
        assertEquals(RemoteControlFiles.class, APIProject.Type.REMOTECONTROL.getFilesClass());
        assertEquals(CalabashFiles.class, APIProject.Type.CALABASH.getFilesClass());
        assertEquals(CalabashIOSFiles.class, APIProject.Type.CALABASH_IOS.getFilesClass());
        assertEquals(AppiumAndroidFiles.class, APIProject.Type.APPIUM_ANDROID.getFilesClass());
        assertEquals(AppiumIOSFiles.class, APIProject.Type.APPIUM_IOS.getFilesClass());
    }

}
