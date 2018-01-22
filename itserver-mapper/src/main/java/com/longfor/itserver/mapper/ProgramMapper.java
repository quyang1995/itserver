package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.Program;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface ProgramMapper extends BeeMapper<Program> {

    List<Program> programList(Map map);

    List<Program>  programLimitList(Map map);

    List<Program> inProgramId(String likeProgram);

    Program getProgramId(long id);

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

    List<Map<String,Object>> yearCost();

}