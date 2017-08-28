package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomFullDateSerializer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "feed_back")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 归属产品
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 问题标题
     */
    @Column(name = "problem_title")
    private String problemTitle;

    /**
     * 问题描述
     */
    @Column(name = "problem_descp")
    private String problemDescp;

    /**
     * 复现步骤
     */
    @Column(name = "reproduction_step")
    private String reproductionStep;

    /**
     * 系统环境
     */
    @Column(name = "sys_environment")
    private String sysEnvironment;

    /**
     * 接口人账户id
     */
    @Column(name = "contact_account_id")
    private String contactAccountId;

    /**
     * 接口人员工号
     */
    @Column(name = "contact_employee_code")
    private Long contactEmployeeCode;

    /**
     * 接口人员工姓名
     */
    @Column(name = "contact_employee_name")
    private String contactEmployeeName;

    /**
     * 接口人部门完整路径
     */
    @Column(name = "contact_full_dept_path")
    private String contactFullDeptPath;

    /**
     * 反馈类型：0=功能异常，1=功能建议
     */
    private Integer type;

    /**
     * 状态：0=已取消，1=已关闭，2=待处理，3=处理中，4=已完成
     */
    private Integer status;

    /**
     * 渠道: 0=OA，1=龙客
     */
    private Integer channel;

    /**
     * 提交人账户id
     */
    @Column(name = "modified_account_id")
    private String modifiedAccountId;

    /**
     * 提交人员工号
     */
    @Column(name = "modified_employee_code")
    private Long modifiedEmployeeCode;

    /**
     * 提交人名称
     */
    @Column(name = "modified_name")
    private String modifiedName;

    /**
     * 提交人部门完整路径
     */
    @Column(name = "modified_full_dept_path")
    private String modifiedFullDeptPath;

    @Column(name = "file_path")
    private String filePath;

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
     * 获取归属产品
     *
     * @return product_id - 归属产品
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置归属产品
     *
     * @param productId 归属产品
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取产品名称
     *
     * @return name - 产品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置产品名称
     *
     * @param name 产品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取问题标题
     *
     * @return problem_title - 问题标题
     */
    public String getProblemTitle() {
        return problemTitle;
    }

    /**
     * 设置问题标题
     *
     * @param problemTitle 问题标题
     */
    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle == null ? null : problemTitle.trim();
    }

    /**
     * 获取问题描述
     *
     * @return problem_descp - 问题描述
     */
    public String getProblemDescp() {
        return problemDescp;
    }

    /**
     * 设置问题描述
     *
     * @param problemDescp 问题描述
     */
    public void setProblemDescp(String problemDescp) {
        this.problemDescp = problemDescp == null ? null : problemDescp.trim();
    }

    /**
     * 获取复现步骤
     *
     * @return reproduction_step - 复现步骤
     */
    public String getReproductionStep() {
        return reproductionStep;
    }

    /**
     * 设置复现步骤
     *
     * @param reproductionStep 复现步骤
     */
    public void setReproductionStep(String reproductionStep) {
        this.reproductionStep = reproductionStep == null ? null : reproductionStep.trim();
    }

    /**
     * 获取系统环境
     *
     * @return sys_environment - 系统环境
     */
    public String getSysEnvironment() {
        return sysEnvironment;
    }

    /**
     * 设置系统环境
     *
     * @param sysEnvironment 系统环境
     */
    public void setSysEnvironment(String sysEnvironment) {
        this.sysEnvironment = sysEnvironment == null ? null : sysEnvironment.trim();
    }

    /**
     * 获取接口人账户id
     *
     * @return contact_account_id - 接口人账户id
     */
    public String getContactAccountId() {
        return contactAccountId;
    }

    /**
     * 设置接口人账户id
     *
     * @param contactAccountId 接口人账户id
     */
    public void setContactAccountId(String contactAccountId) {
        this.contactAccountId = contactAccountId == null ? null : contactAccountId.trim();
    }

    /**
     * 获取接口人员工号
     *
     * @return contact_employee_code - 接口人员工号
     */
    public Long getContactEmployeeCode() {
        return contactEmployeeCode;
    }

    /**
     * 设置接口人员工号
     *
     * @param contactEmployeeCode 接口人员工号
     */
    public void setContactEmployeeCode(Long contactEmployeeCode) {
        this.contactEmployeeCode = contactEmployeeCode;
    }

    /**
     * 获取接口人员工姓名
     *
     * @return contact_employee_name - 接口人员工姓名
     */
    public String getContactEmployeeName() {
        return contactEmployeeName;
    }

    /**
     * 设置接口人员工姓名
     *
     * @param contactEmployeeName 接口人员工姓名
     */
    public void setContactEmployeeName(String contactEmployeeName) {
        this.contactEmployeeName = contactEmployeeName == null ? null : contactEmployeeName.trim();
    }

    /**
     * 获取接口人部门完整路径
     *
     * @return contact_full_dept_path - 接口人部门完整路径
     */
    public String getContactFullDeptPath() {
        return contactFullDeptPath;
    }

    /**
     * 设置接口人部门完整路径
     *
     * @param contactFullDeptPath 接口人部门完整路径
     */
    public void setContactFullDeptPath(String contactFullDeptPath) {
        this.contactFullDeptPath = contactFullDeptPath == null ? null : contactFullDeptPath.trim();
    }

    /**
     * 获取反馈类型：0=功能异常，1=功能建议
     *
     * @return type - 反馈类型：0=功能异常，1=功能建议
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置反馈类型：0=功能异常，1=功能建议
     *
     * @param type 反馈类型：0=功能异常，1=功能建议
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取状态：0=已取消，1=已关闭，2=待处理，3=处理中，4=已完成
     *
     * @return status - 状态：0=已取消，1=已关闭，2=待处理，3=处理中，4=已完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0=已取消，1=已关闭，2=待处理，3=处理中，4=已完成
     *
     * @param status 状态：0=已取消，1=已关闭，2=待处理，3=处理中，4=已完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取渠道: 0=OA，1=龙客
     *
     * @return channel - 渠道: 0=OA，1=龙客
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     * 设置渠道: 0=OA，1=龙客
     *
     * @param channel 渠道: 0=OA，1=龙客
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     * 获取提交人账户id
     *
     * @return modified_account_id - 提交人账户id
     */
    public String getModifiedAccountId() {
        return modifiedAccountId;
    }

    /**
     * 设置提交人账户id
     *
     * @param modifiedAccountId 提交人账户id
     */
    public void setModifiedAccountId(String modifiedAccountId) {
        this.modifiedAccountId = modifiedAccountId == null ? null : modifiedAccountId.trim();
    }

    /**
     * 获取提交人员工号
     *
     * @return modified_employee_code - 提交人员工号
     */
    public Long getModifiedEmployeeCode() {
        return modifiedEmployeeCode;
    }

    /**
     * 设置提交人员工号
     *
     * @param modifiedEmployeeCode 提交人员工号
     */
    public void setModifiedEmployeeCode(Long modifiedEmployeeCode) {
        this.modifiedEmployeeCode = modifiedEmployeeCode;
    }

    /**
     * 获取提交人名称
     *
     * @return modified_name - 提交人名称
     */
    public String getModifiedName() {
        return modifiedName;
    }

    /**
     * 设置提交人名称
     *
     * @param modifiedName 提交人名称
     */
    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName == null ? null : modifiedName.trim();
    }

    /**
     * 获取提交人部门完整路径
     *
     * @return modified_full_dept_path - 提交人部门完整路径
     */
    public String getModifiedFullDeptPath() {
        return modifiedFullDeptPath;
    }

    /**
     * 设置提交人部门完整路径
     *
     * @param modifiedFullDeptPath 提交人部门完整路径
     */
    public void setModifiedFullDeptPath(String modifiedFullDeptPath) {
        this.modifiedFullDeptPath = modifiedFullDeptPath == null ? null : modifiedFullDeptPath.trim();
    }

    /**
     * @return file_path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
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