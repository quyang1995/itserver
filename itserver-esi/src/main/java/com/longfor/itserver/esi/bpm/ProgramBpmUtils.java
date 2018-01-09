package com.longfor.itserver.esi.bpm;

import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.vo.programBpm.common.ApplyCreateResultVo;
import com.longfor.itserver.common.vo.programBpm.common.ApplySubmitResultVo;

/**
 * Created by sunyanhui on 2017/12/8.
 */
public class ProgramBpmUtils
{
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
     */
    public static ApplyCreateResultVo submitBrd(){
        return new ApplyCreateResultVo();
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
    public static ApplyCreateResultVo submitProgramReplayApprove(){
        return new ApplyCreateResultVo();
    }

    /***
     * 创建流程_提交终止项目
     */
    public static ApplyCreateResultVo submitFinishSubmit(){
        return new ApplyCreateResultVo();
    }

}
