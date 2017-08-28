package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomFullDateSerializer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "employee_type")
public class EmployeeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 类型名称
     */
    @Column(name = "type_text")
    private String typeText;

    /**
     * 用于显示排序
     */
    private Integer sort;

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
     * 获取类型名称
     *
     * @return type_text - 类型名称
     */
    public String getTypeText() {
        return typeText;
    }

    /**
     * 设置类型名称
     *
     * @param typeText 类型名称
     */
    public void setTypeText(String typeText) {
        this.typeText = typeText == null ? null : typeText.trim();
    }

    /**
     * 获取用于显示排序
     *
     * @return sort - 用于显示排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置用于显示排序
     *
     * @param sort 用于显示排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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