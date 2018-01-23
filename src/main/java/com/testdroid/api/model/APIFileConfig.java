package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Arrays;
import java.util.List;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIFileConfig extends APIEntity {

    @XmlType(namespace = "APIFileConfig")
    public static enum Action {
        COPY_TO_DEVICE,
        INSTALL,
        RUN_TEST
    }

    private Action action;

    private List<Action> availableActions = Arrays.asList(Action.values());

    private List<APIUserFileTag> tags;

    public APIFileConfig() {
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

    public List<APIUserFileTag> getTags() {
        return tags;
    }

    public void setTags(List<APIUserFileTag> tags) {
        this.tags = tags;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIFileConfig original = (APIFileConfig) from;
        cloneBase(original);
        this.action = original.action;
        this.tags = original.tags;
        this.availableActions = original.availableActions;
    }
}
