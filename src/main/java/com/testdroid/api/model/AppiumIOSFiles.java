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
public class AppiumIOSFiles extends APIFiles {

    private IOSAppFile app;

    public AppiumIOSFiles() {
    }

    public AppiumIOSFiles(Long id, DataFile data, IOSAppFile app) {
        super(id, data);
        this.app = app;
    }

    public IOSAppFile getApp() {
        return app;
    }

    public void setApp(IOSAppFile appiumApp) {
        this.app = appiumApp;
    }

    public void uploadApp(File file) throws APIException {
        this.app = client.postFile(getApplicationURI(), "application/vnd.android.package-archive", file, IOSAppFile.class);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        AppiumIOSFiles appiumFiles = (AppiumIOSFiles) from;
        this.app = appiumFiles.app;
    }
}
