package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.DemandChangeLog;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface DemandChangeLogMapper extends BeeMapper<DemandChangeLog> {

    List<DemandChangeLog> orderLimitList(DemandChangeLog changeLog);

    List<DemandChangeLog> paraQuery(Map<String, String> paramsMap);
}