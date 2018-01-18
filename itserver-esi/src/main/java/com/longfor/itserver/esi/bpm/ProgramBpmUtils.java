package com.longfor.itserver.esi.bpm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.vo.programBpm.common.ApplyCreateResultVo;
import com.longfor.itserver.common.vo.programBpm.common.ApplySubmitResultVo;

import java.util.Map;

/**
 * Created by sunyanhui on 2017/12/8.
 */
public class ProgramBpmUtils
{

    private static final String BRD_TEMP_CODE = "ITplus_ITxmlx";//IT项目立项（提交BRD）
    private static final String DEMO_TEMP_CODE = "ITplus_Demos";//提交Demo评审
    private static final String TENDER_TEMP_CODE = "ITplus_ITxmlx";
    private static final String WINBIDDING_TEMP_CODE = "ITplus_ITxmlx";
    private static final String CONTRACTSIGN_TEMP_CODE = "ITplus_ITxmlx";
    private static final String PRODUCTREVIEW_TEMP_CODE = "ITplus_Cpps";//产品评审
    private static final String DEVELOPREVIEW_TEMP_CODE = "ITplus_ITkfps";//IT开发评审
    private static final String TESTREVIEW_TEMP_CODE = "ITplus_Csps";//测试评审
    private static final String ONLINEPLAN_TEMP_CODE = "ITplus_Sxjh";//上线计划
    private static final String PARTINTRODUCE_TEMP_CODE = "ITplus_Hdfb";//灰度发布
    private static final String ALLSPREAD_TEMP_CODE = "ITplus_Qmtg";//全面推广
    private static final String PROGRAMREPLAY_TEMP_CODE = "ITplus_ITxmfp";//项目复盘
    private static final String DELAYONLINE_TEMP_CODE = "ITplus_Yqsx";//延期上线
    private static final String DEMANDCHANGEADVISE_TEMP_CODE = "ITplus_ITxy10wbg";//IT系统小于10万变更需求申请
    private static final String DEMANDCHANGEAPPROVE_TEMP_CODE = "ITplus_ITxqbgsq";//IT系统需求变更申请
    private static final String FinishProgram_TEMP_CODE = "ITplus_ITxmlx";
    private static final String TERMINATIONPROGRAM_TEMP_CODE= "ITplus_Zzxm";//终止项目


    /***
     * 审核通过
     * @param oaAccount  提交人OA账号
     * @param workItemId  流程项ID
     * @param suggestion   审批意见
     * @return
     */
    public static ApplySubmitResultVo approvePass(String oaAccount, String workItemId,String suggestion){
        String result = BpmUtils.submitWorkItem(oaAccount,workItemId,null,suggestion);
        ApplySubmitResultVo pplySubmitResultVo = JSONObject.parseObject(result, ApplySubmitResultVo.class);
        return pplySubmitResultVo;
    }

    /***
     * 审核驳回
     * @param oaAccount
     * @param workItemId
     * @param suggestion
     * @return
     */
    public static ApplySubmitResultVo approveNo(String oaAccount, String workItemId,String suggestion){
        String result = BpmUtils.returnWorkflowToStart(oaAccount,workItemId,suggestion);
        ApplySubmitResultVo pplySubmitResultVo = JSONObject.parseObject(result, ApplySubmitResultVo.class);
        return pplySubmitResultVo;
    }

