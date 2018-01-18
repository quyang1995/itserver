package com.longfor.itserver.esi.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.eds.helper.EDSHelper;
import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.common.util.HttpUtil;
import com.longfor.itserver.esi.IEdsService;
import jodd.props.Props;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author chi.zhang
 * @create 2017/6/9 上午8:33
 *
 * @version v1.0
 **/
@Service("edsServiceImpl")
public class EdsServiceImpl implements IEdsService {

    @Autowired
    private EDSHelper edsHelper;

    private static String url;
    private static String token;
    private static final String GET_EMP_GUID_BY_PFACC = "/api/employee/getEmpListByPfAcc";

    static {
        Props props = JoddHelper.getInstance().getJoddProps();
        url = props.getValue("eds.url");
        token = props.getValue("eds.token");
    }


    /**
     * 根据员工oa账号获取员工guid
     * 多个员工，以‘，’分割，返回guid也以‘，’分割
     * @param oaStr
     * @return
     */
    public String getEmpGuidByPfAcc(String oaStr){
        StringBuffer sb = new StringBuffer();
        try{
            JSONObject para = new JSONObject();
            para.put("psAccList",oaStr);
            JSONObject json = HttpUtil.post(url + GET_EMP_GUID_BY_PFACC,token,para.toString());
            List<Map<String,Object>> jsonList = JSON.parseObject(JSON.toJSONString(json.get("list")),List.class);
            if (jsonList == null || jsonList.isEmpty()) {
                return null;
            }
            for (Map<String,Object> model:jsonList) {
                sb.append(model.get("psGuid"));
                sb.append(",");
            }
            sb.deleteCharAt(sb.length()-1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }



}
