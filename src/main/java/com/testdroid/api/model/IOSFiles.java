package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.model.APIFiles;
import java.io.File;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author kajdus
 */
@XmlRootElement 
public class IOSFiles extends APIFiles {
    private IOSAppFile iosApp;
    private IOSTestFile iosTest;

    public IOSFiles() {}
    
    public IOSFiles(Long id, DataFile data, IOSAppFile iosApp, IOSTestFile iosTest) {
        super(id, data);
        this.iosApp = iosApp;
        this.iosTest = iosTest;
    }

    public IOSAppFile getIosApp() {
        return iosApp;
    }

    public void setIosApp(IOSAppFile iosApp) {
        this.iosApp = iosApp;
    }

    public IOSTestFile getIosTest() {
        return iosTest;
    }

    public void setIosTest(IOSTestFile iosTest) {
        this.iosTest = iosTest;
    } 

    public void uploadApp(File file) throws APIException {
        this.iosApp = client.postFile(getApplicationURI(), "application/vnd.apple.pkpass", file, IOSAppFile.class);
    }

    public void uploadTest(File file) throws APIException {
        this.iosTest = client.postFile(getTestURI(), "application/zip", file, IOSTestFile.class);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        IOSFiles iosFiles = (IOSFiles) from;
        this.iosApp = iosFiles.iosApp;
        this.iosTest = iosFiles.iosTest;
    }

}