    /***
     * 创建流程_提交BRD
     * modifiedAccountId:提交人oa账号
     * modifiedAccountGuid:提交人guid
     * businessAccount:业务对接人guid
     * businessFunctionsList:业务职能人guid
     * businessCenterList:业务中心负责人guid
     * itCenterLeaderList:IT中心负责人guid
     * developAccount:项目技术负责人/开发人员guid
     * businessPresidentList : 业务副总裁
     * ifZqs:是否周琼硕审批guid
     * counterSigners:会签人  string 逗号分隔
     * cOrZ:李川还是傅志华   string 1-李，2-傅   IT部门副总经理
     * ifGj:是否光建总审批   string 0-否，1-是
     * isFirst 是否产品下第一个项目
     */
    public static ApplyCreateResultVo submitBrd(Map<String, String> paramsMap){
        JSONArray jsonArray = new JSONArray();
        //********************添加业务及技术审批**********begin********************
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval200AppendActors");
        String [] businessAccountGuid = paramsMap.get("businessAccount").split(",");
        String [] developAccountGuid = paramsMap.get("developAccount").split(",");
        String str = businessAccountGuid[0]+","+developAccountGuid[0];
        jsonObject.put("ItemValue", str.split(","));//业务人（业务人员）+项目技术负责人（开发人员第一个）
        jsonArray.add(jsonObject);
        //********************添加业务及技术审批**********end********************

        //********************集团审批1**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval300AppendActors");
        str = paramsMap.get("ifZqs")+","+paramsMap.get("itCenterLeaderList")+","+paramsMap.get("businessCenterList")+","+paramsMap.get("counterSigners");
        jsonObject.put("ItemValue", str.split(","));
        jsonArray.add(jsonObject);
        //********************集团审批1**********end********************

        //********************集团审批2**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "textCondition");
        jsonObject.put("ItemValue", ProgramBpmUtil.getApplyApproval2(paramsMap.get("cOrZ")));
        jsonArray.add(jsonObject);
        //********************集团审批2**********end********************

        //********************集团审批3**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval302AppendActors");
        jsonObject.put("ItemValue", paramsMap.get("businessFunctionsList").split(","));
        jsonArray.add(jsonObject);
        //********************集团审批3**********end********************

        //********************集团审批4**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "textCondition");
        String a = paramsMap.get("isFirst");
        jsonObject.put("ItemValue", ProgramBpmUtil.getIsFirst(a));
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval303AppendActors");
        jsonObject.put("ItemValue", paramsMap.get("businessPresidentList"));
        jsonArray.add(jsonObject);
        //********************集团审批4**********end********************

        //********************抄送**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);
        //********************抄送**********end********************

        String para = jsonArray.toString();
        String result = BpmUtils.startWorkFlow(
                BRD_TEMP_CODE,paramsMap.get("modifiedAccountId"),false,para,null);
        return JSONObject.parseObject(result,ApplyCreateResultVo.class);
    }

    /***
     * 创建流程_提交Demo
     * modifiedAccountId:提交人oa账号
     * modifiedAccountGuid:提交人guid
     * businessAccount:业务对接人guid
     * developAccount:项目技术负责人/开发人员guid
     * businessCenterList:业务中心负责人guid
     * itCenterLeaderList:IT中心负责人guid
     * ifZqs:是否周琼硕审批guid
     * cOrZ:李川还是傅志华   string 1-李，2-傅
     */
    public static ApplyCreateResultVo submitDemo(Map<String, String> paramsMap){
        JSONArray jsonArray = new JSONArray();
        //********************添加相关负责人审批**********begin********************
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval200AppendActors");
        String [] businessAccountGuid = paramsMap.get("businessAccount").split(",");
        String [] developAccountGuid = paramsMap.get("developAccount").split(",");
        String str = businessAccountGuid[0]+","+developAccountGuid[0];
        jsonObject.put("ItemValue", str.split(","));//业务人（业务人员）+项目技术负责人（开发人员第一个）
        jsonArray.add(jsonObject);
        //********************添加相关负责人审批**********end********************

        //********************集团审批1**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval300AppendActors");
        str = paramsMap.get("ifZqs")+","+paramsMap.get("itCenterLeaderList")+","+paramsMap.get("businessCenterList");
        jsonObject.put("ItemValue", str.split(","));
        jsonArray.add(jsonObject);
        //********************集团审批1**********end********************

        //********************集团审批2**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "textCondition");
        jsonObject.put("ItemValue", ProgramBpmUtil.getApplyApproval2(paramsMap.get("cOrZ")));
        jsonArray.add(jsonObject);
        //********************集团审批2**********end********************

        //********************抄送**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);
        //********************抄送**********end********************

        String para = jsonArray.toString();
        String result = BpmUtils.startWorkFlow(
                DEMO_TEMP_CODE,paramsMap.get("modifiedAccountId"),false,para,null);
        return JSONObject.parseObject(result,ApplyCreateResultVo.class);
    }

    /***
     * 创建流程_提交招标
     */
    public static ApplyCreateResultVo submitTender(){
        return new ApplyCreateResultVo();
    }

    /***
     * 创建流程_提交中标
     */
    public static ApplyCreateResultVo submitWinBidding(){
        return new ApplyCreateResultVo();
    }

    /***
     * 创建流程_提交合同签订
     */
    public static ApplyCreateResultVo submitContractSign(){
        return new ApplyCreateResultVo();
    }

