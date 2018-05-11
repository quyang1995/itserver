package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.enums.ProgramWarningEnum;
import com.longfor.itserver.common.enums.PublicTypeEnum;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramEmployeeChangeLog;
import com.longfor.itserver.entity.ProgramWarning;
import com.longfor.itserver.mapper.ProgramEmployeeChangeLogMapper;
import com.longfor.itserver.mapper.ProgramMapper;
import com.longfor.itserver.mapper.ProgramWarningMapper;
import com.longfor.itserver.service.IProgramService;
import com.longfor.itserver.service.IProgramWarningService;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.util.AccountUitl;
import net.mayee.commons.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private ProgramEmployeeChangeLogMapper programEmployeeChangeLogMapper;

    @Override
    public List<ProgramWarning> getListByWhere(Map<String,Object> map) {
        return programWarningMapper.getListByWhere(map);
    }

    @Override
    public ProgramWarning getOneByWhere(Map<String,Object> map) {
        return programWarningMapper.getOneByWhere(map);
    }

    @Override
    public int warningSum(String accountId) {
        return programWarningMapper.warningSum(accountId);
    }

    @Override
    public int warningRemarksSum(String accountId) {
        return programWarningMapper.warningRemarksSum(accountId);
    }

    @Override
    public ProgramWarning addProgramWarning(Map<String,Object> map) {
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        ProgramWarning programWarning = JSONObject.toJavaObject(json, ProgramWarning.class);
        Program program = programMapper.selectByPrimaryKey(programWarning.getProgramId());
        Date now = new Date();
        programWarning.setProgramStatus(program.getProgramStatus());
        programWarning.setCreateTime(now);
        programWarning.setModifiedTime(now);
        programWarning.setModifiedAccountId(programWarning.getCreateAccountId());
        programWarning.setModifiedName(programWarning.getCreateName());
        programWarningMapper.insert(programWarning);

        //生成变动日志
        List<String> changeLogTextList = getChangeLogText(null, programWarning);
        /*添加日志*/
        for(String text : changeLogTextList){
            ProgramEmployeeChangeLog employeeChangeLog = new ProgramEmployeeChangeLog();
            employeeChangeLog.setProgramId(program.getId());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setActionChangeInfo(text);
            employeeChangeLog.setModifiedAccountId(program.getModifiedAccountId());
            employeeChangeLog.setModifiedName(program.getModifiedName());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setAccountType(0);
            programEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);
        }
        //实时更新预警日期
        programService.warningTask(program);
        return programWarning;
    }

    @Override
    public ProgramWarning updateProgramWarning(Map<String,Object> map) {
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        //修改后的项目风险备注信息
        ProgramWarning programWarning = JSONObject.toJavaObject(json, ProgramWarning.class);
        programWarning.setModifiedTime(new Date());
        //原先的项目风险备注信息
        ProgramWarning oldProgramWarning = programWarningMapper.selectByPrimaryKey(programWarning.getId());
        //修改风险备注信息
        programWarningMapper.updateByPrimaryKeySelective(programWarning);
        //最新的风险备注信息
        programWarning = programWarningMapper.selectByPrimaryKey(programWarning.getId());
        Program program = programService.selectById(programWarning.getProgramId());
        //生成变动日志
        List<String> changeLogTextList = getChangeLogText(oldProgramWarning, programWarning);
        /*添加日志*/
        for(String text : changeLogTextList){
            ProgramEmployeeChangeLog employeeChangeLog = new ProgramEmployeeChangeLog();
            employeeChangeLog.setProgramId(program.getId());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setActionChangeInfo(text);
            employeeChangeLog.setModifiedAccountId(json.getString("modifiedAccountId"));
            employeeChangeLog.setModifiedName(json.getString("modifiedName"));
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setAccountType(0);
            programEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);
        }
        //实时更新预警日期
        programService.warningTask(program);
        return programWarning;
    }

    @Override
    public List<ProgramWarning> warningList(Map<String,Object> map) {
        this.dealMap(map);
        return programWarningMapper.warningList(map);
    }

    @Override
    public int warningListTotal(Map<String,Object> map) {
        this.dealMap(map);
        return programWarningMapper.warningListTotal(map);
    }
    private void dealMap(Map<String,Object> map){
        if(map.get("warning")!=null){
            String warning = map.get("warning").toString();
            if(StringUtils.isNotBlank(warning) && "-1".equals(warning)){
                warning = "0,1,2";
            }
            String [] warningList = warning.split(",");
            map.put("warningList",warningList);
        }
    }

    private List<String> getChangeLogText(ProgramWarning oldProgramWarning, ProgramWarning newProgramWarning) {
        List<String> textList = new ArrayList<>();
        if(oldProgramWarning == null && newProgramWarning != null){
            StringBuilder sb = new StringBuilder();
            sb.append(newProgramWarning.getCreateName())
                    .append(" 新增了风险备注");
            textList.add(sb.toString());
            return textList;
        }
        if(newProgramWarning == null){
            return textList;
        }
        if(!Objects.equals(oldProgramWarning.getRemark(), newProgramWarning.getRemark())
                || !Objects.equals(oldProgramWarning.getWarning(), newProgramWarning.getWarning())){
            StringBuilder sb = new StringBuilder();
            if(!Objects.equals(oldProgramWarning.getWarning(), newProgramWarning.getWarning())){
                if(StringUtils.isNotBlank(sb.toString())){
                    sb.append(",");
                } else {
                    sb.append(newProgramWarning.getModifiedName());
                }
                sb.append(" 将 风险等级 从 [")
                        .append(ProgramWarningEnum.getByCode(oldProgramWarning.getWarning()).getText())
                        .append("] 更新为 [")
                        .append(ProgramWarningEnum.getByCode(newProgramWarning.getWarning()).getText())
                        .append("]");
            }
            if(!Objects.equals(oldProgramWarning.getRemark(), newProgramWarning.getRemark())){
                if(StringUtils.isNotBlank(sb.toString())){
                    sb.append(",");
                } else {
                    sb.append(newProgramWarning.getModifiedName());
                }
                sb.append(" 将 风险内容 从 [")
                        .append(oldProgramWarning.getRemark())
                        .append("] 更新为 [")
                        .append(newProgramWarning.getRemark())
                        .append("]");
            }
            if (!sb.toString().isEmpty()) {
                textList.add(sb.toString());
            }
        }
        return textList;
    }

}
