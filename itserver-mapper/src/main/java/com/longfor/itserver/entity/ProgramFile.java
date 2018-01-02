package com.longfor.itserver.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "program_file")
public class ProgramFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目id
     */
    @Column(name = "program_id")
    private Long programId;

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

    /**
     * 流程类型：1-立项申请，2-Demo评审，3-招标文件，4-中标通知，5-产品评审，6-进入开发，7-测试部署，8-上线计划，9-灰度发布，10-延期上线，11-变更需求，12-终止项目
     */
    private Integer type;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 文件绝对路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 快照表id
     */
    @Column(name = "snapshot_id")
    private Long snapshotId;

    public void setSnapshotId(Long snapshotId) {
        this.snapshotId = snapshotId;
    }

    public Long getSnapshotId() {
        return snapshotId;
    }

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
     * 获取项目id
     *
     * @return program_id - 项目id
     */
    public Long getProgramId() {
        return programId;
    }

    /**
     * 设置项目id
     *
     * @param programId 项目id
     */
    public void setProgramId(Long programId) {
        this.programId = programId;
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
     * 获取流程类型：1-立项申请，2-Demo评审，3-招标文件，4-中标通知，5-产品评审，6-进入开发，7-测试部署，8-上线计划，9-灰度发布，10-延期上线，11-变更需求，12-终止项目
     *
     * @return type - 流程类型：1-立项申请，2-Demo评审，3-招标文件，4-中标通知，5-产品评审，6-进入开发，7-测试部署，8-上线计划，9-灰度发布，10-延期上线，11-变更需求，12-终止项目
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置流程类型：1-立项申请，2-Demo评审，3-招标文件，4-中标通知，5-产品评审，6-进入开发，7-测试部署，8-上线计划，9-灰度发布，10-延期上线，11-变更需求，12-终止项目
     *
     * @param type 流程类型：1-立项申请，2-Demo评审，3-招标文件，4-中标通知，5-产品评审，6-进入开发，7-测试部署，8-上线计划，9-灰度发布，10-延期上线，11-变更需求，12-终止项目
     */
    public void setType(Integer type) {
        this.type = type;
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