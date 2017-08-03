package com.wei.util.upload;

;

/**
 * Description
 * Author ed
 * Created 2017-08-03 14:10
 */
public class UploadDto {
    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 真实文件名
     */
    private String fileName;

    /**
     * 随机文件名
     */
    private String uuidName;

    /**
     * 文件路径
     */
    private String filePath;

    public UploadDto() {

    }

    public UploadDto(Long fileSize, String fileName, String uuidName, String filePath) {
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.uuidName = uuidName;
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUuidName() {
        return uuidName;
    }

    public void setUuidName(String uuidName) {
        this.uuidName = uuidName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}