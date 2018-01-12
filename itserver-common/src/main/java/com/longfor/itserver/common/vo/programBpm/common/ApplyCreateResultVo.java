package com.longfor.itserver.common.vo.programBpm.common;


public class ApplyCreateResultVo {
    private boolean  Success;//是否成功
    private String  InstanceID;//流程id
    private String  Message;
    private String  WorkItemID;//工作项id
    private String  WorkItemUrl;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getInstanceID() {
        return InstanceID;
    }

    public void setInstanceID(String instanceID) {
        InstanceID = instanceID;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getWorkItemID() {
        return WorkItemID;
    }

    public void setWorkItemID(String workItemID) {
        WorkItemID = workItemID;
    }

    public String getWorkItemUrl() {
        return WorkItemUrl;
    }

    public void setWorkItemUrl(String workItemUrl) {
        WorkItemUrl = workItemUrl;
    }
}