package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.model.APIFiles;
import java.io.File;
import java.io.InputStream;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.http.HttpStatus;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author kajdus
 */
@XmlRootElement 
public class RemoteControlFiles extends APIFiles {
    private AndroidAppFile androidApp;

    public RemoteControlFiles() {}
    
    public RemoteControlFiles(Long id, DataFile data, AndroidAppFile androidApp) {
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
    public InputStream getTestStream() throws APIException {
        throw new APIException(HttpStatus.SC_NOT_FOUND, "Remote control project has no test file");
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        RemoteControlFiles remoteControlFiles = (RemoteControlFiles) from;
        this.androidApp = remoteControlFiles.androidApp;
    }
    
}