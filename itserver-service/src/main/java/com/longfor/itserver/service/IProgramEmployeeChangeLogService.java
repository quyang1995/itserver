package com.longfor.itserver.service;


import com.longfor.itserver.entity.ProductEmployeeChangeLog;
import com.longfor.itserver.entity.ProgramEmployeeChangeLog;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IProgramEmployeeChangeLogService extends IAdminService<ProgramEmployeeChangeLog> {

    List<ProgramEmployeeChangeLog> orderLimitList(Map<String,Object> paramsMap);
    List<ProgramEmployeeChangeLog> paraQuery(Map<String, String> paramsMap);
}