    /***
     * 创建流程_提交产品评审
     * modifiedAccountId:提交人oa账号
     * modifiedAccountGuid:提交人guid
     * businessAccount:业务对接人guid
     * developAccount:项目技术负责人/开发人员guid
     * businessCenterList:业务中心负责人guid
     * itCenterLeaderList:IT中心负责人guid
     * ifZqs:是否周琼硕审批guid
     */
    public static ApplyCreateResultVo submitProductReview(Map<String, String> paramsMap){
        JSONArray jsonArray = new JSONArray();
        //********************添加相关负责人审批**********begin********************
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval200AppendActors");
        String [] businessAccountGuid = paramsMap.get("businessAccount").split(",");
        jsonObject.put("ItemValue", businessAccountGuid[0]);//业务人（业务人员）
        jsonArray.add(jsonObject);
        //********************添加相关负责人审批**********end********************

        //********************审批**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval300AppendActors");
        String str = paramsMap.get("ifZqs")+","+paramsMap.get("itCenterLeaderList")+","+paramsMap.get("businessCenterList");
        jsonObject.put("ItemValue", str.split(","));
        jsonArray.add(jsonObject);
        //********************审批**********end********************

        //********************添加技术负责人申**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval301AppendActors");
        String [] developAccountGuid = paramsMap.get("developAccount").split(",");
        jsonObject.put("ItemValue", developAccountGuid[0]);
        jsonArray.add(jsonObject);
        //********************添加技术负责人申**********end********************

        //********************抄送**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);
        //********************抄送**********end********************

        String para = jsonArray.toString();
        String result = BpmUtils.startWorkFlow(
                PRODUCTREVIEW_TEMP_CODE,paramsMap.get("modifiedAccountId"),false,para,null);
        return JSONObject.parseObject(result,ApplyCreateResultVo.class);
    }

    /***
     * 创建流程_提交开发评审
     * modifiedAccountId:提交人oa账号
     * modifiedAccountGuid:提交人guid
     * productManagerList:产品经理
     * ifZqs:是否周琼硕审批guid
     * developAccount:项目技术负责人/开发人员guid
     * counterSigners:会签人  string 逗号分隔
     * testingList:项目测试负责人
     */
    public static ApplyCreateResultVo submitDevelopReview(Map<String, String> paramsMap){
        JSONArray jsonArray = new JSONArray();
        //********************添加技术负责人审批**********begin********************
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval300AppendActors");
        String [] productManagerList = paramsMap.get("productManagerList").split(",");
        jsonObject.put("ItemValue", productManagerList[0]);//业务人（业务人员）
        jsonArray.add(jsonObject);
        //********************添加技术负责人审批**********end********************

        //********************添加测试测试人审批**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval301AppendActors");
        String [] developAccountList = paramsMap.get("developAccount").split(",");
        jsonObject.put("ItemValue", developAccountList[0]+","+paramsMap.get("counterSigners"));//项目技术负责人+会签人
        jsonArray.add(jsonObject);
        //********************添加测试负责人审批**********end********************

        //********************审批**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval302AppendActors");
        jsonObject.put("ItemValue", paramsMap.get("developAccount"));//项目技术负责人
        jsonArray.add(jsonObject);
        //********************审批**********end********************

        String para = jsonArray.toString();
        String result = BpmUtils.startWorkFlow(
                PRODUCTREVIEW_TEMP_CODE,paramsMap.get("modifiedAccountId"),false,para,null);
        return JSONObject.parseObject(result,ApplyCreateResultVo.class);
    }

    /***
     * 创建流程_提交测试评审
     */
    public static ApplyCreateResultVo submitTestReview(){
        return new ApplyCreateResultVo();
    }

    /***
     * 创建流程_提交上线计划
     */
    public static ApplyCreateResultVo submitOnlinePlan(){
        return new ApplyCreateResultVo();
    }

    /***
     * 创建流程_提交灰度发布
     */
    public static ApplyCreateResultVo submitPartIntroduce(){
        return new ApplyCreateResultVo();
    }

    /***
     * 创建流程_提交全面推广
     */
    public static ApplyCreateResultVo submitAllSpread(){
        return new ApplyCreateResultVo();
    }

    /***
     * 创建流程_提交项目复盘
     */
    public static ApplyCreateResultVo submitProgramReplay(){
        return new ApplyCreateResultVo();
    }

