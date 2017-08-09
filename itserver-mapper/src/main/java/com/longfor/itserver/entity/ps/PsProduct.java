package com.longfor.itserver.entity.ps;

import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.entity.Program;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;


/**
 * @version 1.0
 * @Auther Long.Li
 * @Description
 * @Created 2017/8/7
 */
public class PsProduct extends Product implements Serializable {
    @Transient
    private String employeeName;
    @Transient
    private List<Program> programs;
    @Transient
    private List<ProductEmployee> productEmployees;

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {

        return employeeName;
    }

    public List<ProductEmployee> getProductEmployees() {
        return productEmployees;
    }

    public void setProductEmployees(List<ProductEmployee> productEmployees) {
        this.productEmployees = productEmployees;
    }
}
