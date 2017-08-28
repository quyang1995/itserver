package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomFullDateSerializer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "program_employee_change_log")
public class ProgramEmployeeChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目id
     */
    @Column(name = "program_id")
    private Long programId;

    /**
     * 日志信息
     */
    @Column(name = "action_change_info")
    private String actionChangeInfo;

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
     * 获取日志信息
     *
     * @return action_change_info - 日志信息
     */
    public String getActionChangeInfo() {
        return actionChangeInfo;
    }

    /**
     * 设置日志信息
     *
     * @param actionChangeInfo 日志信息
     */
    public void setActionChangeInfo(String actionChangeInfo) {
        this.actionChangeInfo = actionChangeInfo == null ? null : actionChangeInfo.trim();
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