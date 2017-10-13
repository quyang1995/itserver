package com.longfor.itserver.common.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomFullDateSerializer;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class BuddyCompany implements Serializable {
    private static final long serialVersionUID = 3213848869687496847L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 供方公司简称
     */
    private String name;

    /**
     * 供方公司全称
     */
    private String fullName;

    /**
     * 描述
     */
    private String descp;

    @JsonSerialize(using = CustomFullDateSerializer.class)
    private Date createTime;

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
     * 获取供方公司简称
     *
     * @return name - 供方公司简称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置供方公司简称
     *
     * @param name 供方公司简称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取供方公司全称
     *
     * @return full_name - 供方公司全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置供方公司全称
     *
     * @param fullName 供方公司全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    /**
     * 获取描述
     *
     * @return descp - 描述
     */
    public String getDescp() {
        return descp;
    }

    /**
     * 设置描述
     *
     * @param descp 描述
     */
    public void setDescp(String descp) {
        this.descp = descp == null ? null : descp.trim();
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