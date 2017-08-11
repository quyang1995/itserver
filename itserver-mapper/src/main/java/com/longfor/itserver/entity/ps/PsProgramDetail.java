package com.longfor.itserver.entity.ps;

import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramEmployee;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;


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
}
