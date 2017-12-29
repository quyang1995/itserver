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
    private List<ProgramApprovalSnapshot> costRecordList;
    @Transient
    private List<ProgramApprovalSnapshot> milepostList;

    @Transient
    private List<Map> grayLevelMap;

    @Transient
    private List<Map> costRecordMap;

    @Transient
    private List<Map> milepostMap;

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

    public void setGrayLevelMap(List<Map> grayLevelMap) {
        this.grayLevelMap = grayLevelMap;
    }

    public void setCostRecordMap(List<Map> costRecordMap) {
        this.costRecordMap = costRecordMap;
    }

    public void setMilepostMap(List<Map> milepostMap) {
        this.milepostMap = milepostMap;
    }

    public List<Map> getGrayLevelMap() {
        return grayLevelMap;
    }

    public List<Map> getCostRecordMap() {
        return costRecordMap;
    }

    public List<Map> getMilepostMap() {
        return milepostMap;
    }

    public List<ProgramApprovalSnapshot> getMilepostList() {
        return milepostList;
    }

    public void setMilepostList(List<ProgramApprovalSnapshot> milepostList) {
        this.milepostList = milepostList;
    }

    public List<ProgramApprovalSnapshot> getCostRecordList() {
        return costRecordList;
    }

    public void setCostRecordList(List<ProgramApprovalSnapshot> costRecordList) {
        this.costRecordList = costRecordList;
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
}
