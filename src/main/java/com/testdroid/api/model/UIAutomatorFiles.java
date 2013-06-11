package com.testdroid.api.model;

import com.testdroid.api.APIException;
import com.testdroid.api.model.APIFiles;
import java.io.File;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kajdus
 */
@XmlRootElement public class UIAutomatorFiles extends APIFiles {
    private AndroidAppFile androidApp;
    private UIAutomatorTestFile uiAutomatorTest;

    public UIAutomatorFiles() {}
    public UIAutomatorFiles(Long id, DataFile data, AndroidAppFile androidApp, UIAutomatorTestFile uiAutomatorTest) {
        super(id, data);
        this.androidApp = androidApp;
        this.uiAutomatorTest = uiAutomatorTest;
    }

    public AndroidAppFile getAndroidApp() {
        return androidApp;
    }

    public void setAndroidApp(AndroidAppFile androidApp) {
        this.androidApp = androidApp;
    }

    public UIAutomatorTestFile getUiAutomatorTest() {
        return uiAutomatorTest;
    }

    public void setUiAutomatorTest(UIAutomatorTestFile uiAutomatorTest) {
        this.uiAutomatorTest = uiAutomatorTest;
    } 

    public void uploadApp(File file) throws APIException {
        this.androidApp = client.postFile(getApplicationURI(), "application/vnd.android.package-archive", file, AndroidAppFile.class);
    }

    public void uploadTest(File file) throws APIException {
        this.uiAutomatorTest = client.postFile(getTestURI(), "application/vnd.android.package-archive", file, UIAutomatorTestFile.class);
    }
}
