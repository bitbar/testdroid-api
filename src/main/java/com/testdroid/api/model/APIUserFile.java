package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIUserFile extends APIEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date createTime;

    private APIUserFileProperty[] fileProperties;

    private State state;

    private String name;

    private Long size;

    private URL directUrl;

    private URL iconDirectUrl;

    private String mimetype;

    private Direction direction;

    private InputType inputType;

    private boolean duplicate;

    private String userEmail;

    private Long userId;

    private boolean isShared;

    private transient String storage;

    private transient String uri;

    private transient String iconUri;

    @XmlType(namespace = "APIUserFile")
    public enum InputType {
        APPLICATION,
        TEST,
        DATA
    }

    @XmlType(namespace = "APIUserFile")
    public enum Direction {
        INPUT,
        OUTPUT
    }

    @XmlType(namespace = "APIUserFile", name = "APIUserFileState")
    public enum State {
        PREPARING,
        READY
    }

    public APIUserFile() {
    }

    public APIUserFile(Long id) {
        super(id);
    }

    public APIUserFile(
            Long id, String name, LocalDateTime createTime, Long size, State state, String storage, String uri,
            String iconUri, String mimetype, Direction direction, InputType inputType, Long userId, String userEmail,
            boolean isShared) {
        super(id);
        this.name = name;
        this.createTime = TimeConverter.toDate(createTime);
        this.size = size;
        this.state = state;
        this.storage = storage;
        this.uri = uri;
        this.iconUri = iconUri;
        this.mimetype = mimetype;
        this.direction = direction;
        this.inputType = inputType;
        this.userId = userId;
        this.userEmail = userEmail;
        this.isShared = isShared;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public APIUserFileProperty[] getFileProperties() {
        return fileProperties;
    }

    public void setFileProperties(APIUserFileProperty[] fileProperties) {
        this.fileProperties = fileProperties;
    }

    private String getFileURI() {
        return createUri(selfURI, "/file");
    }

    private String getIconURI() {
        return createUri(selfURI, "/icon");
    }

    private String getFileTagsURI() {
        return createUri(selfURI, "/tags");
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public URL getDirectUrl() {
        return directUrl;
    }

    public void setDirectUrl(URL directUrl) {
        this.directUrl = directUrl;
    }

    @JsonIgnore
    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    @JsonIgnore
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public InputType getInputType() {
        return inputType;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }

    public boolean isDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }

    public URL getIconDirectUrl() {
        return iconDirectUrl;
    }

    public void setIconDirectUrl(URL iconDirectUrl) {
        this.iconDirectUrl = iconDirectUrl;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    @JsonIgnore
    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    @JsonIgnore
    public InputStream getIcon() throws APIException {
        return getFile(getIconURI());
    }

    @JsonIgnore
    public InputStream getFile() throws APIException {
        return getFile(getFileURI());
    }

    @JsonIgnore
    public APIListResource<APIUserFileTag> getTagsResource() throws APIException {
        return getListResource(getFileTagsURI(), APIUserFileTag.class);
    }

    public void delete() throws APIException {
        deleteResource(selfURI);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIUserFile apiUserFile = (APIUserFile) from;
        cloneBase(from);
        this.name = apiUserFile.name;
        this.createTime = apiUserFile.createTime;
        this.size = apiUserFile.size;
        this.fileProperties = apiUserFile.fileProperties;
        this.state = apiUserFile.state;
        this.directUrl = apiUserFile.directUrl;
        this.iconDirectUrl = apiUserFile.iconDirectUrl;
        this.storage = apiUserFile.storage;
        this.uri = apiUserFile.uri;
        this.iconUri = apiUserFile.iconUri;
        this.direction = apiUserFile.direction;
        this.mimetype = apiUserFile.mimetype;
        this.inputType = apiUserFile.inputType;
        this.userId = apiUserFile.userId;
        this.userEmail = apiUserFile.userEmail;
        this.isShared = apiUserFile.isShared;
    }
}
