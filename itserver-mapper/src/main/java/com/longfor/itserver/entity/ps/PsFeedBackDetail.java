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
public class PsFeedBackDetail extends FeedBack implements Serializable {

    @Transient
    private List<DemandComment> demandCommentList;
    @Transient
    private List<BugComment> bugCommentList;
    @Transient
    private List<BugFile> bugFileList;
    @Transient
    private List<DemandFile> demandFileList;

    public List<DemandComment> getDemandCommentList() {
        return demandCommentList;
    }

    public void setDemandCommentList(List<DemandComment> demandCommentList) {
        this.demandCommentList = demandCommentList;
    }

    public List<BugComment> getBugCommentList() {
        return bugCommentList;
    }

    public void setBugCommentList(List<BugComment> bugCommentList) {
        this.bugCommentList = bugCommentList;
    }

    public List<BugFile> getBugFileList() {
        return bugFileList;
    }

    public void setBugFileList(List<BugFile> bugFileList) {
        this.bugFileList = bugFileList;
    }

    public List<DemandFile> getDemandFileList() {
        return demandFileList;
    }

    public void setDemandFileList(List<DemandFile> demandFileList) {
        this.demandFileList = demandFileList;
    }
}
