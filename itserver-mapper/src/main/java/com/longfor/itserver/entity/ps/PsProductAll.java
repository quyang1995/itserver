package com.longfor.itserver.entity.ps;

import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramEmployee;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;


/**
 * @version 1.0
 * @Auther Long.Li
 * @Description
 * @Created 2017/8/7
 */
public class PsProductAll extends Product implements Serializable {
    @Transient
    private List<Program> programs;
    @Transient
    private List<ProductEmployee> personLiableList;
    @Transient
    private List<ProductEmployee> programManagerList;
    @Transient
    private List<ProductEmployee> productManagerList;
    @Transient
    private List<ProductEmployee> developerList;
    @Transient
    private List<ProductEmployee> uedList;
    @Transient
    private List<ProductEmployee> testingList;
    @Transient
    private List<ProductEmployee> businessList;


    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public List<ProductEmployee> getPersonLiableList() {
        return personLiableList;
    }

    public void setPersonLiableList(List<ProductEmployee> personLiableList) {
        this.personLiableList = personLiableList;
    }

    public List<ProductEmployee> getProgramManagerList() {
        return programManagerList;
    }

    public void setProgramManagerList(List<ProductEmployee> programManagerList) {
        this.programManagerList = programManagerList;
    }

    public List<ProductEmployee> getProductManagerList() {
        return productManagerList;
    }

    public void setProductManagerList(List<ProductEmployee> productManagerList) {
        this.productManagerList = productManagerList;
    }

    public List<ProductEmployee> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<ProductEmployee> developerList) {
        this.developerList = developerList;
    }

    public List<ProductEmployee> getUedList() {
        return uedList;
    }

    public void setUedList(List<ProductEmployee> uedList) {
        this.uedList = uedList;
    }

    public List<ProductEmployee> getTestingList() {
        return testingList;
    }

    public void setTestingList(List<ProductEmployee> testingList) {
        this.testingList = testingList;
    }

    public List<ProductEmployee> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<ProductEmployee> businessList) {
        this.businessList = businessList;
    }
}
