package com.longfor.itserver.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "program_draft")
public class ProgramDraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 操作类型：100=添加项目，110=立项，120=Demo评审，130=招投标申请，140=中标申请，150=产品评审，160=开发评审，170=测试评审，180=上线计划，190=灰度发布，193=全面推广，195=项目复盘，200=延期上线，210=需求变更，900=完成，999=终止
     */
    @Column(name = "operation_type")
    private Integer operationType;

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

    /**
     * 归属产品code
     */
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
     * 项目状态：100=未立项，110=立项，120=Demo评审，130=招投标申请，140=中标申请，150=产品评审，160=开发评审，170=测试评审，180=上线计划，190=灰度发布，193=全面推广，195=项目复盘，200=延期上线，210=需求变更，900=完成，999=终止
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
    private Date createTime;

    @Column(name = "modified_time")
    private Date modifiedTime;

    /**
     * 账户类型：0或者null或者空位内部账号，1-供方账号
     */
    @Column(name = "account_type")
    private Integer accountType;

    /**
     * 立项日期
     */
    @Column(name = "commit_date")
    private Date commitDate;

    /**
     * Demo评审日期
     */
    @Column(name = "demo_approval_date")
    private Date demoApprovalDate;

    /**
     * 招标日期
     */
    @Column(name = "bidding_date")
    private Date biddingDate;

    /**
     * 中标日期
     */
    @Column(name = "winning_bid_date")
    private Date winningBidDate;

    /**
     * 产品评审日期
     */
    @Column(name = "prod_approval_date")
    private Date prodApprovalDate;

    /**
     * 开发评审日期
     */
    @Column(name = "dev_approval_date")
    private Date devApprovalDate;

    /**
     * 测试评审日期
     */
    @Column(name = "test_approval_date")
    private Date testApprovalDate;

    /**
     * 上线计划日期
     */
    @Column(name = "online_plan_date")
    private Date onlinePlanDate;

    /**
     * 灰度发布日期
     */
    @Column(name = "gray_release_date")
    private Date grayReleaseDate;

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

    /**
     * 研发工作量
     */
    @Column(name = "bid_dev_workload")
    private Integer bidDevWorkload;

    /**
     * 审批状态：100=审核中，110=审核通过，120=审核驳回，130=变更审核中，140=变更审核驳回
     */
    @Column(name = "approval_status")
    private Integer approvalStatus;

    /**
     * 自动生成项目code（规则：IT_XM000001，顺序递增）
     */
    @Column(name = "new_code")
    private String newCode;

    /**
     * 项目复盘时间
     */
    @Column(name = "replay_date")
    private Date replayDate;

    /**
     * 全面推广时间
     */
    @Column(name = "all_extension_date")
    private Date allExtensionDate;

    /**
     * 内容摘要
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 立项描述
     */
    @Column(name = "commit_descp")
    private String commitDescp;

    @Column(name = "business_center_list")
    private String businessCenterList;

    @Column(name = "business_center_list_name")
    private String businessCenterListName;

    @Column(name = "business_functions_list")
    private String businessFunctionsList;

    @Column(name = "business_functions_list_name")
    private String businessFunctionsListName;

    @Column(name = "business_president_list")
    private String businessPresidentList;

    @Column(name = "business_president_list_name")
    private String businessPresidentListName;

    @Column(name = "t_approval")
    private String tApproval;

    @Column(name = "report_poor")
    private String reportPoor;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取立项描述
     *
     * @return commitDescp - 立项描述
     */
    public String getCommitDescp() {
        return commitDescp;
    }

    /**
     * 设置立项描述
     *
     * @param commitDescp 立项描述
     */
    public void setCommitDescp(String commitDescp) {
        this.commitDescp = commitDescp == null ? null : commitDescp.trim();
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
     * 获取操作类型：100=添加项目，110=立项，120=Demo评审，130=招投标申请，140=中标申请，150=产品评审，160=开发评审，170=测试评审，180=上线计划，190=灰度发布，193=全面推广，195=项目复盘，200=延期上线，210=需求变更，900=完成，999=终止
     *
     * @return operation_type - 操作类型：100=添加项目，110=立项，120=Demo评审，130=招投标申请，140=中标申请，150=产品评审，160=开发评审，170=测试评审，180=上线计划，190=灰度发布，193=全面推广，195=项目复盘，200=延期上线，210=需求变更，900=完成，999=终止
     */
    public Integer getOperationType() {
        return operationType;
    }

    /**
     * 设置操作类型：100=添加项目，110=立项，120=Demo评审，130=招投标申请，140=中标申请，150=产品评审，160=开发评审，170=测试评审，180=上线计划，190=灰度发布，193=全面推广，195=项目复盘，200=延期上线，210=需求变更，900=完成，999=终止
     *
     * @param operationType 操作类型：100=添加项目，110=立项，120=Demo评审，130=招投标申请，140=中标申请，150=产品评审，160=开发评审，170=测试评审，180=上线计划，190=灰度发布，193=全面推广，195=项目复盘，200=延期上线，210=需求变更，900=完成，999=终止
     */
    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
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
     * 获取归属产品code
     *
     * @return product_code - 归属产品code
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置归属产品code
     *
     * @param productCode 归属产品code
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
     * 获取项目状态：100=未立项，110=立项，120=Demo评审，130=招投标申请，140=中标申请，150=产品评审，160=开发评审，170=测试评审，180=上线计划，190=灰度发布，193=全面推广，195=项目复盘，200=延期上线，210=需求变更，900=完成，999=终止
     *
     * @return program_status - 项目状态：100=未立项，110=立项，120=Demo评审，130=招投标申请，140=中标申请，150=产品评审，160=开发评审，170=测试评审，180=上线计划，190=灰度发布，193=全面推广，195=项目复盘，200=延期上线，210=需求变更，900=完成，999=终止
     */
    public Integer getProgramStatus() {
        return programStatus;
    }

    /**
     * 设置项目状态：100=未立项，110=立项，120=Demo评审，130=招投标申请，140=中标申请，150=产品评审，160=开发评审，170=测试评审，180=上线计划，190=灰度发布，193=全面推广，195=项目复盘，200=延期上线，210=需求变更，900=完成，999=终止
     *
     * @param programStatus 项目状态：100=未立项，110=立项，120=Demo评审，130=招投标申请，140=中标申请，150=产品评审，160=开发评审，170=测试评审，180=上线计划，190=灰度发布，193=全面推广，195=项目复盘，200=延期上线，210=需求变更，900=完成，999=终止
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
     * 获取灰度发布日期
     *
     * @return gray_release_date - 灰度发布日期
     */
    public Date getGrayReleaseDate() {
        return grayReleaseDate;
    }

    /**
     * 设置灰度发布日期
     *
     * @param grayReleaseDate 灰度发布日期
     */
    public void setGrayReleaseDate(Date grayReleaseDate) {
        this.grayReleaseDate = grayReleaseDate;
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
     * 获取自动生成项目code（规则：IT_XM000001，顺序递增）
     *
     * @return new_code - 自动生成项目code（规则：IT_XM000001，顺序递增）
     */
    public String getNewCode() {
        return newCode;
    }

    /**
     * 设置自动生成项目code（规则：IT_XM000001，顺序递增）
     *
     * @param newCode 自动生成项目code（规则：IT_XM000001，顺序递增）
     */
    public void setNewCode(String newCode) {
        this.newCode = newCode == null ? null : newCode.trim();
    }

    /**
     * 获取项目复盘时间
     *
     * @return replay_date - 项目复盘时间
     */
    public Date getReplayDate() {
        return replayDate;
    }

    /**
     * 设置项目复盘时间
     *
     * @param replayDate 项目复盘时间
     */
    public void setReplayDate(Date replayDate) {
        this.replayDate = replayDate;
    }

    /**
     * 获取全面推广时间
     *
     * @return all_extension_date - 全面推广时间
     */
    public Date getAllExtensionDate() {
        return allExtensionDate;
    }

    /**
     * 设置全面推广时间
     *
     * @param allExtensionDate 全面推广时间
     */
    public void setAllExtensionDate(Date allExtensionDate) {
        this.allExtensionDate = allExtensionDate;
    }

    public String getBusinessCenterList() {
        return businessCenterList;
    }

    public void setBusinessCenterList(String businessCenterList) {
        this.businessCenterList = businessCenterList;
    }

    public String getBusinessCenterListName() {
        return businessCenterListName;
    }

    public void setBusinessCenterListName(String businessCenterListName) {
        this.businessCenterListName = businessCenterListName;
    }

    public String getBusinessFunctionsList() {
        return businessFunctionsList;
    }

    public void setBusinessFunctionsList(String businessFunctionsList) {
        this.businessFunctionsList = businessFunctionsList;
    }

    public String getBusinessFunctionsListName() {
        return businessFunctionsListName;
    }

    public void setBusinessFunctionsListName(String businessFunctionsListName) {
        this.businessFunctionsListName = businessFunctionsListName;
    }

    public String getBusinessPresidentList() {
        return businessPresidentList;
    }

    public void setBusinessPresidentList(String businessPresidentList) {
        this.businessPresidentList = businessPresidentList;
    }

    public String getBusinessPresidentListName() {
        return businessPresidentListName;
    }

    public void setBusinessPresidentListName(String businessPresidentListName) {
        this.businessPresidentListName = businessPresidentListName;
    }

    public String gettApproval() {
        return tApproval;
    }

    public void settApproval(String tApproval) {
        this.tApproval = tApproval;
    }

    public String getReportPoor() {
        return reportPoor;
    }

    public void setReportPoor(String reportPoor) {
        this.reportPoor = reportPoor;
    }
}