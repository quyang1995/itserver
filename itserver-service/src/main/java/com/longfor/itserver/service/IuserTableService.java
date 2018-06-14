package com.longfor.itserver.service;

import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface userTableService extends IAdminService<BugChangeLog> {

        Map orderLimitList(Map paramsMap);

}
