package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
public class TelerikIOSFiles extends APIFiles {

    private IOSAppFile app;

    public TelerikIOSFiles() {
    }

    public TelerikIOSFiles(Long id, DataFile data, IOSAppFile app) {
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
        this.app = client
                .postFile(getApplicationURI(), "application/vnd.android.package-archive", file, IOSAppFile.class);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        TelerikIOSFiles files = (TelerikIOSFiles) from;
        this.app = files.app;
    }
}
