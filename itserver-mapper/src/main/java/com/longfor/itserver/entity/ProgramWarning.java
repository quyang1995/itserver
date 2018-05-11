package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomDateSerializer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "program_warning")
public class ProgramWarning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目id
     */
    @Column(name = "program_id")
    private Long programId;

    /**
     * 项目状态
     */
    @Column(name = "program_status")
    private Integer programStatus;

    /**
     * 预警值：0=绿色正常，1=黄色中风险-存在潜在问题，进度可控，2=红色高风险-可能导致延期，进度不可控
     */
    private Integer warning;

    /**
     * 备注说明
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    /**
     * 创建人员id
     */
    @Column(name = "create_account_id")
    private String createAccountId;

    /**
     * 创建人员名
     */
    @Column(name = "create_name")
    private String createName;

    /**
     * 修改人员id
     */
    @Column(name = "modified_account_id")
    private String modifiedAccountId;

    /**
     * 修改人员名
     */
    @Column(name = "modified_name")
    private String modifiedName;

    /**
     * 修改时间
     */
    @Column(name = "modified_time")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date modifiedTime;

    /**
     * 项目名称
     */
    @Transient
    private String programName;

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
     * 获取项目状态
     *
     * @return program_status - 项目状态
     */
    public Integer getProgramStatus() {
        return programStatus;
    }

    /**
     * 设置项目状态
     *
     * @param programStatus 项目状态
     */
    public void setProgramStatus(Integer programStatus) {
        this.programStatus = programStatus;
    }

    /**
     * 获取预警值：0=绿色正常，1=黄色中风险-存在潜在问题，进度可控，2=红色高风险-可能导致延期，进度不可控
     *
     * @return warning - 预警值：0=绿色正常，1=黄色中风险-存在潜在问题，进度可控，2=红色高风险-可能导致延期，进度不可控
     */
    public Integer getWarning() {
        return warning;
    }

    /**
     * 设置预警值：0=绿色正常，1=黄色中风险-存在潜在问题，进度可控，2=红色高风险-可能导致延期，进度不可控
     *
     * @param warning 预警值：0=绿色正常，1=黄色中风险-存在潜在问题，进度可控，2=红色高风险-可能导致延期，进度不可控
     */
    public void setWarning(Integer warning) {
        this.warning = warning;
    }

    /**
     * 获取备注说明
     *
     * @return remark - 备注说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注说明
     *
     * @param remark 备注说明
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人员id
     *
     * @return create_account_id - 创建人员id
     */
    public String getCreateAccountId() {
        return createAccountId;
    }

    /**
     * 设置创建人员id
     *
     * @param createAccountId 创建人员id
     */
    public void setCreateAccountId(String createAccountId) {
        this.createAccountId = createAccountId == null ? null : createAccountId.trim();
    }

    /**
     * 获取创建人员名
     *
     * @return create_name - 创建人员名
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 设置创建人员名
     *
     * @param createName 创建人员名
     */
    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public String getModifiedAccountId() {
        return modifiedAccountId;
    }

    public void setModifiedAccountId(String modifiedAccountId) {
        this.modifiedAccountId = modifiedAccountId;
    }

    public String getModifiedName() {
        return modifiedName;
    }

    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}