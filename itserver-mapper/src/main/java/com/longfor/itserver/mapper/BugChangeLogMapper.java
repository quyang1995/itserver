package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.BugChangeLog;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;

public interface BugChangeLogMapper extends BeeMapper<BugChangeLog> {

    List<BugChangeLog> orderLimitList(BugChangeLog changeLog);
}