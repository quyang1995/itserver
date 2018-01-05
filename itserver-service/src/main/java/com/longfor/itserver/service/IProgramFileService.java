package com.longfor.itserver.service;

import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramApprovalSnapshot;
import com.longfor.itserver.entity.ProgramFile;
import com.longfor.itserver.entity.ProgramFileVo;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IProgramFileService extends IAdminService<ProgramFile> {

    List<ProgramFileVo> getListByMap(Map<String,Object> paramsMap)  throws Exception;

    int getListByMapTotal(Map<String,Object> paramsMap)  throws Exception;
}
