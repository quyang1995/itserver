package com.longfor.itserver.entity.ps;

import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ProductEmployee;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @version 1.0
 * @Auther Long.Li
 * @Description
 * @Created 2017/8/9
 */
public class PsProductCount extends Product  implements Serializable {
    @Transient
    private String programCount;
    @Transient
    private String newestProgramName;
    @Transient
    private List<ProductEmployee> personLiableList;

    public List<ProductEmployee> getPersonLiableList() {
        return personLiableList;
    }

    public void setPersonLiableList(List<ProductEmployee> personLiableList) {
        this.personLiableList = personLiableList;
    }

    public String getNewestProgramName() {
        return newestProgramName;
    }

    public void setNewestProgramName(String newestProgramName) {
        this.newestProgramName = newestProgramName;
    }

    public String getProgramCount() {
        return programCount;
    }

    public void setProgramCount(String programCount) {
        this.programCount = programCount;
    }
}
