package com.longfor.itserver.esi.bpm;


import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.esi.bpm.bpm.BPMServiceSoap_BindingStub;
import com.longfor.itserver.esi.bpm.bpm.ComLongforEsbDCBPMBpmServiceLocator;
import com.longfor.itserver.esi.bpm.bpm.InstanceStageModel;
import com.longfor.itserver.esi.bpm.bpm.holders.ArrayOfInstanceStageModelHolder;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.holders.BooleanHolder;
import javax.xml.rpc.holders.StringHolder;
import java.rmi.RemoteException;

/**
 * Created by Cz_star on 2017/7/6.
 */
public class BpmUtils {

    private static String systemCode;

    public BpmUtils(){
        super();
    }
    public static ComLongforEsbDCBPMBpmServiceLocator bpmServiceLocator;

    static {
        bpmServiceLocator = new ComLongforEsbDCBPMBpmServiceLocator();
        systemCode = JoddHelper.getInstance().getJoddProps().getValue("bpm.systemCode");
    }

    /**
     *  bpm接口添加soa调用所需的ClientId和OperationCode
     * @param methodName soa接口方法名
     * @return
     */
    private static BPMServiceSoap_BindingStub getBpmStub(String methodName){

        BPMServiceSoap_BindingStub bpmStub = null;
        try {
            bpmStub = (BPMServiceSoap_BindingStub) bpmServiceLocator.getBPMServiceSoap();
            bpmStub.setHeader("","ClientId","com.longfor.esb.DC."+systemCode);
            bpmStub.setHeader("","OperationCode","com.longfor.esb.DC.BPM.bpmService."+methodName);
        }catch (ServiceException e){

        }
        return bpmStub;
    }

    /**
     * 创建流程
     * @param instanceCode 流程实例编码
     * @param userCode 发起用户 oa账号
     * @param finishStart  false
     * @param jsonDataItems 参数类型需要为json格式
     * @param jsonAppendPerson 流程加载人，传null，自动计算审批人
     * @return
     */
    public static String startWorkFlow(String instanceCode, String userCode,
                                       boolean finishStart, String jsonDataItems, String jsonAppendPerson){

        BPMServiceSoap_BindingStub bpmServiceStub = getBpmStub("StartWorkflow");
        String resultMessage = null;
        try {
            resultMessage = bpmServiceStub.startWorkflow(
                    instanceCode, userCode, finishStart, jsonDataItems, jsonAppendPerson);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return resultMessage;
    }

    /**
     * 提交流程;
     * @param userCode 提交流程人，当前用户
     * @param workItemId 流程实例中当前工作项ID
     * @param approval 审批人，需BPM计算传null
     * @param commentText
     * @return
     */
    public static String submitWorkItem(String userCode, String workItemId, String approval, String commentText){

        BPMServiceSoap_BindingStub bpmServiceStub = getBpmStub("SubmitWorkItem");
        String resultMessage = null;

        try {
            resultMessage = bpmServiceStub.submitWorkItem(userCode, workItemId, approval, commentText, false);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return resultMessage;
    }
    public static void main(String[] args) {
        String a = BpmUtils.submitWorkItem("sunyanhui",
                "c078682b-4315-4c45-8d6d-5279430bb2ad",null,null);
        System.out.println(a);
    }
    /**
     * 获取流程实例信息
     * @return
     * @param instanceId 流程实例ID
     */
    public static String getInstanceInfo(String instanceId){
        BPMServiceSoap_BindingStub bpmServiceStub = getBpmStub("GetInstanceInfo");
        String resultMessage = null;
        try {
            resultMessage = bpmServiceStub.getInstanceInfo(instanceId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return resultMessage;
    }

    /**
     * 获取流程实例审批步骤
     * @param instanceId 流程实例ID
     * @return
     */
    public static String getInstanceSteps(String instanceId){
        BPMServiceSoap_BindingStub bpmServiceStub = getBpmStub("GetInstanceSteps");
        String resultMessage = null;
        try {
            resultMessage = bpmServiceStub.getInstanceSteps(instanceId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return resultMessage;
    }

    /**
     * 撤销流程
     * @param userCode 用户code
     * @param instanceId 流程实例ID
     * @param workItemId 工作项ID
     * @param type
     * @return true成功 false失败
     */
    public static boolean cancelInstance(String userCode, String instanceId, String workItemId, int type){
        BPMServiceSoap_BindingStub bpmServiceStub = getBpmStub("CancelInstance");
        boolean resultMessage = false;
        try {
            resultMessage = bpmServiceStub.cancelInstance(userCode, instanceId, workItemId, type);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return resultMessage;
    }

    /**
     * 获取流程实例某审批人的工作项ID
     * @param instanceId 流程实例ID
     * @param userCode 流程实例中的审批人
     * @return
     */
    public static String getWorkItemID(String instanceId, String userCode){
        BPMServiceSoap_BindingStub bpmServiceStub = getBpmStub("GetWorkItemID");
        String resultMessage = null;
        try {
            resultMessage = bpmServiceStub.getWorkItemID(instanceId, userCode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return resultMessage;
    }

    /**
     * 获取工作项信息
     * @param workItemId 工作项ID
     * @return
     */
    public static String getWorkItemInfo(String workItemId){
        BPMServiceSoap_BindingStub bpmServiceStub = getBpmStub("GetWorkItemInfo");
        String resultMessage = null;
        try {
            resultMessage = bpmServiceStub.getWorkItemInfo(workItemId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return resultMessage;
    }

    public static InstanceStageModel[] getInstanceStage(String instanceId, ArrayOfInstanceStageModelHolder holder, StringHolder stringHolder){
        BPMServiceSoap_BindingStub bpmServiceStub = getBpmStub("GetInstanceStage");
        InstanceStageModel[] instanceStageModels = null;
        try {
            instanceStageModels = bpmServiceStub.getInstanceStage(instanceId, holder, stringHolder);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return instanceStageModels;
    }

    /**
     * 驳回流程至发起人
     * @param userCode 当前审批人的OA账号
     * @param workItemId 当前审批人对应流程的工作项ID
     * @param commentText 驳回意见
     * @return
     */
    public static String returnWorkItem(String userCode, String workItemId, String commentText){
        BPMServiceSoap_BindingStub bpmServiceStub = getBpmStub("ReturnWorkItem");
        String resultMessage = null;
        try {
            resultMessage = bpmServiceStub.returnWorkItem(userCode, workItemId, commentText);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return resultMessage;
    }

    public static String getInstanceActivityCode(String instanceId){
        BPMServiceSoap_BindingStub bpmServiceStub = getBpmStub("GetInstanceActivityCode");
        String stageName = "";
        try {
            stageName = bpmServiceStub.getInstanceActivityCode(instanceId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return stageName;
    }

    public static String setItemValue(String userCode, String instanceId, String keyName, String value, BooleanHolder setItemValueResult, StringHolder errorMessage){
        BPMServiceSoap_BindingStub bpmServiceStub = getBpmStub("SetItemValue");
        String retMsg = "";
        try {
            bpmServiceStub.setItemValue( userCode, instanceId, keyName, value, setItemValueResult, errorMessage );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return retMsg;
    }

    /***
     * 驳回  syh
     * @param userCode
     * @param workItemId
     * @param commentText
     * @return
     */
    public static String returnWorkflowToStart(String userCode, String workItemId, String commentText){
        BPMServiceSoap_BindingStub bpmServiceStub = getBpmStub("ReturnWorkflowToStart");
        String resultMessage = null;
        try {
            resultMessage = bpmServiceStub.returnWorkflowToStart(userCode, workItemId, commentText);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return resultMessage;
    }

}
