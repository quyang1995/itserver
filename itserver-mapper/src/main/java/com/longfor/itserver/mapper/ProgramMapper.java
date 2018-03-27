package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ps.PsProgramDetail;
import com.longfor.itserver.mapper.base.BeeMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProgramMapper extends BeeMapper<Program> {

    List<Program> getListByMap(Map map);

    List<PsProgramDetail> programList(Map map);

    List<PsProgramDetail> programList1(Map map);

    List<Program>  programLimitList(Map map);

    List<Program> inProgramId(String likeProgram);

    Program getProgramId(long id);

    Program getProgram(Map map);

    List<Program> productIdList(Map map);

    /**
     * 获取最新的newCode
     * @return
     */
    String getNewCode();

    int getCountByProductId(Map<String,Object> map);

    List<Program> myFollowProgram(Map<String,Object> map);

    Integer myFollowProgramTotal(Map<String,Object> map);

    List<Map<String,Object>> changeTopFive(Map<String,Object> map);

    /**
     * 年度费用统计
     * @param year
     * @return
     */
    List<Map<String,Object>> yearCost(@Param(value = "year")String year);

    /**
     * 导出项目列表
     * @param map
     * @return
     */
    List<Map<String,Object>> exportProgramList(Map<String,Object> map);

    Integer updateWarningDays();

}