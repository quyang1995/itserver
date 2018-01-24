package com.longfor.itserver.esi.bpm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.common.util.StringUtil;
import com.longfor.itserver.common.vo.programBpm.common.ApplyCreateResultVo;
import com.longfor.itserver.common.vo.programBpm.common.ApplySubmitResultVo;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by sunyanhui on 2017/12/8.(待作废)
 */
public class ProgramBpmUtil
{

    /***
     * 提交立项-审批节点2
     * @param tApproval
     * @return
     */
    public static String getApplyApproval2(String tApproval){
        if(StringUtils.isBlank(tApproval) || tApproval.equals("1"))return ConfigConsts.programBpm.apply_approval2_1;
        if(tApproval.equals("2"))return ConfigConsts.programBpm.apply_approval2_2;
        return null;
    }

    public static String getIsFirst(String isFirst){
        if(StringUtils.isNotBlank(isFirst) && isFirst.equals("1"))return ConfigConsts.programBpm.is_first;
        return null;
    }

    /***
     * 提交立项-创建流程
     * @param paramsMap
     * @return
     */
    public static ApplyCreateResultVo createApplyWorkFlow(Map<String, String> paramsMap){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ItemName", "approval302AppendActors");
        if (paramsMap.get("counterSigners") != null){
            jsonObject.put("ItemValue", paramsMap.get("counterSigners").split(","));
        }
        jsonArray.add(jsonObject);
        jsonObject = new JSONObject();
        jsonObject.put("ItemName", "textCondition");
        jsonObject.put("ItemValue", ProgramBpmUtil.getApplyApproval2(paramsMap.get("tApproval")));
        jsonArray.add(jsonObject);
        String para = jsonArray.toString();
        String clientTempCode = JoddHelper.getInstance().getJoddProps().getValue("bpm.clientTempCode");
        String result = BpmUtils.startWorkFlow(
                clientTempCode,paramsMap.get("modifiedAccountId"),false,para,null);
        return JSONObject.parseObject(result,ApplyCreateResultVo.class);
    }

    /***
     * 提交立项-提交流程
     * @param oaAccount
     * @param workItemId
     * @return
     */
    public static ApplySubmitResultVo applySumbmitWorkItem(String oaAccount,String workItemId,String suggestion){
        String result = BpmUtils.submitWorkItem(oaAccount,workItemId,null,suggestion);
        ApplySubmitResultVo pplySubmitResultVo = JSONObject.parseObject(result, ApplySubmitResultVo.class);
        return pplySubmitResultVo;
    }
    /***
     * 提交立项-提交流程
     * @param oaAccount
     * @param workItemId
     * @return
     */
    public static ApplySubmitResultVo applySumbmitWorkItem(String oaAccount,String workItemId){
        String result = BpmUtils.submitWorkItem(oaAccount,workItemId,null,null);
        ApplySubmitResultVo pplySubmitResultVo = JSONObject.parseObject(result, ApplySubmitResultVo.class);
        return pplySubmitResultVo;
    }

    /***
     * 提交立项-驳回至发起人
     * @param oaAccount
     * @param workItemId
     * @return
     */
    public static ApplySubmitResultVo returnWorkflowToStart(String oaAccount,String workItemId,String suggestion){
        String result = BpmUtils.returnWorkflowToStart(oaAccount,workItemId,suggestion);
        ApplySubmitResultVo pplySubmitResultVo = JSONObject.parseObject(result, ApplySubmitResultVo.class);
        return pplySubmitResultVo;
    }

    /***
     * 提交立项-驳回至发起人
     * @param oaAccount
     * @param workItemId
     * @return
     */
    public static ApplySubmitResultVo returnWorkflowToStart(String oaAccount,String workItemId){
        String result = BpmUtils.returnWorkflowToStart(oaAccount,workItemId,null);
        ApplySubmitResultVo pplySubmitResultVo = JSONObject.parseObject(result, ApplySubmitResultVo.class);
        return pplySubmitResultVo;
    }

}
