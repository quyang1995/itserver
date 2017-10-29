package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomFullDateSerializer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "program_comment")
public class ProgramComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 账户类型：0或者null或者空位内部账号，1-供方账号
     */
    @Column(name = "account_type")
    private Integer accountType;

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
    /**
     * 项目id
     */
    @Column(name = "program_id")
    private Long programId;

    /**
     * 父评论id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 内容
     */
    private String content;

    /**
     * 用户名
     */
    @Column(name = "account_id")
    private String accountId;

    /**
     * 员工号
     */
    @Column(name = "employee_code")
    private Long employeeCode;

    /**
     * 员工姓名
     */
    @Column(name = "employee_name")
    private String employeeName;

    /**
     * 部门完整路径
     */
    @Column(name = "full_dept_path")
    private String fullDeptPath;

    /**
     * 人员类型 id
     */
    @Column(name = "employee_type_id")
    private Long employeeTypeId;

    /**
     * 人员类型名称
     */
    @Column(name = "employee_type_text")
    private String employeeTypeText;

    /**
     * 楼层数
     */
    @Column(name = "level_num")
    private Integer levelNum;

    /**
     * IP
     */
    private String ip;

    /**
     * 0未回复，1已回复
     */
    @Column(name = "reply_type")
    private Integer replyType;

    /**
     * 状态：0=不可用，1=正常
     */
    private Integer status;

    @Column(name = "create_time")
    @JsonSerialize(using = CustomFullDateSerializer.class)
    private Date createTime;

    @Column(name = "modified_time")
    @JsonSerialize(using = CustomFullDateSerializer.class)
    private Date modifiedTime;

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
     * 获取父评论id
     *
     * @return parent_id - 父评论id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父评论id
     *
     * @param parentId 父评论id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取用户名
     *
     * @return account_id - 用户名
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * 设置用户名
     *
     * @param accountId 用户名
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * 获取员工号
     *
     * @return employee_code - 员工号
     */
    public Long getEmployeeCode() {
        return employeeCode;
    }

    /**
     * 设置员工号
     *
     * @param employeeCode 员工号
     */
    public void setEmployeeCode(Long employeeCode) {
        this.employeeCode = employeeCode;
    }

    /**
     * 获取员工姓名
     *
     * @return employee_name - 员工姓名
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * 设置员工姓名
     *
     * @param employeeName 员工姓名
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    /**
     * 获取部门完整路径
     *
     * @return full_dept_path - 部门完整路径
     */
    public String getFullDeptPath() {
        return fullDeptPath;
    }

    /**
     * 设置部门完整路径
     *
     * @param fullDeptPath 部门完整路径
     */
    public void setFullDeptPath(String fullDeptPath) {
        this.fullDeptPath = fullDeptPath == null ? null : fullDeptPath.trim();
    }

    /**
     * 获取人员类型 id
     *
     * @return employee_type_id - 人员类型 id
     */
    public Long getEmployeeTypeId() {
        return employeeTypeId;
    }

    /**
     * 设置人员类型 id
     *
     * @param employeeTypeId 人员类型 id
     */
    public void setEmployeeTypeId(Long employeeTypeId) {
        this.employeeTypeId = employeeTypeId;
    }

    /**
     * 获取人员类型名称
     *
     * @return employee_type_text - 人员类型名称
     */
    public String getEmployeeTypeText() {
        return employeeTypeText;
    }

    /**
     * 设置人员类型名称
     *
     * @param employeeTypeText 人员类型名称
     */
    public void setEmployeeTypeText(String employeeTypeText) {
        this.employeeTypeText = employeeTypeText == null ? null : employeeTypeText.trim();
    }

    /**
     * 获取楼层数
     *
     * @return level_num - 楼层数
     */
    public Integer getLevelNum() {
        return levelNum;
    }

    /**
     * 设置楼层数
     *
     * @param levelNum 楼层数
     */
    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    /**
     * 获取IP
     *
     * @return ip - IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP
     *
     * @param ip IP
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取0未回复，1已回复
     *
     * @return reply_type - 0未回复，1已回复
     */
    public Integer getReplyType() {
        return replyType;
    }

    /**
     * 设置0未回复，1已回复
     *
     * @param replyType 0未回复，1已回复
     */
    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    /**
     * 获取状态：0=不可用，1=正常
     *
     * @return status - 状态：0=不可用，1=正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0=不可用，1=正常
     *
     * @param status 状态：0=不可用，1=正常
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * @return modified_time
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * @param modifiedTime
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}