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
public class CalabashFiles extends APIFiles {
    private AndroidAppFile calabashApp;
    private CalabashTestFile calabashTest;

    public CalabashFiles() {}
    
    public CalabashFiles(Long id, DataFile data, AndroidAppFile calabashApp, CalabashTestFile calabashTest) {
        super(id, data);
        this.calabashApp = calabashApp;
        this.calabashTest = calabashTest;
    }

    public AndroidAppFile getCalabashApp() {
        return calabashApp;
    }

    public void setCalabashApp(AndroidAppFile calabashApp) {
        this.calabashApp = calabashApp;
    }

    public CalabashTestFile getCalabashTest() {
        return calabashTest;
    }

    public void setCalabashTest(CalabashTestFile calabashTest) {
        this.calabashTest = calabashTest;
    }

    public void uploadApp(File file) throws APIException {
        this.calabashApp = client.postFile(getApplicationURI(), "application/vnd.android.package-archive", file, AndroidAppFile.class);
    }

    public void uploadTest(File file) throws APIException {
        this.calabashTest = client.postFile(getTestURI(), "application/zip", file, CalabashTestFile.class);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        CalabashFiles calabashFiles = (CalabashFiles) from;
        this.calabashApp = calabashFiles.calabashApp;
        this.calabashTest = calabashFiles.calabashTest;
    }
    
}
