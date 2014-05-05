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
public class AndroidFiles extends APIFiles {

    private AndroidAppFile app;

    private AndroidTestFile test;

    public AndroidFiles() {
    }

    public AndroidFiles(Long id, DataFile data, AndroidAppFile app, AndroidTestFile test) {
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

    public AndroidTestFile getTest() {
        return test;
    }

    public void setTest(AndroidTestFile test) {
        this.test = test;
    }

    public void uploadApp(File file) throws APIException {
        this.app = client
                .postFile(getApplicationURI(), "application/vnd.android.package-archive", file, AndroidAppFile.class);
    }

    public void uploadTest(File file) throws APIException {
        this.test = client
                .postFile(getTestURI(), "application/vnd.android.package-archive", file, AndroidTestFile.class);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        AndroidFiles androidFiles = (AndroidFiles) from;
        this.app = androidFiles.app;
        this.test = androidFiles.test;
    }

}
