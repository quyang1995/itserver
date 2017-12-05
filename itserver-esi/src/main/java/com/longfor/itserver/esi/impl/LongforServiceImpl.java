package com.longfor.itserver.esi.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.entity.AccountLongforLike;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.common.util.HttpUtil;
import com.longfor.itserver.common.vo.BuddyAccount;
import com.longfor.itserver.entity.Demand;
import com.longfor.itserver.esi.IAdsService;
import com.longfor.itserver.esi.ILongforService;
import jodd.http.HttpRequest;
import jodd.props.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chi.zhang
 * @create 2017/6/9 上午8:33
 *
 * @version v1.0
 **/
@Service("longforService")
public class LongforServiceImpl implements ILongforService {
    private static String URL;
    private static String token;
    private static final String LONGFOR_MSGCENTER = "/longfor/msgcenter";
    static {
        Props props = JoddHelper.getInstance().getJoddProps();
        URL = props.getValue("openUrl.longforPath");
    }
    //龙信消息提醒
    @Override
    public JSONObject msgcenter(Map<String, String> paramMap) {
        String reJSONStr = HttpRequest
                .post(URL + LONGFOR_MSGCENTER)
                .bodyText(JSON.toJSONString(paramMap), ConfigConsts.MEDIA_TYPE, ConfigConsts.UTF8).send().bodyText();
        JSONObject reObject = JSON.parseObject(reJSONStr);

        return "SSSS".equals(reObject.get("code")) ? reObject : null;
    }

    public Map param(){
        Map paramMap = new HashMap<>();
        paramMap.put("suser","消息中心");
        paramMap.put("shapType","card");
        JSONObject paramMapCont = new JSONObject();
        paramMapCont.put("msgType","8485655c-6770-11e7-907b-a6006ad3dba0");
        paramMapCont.put("centerImg","");
        JSONArray arr = new JSONArray();
        paramMapCont.put("centerKeyvals",arr);
        paramMapCont.put("fromName","IT+");
        paramMapCont.put("fromAppUrl","");
        paramMapCont.put("fromIcon","http://mop.longfor.com:8090/manager/download/lightapp/it.png");
        paramMap.put("content", paramMapCont);
        return paramMap;
    }
}
