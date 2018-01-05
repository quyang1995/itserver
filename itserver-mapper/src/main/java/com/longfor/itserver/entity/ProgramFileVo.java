package com.longfor.itserver.entity;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by admin on 2018/1/4.
 */
public class ProgramFileVo extends ProgramFile implements Serializable {

    @Transient
    private String programName;
    @Transient
    private Long productId;
    @Transient
    private String productName;
    @Transient
    private String modifiedAccountId;
    @Transient
    private String modifiedName;

    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }

    public void setModifiedAccountId(String modifiedAccountId) {
        this.modifiedAccountId = modifiedAccountId;
    }

    public String getModifiedAccountId() {
        return modifiedAccountId;
    }

    public String getModifiedName() {
        return modifiedName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }


    public String getProgramName() {
        return programName;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

}
