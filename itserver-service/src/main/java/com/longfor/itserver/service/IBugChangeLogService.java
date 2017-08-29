package com.longfor.itserver.service;

import com.longfor.itserver.entity.BugChangeLog;
import com.longfor.itserver.service.base.IAdminService;

import java.util.Map;

public interface IBugChangeLogService extends IAdminService<BugChangeLog> {

        Map orderLimitList(Map paramsMap);
}
