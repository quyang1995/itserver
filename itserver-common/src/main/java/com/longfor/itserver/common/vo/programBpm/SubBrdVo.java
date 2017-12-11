package com.longfor.itserver.common.vo.programBpm;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.longfor.itserver.common.vo.programBpm.common.CounterSigner;
import com.longfor.itserver.common.vo.programBpm.common.FileVo;
import net.mayee.commons.CustomDateSerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/***
 * 提交立项申请VO
 */
public class SubBrdVo{
    private String programId;
    private String remark;//内容摘要
    private List<FileVo> fileList;
    private int devType;
    private int analyzingConditions;
    private int devWorkload;
    private BigDecimal overallCost;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date commitDate;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date demoApprovalDate;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date biddingDate;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date winningBidDate;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date grayReleaseDate;
    private List<CounterSigner> counterSigners;
    private String modifiedAccountId;
    private String modifiedName;
    private int accountType;

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<FileVo> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileVo> fileList) {
        this.fileList = fileList;
    }

    public int getDevType() {
        return devType;
    }

    public void setDevType(int devType) {
        this.devType = devType;
    }

    public int getAnalyzingConditions() {
        return analyzingConditions;
    }

    public void setAnalyzingConditions(int analyzingConditions) {
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

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
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

    public Date getGrayReleaseDate() {
        return grayReleaseDate;
    }

    public void setGrayReleaseDate(Date grayReleaseDate) {
        this.grayReleaseDate = grayReleaseDate;
    }

    public List<CounterSigner> getCounterSigners() {
        return counterSigners;
    }

    public void setCounterSigners(List<CounterSigner> counterSigners) {
        this.counterSigners = counterSigners;
    }

    public String getModifiedAccountId() {
        return modifiedAccountId;
    }

    public void setModifiedAccountId(String modifiedAccountId) {
        this.modifiedAccountId = modifiedAccountId;
    }

    public String getModifiedName() {
        return modifiedName;
    }

    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}