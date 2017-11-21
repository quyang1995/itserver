package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.entity.ps.PsBugTimeTask;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface BugInfoMapper extends BeeMapper<BugInfo> {

    List<BugInfo> bugList(Map map);

    boolean addBug(Map map);

    BugInfo getBugId(long id);

    boolean updateBug(Map map);

    List<BugInfo> statusList(Map paramsMap);

    List<PsBugTimeTask> bugTask();

}