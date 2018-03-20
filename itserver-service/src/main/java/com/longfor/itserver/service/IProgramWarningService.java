package com.longfor.itserver.service;

import com.longfor.itserver.entity.ProgramWarning;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IProgramWarningService extends IAdminService<ProgramWarning>{

    List<ProgramWarning> getListByWhere (Map<String,Object> map);

    ProgramWarning addProgramWarning(Map<String,Object> map);
}
