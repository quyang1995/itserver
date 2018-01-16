package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomDateSerializer;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = -6776870685616143799L;
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
     * 产品名称
     */
    private String name;

    private String code;

    /**
     * 产品描述
     */
    private String descp;

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
     * 关联项目id字符串，e.g. xx,xx,xx,...
     */
    @Column(name = "like_program")
    private String likeProgram;

    /**
     * 公开类型：0=私有，1=公开
     */
    private Integer type;

    /**
     * 状态：0=已下线，1=正常，-1=全部
     */
    private Integer status;

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
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    @Column(name = "modified_time")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date modifiedTime;

    @Column(name = "new_code")
    private String newCode;

    @Column(name = "analyzing_conditions")
    private String analyzingConditions;

    public String getAnalyzingConditions() {
        return analyzingConditions;
    }

    public void setAnalyzingConditions(String analyzingConditions) {
        this.analyzingConditions = analyzingConditions;
    }

    public String getNewCode() {
        return newCode;
    }

    public void setNewCode(String newCode) {
        this.newCode = newCode;
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
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取产品描述
     *
     * @return descp - 产品描述
     */
    public String getDescp() {
        return descp;
    }

    /**
     * 设置产品描述
     *
     * @param descp 产品描述
     */
    public void setDescp(String descp) {
        this.descp = descp == null ? null : descp.trim();
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
     * 获取关联项目id字符串，e.g. xx,xx,xx,...
     *
     * @return like_program - 关联项目id字符串，e.g. xx,xx,xx,...
     */
    public String getLikeProgram() {
        return likeProgram;
    }

    /**
     * 设置关联项目id字符串，e.g. xx,xx,xx,...
     *
     * @param likeProgram 关联项目id字符串，e.g. xx,xx,xx,...
     */
    public void setLikeProgram(String likeProgram) {
        this.likeProgram = likeProgram == null ? null : likeProgram.trim();
    }

    /**
     * 获取公开类型：0=私有，1=公开
     *
     * @return type - 公开类型：0=私有，1=公开
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置公开类型：0=私有，1=公开
     *
     * @param type 公开类型：0=私有，1=公开
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取状态：0=已下线，1=正常，-1=全部
     *
     * @return status - 状态：0=已下线，1=正常，-1=全部
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0=已下线，1=正常，-1=全部
     *
     * @param status 状态：0=已下线，1=正常，-1=全部
     */
    public void setStatus(Integer status) {
        this.status = status;
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