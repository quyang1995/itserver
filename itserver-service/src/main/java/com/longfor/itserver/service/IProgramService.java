package com.longfor.itserver.service;

import com.longfor.itserver.common.vo.MoApprove.MoApproveListVo;
import com.longfor.itserver.common.vo.programBpm.ApproveListVo;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.entity.ps.PsProgramDetail;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IProgramService extends IAdminService<Program> {

    List<PsProgramDetail>  programList(Map map);

    List<PsProgramDetail>  programList1(Map map);

    List<Program>  programLimitList(Map map);

    boolean addProgram(Map map) throws Exception;

    List<Program> inProgramId(String likeProgram);

    Program getProgramId(long id);

    Program getProgram(long id);
    
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

    List<Map<String,Object>> yearCost(String year);

    List<ProgramFile> getFileListByWhere(Map<String,Object> map);

    /**
     * 导出项目列表
     * @param map
     * @return
     */
    List<Map<String,Object>> exportProgramList(Map<String,Object> map);

    /**
     * 定时任务 发送龙信小秘书提示流程节点信息
     * @throws Exception
     */
    void programTask() throws Exception;

    /**
     * D定时批量修改 预警天数
     * @throws Exception
     */
    void warningDaysTask() throws Exception;
    /**
     * 单个修改项目信息时 修改 预警天数(与定时批量修改时公用)
     * @throws Exception
     */
    void warningTask(Program program);
}
