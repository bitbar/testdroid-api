package com.testdroid.api.model.build;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIBuildResultConfig extends APIEntity implements Serializable {

    private String sourceName;

    private String destinationName;

    private boolean isDirectory;

    private String fileUrlEnvVariable;

    private String storage;

    public APIBuildResultConfig() {
    }

    public APIBuildResultConfig(String sourceName, String destinationName, boolean isDirectory) {
        this.sourceName = sourceName;
        this.destinationName = destinationName;
        this.isDirectory = isDirectory;
    }

    public APIBuildResultConfig(
            String sourceName, String destinationName, boolean isDirectory, String fileUrlEnvVariable) {
        this(sourceName, destinationName, isDirectory);
        this.fileUrlEnvVariable = fileUrlEnvVariable;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public String getFileUrlEnvVariable() {
        return fileUrlEnvVariable;
    }

    public void setFileUrlEnvVariable(String fileUrlEnvVariable) {
        this.fileUrlEnvVariable = fileUrlEnvVariable;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIBuildResultConfig original = (APIBuildResultConfig) from;
        cloneBase(from);
        this.sourceName = original.sourceName;
        this.destinationName = original.destinationName;
        this.isDirectory = original.isDirectory;
        this.fileUrlEnvVariable = original.fileUrlEnvVariable;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getStorage() {
        return storage;
    }
}
