package com.longfor.itserver.service;


import com.longfor.itserver.entity.ProgramEmployeeChangeLog;
import com.longfor.itserver.service.base.IAdminService;

import java.util.Map;

public interface IProgramEmployeeChangeLogService extends IAdminService<ProgramEmployeeChangeLog> {

    Map orderLimitList(Map paramsMap);
}

