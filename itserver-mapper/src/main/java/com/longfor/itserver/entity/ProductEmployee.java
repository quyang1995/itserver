package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomFullDateSerializer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "product_employee")
public class ProductEmployee {
    private static final long serialVersionUID = -6776870685616143799L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 产品id
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 账户id
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
     * 人员类型：1=责任人，2=成员
     */
    @Column(name = "employee_type")
    private Integer employeeType;

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
     * 获取产品id
     *
     * @return product_id - 产品id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置产品id
     *
     * @param productId 产品id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取账户id
     *
     * @return account_id - 账户id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * 设置账户id
     *
     * @param accountId 账户id
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
     * 获取人员类型：1=责任人，2=成员
     *
     * @return employee_type - 人员类型：1=责任人，2=成员
     */
    public Integer getEmployeeType() {
        return employeeType;
    }

    /**
     * 设置人员类型：1=责任人，2=成员
     *
     * @param employeeType 人员类型：1=责任人，2=成员
     */
    public void setEmployeeType(Integer employeeType) {
        this.employeeType = employeeType;
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