package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomFullDateSerializer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bug_info")
public class BugInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 归属反馈id
     */
    @Column(name = "feed_back_id")
    private Long feedBackId;

    /**
     * 归属产品/项目id
     */
    @Column(name = "relation_id")
    private Long relationId;

    /**
     * 归属类型，1=产品，2=项目
     */
    @Column(name = "relation_type")
    private Integer relationType;

    /**
     * BUG名称
     */
    private String name;

    /**
     * BUG描述
     */
    private String descp;

    /**
     * 复现步骤
     */
    @Column(name = "reproduction_step")
    private String reproductionStep;

    /**
     * 浏览器
     */
    private String brower;

    /**
     * 期望完成日期
     */
    @Column(name = "hope_date")
    @JsonSerialize(using = CustomFullDateSerializer.class)
    private Date hopeDate;

    /**
     * 指派人用户名
     */
    @Column(name = "callon_account_id")
    private String callonAccountId;

    /**
     * 指派人员工号
     */
    @Column(name = "callon_employee_code")
    private Long callonEmployeeCode;

    /**
     * 指派人员工姓名
     */
    @Column(name = "callon_employee_name")
    private String callonEmployeeName;

    /**
     * 指派人部门完整路径
     */
    @Column(name = "callon_full_dept_path")
    private String callonFullDeptPath;

    /**
     * 发起人用户名
     */
    @Column(name = "drafted_account_id")
    private String draftedAccountId;

    /**
     * 发起人员工号
     */
    @Column(name = "drafted_employee_code")
    private Long draftedEmployeeCode;

    /**
     * 发起人员工姓名
     */
    @Column(name = "drafted_employee_name")
    private String draftedEmployeeName;

    /**
     * 发起人部门完整路径
     */
    @Column(name = "drafted_full_dept_path")
    private String draftedFullDeptPath;

    /**
     * 抄送人账号字符串，e.g. xx,xx,
     */
    @Column(name = "cc_account")
    private String ccAccount;

    /**
     * 关联产品id字符串，e.g. 1,2,3,...
     */
    @Column(name = "like_product")
    private String likeProduct;

    /**
     * 关联项目id字符串，e.g. 1,2,3,...
     */
    @Column(name = "like_program")
    private String likeProgram;

    /**
     * 优先级：0=低，1=中，2=高，3=非常高
     */
    private Integer level;

    /**
     * 状态：0=已取消，1=已关闭，2=待处理，3=处理中，4=已完成
     */
    private Integer status;

    private Integer channel;

    /**
     * 最后修改人账户id
     */
    @Column(name = "modified_account_id")
    private String modifiedAccountId;

    /**
     * 最后修改人名称
     */
    @Column(name = "modified_name")
    private String modifiedName;

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
     * 获取归属反馈id
     *
     * @return feed_back_id - 归属反馈id
     */
    public Long getFeedBackId() {
        return feedBackId;
    }

    /**
     * 设置归属反馈id
     *
     * @param feedBackId 归属反馈id
     */
    public void setFeedBackId(Long feedBackId) {
        this.feedBackId = feedBackId;
    }

    /**
     * 获取归属产品/项目id
     *
     * @return relation_id - 归属产品/项目id
     */
    public Long getRelationId() {
        return relationId;
    }

    /**
     * 设置归属产品/项目id
     *
     * @param relationId 归属产品/项目id
     */
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    /**
     * 获取归属类型，1=产品，2=项目
     *
     * @return relation_type - 归属类型，1=产品，2=项目
     */
    public Integer getRelationType() {
        return relationType;
    }

    /**
     * 设置归属类型，1=产品，2=项目
     *
     * @param relationType 归属类型，1=产品，2=项目
     */
    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    /**
     * 获取BUG名称
     *
     * @return name - BUG名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置BUG名称
     *
     * @param name BUG名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取BUG描述
     *
     * @return descp - BUG描述
     */
    public String getDescp() {
        return descp;
    }

    /**
     * 设置BUG描述
     *
     * @param descp BUG描述
     */
    public void setDescp(String descp) {
        this.descp = descp == null ? null : descp.trim();
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
     * 获取浏览器
     *
     * @return brower - 浏览器
     */
    public String getBrower() {
        return brower;
    }

    /**
     * 设置浏览器
     *
     * @param brower 浏览器
     */
    public void setBrower(String brower) {
        this.brower = brower == null ? null : brower.trim();
    }

    /**
     * 获取期望完成日期
     *
     * @return hope_date - 期望完成日期
     */
    public Date getHopeDate() {
        return hopeDate;
    }

    /**
     * 设置期望完成日期
     *
     * @param hopeDate 期望完成日期
     */
    public void setHopeDate(Date hopeDate) {
        this.hopeDate = hopeDate;
    }

    /**
     * 获取指派人用户名
     *
     * @return callon_account_id - 指派人用户名
     */
    public String getCallonAccountId() {
        return callonAccountId;
    }

    /**
     * 设置指派人用户名
     *
     * @param callonAccountId 指派人用户名
     */
    public void setCallonAccountId(String callonAccountId) {
        this.callonAccountId = callonAccountId == null ? null : callonAccountId.trim();
    }

    /**
     * 获取指派人员工号
     *
     * @return callon_employee_code - 指派人员工号
     */
    public Long getCallonEmployeeCode() {
        return callonEmployeeCode;
    }

    /**
     * 设置指派人员工号
     *
     * @param callonEmployeeCode 指派人员工号
     */
    public void setCallonEmployeeCode(Long callonEmployeeCode) {
        this.callonEmployeeCode = callonEmployeeCode;
    }

    /**
     * 获取指派人员工姓名
     *
     * @return callon_employee_name - 指派人员工姓名
     */
    public String getCallonEmployeeName() {
        return callonEmployeeName;
    }

    /**
     * 设置指派人员工姓名
     *
     * @param callonEmployeeName 指派人员工姓名
     */
    public void setCallonEmployeeName(String callonEmployeeName) {
        this.callonEmployeeName = callonEmployeeName == null ? null : callonEmployeeName.trim();
    }

    /**
     * 获取指派人部门完整路径
     *
     * @return callon_full_dept_path - 指派人部门完整路径
     */
    public String getCallonFullDeptPath() {
        return callonFullDeptPath;
    }

    /**
     * 设置指派人部门完整路径
     *
     * @param callonFullDeptPath 指派人部门完整路径
     */
    public void setCallonFullDeptPath(String callonFullDeptPath) {
        this.callonFullDeptPath = callonFullDeptPath == null ? null : callonFullDeptPath.trim();
    }

    /**
     * 获取发起人用户名
     *
     * @return drafted_account_id - 发起人用户名
     */
    public String getDraftedAccountId() {
        return draftedAccountId;
    }

    /**
     * 设置发起人用户名
     *
     * @param draftedAccountId 发起人用户名
     */
    public void setDraftedAccountId(String draftedAccountId) {
        this.draftedAccountId = draftedAccountId == null ? null : draftedAccountId.trim();
    }

    /**
     * 获取发起人员工号
     *
     * @return drafted_employee_code - 发起人员工号
     */
    public Long getDraftedEmployeeCode() {
        return draftedEmployeeCode;
    }

    /**
     * 设置发起人员工号
     *
     * @param draftedEmployeeCode 发起人员工号
     */
    public void setDraftedEmployeeCode(Long draftedEmployeeCode) {
        this.draftedEmployeeCode = draftedEmployeeCode;
    }

    /**
     * 获取发起人员工姓名
     *
     * @return drafted_employee_name - 发起人员工姓名
     */
    public String getDraftedEmployeeName() {
        return draftedEmployeeName;
    }

    /**
     * 设置发起人员工姓名
     *
     * @param draftedEmployeeName 发起人员工姓名
     */
    public void setDraftedEmployeeName(String draftedEmployeeName) {
        this.draftedEmployeeName = draftedEmployeeName == null ? null : draftedEmployeeName.trim();
    }

    /**
     * 获取发起人部门完整路径
     *
     * @return drafted_full_dept_path - 发起人部门完整路径
     */
    public String getDraftedFullDeptPath() {
        return draftedFullDeptPath;
    }

    /**
     * 设置发起人部门完整路径
     *
     * @param draftedFullDeptPath 发起人部门完整路径
     */
    public void setDraftedFullDeptPath(String draftedFullDeptPath) {
        this.draftedFullDeptPath = draftedFullDeptPath == null ? null : draftedFullDeptPath.trim();
    }

    /**
     * 获取抄送人账号字符串，e.g. xx,xx,
     *
     * @return cc_account - 抄送人账号字符串，e.g. xx,xx,
     */
    public String getCcAccount() {
        return ccAccount;
    }

    /**
     * 设置抄送人账号字符串，e.g. xx,xx,
     *
     * @param ccAccount 抄送人账号字符串，e.g. xx,xx,
     */
    public void setCcAccount(String ccAccount) {
        this.ccAccount = ccAccount == null ? null : ccAccount.trim();
    }

    /**
     * 获取关联产品id字符串，e.g. 1,2,3,...
     *
     * @return like_product - 关联产品id字符串，e.g. 1,2,3,...
     */
    public String getLikeProduct() {
        return likeProduct;
    }

    /**
     * 设置关联产品id字符串，e.g. 1,2,3,...
     *
     * @param likeProduct 关联产品id字符串，e.g. 1,2,3,...
     */
    public void setLikeProduct(String likeProduct) {
        this.likeProduct = likeProduct == null ? null : likeProduct.trim();
    }

    /**
     * 获取关联项目id字符串，e.g. 1,2,3,...
     *
     * @return like_program - 关联项目id字符串，e.g. 1,2,3,...
     */
    public String getLikeProgram() {
        return likeProgram;
    }

    /**
     * 设置关联项目id字符串，e.g. 1,2,3,...
     *
     * @param likeProgram 关联项目id字符串，e.g. 1,2,3,...
     */
    public void setLikeProgram(String likeProgram) {
        this.likeProgram = likeProgram == null ? null : likeProgram.trim();
    }

    /**
     * 获取优先级：0=低，1=中，2=高，3=非常高
     *
     * @return level - 优先级：0=低，1=中，2=高，3=非常高
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置优先级：0=低，1=中，2=高，3=非常高
     *
     * @param level 优先级：0=低，1=中，2=高，3=非常高
     */
    public void setLevel(Integer level) {
        this.level = level;
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
     * @return channel
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     * @param channel
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     * 获取最后修改人账户id
     *
     * @return modified_account_id - 最后修改人账户id
     */
    public String getModifiedAccountId() {
        return modifiedAccountId;
    }

    /**
     * 设置最后修改人账户id
     *
     * @param modifiedAccountId 最后修改人账户id
     */
    public void setModifiedAccountId(String modifiedAccountId) {
        this.modifiedAccountId = modifiedAccountId == null ? null : modifiedAccountId.trim();
    }

    /**
     * 获取最后修改人名称
     *
     * @return modified_name - 最后修改人名称
     */
    public String getModifiedName() {
        return modifiedName;
    }

    /**
     * 设置最后修改人名称
     *
     * @param modifiedName 最后修改人名称
     */
    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName == null ? null : modifiedName.trim();
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