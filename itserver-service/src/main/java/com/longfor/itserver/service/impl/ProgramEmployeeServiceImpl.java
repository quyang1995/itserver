package com.longfor.itserver.service.impl;

import com.longfor.itserver.common.enums.AvaStatusTypeEnum;
import com.longfor.itserver.entity.ProgramEmployee;
import com.longfor.itserver.entity.ProgramEmployeeChangeLog;
import com.longfor.itserver.mapper.ProgramEmployeeChangeLogMapper;
import com.longfor.itserver.mapper.ProgramEmployeeMapper;
import com.longfor.itserver.service.IProgramEmployeeService;
import com.longfor.itserver.service.base.AdminBaseService;
import net.mayee.commons.TimeUtils;
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
    @Autowired
    private ProgramEmployeeChangeLogMapper changeLogMapper;


    @Override
    public List<ProgramEmployee> selectTypeList(Map map){
        return programEmployeeMapper.selectTypeList(map);
    }

    @Override
    public List<ProgramEmployee> selectPersonList(Map map){
        return programEmployeeMapper.selectPersonList(map);
    }

    @Override
    public boolean delEmployee(ProgramEmployee employee,String accountType) {


        List<ProgramEmployee> employeeList = programEmployeeMapper.select(employee);
        /*添加日志*/
        ProgramEmployeeChangeLog changeLog = new ProgramEmployeeChangeLog();
        StringBuilder log = new StringBuilder();
        log.append("[");
        log.append(employeeList.get(0).getEmployeeName());
        Integer i = 1;
        if(employeeList.get(0).getEmployeeTypeId()!=null){
            i = Integer.valueOf(employeeList.get(0).getEmployeeTypeId().toString());
        }
        log.append("("+ AvaStatusTypeEnum.getTextByCode(i)+")");
        log.append("] 退出了项目组。");
        changeLog.setModifiedName(employeeList.get(0).getEmployeeName());
        changeLog.setModifiedAccountId(employeeList.get(0).getAccountId());
        changeLog.setProgramId(employeeList.get(0).getProgramId());
        changeLog.setActionChangeInfo(log.toString());
        changeLog.setCreateTime(TimeUtils.getTodayByDateTime());
        changeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
        changeLog.setAccountType(Integer.parseInt(accountType));
        changeLogMapper.insertUseGeneratedKeys(changeLog);

        programEmployeeMapper.delete(employee);
        return true;
    }
}
