package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramWarning;
import com.longfor.itserver.mapper.ProgramWarningMapper;
import com.longfor.itserver.service.IProgramWarningService;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.util.AccountUitl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangs on 2017/8/31.
 */
@Service("ProgramWarningService")
public class ProgramWarningServiceImpl extends AdminBaseService<ProgramWarning> implements IProgramWarningService {
    @Autowired
    private ProgramWarningMapper programWarningMapper;
    @Override
    public List<ProgramWarning> getListByWhere(Map<String,Object> map) {
        return programWarningMapper.getListByWhere(map);
    }

    @Override
    public ProgramWarning addProgramWarning(Map<String,Object> map) {
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        ProgramWarning programWarning = JSONObject.toJavaObject(json, ProgramWarning.class);
        programWarning.setCreateTime(new Date());
        programWarningMapper.insert(programWarning);
        return programWarning;
    }
}
