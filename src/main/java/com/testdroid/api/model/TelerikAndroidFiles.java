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
public class TelerikAndroidFiles extends APIFiles {

    private AndroidAppFile app;

    private TelerikTestFile test;

    public TelerikAndroidFiles() {
    }

    public TelerikAndroidFiles(Long id, DataFile data, AndroidAppFile app, TelerikTestFile test) {
        super(id, data);
        this.app = app;
        this.test = test;
    }

    public AndroidAppFile getApp() {
        return app;
    }

    public void setApp(AndroidAppFile app) {
        this.app = app;
    }

    public void uploadApp(File file) throws APIException {
        this.app = client
                .postFile(getApplicationURI(), "application/vnd.android.package-archive", file, AndroidAppFile.class);
    }

    public TelerikTestFile getTest() {
        return test;
    }

    public void setTest(TelerikTestFile test) {
        this.test = test;
    }

    public void uploadTest(File file) throws APIException {
        this.test = client.postFile(getTestURI(), "application/zip", file, TelerikTestFile.class);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        TelerikAndroidFiles files = (TelerikAndroidFiles) from;
        this.app = files.app;
        this.test = files.test;
    }
}
