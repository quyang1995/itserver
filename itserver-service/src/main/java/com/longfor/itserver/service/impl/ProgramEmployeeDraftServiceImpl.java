package com.longfor.itserver.service.impl;

import com.longfor.itserver.entity.ProgramEmployeeDraft;
import com.longfor.itserver.mapper.ProgramEmployeeDraftMapper;
import com.longfor.itserver.service.IProgramEmployeeDraftService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ProgramEmployeeDraftService")
public class ProgramEmployeeDraftServiceImpl extends AdminBaseService<ProgramEmployeeDraft> implements IProgramEmployeeDraftService {

    @Autowired
    private ProgramEmployeeDraftMapper programEmployeeDraftMapper;
    @Override
    public List<ProgramEmployeeDraft> selectTypeList(Map map){
        return programEmployeeDraftMapper.selectTypeList(map);
    }
}

