package com.hjl.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author hjl
 * @Description 文件实体类
 * @Date 2019/8/3 16:33
 */
public class Files implements Serializable {
    /**
     * 文件编号
      */
    private Integer fileId;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件描述
     */
    private String fileDesc;
    /**
     * 文件版本
     */
    private String fileVersion;
    /**
     * 文件大小(值)
     */
    private long fileSize;
    /**
     * 文件大小的单位(KB,MB.....)
     */
    private String fileSizeUtil;
    /**
     * 文件存在状态
     */
    private String status;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 最后修改时间
     */
    private Timestamp lastUpdateTime;
    /**
     * 最后修改人
     */
    private String lastUpdateUser;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public String getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(String fileVersion) {
        this.fileVersion = fileVersion;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSizeUtil() {
        return fileSizeUtil;
    }

    public void setFileSizeUtil(String fileSizeUtil) {
        this.fileSizeUtil = fileSizeUtil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }
}
