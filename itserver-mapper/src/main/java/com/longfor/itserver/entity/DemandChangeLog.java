package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomFullDateSerializer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "demand_change_log")
public class DemandChangeLog {
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
     * 需求id
     */
    @Column(name = "demand_id")
    private Long demandId;

    /**
     * 改之前需求描述
     */
    @Column(name = "bef_descp")
    private String befDescp;

    /**
     * 日志信息
     */
    @Column(name = "action_change_info")
    private String actionChangeInfo;

    /**
     * 日志修改类型：1=描述修改，2=其它修改
     */
    private Integer type;

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
     * 获取需求id
     *
     * @return demand_id - 需求id
     */
    public Long getDemandId() {
        return demandId;
    }

    /**
     * 设置需求id
     *
     * @param demandId 需求id
     */
    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    /**
     * 获取改之前需求描述
     *
     * @return bef_descp - 改之前需求描述
     */
    public String getBefDescp() {
        return befDescp;
    }

    /**
     * 设置改之前需求描述
     *
     * @param befDescp 改之前需求描述
     */
    public void setBefDescp(String befDescp) {
        this.befDescp = befDescp == null ? null : befDescp.trim();
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
     * 获取日志修改类型：1=描述修改，2=其它修改
     *
     * @return type - 日志修改类型：1=描述修改，2=其它修改
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置日志修改类型：1=描述修改，2=其它修改
     *
     * @param type 日志修改类型：1=描述修改，2=其它修改
     */
    public void setType(Integer type) {
        this.type = type;
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