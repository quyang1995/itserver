package com.longfor.itserver.esi.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.entity.AccountLongforLike;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.eds.entity.PsEmployee;
import com.longfor.eds.entity.PsEmployeeClass;
import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.common.util.HttpUtil;
import com.longfor.itserver.common.vo.BuddyAccount;
import com.longfor.itserver.esi.IAdsService;
import jodd.props.Props;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author chi.zhang
 * @create 2017/6/9 上午8:33
 *
 * @version v1.0
 **/
@Service("adsServiceImpl")
public class AdsServiceImpl implements IAdsService {
    private static String url;
    private static String token;
    private static final String BUDDY_ACCOUNT_GETBYLOGINNAME = "/api/account/buddy/getByLoginName";

    static {
        Props props = JoddHelper.getInstance().getJoddProps();
        url = props.getValue("ads.url");
        token = props.getValue("ads.token");
    }

    public static BuddyAccount buddyAccountGetByLoginName(String loginName){
        try{
            JSONObject para = new JSONObject();
            para.put("loginName",loginName);
            JSONObject result = HttpUtil.post(url + BUDDY_ACCOUNT_GETBYLOGINNAME,token,para.toString());
            if(result == null){
                return null;
            }

            if ("SSSS".equals(result.get("code"))) {
                return JSON.parseObject(JSON.toJSONString(result.get("buddyAccount")), BuddyAccount.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    ////////////////////////////////////////////////////新旧分隔线//////////////////////////////////////////////////////////////////
    @Autowired
    private ADSHelper adsHelper;

    @Override
    public List<AccountLongforLike> likeByName(String name) {
        return adsHelper.likeByName(name);
    }

    /**
     * 判断账户是否存在
     * 返回值：true存在，false不存在
     *
     * @param loginName
     * @return
     */
    @Override
    public boolean isAvaAccount(String loginName) {
        return adsHelper.getAccountLongforByLoginName(loginName) != null;
    }

    @Override
    public AccountLongfor getAccountLongfor(String loginName) {
        return adsHelper.getAccountLongforByLoginName(loginName);
    }
    @Override
    public com.longfor.ads.entity.BuddyAccount getBuddyAccountByLoginName(String loginName) {
        return adsHelper.getByLoginNameBuddyAccount(loginName);
    }

}
