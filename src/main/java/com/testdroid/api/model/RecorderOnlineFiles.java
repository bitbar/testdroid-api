package com.testdroid.api.model;

import com.testdroid.api.APIException;
import java.io.File;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kajdus
 */
@XmlRootElement public class RecorderOnlineFiles extends APIFiles {
    private AndroidAppFile androidApp;

    public RecorderOnlineFiles() {}
    public RecorderOnlineFiles(Long id, DataFile data, AndroidAppFile androidApp) {
        super(id, data);
        this.androidApp = androidApp;
    }

    public AndroidAppFile getAndroidApp() {
        return androidApp;
    }

    public void setAndroidApp(AndroidAppFile androidApp) {
        this.androidApp = androidApp;
    }      

    public void uploadApp(File file) throws APIException {
        this.androidApp = client.postFile(getApplicationURI(), "application/vnd.android.package-archive", file, AndroidAppFile.class);
    }

}
