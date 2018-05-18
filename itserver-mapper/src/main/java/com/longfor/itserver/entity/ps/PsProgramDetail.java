package com.longfor.itserver.entity.ps;

import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramApprovalSnapshot;
import com.longfor.itserver.entity.ProgramEmployee;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * @version 1.0
 * @Auther wax
 * @Description
 * @Created 2017/8/9
 */
public class PsProgramDetail extends Program implements Serializable {

    @Transient
    private Integer changeStatus;

    @Transient
    private Integer changeApprovalStatus;

    @Transient
    private String warning;

    @Transient
    private String warningRemark;

    @Transient
    private String currNodeDate;

    @Transient
    private String personLiable;

    /**
     * 当前项目审批中的bpmCode
     */
    @Transient
    private String bpmCode;

    @Transient
    private List<Product> productList;

    @Transient
    private List<ProgramEmployee> personLiableList;

    @Transient
    private List<ProgramEmployee> programManagerList;

    @Transient
    private List<ProgramEmployee> productManagerList;

    @Transient
    private List<ProgramEmployee> developerList;

    @Transient
    private List<ProgramEmployee> uedList;
    @Transient
    private List<ProgramEmployee> testingList;
    @Transient
    private List<ProgramEmployee> operationList;
    @Transient
    private List<ProgramEmployee> operateList;
    @Transient
    private List<ProgramEmployee> businessList;
    @Transient
    private List<ProgramApprovalSnapshot> grayLevelList;
    @Transient
    private List<ProgramApprovalSnapshot> milepostList;

    @Transient
    private List<ProgramApprovalSnapshot> costRecordList;

    public Integer getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(Integer changeStatus) {
        this.changeStatus = changeStatus;
    }

    public Integer getChangeApprovalStatus() {
        return changeApprovalStatus;
    }

    public void setChangeApprovalStatus(Integer changeApprovalStatus) {
        this.changeApprovalStatus = changeApprovalStatus;
    }

    public void setOperationList(List<ProgramEmployee> operationList) {
        this.operationList = operationList;
    }

    public void setOperateList(List<ProgramEmployee> operateList) {
        this.operateList = operateList;
    }

    public List<ProgramEmployee> getOperationList() {
        return operationList;
    }

    public List<ProgramEmployee> getOperateList() {
        return operateList;
    }

    public void setCostRecordList(List<ProgramApprovalSnapshot> costRecordList) {
        this.costRecordList = costRecordList;
    }

    public List<ProgramApprovalSnapshot> getCostRecordList() {
        return costRecordList;
    }

    public List<ProgramApprovalSnapshot> getMilepostList() {
        return milepostList;
    }

    public void setMilepostList(List<ProgramApprovalSnapshot> milepostList) {
        this.milepostList = milepostList;
    }

    public List<ProgramApprovalSnapshot> getGrayLevelList() {
        return grayLevelList;
    }

    public void setGrayLevelList(List<ProgramApprovalSnapshot> grayLevelList) {
        this.grayLevelList = grayLevelList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<ProgramEmployee> getPersonLiableList() {
        return personLiableList;
    }

    public void setPersonLiableList(List<ProgramEmployee> personLiableList) {
        this.personLiableList = personLiableList;
    }

    public List<ProgramEmployee> getProgramManagerList() {
        return programManagerList;
    }

    public void setProgramManagerList(List<ProgramEmployee> programManagerList) {
        this.programManagerList = programManagerList;
    }

    public List<ProgramEmployee> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<ProgramEmployee> developerList) {
        this.developerList = developerList;
    }

    public List<ProgramEmployee> getUedList() {
        return uedList;
    }

    public void setUedList(List<ProgramEmployee> uedList) {
        this.uedList = uedList;
    }

    public List<ProgramEmployee> getProductManagerList() {
        return productManagerList;
    }

    public void setProductManagerList(List<ProgramEmployee> productManagerList) {
        this.productManagerList = productManagerList;
    }

    public List<ProgramEmployee> getTestingList() {
        return testingList;
    }

    public void setTestingList(List<ProgramEmployee> testingList) {
        this.testingList = testingList;
    }

    public List<ProgramEmployee> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<ProgramEmployee> businessList) {
        this.businessList = businessList;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getWarningRemark() {
        return warningRemark;
    }

    public void setWarningRemark(String warningRemark) {
        this.warningRemark = warningRemark;
    }

    public String getCurrNodeDate() {
        return currNodeDate;
    }

    public void setCurrNodeDate(String currNodeDate) {
        this.currNodeDate = currNodeDate;
    }

    public String getPersonLiable() {
        return personLiable;
    }

    public void setPersonLiable(String personLiable) {
        this.personLiable = personLiable;
    }

    public String getBpmCode() {
        return bpmCode;
    }

    public void setBpmCode(String bpmCode) {
        this.bpmCode = bpmCode;
    }
}
