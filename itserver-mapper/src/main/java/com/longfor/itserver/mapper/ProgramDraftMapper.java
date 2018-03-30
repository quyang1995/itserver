package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.ProgramDraft;
import com.longfor.itserver.entity.ps.PsProgramDraftDetail;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.Map;

public interface ProgramDraftMapper extends BeeMapper<ProgramDraft> {

    PsProgramDraftDetail getProgramDraftDetail(Map map);
}