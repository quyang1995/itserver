package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.BugChangeLog;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface BugChangeLogMapper extends BeeMapper<BugChangeLog> {

    List<BugChangeLog> orderLimitList(BugChangeLog changeLog);

    List<BugChangeLog> paraQuery(Map<String, String> paramsMap);
}