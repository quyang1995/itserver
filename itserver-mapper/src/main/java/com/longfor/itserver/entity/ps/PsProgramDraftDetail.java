package com.longfor.itserver.entity.ps;

import com.longfor.itserver.entity.*;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;


public class PsProgramDraftDetail extends ProgramDraft implements Serializable {

    @Transient
    private List<Product> productList;

    @Transient
    private List<ProgramEmployeeDraft> personLiableList;

    @Transient
    private List<ProgramEmployeeDraft> programManagerList;

    @Transient
    private List<ProgramEmployeeDraft> productManagerList;

    @Transient
    private List<ProgramEmployeeDraft> developerList;

    @Transient
    private List<ProgramEmployeeDraft> uedList;
    @Transient
    private List<ProgramEmployeeDraft> testingList;
    @Transient
    private List<ProgramEmployeeDraft> operationList;
    @Transient
    private List<ProgramEmployeeDraft> operateList;
    @Transient
    private List<ProgramEmployeeDraft> businessList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<ProgramEmployeeDraft> getPersonLiableList() {
        return personLiableList;
    }

    public void setPersonLiableList(List<ProgramEmployeeDraft> personLiableList) {
        this.personLiableList = personLiableList;
    }

    public List<ProgramEmployeeDraft> getProgramManagerList() {
        return programManagerList;
    }

    public void setProgramManagerList(List<ProgramEmployeeDraft> programManagerList) {
        this.programManagerList = programManagerList;
    }

    public List<ProgramEmployeeDraft> getProductManagerList() {
        return productManagerList;
    }

    public void setProductManagerList(List<ProgramEmployeeDraft> productManagerList) {
        this.productManagerList = productManagerList;
    }

    public List<ProgramEmployeeDraft> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<ProgramEmployeeDraft> developerList) {
        this.developerList = developerList;
    }

    public List<ProgramEmployeeDraft> getUedList() {
        return uedList;
    }

    public void setUedList(List<ProgramEmployeeDraft> uedList) {
        this.uedList = uedList;
    }

    public List<ProgramEmployeeDraft> getTestingList() {
        return testingList;
    }

    public void setTestingList(List<ProgramEmployeeDraft> testingList) {
        this.testingList = testingList;
    }

    public List<ProgramEmployeeDraft> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<ProgramEmployeeDraft> operationList) {
        this.operationList = operationList;
    }

    public List<ProgramEmployeeDraft> getOperateList() {
        return operateList;
    }

    public void setOperateList(List<ProgramEmployeeDraft> operateList) {
        this.operateList = operateList;
    }

    public List<ProgramEmployeeDraft> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<ProgramEmployeeDraft> businessList) {
        this.businessList = businessList;
    }
}
