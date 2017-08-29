package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.entity.ProgramEmployeeChangeLog;
import com.longfor.itserver.mapper.ProgramEmployeeChangeLogMapper;
import com.longfor.itserver.service.IProgramEmployeeChangeLogService;
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
@Service("ProgramEmployeeChangeLogService")
public class ProgramEmployeeChangeLogServiceImpl extends AdminBaseService<ProgramEmployeeChangeLog> implements IProgramEmployeeChangeLogService {

    @Autowired
    private ProgramEmployeeChangeLogMapper programEmployeeChangeLogMapper;

    @Override
    public Map orderLimitList(Map paramsMap) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(paramsMap);
        Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        ProgramEmployeeChangeLog employeeChangeLog = JSONObject.toJavaObject(jsonObject,ProgramEmployeeChangeLog.class);

        List list = programEmployeeChangeLogMapper.orderLimitList(employeeChangeLog);
        resultMap.put("list",list);

        return resultMap;
    }
}
