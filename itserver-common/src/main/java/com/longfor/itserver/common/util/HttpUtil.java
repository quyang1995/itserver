package com.longfor.itserver.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.constant.ConfigConsts;
import jodd.http.HttpRequest;

/**
 * Created by sunyanhui on 2017/9/18.
 */
public class HttpUtil {

    public static JSONObject post(String url, String token, String para){
        try{
            String reJSONStr = HttpRequest
                    .post(url)
                    .header(ConfigConsts.TOKEN_NAME, token)
                    .bodyText(para, ConfigConsts.MEDIA_TYPE, ConfigConsts.CHARSET).send().bodyText();
            JSONObject reObject = JSON.parseObject(reJSONStr);
            return reObject;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
