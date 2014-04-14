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
public class CalabashFiles extends APIFiles {

    private AndroidAppFile app;

    private CalabashTestFile test;

    public CalabashFiles() {
    }

    public CalabashFiles(Long id, DataFile data, AndroidAppFile app, CalabashTestFile test) {
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

    public CalabashTestFile getTest() {
        return test;
    }

    public void setTest(CalabashTestFile test) {
        this.test = test;
    }

    public void uploadApp(File file) throws APIException {
        this.app = client.postFile(getApplicationURI(), "application/vnd.android.package-archive", file, AndroidAppFile.class);
    }

    public void uploadTest(File file) throws APIException {
        this.test = client.postFile(getTestURI(), "application/zip", file, CalabashTestFile.class);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        CalabashFiles calabashFiles = (CalabashFiles) from;
        this.app = calabashFiles.app;
        this.test = calabashFiles.test;
    }

}
