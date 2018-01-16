package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomDateSerializer;
import net.mayee.commons.CustomFullDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "program_approval_snapshot")
public class ProgramApprovalSnapshot implements Serializable {
    private static final long serialVersionUID = -6776870685616143799L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目ID
     */
    @Column(name = "program_id")
    private Long programId;

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

    private String name;

    /**
     * 项目描述
     */
    private String descp;

    @Column(name = "commit_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date commitDate;

    @Column(name = "start_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date startDate;

    @Column(name = "gray_release_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date grayReleaseDate;

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

    private Integer type;

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
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date modifiedTime;

    /**
     * 账户类型：0或者null或者空位内部账号，1-供方账号
     */
    @Column(name = "account_type")
    private Integer accountType;

    /**
     * Demo评审日期
     */
    @Column(name = "demo_approval_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date demoApprovalDate;

    /**
     * 招标日期
     */
    @Column(name = "bidding_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date biddingDate;

    /**
     * 中标日期
     */
    @Column(name = "winning_bid_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date winningBidDate;

    /**
     * 产品评审日期
     */
    @Column(name = "prod_approval_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date prodApprovalDate;

    /**
     * 开发评审日期
     */
    @Column(name = "dev_approval_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date devApprovalDate;

    /**
     * 测试评审日期
     */
    @Column(name = "test_approval_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date testApprovalDate;

    /**
     * 上线计划日期
     */
    @Column(name = "online_plan_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date onlinePlanDate;

    /**
     * 研发方式：1=招投标，2=自研
     */
    @Column(name = "dev_type")
    private Integer devType;

    /**
     * 判断条件：1=地产，2=商业，3=数据，4=冠寓，5=养老，6=产城，7=基础中心
     */
    @Column(name = "analyzing_conditions")
    private Integer analyzingConditions;

    /**
     * 研发工作量预估
     */
    @Column(name = "dev_workload")
    private Integer devWorkload;

    /**
     * 整体费用预估
     */
    @Column(name = "overall_cost")
    private BigDecimal overallCost;

    @Column(name = "bid_oversingle_cost")
    private BigDecimal bidOversingleCost;

    /**
     * 研发工作量
     */
    @Column(name = "bid_dev_workload")
    private Integer bidDevWorkload;

    /**
     * 整体费用
     */
    @Column(name = "bid_overall_cost")
    private BigDecimal bidOverallCost;

    /**
     * 审批状态：100=审核中，110=审核通过，120=审核驳回，130=变更审核中，140=变更审核驳回
     */
    @Column(name = "approval_status")
    private Integer approvalStatus;

    @Column(name = "bpm_code")
    private String bpmCode;

    @Column(name = "suggestion")
    private String suggestion;

    @Column(name = "apply_account")
    private String applyAccount;

    /**
     * 延期原因，1：需求变更，2：其他原因（不涉及费用）
     */
    @Column(name = "cause_delay")
    private String causeDelay;

    @Column(name = "uid")
    private String uid;

    /**
     * 跟琼朔相关的项目，手动添加琼朔,
     * 1:需要琼朔审批2:不需要琼朔审批
     */
    @Column(name = "report_poor")
    private String reportPoor;

    /**
     * 自动生成项目code（规则：IT_XM000001，顺序递增）
     */
    @Column(name = "new_code")
    private String newCode;

    public String getNewCode() {
        return newCode;
    }

    public void setNewCode(String newCode) {
        this.newCode = newCode;
    }

    @Transient
    private List<ProgramFile> fileList;
    /**
     * 人员：项目经理
     */
    @Transient
    private List<ProgramEmployee> empList;
    /* 责任人 */
    @Transient
    private List<ProgramEmployee> personLiableList;
    /* 项目经理 */
    @Transient
    private List<ProgramEmployee> programManagerList;
    /* 产品经理 */
    @Transient
    private List<ProgramEmployee> productManagerList;
    /* 开发人员 */
    @Transient
    private List<ProgramEmployee> developerList;
    /* UED人员 */
    @Transient
    private List<ProgramEmployee> uedList;
    /* 测试人员 */
    @Transient
    private List<ProgramEmployee> testingList;
    /* 运维人员 */
    @Transient
    private List<ProgramEmployee> operationList;
    /* 运营人员 */
    @Transient
    private List<ProgramEmployee> operateList;
    /* 业务人员 */
    @Transient
    private List<ProgramEmployee> businessList;

    public void setPersonLiableList(List<ProgramEmployee> personLiableList) {
        this.personLiableList = personLiableList;
    }

    public void setProgramManagerList(List<ProgramEmployee> programManagerList) {
        this.programManagerList = programManagerList;
    }

    public void setProductManagerList(List<ProgramEmployee> productManagerList) {
        this.productManagerList = productManagerList;
    }

    public void setDeveloperList(List<ProgramEmployee> developerList) {
        this.developerList = developerList;
    }

    public void setUedList(List<ProgramEmployee> uedList) {
        this.uedList = uedList;
    }

    public void setTestingList(List<ProgramEmployee> testingList) {
        this.testingList = testingList;
    }

    public void setOperationList(List<ProgramEmployee> operationList) {
        this.operationList = operationList;
    }

    public void setOperateList(List<ProgramEmployee> operateList) {
        this.operateList = operateList;
    }

    public void setBusinessList(List<ProgramEmployee> businessList) {
        this.businessList = businessList;
    }

    public List<ProgramEmployee> getPersonLiableList() {
        return personLiableList;
    }

    public List<ProgramEmployee> getProgramManagerList() {
        return programManagerList;
    }

    public List<ProgramEmployee> getProductManagerList() {
        return productManagerList;
    }

    public List<ProgramEmployee> getDeveloperList() {
        return developerList;
    }

    public List<ProgramEmployee> getUedList() {
        return uedList;
    }

    public List<ProgramEmployee> getTestingList() {
        return testingList;
    }

    public List<ProgramEmployee> getOperationList() {
        return operationList;
    }

    public List<ProgramEmployee> getOperateList() {
        return operateList;
    }

    public List<ProgramEmployee> getBusinessList() {
        return businessList;
    }

    public void setReportPoor(String reportPoor) {
        this.reportPoor = reportPoor;
    }

    public String getReportPoor() {
        return reportPoor;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public String getApplyAccount() {
        return applyAccount;
    }

    public void setApplyAccount(String applyAccount) {
        this.applyAccount = applyAccount;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public List<ProgramEmployee> getEmpList() {
        return empList;
    }

    public void setEmpList(List<ProgramEmployee> empList) {
        this.empList = empList;
    }

    public List<ProgramFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<ProgramFile> fileList) {
        this.fileList = fileList;
    }

    /**
     * 内容摘要
     */
    private String remark;

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    /**
     * 审核意见
     */
    @Column(name = "approval_view")
    private String approvalView;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
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
     * @return commit_date
     */
    public Date getCommitDate() {
        return commitDate;
    }

    /**
     * @param commitDate
     */
    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    /**
     * @return start_date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return gray_release_date
     */
    public Date getGrayReleaseDate() {
        return grayReleaseDate;
    }

    /**
     * @param grayReleaseDate
     */
    public void setGrayReleaseDate(Date grayReleaseDate) {
        this.grayReleaseDate = grayReleaseDate;
    }

    /**
     * @return release_date
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate
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
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return program_status
     */
    public Integer getProgramStatus() {
        return programStatus;
    }

    /**
     * @param programStatus
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

    /**
     * 获取Demo评审日期
     *
     * @return demo_approval_date - Demo评审日期
     */
    public Date getDemoApprovalDate() {
        return demoApprovalDate;
    }

    /**
     * 设置Demo评审日期
     *
     * @param demoApprovalDate Demo评审日期
     */
    public void setDemoApprovalDate(Date demoApprovalDate) {
        this.demoApprovalDate = demoApprovalDate;
    }

    /**
     * 获取招标日期
     *
     * @return bidding_date - 招标日期
     */
    public Date getBiddingDate() {
        return biddingDate;
    }

    /**
     * 设置招标日期
     *
     * @param biddingDate 招标日期
     */
    public void setBiddingDate(Date biddingDate) {
        this.biddingDate = biddingDate;
    }

    /**
     * 获取中标日期
     *
     * @return winning_bid_date - 中标日期
     */
    public Date getWinningBidDate() {
        return winningBidDate;
    }

    /**
     * 设置中标日期
     *
     * @param winningBidDate 中标日期
     */
    public void setWinningBidDate(Date winningBidDate) {
        this.winningBidDate = winningBidDate;
    }

    /**
     * 获取产品评审日期
     *
     * @return prod_approval_date - 产品评审日期
     */
    public Date getProdApprovalDate() {
        return prodApprovalDate;
    }

    /**
     * 设置产品评审日期
     *
     * @param prodApprovalDate 产品评审日期
     */
    public void setProdApprovalDate(Date prodApprovalDate) {
        this.prodApprovalDate = prodApprovalDate;
    }

    /**
     * 获取开发评审日期
     *
     * @return dev_approval_date - 开发评审日期
     */
    public Date getDevApprovalDate() {
        return devApprovalDate;
    }

    /**
     * 设置开发评审日期
     *
     * @param devApprovalDate 开发评审日期
     */
    public void setDevApprovalDate(Date devApprovalDate) {
        this.devApprovalDate = devApprovalDate;
    }

    /**
     * 获取测试评审日期
     *
     * @return test_approval_date - 测试评审日期
     */
    public Date getTestApprovalDate() {
        return testApprovalDate;
    }

    /**
     * 设置测试评审日期
     *
     * @param testApprovalDate 测试评审日期
     */
    public void setTestApprovalDate(Date testApprovalDate) {
        this.testApprovalDate = testApprovalDate;
    }

    /**
     * 获取上线计划日期
     *
     * @return online_plan_date - 上线计划日期
     */
    public Date getOnlinePlanDate() {
        return onlinePlanDate;
    }

    /**
     * 设置上线计划日期
     *
     * @param onlinePlanDate 上线计划日期
     */
    public void setOnlinePlanDate(Date onlinePlanDate) {
        this.onlinePlanDate = onlinePlanDate;
    }

    /**
     * 获取研发方式：1=招投标，2=自研
     *
     * @return dev_type - 研发方式：1=招投标，2=自研
     */
    public Integer getDevType() {
        return devType;
    }

    /**
     * 设置研发方式：1=招投标，2=自研
     *
     * @param devType 研发方式：1=招投标，2=自研
     */
    public void setDevType(Integer devType) {
        this.devType = devType;
    }

    /**
     * 获取判断条件：1=地产，2=商业，3=数据，4=冠寓，5=养老，6=产城，7=基础中心
     *
     * @return analyzing_conditions - 判断条件：1=地产，2=商业，3=数据，4=冠寓，5=养老，6=产城，7=基础中心
     */
    public Integer getAnalyzingConditions() {
        return analyzingConditions;
    }

    /**
     * 设置判断条件：1=地产，2=商业，3=数据，4=冠寓，5=养老，6=产城，7=基础中心
     *
     * @param analyzingConditions 判断条件：1=地产，2=商业，3=数据，4=冠寓，5=养老，6=产城，7=基础中心
     */
    public void setAnalyzingConditions(Integer analyzingConditions) {
        this.analyzingConditions = analyzingConditions;
    }

    /**
     * 获取研发工作量预估
     *
     * @return dev_workload - 研发工作量预估
     */
    public Integer getDevWorkload() {
        return devWorkload;
    }

    /**
     * 设置研发工作量预估
     *
     * @param devWorkload 研发工作量预估
     */
    public void setDevWorkload(Integer devWorkload) {
        this.devWorkload = devWorkload;
    }

    /**
     * 获取整体费用预估
     *
     * @return overall_cost - 整体费用预估
     */
    public BigDecimal getOverallCost() {
        return overallCost;
    }

    /**
     * 设置整体费用预估
     *
     * @param overallCost 整体费用预估
     */
    public void setOverallCost(BigDecimal overallCost) {
        this.overallCost = overallCost;
    }

    public BigDecimal getBidOversingleCost() {
        return bidOversingleCost;
    }

    public void setBidOversingleCost(BigDecimal bidOversingleCost) {
        this.bidOversingleCost = bidOversingleCost;
    }

    /**
     * 获取研发工作量
     *
     * @return bid_dev_workload - 研发工作量
     */
    public Integer getBidDevWorkload() {
        return bidDevWorkload;
    }

    /**
     * 设置研发工作量
     *
     * @param bidDevWorkload 研发工作量
     */
    public void setBidDevWorkload(Integer bidDevWorkload) {
        this.bidDevWorkload = bidDevWorkload;
    }

    /**
     * 获取整体费用
     *
     * @return bid_overall_cost - 整体费用
     */
    public BigDecimal getBidOverallCost() {
        return bidOverallCost;
    }

    /**
     * 设置整体费用
     *
     * @param bidOverallCost 整体费用
     */
    public void setBidOverallCost(BigDecimal bidOverallCost) {
        this.bidOverallCost = bidOverallCost;
    }

    /**
     * 获取审批状态：100=审核中，110=审核通过，120=审核驳回，130=变更审核中，140=变更审核驳回
     *
     * @return approval_status - 审批状态：100=审核中，110=审核通过，120=审核驳回，130=变更审核中，140=变更审核驳回
     */
    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * 设置审批状态：100=审核中，110=审核通过，120=审核驳回，130=变更审核中，140=变更审核驳回
     *
     * @param approvalStatus 审批状态：100=审核中，110=审核通过，120=审核驳回，130=变更审核中，140=变更审核驳回
     */
    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    /**
     * @return bpm_code
     */
    public String getBpmCode() {
        return bpmCode;
    }

    /**
     * @param bpmCode
     */
    public void setBpmCode(String bpmCode) {
        this.bpmCode = bpmCode == null ? null : bpmCode.trim();
    }

    /**
     * 获取内容摘要
     *
     * @return remark - 内容摘要
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置内容摘要
     *
     * @param remark 内容摘要
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取审核意见
     *
     * @return approval_view - 审核意见
     */
    public String getApprovalView() {
        return approvalView;
    }

    /**
     * 设置审核意见
     *
     * @param approvalView 审核意见
     */
    public void setApprovalView(String approvalView) {
        this.approvalView = approvalView == null ? null : approvalView.trim();
    }

    public String getCauseDelay() {
        return causeDelay;
    }

    public void setCauseDelay(String causeDelay) {
        this.causeDelay = causeDelay;
    }
}