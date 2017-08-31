package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomFullDateSerializer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "demand_file")
public class DemandFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件扩展名
     */
    @Column(name = "file_suffix")
    private String fileSuffix;

    /**
     * 文件大小
     */
    @Column(name = "file_size")
    private String fileSize;

    @Column(name = "create_time")
    @JsonSerialize(using = CustomFullDateSerializer.class)
    private Date createTime;

    /**
     * 文件绝对路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取文件名称
     *
     * @return file_name - 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 获取文件扩展名
     *
     * @return file_suffix - 文件扩展名
     */
    public String getFileSuffix() {
        return fileSuffix;
    }

    /**
     * 设置文件扩展名
     *
     * @param fileSuffix 文件扩展名
     */
    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix == null ? null : fileSuffix.trim();
    }

    /**
     * 获取文件大小
     *
     * @return file_size - 文件大小
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * 设置文件大小
     *
     * @param fileSize 文件大小
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取文件绝对路径
     *
     * @return file_path - 文件绝对路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置文件绝对路径
     *
     * @param filePath 文件绝对路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }
}