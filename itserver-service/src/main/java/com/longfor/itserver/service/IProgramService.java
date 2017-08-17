package com.longfor.itserver.service;

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
}
