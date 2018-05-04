package com.longfor.itserver.service;


import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.entity.ps.PsBugInfoDetail;
import com.longfor.itserver.entity.ps.PsBugTimeTask;
import com.longfor.itserver.service.base.IAdminService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IBugInfoService extends IAdminService<BugInfo> {

    List<PsBugInfoDetail> bugList(Map map);

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

    Map statusList(HttpServletRequest request,Map<String, String> paramsMap);

    /**
     * 定时任务
     * @return
     */
    List<PsBugTimeTask> bugTask();

    /**
     * 导出BUG列表(新)
     * @param map
     * @return
     */
    List<Map<String,Object>> newExport(Map map);

    /**
     * 删除项目或产品相关bug，relationType=1：产品，relationType=2：项目
     * @param id
     * @param relationType
     */
    void deleteBugInfo(Long id,Integer relationType);
}
