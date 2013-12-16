package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import java.io.File;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Jarno Tuovinen <jarno.tuovinen@bitbar.com>
 */
@XmlRootElement
public class CalabashIOSFiles extends APIFiles {
    private IOSAppFile calabashApp;
    private CalabashTestFile calabashTest;

    public CalabashIOSFiles() {}

    public CalabashIOSFiles(Long id, DataFile data, IOSAppFile calabashApp, CalabashTestFile calabashTest) {
        super(id, data);
        this.calabashApp = calabashApp;
        this.calabashTest = calabashTest;
    }

    public IOSAppFile getCalabashApp() {
        return calabashApp;
    }

    public void setCalabashApp(IOSAppFile calabashApp) {
        this.calabashApp = calabashApp;
    }

    public CalabashTestFile getCalabashTest() {
        return calabashTest;
    }

    public void setCalabashTest(CalabashTestFile calabashTest) {
        this.calabashTest = calabashTest;
    }

    public void uploadApp(File file) throws APIException {
        this.calabashApp = client.postFile(getApplicationURI(), "application/vnd.apple.pkpass", file, IOSAppFile.class);
    }

    public void uploadTest(File file) throws APIException {
        this.calabashTest = client.postFile(getTestURI(), "application/zip", file, CalabashTestFile.class);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        CalabashIOSFiles calabashFiles = (CalabashIOSFiles) from;
        this.calabashApp = calabashFiles.calabashApp;
        this.calabashTest = calabashFiles.calabashTest;
    }

}
