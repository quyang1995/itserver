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
    private static final String BRD_TEMP_CODE = "ITplus_ITxmlx";
    private static final String demo_temp_code = "ITplus_ITxmlx";
    private static final String Tender_TEMP_CODE = "ITplus_ITxmlx";
    private static final String WinBidding_TEMP_CODE = "ITplus_ITxmlx";
    private static final String ContractSign_TEMP_CODE = "ITplus_ITxmlx";
    private static final String ProductReview_TEMP_CODE = "ITplus_ITxmlx";
    private static final String DevelopReview_TEMP_CODE = "ITplus_ITxmlx";
    private static final String TestReview_TEMP_CODE = "ITplus_ITxmlx";
    private static final String OnlinePlan_TEMP_CODE = "ITplus_ITxmlx";
    private static final String PartIntroduce_TEMP_CODE = "ITplus_ITxmlx";
    private static final String AllSpread_TEMP_CODE = "ITplus_ITxmlx";
    private static final String ProgramReplay_TEMP_CODE = "ITplus_ITxmlx";
    private static final String DelayOnline_TEMP_CODE = "ITplus_ITxmlx";
    private static final String DemandChangeAdvise_TEMP_CODE = "ITplus_ITxmlx";
    private static final String DemandChangeApprove_TEMP_CODE = "ITplus_ITxmlx";
    private static final String FinishProgram_TEMP_CODE = "ITplus_ITxmlx";


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
     * submitAccount:提交人oa账号
     * businessAccount:业务对接人guid
     * developAccount:项目技术负责人/开发人员guid
     * ifZqs:是否周琼硕审批  string 0-否，1-是
     * counterSigners:会签人  string 逗号分隔
     * cOrZ:李川还是傅志华   string 0-李，1-傅
     * ifGj:是否光建总审批   string 0-否，1-是
     */
    public static ApplyCreateResultVo submitBrd(Map<String, String> paramsMap){
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval302AppendActors");
        jsonObject.put("ItemValue", paramsMap.get("counterSigners").split(","));
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "textCondition");
        jsonObject.put("ItemValue", ProgramBpmUtil.getApplyApproval2(paramsMap.get("tApproval")));
        jsonArray.add(jsonObject);

        String para = jsonArray.toString();
        String result = BpmUtils.startWorkFlow(
                BRD_TEMP_CODE,paramsMap.get("modifiedAccountId"),false,para,null);
        return JSONObject.parseObject(result,ApplyCreateResultVo.class);
    }

    /***
     * 创建流程_提交Demo
     */
    public static ApplyCreateResultVo submitDemo(){
        return new ApplyCreateResultVo();
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
     */
    public static ApplyCreateResultVo submitProductReview(){
        return new ApplyCreateResultVo();
    }

    /***
     * 创建流程_提交开发评审
     */
    public static ApplyCreateResultVo submitDevelopReview(){
        return new ApplyCreateResultVo();
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
     */
    public static ApplyCreateResultVo submitDelayOnline(){
        return new ApplyCreateResultVo();
    }

    /***
     * 创建流程_提交需求变更(小于10万走通知)
     */
    public static ApplyCreateResultVo submitDemandChangeAdvise(){
        return new ApplyCreateResultVo();
    }

    /***
     * 创建流程_提交需求变更(大于等于10万走审批)
     */
    public static ApplyCreateResultVo submitDemandChangeApprove(){
        return new ApplyCreateResultVo();
    }

    /***
     * 创建流程_提交终止项目
     */
    public static ApplyCreateResultVo submitFinishProgram(){
        return new ApplyCreateResultVo();
    }

}
