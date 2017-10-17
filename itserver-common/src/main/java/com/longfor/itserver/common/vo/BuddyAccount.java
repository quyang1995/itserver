package com.longfor.itserver.common.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomDateSerializer;
import net.mayee.commons.CustomFullDateSerializer;

import java.io.Serializable;
import java.util.Date;

public class BuddyAccount implements Serializable {
    private static final long serialVersionUID = -653566611207069924L;
    private Long id;
    /**
     * uid
     */
    private Long uid;

    /**
     * 用户名
     */
    private String loginName;

    /**
     * 密码
     */
    private String loginPassword;

    /**
     * 供方公司id
     */
    private Long companyId;

    /**
     * 供方公司简称
     */
    private String companyName;

    /**
     * 供方公司全称
     */
    private String companyFullName;

    /**
     * 接口人uid
     */
    private Long contactAccountUid;

    /**
     * 接口人账户id
     */
    private String contactAccountId;

    /**
     * 接口人员工号
     */
    private Long contactEmployeeCode;

    /**
     * 接口人员工姓名
     */
    private String contactEmployeeName;

    /**
     * 接口人部门完整路径
     */
    private String contactFullDeptPath;

    /**
     * 姓名
     */
    private String name;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 性别
     */
    private Integer psSex;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * pc端mac地址
     */
    private String pcMacAddress;

    /**
     * pc名
     */
    private String pcName;

    /**
     * 账户过期日期
     */
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date expiredDate;

    /**
     * 状态,0=未激活,1=正常,2=锁定
     */
    private Integer status;

    @JsonSerialize(using = CustomFullDateSerializer.class)
    private Date createTime;

    @JsonSerialize(using = CustomFullDateSerializer.class)
    private Date modifiedTime;
    private String salt;
    @JsonSerialize(using = CustomFullDateSerializer.class)
    private Date lastLoginTime;
    private String createId;

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
     * 获取uid
     *
     * @return uid - uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置uid
     *
     * @param uid uid
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取用户名
     *
     * @return login_name - 用户名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置用户名
     *
     * @param loginName 用户名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 获取密码
     *
     * @return login_password - 密码
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * 设置密码
     *
     * @param loginPassword 密码
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    /**
     * 获取供方公司id
     *
     * @return company_id - 供方公司id
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 设置供方公司id
     *
     * @param companyId 供方公司id
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取供方公司简称
     *
     * @return company_name - 供方公司简称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置供方公司简称
     *
     * @param companyName 供方公司简称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 获取供方公司全称
     *
     * @return company_full_name - 供方公司全称
     */
    public String getCompanyFullName() {
        return companyFullName;
    }

    /**
     * 设置供方公司全称
     *
     * @param companyFullName 供方公司全称
     */
    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName == null ? null : companyFullName.trim();
    }

    /**
     * 获取接口人uid
     *
     * @return contact_account_uid - 接口人uid
     */
    public Long getContactAccountUid() {
        return contactAccountUid;
    }

    /**
     * 设置接口人uid
     *
     * @param contactAccountUid 接口人uid
     */
    public void setContactAccountUid(Long contactAccountUid) {
        this.contactAccountUid = contactAccountUid;
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
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取拼音
     *
     * @return pinyin - 拼音
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 设置拼音
     *
     * @param pinyin 拼音
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    /**
     * 获取性别
     *
     * @return ps_sex - 性别
     */
    public Integer getPsSex() {
        return psSex;
    }

    /**
     * 设置性别
     *
     * @param psSex 性别
     */
    public void setPsSex(Integer psSex) {
        this.psSex = psSex;
    }

    /**
     * 获取手机号
     *
     * @return mobile_phone - 手机号
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置手机号
     *
     * @param mobilePhone 手机号
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取pc端mac地址
     *
     * @return pc_mac_address - pc端mac地址
     */
    public String getPcMacAddress() {
        return pcMacAddress;
    }

    /**
     * 设置pc端mac地址
     *
     * @param pcMacAddress pc端mac地址
     */
    public void setPcMacAddress(String pcMacAddress) {
        this.pcMacAddress = pcMacAddress == null ? null : pcMacAddress.trim();
    }

    /**
     * 获取pc名
     *
     * @return pc_name - pc名
     */
    public String getPcName() {
        return pcName;
    }

    /**
     * 设置pc名
     *
     * @param pcName pc名
     */
    public void setPcName(String pcName) {
        this.pcName = pcName == null ? null : pcName.trim();
    }

    /**
     * 获取账户过期日期
     *
     * @return expired_date - 账户过期日期
     */
    public Date getExpiredDate() {
        return expiredDate;
    }

    /**
     * 设置账户过期日期
     *
     * @param expiredDate 账户过期日期
     */
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    /**
     * 获取状态,0=未激活,1=正常,2=锁定
     *
     * @return status - 状态,0=未激活,1=正常,2=锁定
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态,0=未激活,1=正常,2=锁定
     *
     * @param status 状态,0=未激活,1=正常,2=锁定
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