package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.entity.ProgramEmployee;
import com.longfor.itserver.mapper.base.BeeMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProgramEmployeeMapper extends BeeMapper<ProgramEmployee> {

    List<ProgramEmployee> selectTypeList(Map map);

    /**
     * 查询责任人，产品经理，以及测试或开发人员
     * @param map
     * @return
     */
    List<ProgramEmployee> selectPersonList(Map map);

}