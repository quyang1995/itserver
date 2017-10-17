package com.longfor.itserver.service;


import com.longfor.itserver.entity.DemandChangeLog;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IDemandChangeLogService extends IAdminService<DemandChangeLog> {

    Map orderLimitList(Map paramsMap);

    List<DemandChangeLog> paraQuery(Map<String, String> paramsMap);
}
