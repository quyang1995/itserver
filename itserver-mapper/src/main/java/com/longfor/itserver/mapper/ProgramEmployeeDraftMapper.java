package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.ProgramEmployeeDraft;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface ProgramEmployeeDraftMapper extends BeeMapper<ProgramEmployeeDraft> {
    List<ProgramEmployeeDraft> selectTypeList(Map map);

}