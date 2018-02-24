package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomDateSerializer;
import net.mayee.commons.CustomFullDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "program")
public class Program implements Serializable {
    @Column(name = "demo_approval_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date demoApprovalDate;
    @Column(name = "bidding_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date biddingDate;
    @Column(name = "winning_bid_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date winningBidDate;
    @Column(name = "prod_approval_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date prodApprovalDate;
    @Column(name = "dev_approval_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date devApprovalDate;
    @Column(name = "test_approval_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date testApprovalDate;
    @Column(name = "online_plan_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date onlinePlanDate;
    @Column(name = "dev_type")
    private Integer devType;
    @Column(name = "analyzing_conditions")
    private String analyzingConditions;
    @Column(name = "dev_workload")
    private int devWorkload;
    @Column(name = "overall_cost")
    private BigDecimal overallCost;
    @Column(name = "bid_dev_workload")
    private int bidDevWorkload;
    @Column(name = "bid_overall_cost")
    private BigDecimal bidOverallCost;
    @Column(name = "bid_oversingle_cost")
    private BigDecimal bidOversingleCost;
    @Column(name = "approval_status")
    private int approvalStatus;


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
     * 归属产品
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 归属产品名称
     */
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_code")
    private String productCode;

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

    @Column(name = "ued_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date uedDate;

    @Column(name = "architecture_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date architectureDate;

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

    @Column(name = "new_code")
    private String newCode;

    @Column(name = "replay_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date replayDate;

    @Column(name = "all_extension_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date allExtensionDate;

    /**
     * 是否关注，0：未关注，1：已关注
     */
    @Transient
    private int isFollow;

    public Date getReplayDate() {
        return replayDate;
    }

    public void setReplayDate(Date replayDate) {
        this.replayDate = replayDate;
    }

    public Date getAllExtensionDate() {
        return allExtensionDate;
    }

    public void setAllExtensionDate(Date allExtensionDate) {
        this.allExtensionDate = allExtensionDate;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public void setNewCode(String newCode) {
        this.newCode = newCode;
    }

    public String getNewCode() {
        return newCode;
    }

    public BigDecimal getBidOversingleCost() {
        return bidOversingleCost;
    }

    public void setBidOversingleCost(BigDecimal bidOversingleCost) {
        this.bidOversingleCost = bidOversingleCost;
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
     * 获取归属产品名称
     *
     * @return product_name - 归属产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置归属产品名称
     *
     * @param productName 归属产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     * @return product_code
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
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
     * @return ued_date
     */
    public Date getUedDate() {
        return uedDate;
    }

    /**
     * @param uedDate
     */
    public void setUedDate(Date uedDate) {
        this.uedDate = uedDate;
    }

    /**
     * @return architecture_date
     */
    public Date getArchitectureDate() {
        return architectureDate;
    }

    /**
     * @param architectureDate
     */
    public void setArchitectureDate(Date architectureDate) {
        this.architectureDate = architectureDate;
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

    public Date getDemoApprovalDate() {
        return demoApprovalDate;
    }

    public void setDemoApprovalDate(Date demoApprovalDate) {
        this.demoApprovalDate = demoApprovalDate;
    }

    public Date getBiddingDate() {
        return biddingDate;
    }

    public void setBiddingDate(Date biddingDate) {
        this.biddingDate = biddingDate;
    }

    public Date getWinningBidDate() {
        return winningBidDate;
    }

    public void setWinningBidDate(Date winningBidDate) {
        this.winningBidDate = winningBidDate;
    }

    public Date getProdApprovalDate() {
        return prodApprovalDate;
    }

    public void setProdApprovalDate(Date prodApprovalDate) {
        this.prodApprovalDate = prodApprovalDate;
    }

    public Date getDevApprovalDate() {
        return devApprovalDate;
    }

    public void setDevApprovalDate(Date devApprovalDate) {
        this.devApprovalDate = devApprovalDate;
    }

    public Date getTestApprovalDate() {
        return testApprovalDate;
    }

    public void setTestApprovalDate(Date testApprovalDate) {
        this.testApprovalDate = testApprovalDate;
    }

    public Date getOnlinePlanDate() {
        return onlinePlanDate;
    }

    public void setOnlinePlanDate(Date onlinePlanDate) {
        this.onlinePlanDate = onlinePlanDate;
    }

    public Integer getDevType() {
        return devType;
    }

    public void setDevType(Integer devType) {
        this.devType = devType;
    }

    public String getAnalyzingConditions() {
        return analyzingConditions;
    }

    public void setAnalyzingConditions(String analyzingConditions) {
        this.analyzingConditions = analyzingConditions;
    }

    public int getDevWorkload() {
        return devWorkload;
    }

    public void setDevWorkload(int devWorkload) {
        this.devWorkload = devWorkload;
    }

    public BigDecimal getOverallCost() {
        return overallCost;
    }

    public void setOverallCost(BigDecimal overallCost) {
        this.overallCost = overallCost;
    }

    public int getBidDevWorkload() {
        return bidDevWorkload;
    }

    public void setBidDevWorkload(int bidDevWorkload) {
        this.bidDevWorkload = bidDevWorkload;
    }

    public BigDecimal getBidOverallCost() {
        return bidOverallCost;
    }

    public void setBidOverallCost(BigDecimal bidOverallCost) {
        this.bidOverallCost = bidOverallCost;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}