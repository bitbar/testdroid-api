package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.dto.Context;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIFileSet extends APIEntity {

    private Date createTime;

    private Long fileCount;

    private String name;

    private String userEmail;

    private Long userId;

    private boolean isShared;

    public APIFileSet() {
    }

    public APIFileSet(Long id, String name, LocalDateTime createTime, Long fileCount, Long ownerEmail,
            String userEmail, boolean isShared) {
        super(id);
        this.name = name;
        this.createTime = TimeConverter.toDate(createTime);
        this.fileCount = fileCount;
        this.userId = ownerEmail;
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

    public Long getFileCount() {
        return fileCount;
    }

    public void setFileCount(Long fileCount) {
        this.fileCount = fileCount;
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

    private String getIncludedFilesURI() {
        return createUri(selfURI, "/files");
    }

    private String getIncludedFilesURI(Long fileId) {
        return createUri(selfURI, "/files/" + fileId);
    }

    @JsonIgnore
    public APIListResource<APIUserFile> getIncludedFilesResource() throws APIException {
        return getListResource(getIncludedFilesURI(), APIUserFile.class);
    }

    @JsonIgnore
    public APIListResource<APIUserFile> getIncludedFilesResource(Context<APIUserFile> context) throws APIException {
        return getListResource(getIncludedFilesURI(), context);
    }

    public void delete() throws APIException {
        deleteResource(selfURI);
    }

    public void addFile(APIUserFile file) throws APIException {
        postResource(getIncludedFilesURI(), Collections.singletonMap("fileId", file.getId()), null);
    }

    public void removeFile(APIUserFile file) throws APIException {
        deleteResource(getIncludedFilesURI(file.getId()));
    }

    public void update() throws APIException {
        APIFileSet fileSet = postResource(selfURI, Collections.singletonMap("name", name), APIFileSet.class);
        clone(fileSet);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIFileSet apiFileSet = (APIFileSet) from;
        cloneBase(from);
        this.name = apiFileSet.name;
        this.createTime = apiFileSet.createTime;
        this.fileCount = apiFileSet.fileCount;
        this.userId = apiFileSet.userId;
        this.userEmail = apiFileSet.userEmail;
        this.isShared = apiFileSet.isShared;
    }
}
