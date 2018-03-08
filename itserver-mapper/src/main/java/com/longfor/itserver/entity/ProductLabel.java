package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomDateSerializer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "product_label")
public class ProductLabel {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标签所属类型
     */
    @Column(name = "label_type_id")
    private Long labelTypeId;

    /**
     * 标签名称
     */
    @Column(name = "label_name")
    private String labelName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modified_time")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date modifiedTime;

    /**
     * 最后修改人账户id
     */
    @Column(name = "modified_account_id")
    private String modifiedAccountId;

    /**
     * 最后修改人账户name
     */
    @Column(name = "modified_name")
    private String modifiedName;

    /**
     * 创建人账户id
     */
    @Column(name = "create_account_id")
    private String createAccountId;

    /**
     * 创建人账户name
     */
    @Column(name = "create_name")
    private String createName;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取标签所属类型
     *
     * @return label_type_id - 标签所属类型
     */
    public Long getLabelTypeId() {
        return labelTypeId;
    }

    /**
     * 设置标签所属类型
     *
     * @param labelTypeId 标签所属类型
     */
    public void setLabelTypeId(Long labelTypeId) {
        this.labelTypeId = labelTypeId;
    }

    /**
     * 获取标签名称
     *
     * @return label_name - 标签名称
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * 设置标签名称
     *
     * @param labelName 标签名称
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return modified_time - 修改时间
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifiedTime 修改时间
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
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
     * 获取最后修改人账户name
     *
     * @return modified_account_name - 最后修改人账户name
     */
    public String getModifiedName() {
        return modifiedName;
    }

    /**
     * 设置最后修改人账户name
     *
     * @param modifiedName 最后修改人账户iname
     */
    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName == null ? null : modifiedName.trim();
    }

    /**
     * 获取创建人账户name
     *
     * @return create_account_name - 创建人账户name
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 设置创建人账户name
     *
     * @param createName 创建人账户iname
     */
    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    /**
     * 获取最后创建人账户id
     *
     * @return create_account_id - 创建人账户id
     */
    public String getCreateAccountId() {
        return createAccountId;
    }

    /**
     * 设置创建人账户id
     *
     * @param createAccountId 创建人账户id
     */
    public void setCreateAccountId(String createAccountId) {
        this.createAccountId = createAccountId == null ? null : createAccountId.trim();
    }
}