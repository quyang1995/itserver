package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomDateSerializer;
import net.mayee.commons.CustomFullDateSerializer;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Table(name = "program")
public class Program implements Serializable {

    private static final long serialVersionUID = -6776870685616143799L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 归属产品
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目描述
     */
    private String descp;

    /**
     * 立项日期
     */
    @Column(name = "commit_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date commitDate;

    /**
     * 启动日期
     */
    @Column(name = "start_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date startDate;

    /**
     * 灰度日期
     */
    @Column(name = "gray_release_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date grayReleaseDate;

    /**
     * 发布日期
     */
    @Column(name = "release_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date releaseDate;

    /**
     * 关联产品id字符串，e.g. 1,2,3,...
     */
    @Column(name = "like_product")
    private String likeProduct;

    /**
     * 关联项目id字符串，e.g. 1,2,3,...
     */
    @Column(name = "like_program")
    private String likeProgram;

    /**
     * 公开类型：0=私有，1=公开
     */
    private Integer type;

    /**
     * 状态：0=未开始，1=进行中，2=已完成
     */
    @Column(name = "program_status")
    private Integer programStatus;

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
    
    @Transient
    private String productName;

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
     * 获取归属产品
     *
     * @return product_id - 归属产品
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置归属产品
     *
     * @param productId 归属产品
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取项目名称
     *
     * @return name - 项目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置项目名称
     *
     * @param name 项目名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取项目描述
     *
     * @return descp - 项目描述
     */
    public String getDescp() {
        return descp;
    }

    /**
     * 设置项目描述
     *
     * @param descp 项目描述
     */
    public void setDescp(String descp) {
        this.descp = descp == null ? null : descp.trim();
    }

    /**
     * 获取立项日期
     *
     * @return commit_date - 立项日期
     */
    public Date getCommitDate() {
        return commitDate;
    }

    /**
     * 设置立项日期
     *
     * @param commitDate 立项日期
     */
    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    /**
     * 获取启动日期
     *
     * @return start_date - 启动日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置启动日期
     *
     * @param startDate 启动日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取灰度日期
     *
     * @return gray_release_date - 灰度日期
     */
    public Date getGrayReleaseDate() {
        return grayReleaseDate;
    }

    /**
     * 设置灰度日期
     *
     * @param grayReleaseDate 灰度日期
     */
    public void setGrayReleaseDate(Date grayReleaseDate) {
        this.grayReleaseDate = grayReleaseDate;
    }

    /**
     * 获取发布日期
     *
     * @return release_date - 发布日期
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * 设置发布日期
     *
     * @param releaseDate 发布日期
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 获取关联产品id字符串，e.g. 1,2,3,...
     *
     * @return like_product - 关联产品id字符串，e.g. 1,2,3,...
     */
    public String getLikeProduct() {
        return likeProduct;
    }

    /**
     * 设置关联产品id字符串，e.g. 1,2,3,...
     *
     * @param likeProduct 关联产品id字符串，e.g. 1,2,3,...
     */
    public void setLikeProduct(String likeProduct) {
        this.likeProduct = likeProduct == null ? null : likeProduct.trim();
    }

    /**
     * 获取关联项目id字符串，e.g. 1,2,3,...
     *
     * @return like_program - 关联项目id字符串，e.g. 1,2,3,...
     */
    public String getLikeProgram() {
        return likeProgram;
    }

    /**
     * 设置关联项目id字符串，e.g. 1,2,3,...
     *
     * @param likeProgram 关联项目id字符串，e.g. 1,2,3,...
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
     * 获取状态：0=未开始，1=进行中，2=已完成
     *
     * @return program_status - 状态：0=未开始，1=进行中，2=已完成
     */
    public Integer getProgramStatus() {
        return programStatus;
    }

    /**
     * 设置状态：0=未开始，1=进行中，2=已完成
     *
     * @param programStatus 状态：0=未开始，1=进行中，2=已完成
     */
    public void setProgramStatus(Integer programStatus) {
        this.programStatus = programStatus;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


}