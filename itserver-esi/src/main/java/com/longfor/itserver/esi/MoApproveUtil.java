package com.longfor.itserver.esi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.common.util.HttpUtil;
import com.longfor.itserver.common.vo.MoApprove.MoApproveListVo;
import jodd.props.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 移动审批工具类
 */
public class MoApproveUtil{
    private static final Logger LOG = LoggerFactory.getLogger(MoApproveUtil.class);
    private static String url;
    private static String token;
    private static String systemCode;
    private static final String FLOWAPI_LIST = "/flowapi/list";

    static {
        Props props = JoddHelper.getInstance().getJoddProps();
        url = props.getValue("moApprovel.url");
        token = props.getValue("moApprovel.token");
        systemCode = props.getValue("moApprovel.systemCode");
    }

    public static MoApproveListVo flowapiList(int status,String userName,String searchType,int page,int pageSize){
        String paraR = "";
        String resultR = "";
        try{
            JSONObject para = new JSONObject();
            para.put("status",status);
            para.put("username",userName);
            para.put("searchType",searchType);
            para.put("page",page);
            para.put("pageSize",pageSize);
            para.put("systemNo",systemCode);
            paraR = para.toString();
            JSONObject result = HttpUtil.post(url + FLOWAPI_LIST,token,paraR);
            resultR = result.toJSONString();
            if ("0".equals(result.get("code").toString())) {
                String data = JSON.toJSONString(result.get("data"));
                return JSON.parseObject(data, MoApproveListVo.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        LOG.error("调用移动审批方法/flowapi/list发生异常，入参:"+paraR+",出参："+resultR);
        return null;
    }
}
