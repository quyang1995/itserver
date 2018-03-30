package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.enums.ProgramApprovalStatusEnum;
import com.longfor.itserver.common.enums.ProgramStatusNewEnum;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramWarning;
import com.longfor.itserver.mapper.ProgramMapper;
import com.longfor.itserver.mapper.ProgramWarningMapper;
import com.longfor.itserver.service.IProgramService;
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
    @Autowired
    private ProgramMapper programMapper;
    @Autowired
    private IProgramService programService;
    @Override
    public List<ProgramWarning> getListByWhere(Map<String,Object> map) {
        return programWarningMapper.getListByWhere(map);
    }

    @Override
    public ProgramWarning getOneByWhere(Map<String,Object> map) {
        return programWarningMapper.getOneByWhere(map);
    }

    @Override
    public ProgramWarning addProgramWarning(Map<String,Object> map) {
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        ProgramWarning programWarning = JSONObject.toJavaObject(json, ProgramWarning.class);
        Program program = programMapper.selectByPrimaryKey(programWarning.getProgramId());
        //当前节点状态为审核通过时，预警的节点值是，当前项目状态下个节点
        //若不是审核通过，预警的是项目当前节点的值
        if(program.getApprovalStatus()== ProgramApprovalStatusEnum.SHTG.getCode()){
            if(program.getProgramStatus() == ProgramStatusNewEnum.WLX.getCode()){
                programWarning.setProgramStatus(ProgramStatusNewEnum.LX.getCode());
            }
            if(program.getProgramStatus() == ProgramStatusNewEnum.LX.getCode()){
                programWarning.setProgramStatus(ProgramStatusNewEnum.DPS.getCode());
            }
            if(program.getProgramStatus() == ProgramStatusNewEnum.DPS.getCode()){
                programWarning.setProgramStatus(ProgramStatusNewEnum.CPPS.getCode());
            }
            if(program.getProgramStatus() == ProgramStatusNewEnum.CPPS.getCode()){
                programWarning.setProgramStatus(ProgramStatusNewEnum.KFPS.getCode());
            }
            if(program.getProgramStatus() == ProgramStatusNewEnum.KFPS.getCode()){
                programWarning.setProgramStatus(ProgramStatusNewEnum.CSPS.getCode());
            }
            if(program.getProgramStatus() == ProgramStatusNewEnum.CSPS.getCode()){
                programWarning.setProgramStatus(ProgramStatusNewEnum.SXPS.getCode());
            }
            if(program.getProgramStatus() == ProgramStatusNewEnum.SXPS.getCode()){
                programWarning.setProgramStatus(ProgramStatusNewEnum.HDFB.getCode());
            }
            if(program.getProgramStatus() == ProgramStatusNewEnum.HDFB.getCode()){
                programWarning.setProgramStatus(ProgramStatusNewEnum.QMTG.getCode());
            }
            if(program.getProgramStatus() == ProgramStatusNewEnum.QMTG.getCode()){
                programWarning.setProgramStatus(ProgramStatusNewEnum.XMFP.getCode());
            }
            if(program.getProgramStatus() == ProgramStatusNewEnum.XMFP.getCode()){
                programWarning.setProgramStatus(ProgramStatusNewEnum.WC.getCode());
            }
        }
        programWarning.setCreateTime(new Date());
        programWarningMapper.insert(programWarning);
        //实时更新预警日期
        programService.warningTask(program);
        return programWarning;
    }

}
