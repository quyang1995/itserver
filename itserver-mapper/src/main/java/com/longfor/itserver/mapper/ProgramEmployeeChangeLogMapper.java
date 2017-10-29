package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.ProgramEmployeeChangeLog;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface ProgramEmployeeChangeLogMapper extends BeeMapper<ProgramEmployeeChangeLog> {

    List<ProgramEmployeeChangeLog> orderLimitList(ProgramEmployeeChangeLog employeeChangeLog);
    List<ProgramEmployeeChangeLog> paraQuery(Map<String, String> paramsMap);
}