    /***
     * 创建流程_提交延期上线
     * modifiedAccountId:提交人oa账号
     * modifiedAccountGuid:提交人guid
     * businessList:业务对接人guid
     * developerList:项目技术负责人/开发人员guid
     * businessFunctionsList:业务职能人guid
     * businessCenterList:业务中心负责人guid
     * itCenterLeaderList:IT中心负责人guid
     * ifZqs:是否周琼硕审批guid
     * tApproval:李川还是傅志华   string 1-李，2-傅   IT部门副总经理
     */
    public static ApplyCreateResultVo submitDelayOnline(Map<String, String> paramsMap){
        JSONArray jsonArray = new JSONArray();
        //********************添加业务人员及项目技术负责人**********begin********************
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval200AppendActors");
        String str = paramsMap.get("businessList")+","+ paramsMap.get("developerList");
        jsonObject.put("ItemValue", str.split(","));//业务对接人（业务人员）+项目技术负责人（开发人员第一个）
        jsonArray.add(jsonObject);
        //********************添加业务人员及项目技术负责人**********end********************

        //********************集团审批1**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval300AppendActors");
        str = paramsMap.get("itCenterLeaderList")+","+paramsMap.get("businessCenterList");
        if("1".equals(paramsMap.get("reportPoor"))){
            str += "," + paramsMap.get("ifZqs");
        }
        jsonObject.put("ItemValue", str.split(","));
        jsonArray.add(jsonObject);
        //********************集团审批1**********end********************

        //********************集团审批2**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "textCondition");
        jsonObject.put("ItemValue", ProgramBpmUtil.getApplyApproval2(paramsMap.get("tApproval")));
        jsonArray.add(jsonObject);
        //业务职能负责人审批
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval301AppendActors");
        jsonObject.put("ItemValue", paramsMap.get("businessFunctionsList").split(","));
        jsonArray.add(jsonObject);
        //********************集团审批2**********end********************

        //********************抄送**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);
        //********************抄送**********end********************

        String para = jsonArray.toString();
        String result = BpmUtils.startWorkFlow(
                DELAYONLINE_TEMP_CODE,paramsMap.get("modifiedAccountId"),false,para,null);
        return JSONObject.parseObject(result,ApplyCreateResultVo.class);
    }

    /***
     * 创建流程_提交需求变更(小于10万走通知)
     * modifiedAccountId:提交人oa账号
     * modifiedAccountGuid:提交人guid
     * businessList:业务对接人guid
     * developerList:项目技术负责人/开发人员guid
     * businessFunctionsList:业务职能人guid
     * businessCenterList:业务中心负责人guid
     * itCenterLeaderList:IT中心负责人guid
     * ifZqs:是否周琼硕审批guid
     * tApproval:李川还是傅志华   string 1-李，2-傅   IT部门副总经理
     */
    public static ApplyCreateResultVo submitDemandChangeAdvise(Map<String, String> paramsMap){
        JSONArray jsonArray = new JSONArray();

        //********************集团通知**********begin********************
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval300AppendActors");
        String str = paramsMap.get("itCenterLeaderList")+","+paramsMap.get("businessCenterList")+ "," + paramsMap.get("businessFunctionsList") +","+paramsMap.get("businessList")+","+ paramsMap.get("developerList")+","+ paramsMap.get("lorf");
        if("1".equals(paramsMap.get("reportPoor"))){
            str += "," + paramsMap.get("ifZqs");
        }
        jsonObject.put("ItemValue", str.split(","));
        jsonArray.add(jsonObject);
        //********************集团通知**********end********************

        //********************抄送**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);
        //********************抄送**********end********************

        String para = jsonArray.toString();
        String result = BpmUtils.startWorkFlow(
                DEMANDCHANGEADVISE_TEMP_CODE,paramsMap.get("modifiedAccountId"),false,para,null);
        return JSONObject.parseObject(result,ApplyCreateResultVo.class);
    }

