package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.entity.ProgramEmployee;
import com.longfor.itserver.mapper.base.BeeMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProgramEmployeeMapper extends BeeMapper<ProgramEmployee> {

    List<ProgramEmployee> selectTypeList(Map map);

}