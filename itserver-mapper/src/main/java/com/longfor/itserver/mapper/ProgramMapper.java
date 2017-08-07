package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.Program;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface ProgramMapper extends BeeMapper<Program> {

    List<Program> programList(Map map);

    List<Program>  programLimitList(Map map);

}