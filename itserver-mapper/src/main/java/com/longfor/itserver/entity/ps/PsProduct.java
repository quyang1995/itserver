package com.longfor.itserver.entity.ps;

import com.longfor.itserver.entity.Product;

import javax.persistence.Transient;
import java.io.Serializable;


/**
 * @version 1.0
 * @Auther Long.Li
 * @Description
 * @Created 2017/8/7
 */
public class PsProduct extends Product implements Serializable {
    @Transient
    private String employeeName;
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {

        return employeeName;
    }
}
