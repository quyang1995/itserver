package com.longfor.itserver.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.entity.DemandChangeLog;
import com.longfor.itserver.mapper.DemandChangeLogMapper;
import com.longfor.itserver.service.IDemandChangeLogService;
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
@Service("DemandChangeLogService")
public class DemandChangeLogServiceImpl extends AdminBaseService<DemandChangeLog> implements IDemandChangeLogService {

    @Autowired
    private DemandChangeLogMapper demandChangeLogMapper;

    @Override
    public Map orderLimitList(Map paramsMap) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(paramsMap);
        Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        DemandChangeLog log = JSONObject.toJavaObject(jsonObject,DemandChangeLog.class);

        List<DemandChangeLog> list = demandChangeLogMapper.orderLimitList(log);
        resultMap.put("list",list);

        return resultMap;
    }
}
