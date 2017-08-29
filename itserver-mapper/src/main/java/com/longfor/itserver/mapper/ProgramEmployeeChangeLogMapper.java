package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.ProgramEmployeeChangeLog;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;

public interface ProgramEmployeeChangeLogMapper extends BeeMapper<ProgramEmployeeChangeLog> {

    List<ProgramEmployeeChangeLog> orderLimitList(ProgramEmployeeChangeLog employeeChangeLog);
}