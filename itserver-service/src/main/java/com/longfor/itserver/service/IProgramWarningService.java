package com.longfor.itserver.service;

import com.longfor.itserver.entity.ProgramWarning;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IProgramWarningService extends IAdminService<ProgramWarning>{

    List<ProgramWarning> getListByWhere (Map<String,Object> map);

    ProgramWarning addProgramWarning(Map<String,Object> map);
    /**
     * 取当前项目节点的最新的一条手动预警数据
     * @param map
     * @return
     */
    ProgramWarning getOneByWhere (Map<String,Object> map);

}
