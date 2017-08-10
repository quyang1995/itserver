package com.longfor.itserver.entity.ps;

import com.longfor.itserver.entity.Program;

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
    private List<PsProduct> psProductList;

    public List<PsProduct> getPsProductList() {
        return psProductList;
    }

    public void setPsProductList(List<PsProduct> psProductList) {
        this.psProductList = psProductList;
    }
}
