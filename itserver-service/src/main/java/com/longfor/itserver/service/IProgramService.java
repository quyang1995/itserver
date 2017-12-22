package com.longfor.itserver.service;

import com.longfor.itserver.common.vo.programBpm.ApplyViewVo;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IProgramService extends IAdminService<Program> {

    List<Program>  programList(Map map);

    List<Program>  programLimitList(Map map);

    boolean addProgram(Map map);

    List<Program> inProgramId(String likeProgram);

    Program getProgramId(long id);
    
    boolean updateProgram(Map map);

    List<Program> productIdList(Map map);

    List<Program> productIdAllList(Map parsmsMap);

    boolean updateStatus(Map paramsMap);

    void apply(Map<String, String> paramsMap,Program program);

    void approvalPass(Map<String, String> paramsMap,Program program);

    void approvalRebut(Map<String, String> paramsMap,Program program);

    ApplyViewVo applyView(Map<String, String> paramsMap, Program program) throws Exception;
}
