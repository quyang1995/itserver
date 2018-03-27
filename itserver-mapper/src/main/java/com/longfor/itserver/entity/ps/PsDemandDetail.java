package com.longfor.itserver.entity.ps;


import com.longfor.itserver.entity.*;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;


/**
 * @version 1.0
 * @Auther wax
 * @Description
 * @Created 2017/8/9
 */
public class PsDemandDetail extends Demand implements Serializable {

    @Transient
    private List<Product> productList;
    @Transient
    private List<Program> programList;
    //归属产品/项目 名称
    @Transient
    private String relationName;
    @Transient
    private List<DemandFile> demandFileList;
    //归属产品/项目 产品经理
    @Transient
    private List<ProgramEmployee> productManagerList;

    public List<ProgramEmployee> getProductManagerList() {
        return productManagerList;
    }

    public void setProductManagerList(List<ProgramEmployee> productManagerList) {
        this.productManagerList = productManagerList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Program> getProgramList() {
        return programList;
    }

    public void setProgramList(List<Program> programList) {
        this.programList = programList;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public List<DemandFile> getDemandFileList() {
        return demandFileList;
    }

    public void setDemandFileList(List<DemandFile> demandFileList) {
        this.demandFileList = demandFileList;
    }
}
