package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import java.io.File;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author kajdus
 */
@XmlRootElement 
public class RecorderOnlineFiles extends APIFiles {
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

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        RecorderOnlineFiles recorderOnlineFiles = (RecorderOnlineFiles) from;
        this.androidApp = recorderOnlineFiles.androidApp;
    }

}
