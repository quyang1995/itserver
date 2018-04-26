package com.longfor.itserver.service.impl;

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
    public List<ProgramEmployeeChangeLog> orderLimitList(Map<String,Object> paramsMap) {

        return programEmployeeChangeLogMapper.orderLimitList(paramsMap);
    }

    @Override
    public int orderLimitTotal(Map<String,Object> paramsMap) {

        return programEmployeeChangeLogMapper.orderLimitTotal(paramsMap);
    }

    @Override
    public  List<ProgramEmployeeChangeLog> paraQuery(Map<String, String> paramsMap){
        return programEmployeeChangeLogMapper.paraQuery(paramsMap);
    }
}
