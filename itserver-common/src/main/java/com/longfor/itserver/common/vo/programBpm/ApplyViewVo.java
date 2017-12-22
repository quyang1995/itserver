package com.longfor.itserver.common.vo.programBpm;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.longfor.itserver.common.vo.programBpm.common.FileVo;
import net.mayee.commons.CustomDateSerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/***
 * 查看立项申请VO
 */
public class ApplyViewVo {
    private String programName;
    private List<ProgramManagerVo> programManagerList;
    private String remark;
    private List<FileVo> fileList;
    private String proveStatus;
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
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date proveViewUrl;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProveStatus() {
        return proveStatus;
    }

    public void setProveStatus(String proveStatus) {
        this.proveStatus = proveStatus;
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

    public Date getProveViewUrl() {
        return proveViewUrl;
    }

    public void setProveViewUrl(Date proveViewUrl) {
        this.proveViewUrl = proveViewUrl;
    }

    public java.util.List<ProgramManagerVo> getProgramManagerList() {
        return programManagerList;
    }

    public void setProgramManagerList(java.util.List<ProgramManagerVo> programManagerList) {
        this.programManagerList = programManagerList;
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
}