package com.longfor.itserver.service;

import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramApprovalSnapshot;
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

    List<ProgramApprovalSnapshot> milepost(Map<String,Object> map);

    List<ProgramApprovalSnapshot> lookNodes(Map<String,Object> map);

    ProgramApprovalSnapshot getProgramByBpmCode(Map<String,Object> map);

    void approvalPass(Map<String, String> paramsMap,Program program);

    void approvalRebut(Map<String, String> paramsMap,Program program);

    void delay(Map<String, String> paramsMap,Program program);

    void demandChange(Map<String, String> paramsMap,Program program);

    void stopProgram(Map<String, String> paramsMap,Program program);

    void submit(Map<String, String> paramsMap,Program program,int programStatus);
//    ApplyViewVo applyView(Map<String, String> paramsMap, Program program) throws Exception;
}