    /***
     * 创建流程_提交需求变更(大于等于10万走审批)
     * modifiedAccountId:提交人oa账号
     * modifiedAccountGuid:提交人guid
     * businessList:业务对接人guid
     * developerList:项目技术负责人/开发人员guid
     * businessFunctionsList:业务职能人guid
     * businessCenterList:业务中心负责人guid
     * itCenterLeaderList:IT中心负责人guid
     * ifZqs:是否周琼硕审批guid
     * tApproval:李川还是傅志华   string 1-李，2-傅   IT部门副总经理
     */
    public static ApplyCreateResultVo submitDemandChangeApprove(Map<String, String> paramsMap){
        JSONArray jsonArray = new JSONArray();
        //********************添加业务人员及项目技术负责人**********begin********************
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval200AppendActors");
        String str = paramsMap.get("businessList")+","+ paramsMap.get("developerList");
        jsonObject.put("ItemValue", str.split(","));//业务对接人（业务人员）+项目技术负责人（开发人员第一个）
        jsonArray.add(jsonObject);
        //********************添加业务人员及项目技术负责人**********end********************

        //********************集团审批1**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval300AppendActors");
        str = paramsMap.get("itCenterLeaderList")+","+paramsMap.get("businessCenterList");
        if("1".equals(paramsMap.get("reportPoor"))){
            str += "," + paramsMap.get("ifZqs");
        }
        jsonObject.put("ItemValue", str.split(","));
        jsonArray.add(jsonObject);
        //********************集团审批1**********end********************

        //********************集团审批2**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "textCondition");
        jsonObject.put("ItemValue", ProgramBpmUtil.getApplyApproval2(paramsMap.get("tApproval")));
        jsonArray.add(jsonObject);
        //业务职能负责人审批
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval301AppendActors");
        jsonObject.put("ItemValue", paramsMap.get("businessFunctionsList").split(","));
        jsonArray.add(jsonObject);
        //********************集团审批2**********end********************

        //********************抄送**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);
        //********************抄送**********end********************

        String para = jsonArray.toString();
        String result = BpmUtils.startWorkFlow(
                DEMANDCHANGEAPPROVE_TEMP_CODE,paramsMap.get("modifiedAccountId"),false,para,null);
        return JSONObject.parseObject(result,ApplyCreateResultVo.class);
    }

    /***
     * 创建流程_提交终止项目
     * modifiedAccountId:提交人oa账号
     * modifiedAccountGuid:提交人guid
     * businessList:业务对接人guid
     * developerList:项目技术负责人/开发人员guid
     * businessFunctionsList:业务职能人guid
     * businessCenterList:业务中心负责人guid
     * itCenterLeaderList:IT中心负责人guid
     * businessPresidentList:业务副总裁guid
     * ifZqs:是否周琼硕审批guid
     * tApproval:李川还是傅志华   string 1-李，2-傅   IT部门副总经理
     */
    public static ApplyCreateResultVo submitFinishProgram(Map<String, String> paramsMap){

        JSONArray jsonArray = new JSONArray();
        //********************添加业务人员及项目技术负责人**********begin********************
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval200AppendActors");
        String str = paramsMap.get("businessList")+","+ paramsMap.get("developerList");
        jsonObject.put("ItemValue", str.split(","));//业务对接人（业务人员）+项目技术负责人（开发人员第一个）
        jsonArray.add(jsonObject);
        //********************添加业务人员及项目技术负责人**********end********************

        //********************集团审批1**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval300AppendActors");
        str = paramsMap.get("itCenterLeaderList")+","+paramsMap.get("businessCenterList");
        if("1".equals(paramsMap.get("reportPoor"))){
            str += "," + paramsMap.get("ifZqs");
        }
        jsonObject.put("ItemValue", str.split(","));
        jsonArray.add(jsonObject);
        //********************集团审批1**********end********************

        //********************集团审批2**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "textCondition");
        jsonObject.put("ItemValue", ProgramBpmUtil.getApplyApproval2(paramsMap.get("tApproval")));
        jsonArray.add(jsonObject);
        //********************集团审批2**********end********************

        //********************业务职能负责人审批**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval302AppendActors");
        jsonObject.put("ItemValue", paramsMap.get("businessFunctionsList").split(","));
        jsonArray.add(jsonObject);
        //********************业务职能负责人审批**********end********************

        //********************集团审批4**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval303AppendActors");
        jsonObject.put("ItemValue", paramsMap.get("businessPresidentList"));
        jsonArray.add(jsonObject);
        //********************集团审批4**********end********************

        //********************抄送**********begin********************
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "Originator");
        jsonObject.put("ItemValue", paramsMap.get("modifiedAccountGuid"));
        jsonArray.add(jsonObject);
        //********************抄送**********end********************

        String para = jsonArray.toString();
        String result = BpmUtils.startWorkFlow(
                TERMINATIONPROGRAM_TEMP_CODE,paramsMap.get("modifiedAccountId"),false,para,null);
        return JSONObject.parseObject(result,ApplyCreateResultVo.class);
    }

}
