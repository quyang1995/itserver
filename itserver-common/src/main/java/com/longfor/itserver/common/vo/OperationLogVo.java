package com.longfor.itserver.common.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomFullDateSerializer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sunyanhui on 2017/10/13.
 */
public class OperationLogVo implements Comparable<OperationLogVo> {
    private long id;
    private String modifiedAccountId;//邮箱
    private String name;//姓名
    private String companyName;//
    private String operateModule;//
    private String actionChangeInfo;//
    @JsonSerialize(using = CustomFullDateSerializer.class)
    private Date createTime;//

    public OperationLogVo(String companyName,String operateModule){
        this.companyName = companyName;
        this.operateModule = operateModule;
    }
    @Override
    public int compareTo(OperationLogVo s) {
        long a = s.getId();
        long b = this.id;
        if(a > b)return 1;
        return -1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
