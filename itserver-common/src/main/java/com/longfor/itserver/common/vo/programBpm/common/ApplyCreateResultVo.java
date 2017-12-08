package com.longfor.itserver.common.vo.programBpm.common;


public class ApplyCreateResultVo {
    private boolean  Success;
    private String  InstanceID;
    private String  Message;
    private String  WorkItemID;
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