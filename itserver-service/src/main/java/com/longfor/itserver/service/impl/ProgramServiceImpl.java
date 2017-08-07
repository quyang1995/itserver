package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramEmployee;
import com.longfor.itserver.mapper.ProgramEmployeeMapper;
import com.longfor.itserver.mapper.ProgramMapper;
import com.longfor.itserver.service.IProgramService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wax
 *         Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("ProgramService")
public class ProgramServiceImpl extends AdminBaseService<Program> implements IProgramService {

    @Autowired
    private ProgramMapper programMapper;

    @Autowired
    private ProgramEmployeeMapper programEmployeeMapper;

    @Override
    public List<Program> programList(Map map){
        return programMapper.programList(map);
    }

    @Override
    public List<Program> programLimitList(Map map){
        return programMapper.programLimitList(map);
    }


    @Override
    public boolean addProgram(Map map){
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        Program program = JSONObject.toJavaObject(json,Program.class);
        programMapper.insert(program);

        //项目人员
        List<ProgramEmployee> programEmployeeList =
                JSONArray.parseArray(json.get("programEmployee").toString(),ProgramEmployee.class);
        for(ProgramEmployee programEmployee : programEmployeeList){
            JSONObject jsonPe = (JSONObject) JSONObject.toJSON(programEmployee);
            ProgramEmployee pe = JSONObject.toJavaObject(jsonPe,ProgramEmployee.class);
            pe.setProgramId(program.getId());
            programEmployeeMapper.insert(pe);
        }
        return true;
    }
}
