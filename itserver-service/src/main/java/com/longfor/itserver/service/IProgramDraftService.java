package com.longfor.itserver.service;

import com.longfor.itserver.entity.ProgramDraft;
import com.longfor.itserver.entity.ps.PsProgramDraftDetail;
import com.longfor.itserver.service.base.IAdminService;

import java.util.Map;

public interface IProgramDraftService extends IAdminService<ProgramDraft> {

    boolean addProgramDraft(Map map) throws Exception;

    boolean applyNode(Map map) throws Exception;

    boolean deleteProgramDraft(Map map);

    PsProgramDraftDetail getProgramDraftDetail(Map map);

}
