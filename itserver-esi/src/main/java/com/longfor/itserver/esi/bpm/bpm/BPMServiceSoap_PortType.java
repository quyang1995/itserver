/**
 * BPMServiceSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.longfor.itserver.esi.bpm.bpm;


import com.longfor.itserver.esi.bpm.bpm.holders.ArrayOfInstanceStageModelHolder;

import javax.xml.rpc.holders.StringHolder;

public interface BPMServiceSoap_PortType extends java.rmi.Remote {

    /**
     * Test
     */
    public Object test(String instanceid, String userCode) throws java.rmi.RemoteException;

    /**
     * StartWorkflow
     */
    public String startWorkflow(String workflowCode, String userCode, boolean finishStart, String jsonDataItems, String jsonAppendPerson) throws java.rmi.RemoteException;

    /**
     * ActiveInstance
     */
    public void activeInstance(String userCode, String instanceId, javax.xml.rpc.holders.BooleanHolder activeInstanceResult, javax.xml.rpc.holders.StringHolder errorMsg) throws java.rmi.RemoteException;

    /**
     * FinishInstance
     */
    public void finishInstance(String userCode, String instanceId, String workItemId, javax.xml.rpc.holders.BooleanHolder finishInstanceResult, javax.xml.rpc.holders.StringHolder errorMsg) throws java.rmi.RemoteException;

    /**
     * ActiveToken
     */
    public void activeToken(String userCode, String instanceId, String activityCode, String[] participants, javax.xml.rpc.holders.BooleanHolder activeTokenResult, javax.xml.rpc.holders.StringHolder errorMsg) throws java.rmi.RemoteException;

    /**
     * CancelToken
     */
    public void cancelToken(String userCode, String instanceId, String activityCode, javax.xml.rpc.holders.BooleanHolder cancelTokenResult, javax.xml.rpc.holders.StringHolder errorMsg) throws java.rmi.RemoteException;

    /**
     * GetInstanceInfo
     */
    public String getInstanceInfo(String instanceId) throws java.rmi.RemoteException;

    /**
     * GetWorkItemInfo
     */
    public String getWorkItemInfo(String workItemId) throws java.rmi.RemoteException;

    /**
     * GetInstanceSteps
     */
    public String getInstanceSteps(String instanceid) throws java.rmi.RemoteException;

    /**
     * FinishInstanceInStart
     */
    public boolean finishInstanceInStart(String userCode, String instanceId, String workItemId) throws java.rmi.RemoteException;

    /**
     * CancelInstance
     */
    public boolean cancelInstance(String userCode, String instanceId, String workItemId, int type) throws java.rmi.RemoteException;

    /**
     * GetInstanceActivityCode
     */
    public String getInstanceActivityCode(String instanceId) throws java.rmi.RemoteException;

    /**
     * DeleteInstance
     */
    public void deleteInstance(String instanceId, javax.xml.rpc.holders.BooleanHolder deleteInstanceResult, javax.xml.rpc.holders.StringHolder errorMsg) throws java.rmi.RemoteException;

    /**
     * GetUnFinisher
     */
    public void getUnFinisher(String instanceId, com.longfor.itserver.esi.bpm.bpm.holders.ArrayOfNextPersonHolder getUnFinisherResult, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException;

    /**
     * AddApproval
     */
    public void addApproval(String instanceId, String userCode, javax.xml.rpc.holders.BooleanHolder addApprovalResult, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException;

    /**
     * GetInstanceStage
     */
    public InstanceStageModel[] getInstanceStage(String instanceId, ArrayOfInstanceStageModelHolder getInstanceStageResult, StringHolder errorMessage) throws java.rmi.RemoteException;

    /**
     * GetWorkItemByWorkflow
     */
    public String getWorkItemByWorkflow(String userCode, int pageSize, int pageIndex, String workflowCode, int workitemState, String isDraft) throws java.rmi.RemoteException;

    /**
     * GetUserUnfinishedWorkItemByWorkflow
     */
    public String getUserUnfinishedWorkItemByWorkflow(String userCode, int pageSize, int pageIndex, String workflowCode, String workflowName) throws java.rmi.RemoteException;

    /**
     * GetAllUnfinishedWorkItemByWorkflow
     */
    public String getAllUnfinishedWorkItemByWorkflow(int pageSize, int pageIndex, String workflowCode, String workflowName) throws java.rmi.RemoteException;

    /**
     * GetUserFinishedWorkItemByWorkflow
     */
    public String getUserFinishedWorkItemByWorkflow(String userCode, int pageSize, int pageIndex, String workflowCode, String workflowName) throws java.rmi.RemoteException;

    /**
     * GetAllFinishedWorkItemByWorkflow
     */
    public String getAllFinishedWorkItemByWorkflow(int pageSize, int pageIndex, String workFlowCode, String workflowName) throws java.rmi.RemoteException;

    /**
     * GetFinishedWorkItemByInstanceId
     */
    public String getFinishedWorkItemByInstanceId(String instanceId, int pageSize, int pageIndex, String workflowCode, String workflowName) throws java.rmi.RemoteException;

    /**
     * GetUserFinishedWorkItemCount
     */
    public int getUserFinishedWorkItemCount(String userCode) throws java.rmi.RemoteException;

    /**
     * GetUserUnfinishedWorkItemCount
     */
    public int getUserUnfinishedWorkItemCount(String userCode) throws java.rmi.RemoteException;

    /**
     * SubmitWorkItem
     */
    public String submitWorkItem(String userCode, String workItemId, String approval, String commentText, boolean isValidate) throws java.rmi.RemoteException;

    /**
     * SubmitWorkToRetrunActivity
     */
    public String submitWorkToRetrunActivity(String userCode, String workItemId, String approval, String commentText) throws java.rmi.RemoteException;

    /**
     * RetrieveWorkItem
     */
    public void retrieveWorkItem(String userCode, String workitemId, javax.xml.rpc.holders.BooleanHolder retrieveWorkItemResult, javax.xml.rpc.holders.StringHolder errorMsg) throws java.rmi.RemoteException;

    /**
     * ReturnWorkItem
     */
    public String returnWorkItem(String userCode, String workItemId, String commentText) throws java.rmi.RemoteException;

    /**
     * AdjustWorkflow
     */
    public boolean adjustWorkflow(String workItemId, String userCode, String commentText) throws java.rmi.RemoteException;

    /**
     * AssistWorkflow
     */
    public boolean assistWorkflow(String workItemId, String userCode, String commentText) throws java.rmi.RemoteException;

    /**
     * CirculateWorkflow
     */
    public boolean circulateWorkflow(String workItemId, String userCode, String commentText) throws java.rmi.RemoteException;

    /**
     * ReturnWorkflowToStart
     */
    public String returnWorkflowToStart(String workItemId, String userCode, String commentText) throws java.rmi.RemoteException;

    /**
     * ReturnWorkflowToActivity
     */
    public void returnWorkflowToActivity(String workItemId, String commentText, String activity) throws java.rmi.RemoteException;

    /**
     * ReadWorkflow
     */
    public boolean readWorkflow(String userCode, String workItemId) throws java.rmi.RemoteException;

    /**
     * GetWorkItemID
     */
    public String getWorkItemID(String instanceId, String userCode) throws java.rmi.RemoteException;

    /**
     * AddConsult
     */
    public void addConsult(String workItemId, String itemComment, javax.xml.rpc.holders.BooleanHolder addConsultResult, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException;

    /**
     * SetItemValue
     */
    public void setItemValue(String userCode, String instanceId, String keyName, Object keyValue, javax.xml.rpc.holders.BooleanHolder setItemValueResult, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException;

    /**
     * SetItemValuesWithDataItemList
     */
    public void setItemValuesWithDataItemList(String userCode, String bizObjectSchemaCode, String bizObjectId, DataItemParam[] keyValues, javax.xml.rpc.holders.BooleanHolder setItemValuesWithDataItemListResult, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException;

    /**
     * SetItemValuesByInstanceId
     */
    public boolean setItemValuesByInstanceId(String userCode, String bizObjectSchemaCode, String instanceId, String jsonDataItem) throws java.rmi.RemoteException;

    /**
     * SetItemValues
     */
    public boolean setItemValues(String userCode, String bizObjectSchemaCode, String bizObjectId, String jsonDataItem) throws java.rmi.RemoteException;

    /**
     * AddUser
     */
    public String addUser(String userCode, String userName) throws java.rmi.RemoteException;
}
