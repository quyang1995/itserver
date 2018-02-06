package com.longfor.itserver.service;

import com.longfor.itserver.common.vo.MoApprove.MoApproveListVo;
import com.longfor.itserver.common.vo.programBpm.ApproveListVo;
import com.longfor.itserver.entity.*;
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

    void cancelInstance(Map<String, String> paramsMap,Program program);

    void delay(Map<String, String> paramsMap,Program program);

    void demandChange(Map<String, String> paramsMap,Program program);

    void stopProgram(Map<String, String> paramsMap,Program program);

    void submit(Map<String, String> paramsMap,Program program,int programStatus);

    ProgramApprovalSnapshot getSnapshot(Long id) throws Exception;

    ApproveListVo getApprovelapprovList(MoApproveListVo moApproveListVo,String isMyLaunch) throws Exception;

    void addProgramFollow(Map<String,String> paramMap);

    void cancelFollow(Map<String,String> paramMap);

    List<Product> getListByLikeAnalyzingConditions(Map<String,Object> paramMap,int type);

    int getProgramSum(Map<String, Object> paramsMap,int type);

    Map getExceptionProgramList(Map<String, Object> paramsMap,Map resultMap);

    Map latelyChangeList(Map<String, Object> paramsMap,Map resultMap);

    Map myFollowProgram(Map<String, Object> paramsMap,Map resultMap) throws Exception;

    List<Map<String,Object>> changeTopFive(List<Product> productList,Map<String, Object> paramsMap);

    List<Map<String,Object>> yearCost();

    List<ProgramFile> getFileListByWhere(Map<String,Object> map);
}
