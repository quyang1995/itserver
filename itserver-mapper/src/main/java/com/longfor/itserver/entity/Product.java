package com.longfor.itserver.entity;

import java.util.Date;
import javax.persistence.*;

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 产品名称
     */
    private String name;

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
     * 关联产品id字符串，e.g. xx,xx,xx,...
     */
    @Column(name = "like_product")
    private String likeProduct;

    /**
     * 公开类型：0=私有，1=公开
     */
    private Integer type;

    /**
     * 状态：0=已关闭，1=未关闭
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
    private Date createTime;

    @Column(name = "modified_time")
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
     * 获取关联产品id字符串，e.g. xx,xx,xx,...
     *
     * @return like_product - 关联产品id字符串，e.g. xx,xx,xx,...
     */
    public String getLikeProduct() {
        return likeProduct;
    }

    /**
     * 设置关联产品id字符串，e.g. xx,xx,xx,...
     *
     * @param likeProduct 关联产品id字符串，e.g. xx,xx,xx,...
     */
    public void setLikeProduct(String likeProduct) {
        this.likeProduct = likeProduct == null ? null : likeProduct.trim();
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
     * 获取状态：0=已关闭，1=未关闭
     *
     * @return status - 状态：0=已关闭，1=未关闭
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0=已关闭，1=未关闭
     *
     * @param status 状态：0=已关闭，1=未关闭
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