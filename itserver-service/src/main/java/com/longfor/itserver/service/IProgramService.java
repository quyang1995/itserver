package com.longfor.itserver.service;

import com.longfor.itserver.common.vo.MoApprove.MoApproveListVo;
import com.longfor.itserver.common.vo.programBpm.ApproveListVo;
import com.longfor.itserver.entity.ExceptionProgramVo;
import com.longfor.itserver.entity.Product;
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

    ProgramApprovalSnapshot getSnapshot(Long id) throws Exception;

    ApproveListVo getApprovelapprovList(MoApproveListVo moApproveListVo) throws Exception;

    void addProgramFollow(Map<String,String> paramMap);

    void cancelFollow(Map<String,String> paramMap);

    List<Product> getListByLikeAnalyzingConditions(Map<String,Object> paramMap,int type);

    int getProgramSum(List<Product> productList,int type);

    List<ExceptionProgramVo> getExceptionProgramList(Map<String, Object> paramsMap);

    List<ProgramApprovalSnapshot> latelyChangeList(Map<String, Object> paramsMap);

    List<Map<String,Object>> myFollowProgram(Map<String, Object> paramsMap) throws Exception;

    Integer myFollowProgramTotal(Map<String, Object> paramsMap);

    List<Map<String,Object>> changeTopFive(List<Product> productList,Map<String, Object> paramsMap);

    List<Map<String,Object>> yearCost();
}
