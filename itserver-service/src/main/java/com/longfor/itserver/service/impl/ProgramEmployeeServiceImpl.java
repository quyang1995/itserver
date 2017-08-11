package com.longfor.itserver.service.impl;

import com.longfor.itserver.entity.ProgramEmployee;
import com.longfor.itserver.mapper.ProgramEmployeeMapper;
import com.longfor.itserver.mapper.ProgramMapper;
import com.longfor.itserver.service.IProgramEmployeeService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wax
 *         Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("ProgramEmployeeService")
public class ProgramEmployeeServiceImpl extends AdminBaseService<ProgramEmployee> implements IProgramEmployeeService {

    @Autowired
    private ProgramEmployeeMapper programEmployeeMapper;

    @Override
    public List<ProgramEmployee> selectTypeList(Map map){
        return programEmployeeMapper.selectTypeList(map);
    }
}
