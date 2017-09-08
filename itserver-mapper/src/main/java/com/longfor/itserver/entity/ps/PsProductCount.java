package com.longfor.itserver.entity.ps;

import com.longfor.itserver.entity.Product;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @version 1.0
 * @Auther Long.Li
 * @Description
 * @Created 2017/8/9
 */
public class PsProductCount extends Product  implements Serializable {
    @Transient
    private String programCount;

    public String getProgramCount() {
        return programCount;
    }

    public void setProgramCount(String programCount) {
        this.programCount = programCount;
    }
}
