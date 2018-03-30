package com.longfor.itserver.service;

import com.longfor.itserver.entity.ProgramEmployeeDraft;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IProgramEmployeeDraftService extends IAdminService<ProgramEmployeeDraft> {

    List<ProgramEmployeeDraft> selectTypeList(Map map);

}
