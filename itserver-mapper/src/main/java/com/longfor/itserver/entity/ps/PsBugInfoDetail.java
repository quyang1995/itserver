package com.longfor.itserver.entity.ps;

import com.longfor.itserver.entity.BugFile;
import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.entity.Product;
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
public class PsBugInfoDetail extends BugInfo implements Serializable {

    @Transient
    private List<Product> productList;
    @Transient
    private List<Program> programList;
    @Transient
    private String relationName;
    @Transient
    private List<BugFile> bugFileList;

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

    public List<BugFile> getBugFileList() {
        return bugFileList;
    }

    public void setBugFileList(List<BugFile> bugFileList) {
        this.bugFileList = bugFileList;
    }
}
