package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIFileConfig extends APIEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlType(namespace = "APIFileConfig")
    public enum Action {
        COPY_TO_DEVICE("data"),
        INSTALL("app"),
        RUN_TEST("test");

        private final String fileType;

        Action(String fileType) {
            this.fileType = fileType;
        }

        public String getFileType() {
            return fileType;
        }
    }

    private Action action;

    private List<Action> availableActions = new ArrayList<>();

    private APIUserFile file;

    public APIFileConfig() {
    }

    public APIFileConfig(Long id, Action action) {
        super(id);
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Action> getAvailableActions() {
        return availableActions;
    }

    public void setAvailableActions(List<Action> availableActions) {
        this.availableActions = availableActions;
    }

    public APIUserFile getFile() {
        return file;
    }

    public void setFile(APIUserFile file) {
        if (file != null) {
            this.id = file.getId();
        }
        this.file = file;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIFileConfig original = (APIFileConfig) from;
        cloneBase(original);
        this.action = original.action;
        this.availableActions = original.availableActions;
        this.file = original.file;
    }
}
