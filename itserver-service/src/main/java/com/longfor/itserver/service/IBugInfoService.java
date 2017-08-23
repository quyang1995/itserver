package com.longfor.itserver.service;


import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IBugInfoService extends IAdminService<BugInfo> {

    List<BugInfo> bugList(Map map);

    boolean addBug(Map map);

    BugInfo getBugId(long id);

    boolean updateBug(Map map);
}
