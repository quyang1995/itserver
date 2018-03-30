package com.longfor.itserver.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "program_employee_draft")
public class ProgramEmployeeDraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目uuid
     */
    @Column(name = "program_id")
    private Long programId;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "employee_code")
    private Long employeeCode;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "full_dept_path")
    private String fullDeptPath;

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

    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modified_time")
    private Date modifiedTime;

    /**
     * 账户类型：0或者null或者空位内部账号，1-供方账号
     */
    @Column(name = "account_type")
    private Integer accountType;

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
     * 获取项目uuid
     *
     * @return program_id - 项目uuid
     */
    public Long getProgramId() {
        return programId;
    }

    /**
     * 设置项目uuid
     *
     * @param programId 项目uuid
     */
    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    /**
     * @return account_id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * @return employee_code
     */
    public Long getEmployeeCode() {
        return employeeCode;
    }

    /**
     * @param employeeCode
     */
    public void setEmployeeCode(Long employeeCode) {
        this.employeeCode = employeeCode;
    }

    /**
     * @return employee_name
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    /**
     * @return full_dept_path
     */
    public String getFullDeptPath() {
        return fullDeptPath;
    }

    /**
     * @param fullDeptPath
     */
    public void setFullDeptPath(String fullDeptPath) {
        this.fullDeptPath = fullDeptPath == null ? null : fullDeptPath.trim();
    }

    /**
     * @return employee_type
     */
    public Integer getEmployeeType() {
        return employeeType;
    }

    /**
     * @param employeeType
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
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
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

    /**
     * 获取账户类型：0或者null或者空位内部账号，1-供方账号
     *
     * @return account_type - 账户类型：0或者null或者空位内部账号，1-供方账号
     */
    public Integer getAccountType() {
        return accountType;
    }

    /**
     * 设置账户类型：0或者null或者空位内部账号，1-供方账号
     *
     * @param accountType 账户类型：0或者null或者空位内部账号，1-供方账号
     */
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
}