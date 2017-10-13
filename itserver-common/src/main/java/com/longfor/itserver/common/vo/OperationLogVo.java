package com.longfor.itserver.common.vo;

/**
 * Created by sunyanhui on 2017/10/13.
 */
public class OperationLogVo {
    private String modifiedAccountId;//邮箱
    private String name;//姓名
    private String companyName;//
    private String operateModule;//
    private String actionChangeInfo;//
    private String createTime;//

    public OperationLogVo(String companyName,String operateModule){
        this.companyName = companyName;
        this.operateModule = operateModule;
    }

    public String getModifiedAccountId() {
        return modifiedAccountId;
    }

    public void setModifiedAccountId(String modifiedAccountId) {
        this.modifiedAccountId = modifiedAccountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOperateModule() {
        return operateModule;
    }

    public void setOperateModule(String operateModule) {
        this.operateModule = operateModule;
    }

    public String getActionChangeInfo() {
        return actionChangeInfo;
    }

    public void setActionChangeInfo(String actionChangeInfo) {
        this.actionChangeInfo = actionChangeInfo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
