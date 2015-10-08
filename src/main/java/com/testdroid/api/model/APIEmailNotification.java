package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Piotr Kostecki <piotr.kostecki@bitbar.com>
 */
@XmlRootElement
public class APIEmailNotification extends APIEntity {

    private String message;

    private String subject;

    private boolean toggle;

    private Long[] users;

    public APIEmailNotification() {
    }

    public APIEmailNotification(String subject, String message, boolean toggle,  Long[] users) {
        this.subject = subject;
        this.message = message;
        this.toggle = toggle;
        this.users =  users;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isToggle() {
        return toggle;
    }

    public Long[] getUsers() {
        return users;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setToggle(boolean toggle) {
        this.toggle = toggle;
    }

    public void setUsers(Long[] users) {
        this.users = users;
    }


    @Override protected <T extends APIEntity> void clone(T from) {
        APIEmailNotification apiDeviceGroup = (APIEmailNotification) from;
        cloneBase(from);
        this.subject = apiDeviceGroup.subject;
        this.message = apiDeviceGroup.message;
        this.toggle = apiDeviceGroup.toggle;
        this.users = apiDeviceGroup.users;
    }
}
