package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class RecorderOnlineFiles extends APIFiles {

    private AndroidAppFile app;

    public RecorderOnlineFiles() {
    }

    public RecorderOnlineFiles(Long id, DataFile data, AndroidAppFile app) {
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
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        RecorderOnlineFiles recorderOnlineFiles = (RecorderOnlineFiles) from;
        this.app = recorderOnlineFiles.app;
    }

}
