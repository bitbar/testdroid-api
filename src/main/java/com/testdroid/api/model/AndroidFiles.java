package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.model.APIFiles;
import java.io.File;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author kajdus
 */
@XmlRootElement 
public class AndroidFiles extends APIFiles {
    private AndroidAppFile androidApp;
    private AndroidTestFile androidTest;

    public AndroidFiles() {}
    public AndroidFiles(Long id, DataFile data, AndroidAppFile androidApp, AndroidTestFile androidTest) {
        super(id, data);
        this.androidApp = androidApp;
        this.androidTest = androidTest;
    }

    public AndroidAppFile getAndroidApp() {
        return androidApp;
    }

    public void setAndroidApp(AndroidAppFile androidApp) {
        this.androidApp = androidApp;
    }

    public AndroidTestFile getAndroidTest() {
        return androidTest;
    }

    public void setAndroidTest(AndroidTestFile androidTest) {
        this.androidTest = androidTest;
    }

    public void uploadApp(File file) throws APIException {
        this.androidApp = client.postFile(getApplicationURI(), "application/vnd.android.package-archive", file, AndroidAppFile.class);
    }

    public void uploadTest(File file) throws APIException {
        this.androidTest = client.postFile(getTestURI(), "application/vnd.android.package-archive", file, AndroidTestFile.class);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        AndroidFiles androidFiles = (AndroidFiles) from;
        this.androidApp = androidFiles.androidApp;
        this.androidTest = androidFiles.androidTest;
    }

}
