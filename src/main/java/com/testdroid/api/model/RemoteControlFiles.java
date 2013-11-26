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
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement 
public class RemoteControlFiles extends APIFiles {
    private AndroidAppFile app;

    public RemoteControlFiles() {}
    
    public RemoteControlFiles(Long id, DataFile data, AndroidAppFile app) {
        super(id, data);
        this.app = app;
    }

    public AndroidAppFile getApp() {
        return app;
    }

    public void setApp(AndroidAppFile app) {
        this.app = app;
    }      

    public void uploadApp(File file) throws APIException {
        this.app = client.postFile(getApplicationURI(), "application/vnd.android.package-archive", file, AndroidAppFile.class);
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
        this.app = remoteControlFiles.app;
    }
    
}