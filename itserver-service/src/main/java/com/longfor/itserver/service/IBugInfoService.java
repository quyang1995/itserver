package com.longfor.itserver.service;


import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IBugInfoService extends IAdminService<BugInfo> {

    List<BugInfo> bugList(Map map);

    boolean addBug(Map map);

    BugInfo getBugId(long id);

    boolean updateBug(Map map);

    /**
     * 更新bug状态
     */
    boolean updateStatus(Map<String,String> paramsMap);

    /**
     * 更新bug指派人
     */
    boolean updateCallon(Map<String,String> paramsMap);
}
