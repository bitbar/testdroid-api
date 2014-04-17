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
public class CalabashIOSFiles extends APIFiles {

    private IOSAppFile app;

    private CalabashTestFile test;

    public CalabashIOSFiles() {
    }

    public CalabashIOSFiles(Long id, DataFile data, IOSAppFile calabashApp, CalabashTestFile calabashTest) {
        super(id, data);
        this.app = calabashApp;
        this.test = calabashTest;
    }

    public IOSAppFile getApp() {
        return app;
    }

    public void setApp(IOSAppFile calabashApp) {
        this.app = calabashApp;
    }

    public CalabashTestFile getTest() {
        return test;
    }

    public void setTest(CalabashTestFile calabashTest) {
        this.test = calabashTest;
    }

    public void uploadApp(File file) throws APIException {
        this.app = client.postFile(getApplicationURI(), "application/vnd.apple.pkpass", file, IOSAppFile.class);
    }

    public void uploadTest(File file) throws APIException {
        this.test = client.postFile(getTestURI(), "application/zip", file, CalabashTestFile.class);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        CalabashIOSFiles calabashFiles = (CalabashIOSFiles) from;
        this.app = calabashFiles.app;
        this.test = calabashFiles.test;
    }

}
