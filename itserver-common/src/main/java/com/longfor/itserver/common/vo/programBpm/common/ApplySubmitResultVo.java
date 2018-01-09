package com.longfor.itserver.common.vo.programBpm.common;


import java.util.List;

public class ApplySubmitResultVo {
    private String  IsSuccess;//是否成功
    private String WorkItemUrl;
    private List<NextPersonVo> NextPersons;
    private String InstanceState;//判断是否最后一个审批节点，ALLEND表示最后一个
    private String ErrorMessage;


    public String getWorkItemUrl() {
        return WorkItemUrl;
    }

    public void setWorkItemUrl(String workItemUrl) {
        WorkItemUrl = workItemUrl;
    }

    public List<NextPersonVo> getNextPersons() {
        return NextPersons;
    }

    public void setNextPersons(List<NextPersonVo> nextPersons) {
        NextPersons = nextPersons;
    }

    public String getInstanceState() {
        return InstanceState;
    }

    public void setInstanceState(String instanceState) {
        InstanceState = instanceState;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        IsSuccess = isSuccess;
    }
}