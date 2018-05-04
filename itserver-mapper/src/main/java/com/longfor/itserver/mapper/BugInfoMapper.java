package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.entity.ps.PsBugInfoDetail;
import com.longfor.itserver.entity.ps.PsBugTimeTask;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface BugInfoMapper extends BeeMapper<BugInfo> {

    List<PsBugInfoDetail> bugList(Map map);

    boolean addBug(Map map);

    BugInfo getBugId(long id);

    boolean updateBug(Map map);

    List<BugInfo> statusList(Map paramsMap);

    List<PsBugTimeTask> bugTask();

    /**
     * 导出BUG列表(新)
     * @param map
     * @return
     */
    List<Map<String,Object>> newExport(Map map);

}