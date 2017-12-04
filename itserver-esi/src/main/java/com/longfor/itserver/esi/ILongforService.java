package com.longfor.itserver.esi;

import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.entity.AccountLongforLike;
import com.longfor.ads.entity.BuddyAccount;

import java.util.List;
import java.util.Map;

/**
 *
 * @author wax
 * @create 2017/11/16 上午8:34
 *
 * @version v1.0
 **/
public interface ILongforService {

    JSONObject msgcenter(Map<String, String> paramMap);
}
