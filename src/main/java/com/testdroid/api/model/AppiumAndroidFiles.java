package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

/**
 * @author Jarno Tuovinen <jarno.tuovinen@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class AppiumAndroidFiles extends APIFiles {

    private AndroidAppFile app;

    public AppiumAndroidFiles() {
    }

    public AppiumAndroidFiles(Long id, DataFile data, AndroidAppFile app) {
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
        AppiumAndroidFiles appiumFiles = (AppiumAndroidFiles) from;
        this.app = appiumFiles.app;
    }
}
