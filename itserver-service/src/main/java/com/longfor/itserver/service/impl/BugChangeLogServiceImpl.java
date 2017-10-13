package com.longfor.itserver.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.entity.BugChangeLog;
import com.longfor.itserver.mapper.BugChangeLogMapper;
import com.longfor.itserver.service.IBugChangeLogService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wax
 *         Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("BugChangeLogService")
public class BugChangeLogServiceImpl extends AdminBaseService<BugChangeLog> implements IBugChangeLogService {

    @Autowired
    private BugChangeLogMapper bugChangeLogMapper;

    @Override
    public Map orderLimitList(Map paramsMap) {
        JSONObject jsonObject =  (JSONObject)JSONObject.toJSON(paramsMap);
        BugChangeLog log =  JSONObject.toJavaObject(jsonObject , BugChangeLog.class);
        Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);

        List<BugChangeLog> list = bugChangeLogMapper.orderLimitList(log);
        resultMap.put("list",list);
        return resultMap;
    }
    @Override
    public List<BugChangeLog> paraQuery(Map<String, String> paramsMap) {
        return bugChangeLogMapper.paraQuery(paramsMap);
    }
}
