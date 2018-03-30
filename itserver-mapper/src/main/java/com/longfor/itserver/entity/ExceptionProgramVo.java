package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomFullDateSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ExceptionProgramVo implements Serializable {
    private static final long serialVersionUID = -653566611207069924L;
    private String programId;
    private String programName;
    private String productId;
    private String programStatus;
    @JsonSerialize(using = CustomFullDateSerializer.class)
    private Date createTime;
    private String warningDays;
    private String warning;

    private List<ProgramEmployee> productManagerList;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProgramStatus() {
        return programStatus;
    }

    public void setProgramStatus(String programStatus) {
        this.programStatus = programStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<ProgramEmployee> getProductManagerList() {
        return productManagerList;
    }

    public void setProductManagerList(List<ProgramEmployee> productManagerList) {
        this.productManagerList = productManagerList;
    }

    public String getWarningDays() {
        return warningDays;
    }

    public void setWarningDays(String warningDays) {
        this.warningDays = warningDays;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}