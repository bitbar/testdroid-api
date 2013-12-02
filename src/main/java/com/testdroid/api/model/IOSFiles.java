package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.model.APIFiles;
import java.io.File;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement 
public class IOSFiles extends APIFiles {
    private IOSAppFile app;
    private IOSTestFile test;

    public IOSFiles() {}
    
    public IOSFiles(Long id, DataFile data, IOSAppFile app, IOSTestFile test) {
        super(id, data);
        this.app = app;
        this.test = test;
    }

    public IOSAppFile getApp() {
        return app;
    }

    public void setApp(IOSAppFile app) {
        this.app = app;
    }

    public IOSTestFile getTest() {
        return test;
    }

    public void setTest(IOSTestFile test) {
        this.test = test;
    } 

    public void uploadApp(File file) throws APIException {
        this.app = client.postFile(getApplicationURI(), "application/vnd.apple.pkpass", file, IOSAppFile.class);
    }

    public void uploadTest(File file) throws APIException {
        this.test = client.postFile(getTestURI(), "application/zip", file, IOSTestFile.class);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        IOSFiles iosFiles = (IOSFiles) from;
        this.app = iosFiles.app;
        this.test = iosFiles.test;
    }

}
