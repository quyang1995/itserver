package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramEmployee;
import com.longfor.itserver.mapper.ProgramEmployeeMapper;
import com.longfor.itserver.mapper.ProgramMapper;
import com.longfor.itserver.service.IProgramService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private ADSHelper adsHelper;

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
        if(program.getDescp().length() > 3000){
            program.setDescp(program.getDescp().substring(0,3000));
        }
        programMapper.insert(program);

        //项目责任人
        String jsonArrPl = json.get("personLiableList").toString();
        if(!"".equals(jsonArrPl)){
            getAccountLongfor(program,jsonArrPl,"0");
        }
        //产品经理
        String jsonArrPm = json.get("productManagerList").toString();
        if(!"".equals(jsonArrPm)) {
            getAccountLongfor(program, jsonArrPm, "1");
        }
        //项目经理
        String jsonArrPMl = json.get("programManagerList").toString();
        if(!"".equals(jsonArrPMl)) {
            getAccountLongfor(program, jsonArrPMl, "2");
        }
        //开发人员
        String jsonArrDe = json.get("developerList").toString();
        if(!"".equals(jsonArrDe)) {
            getAccountLongfor(program, jsonArrDe, "3");
        }
        //UED人员
        String jsonArrUed = json.get("uedList").toString();
        if(!"".equals(jsonArrUed)) {
            getAccountLongfor(program, jsonArrUed, "4");
        }
        return true;
    }

    public boolean getAccountLongfor(Program program,String str,String id){
        String[] strArr = str.split(",");
        for(int i = 0; i < strArr.length; i++){
            String loginName = strArr[i].toString();
            AccountLongfor accountLongfor = adsHelper.getAccountLongforByLoginName(loginName);
            if(accountLongfor != null){
                ProgramEmployee pe = new ProgramEmployee();
                pe.setProgramId(program.getId());
                pe.setAccountId(accountLongfor.getLoginName());
                pe.setEmployeeCode(Long.parseLong(accountLongfor.getPsEmployeeCode()));
                pe.setEmployeeName(accountLongfor.getName());
                pe.setFullDeptPath(accountLongfor.getPsDeptFullName());
                if("0".equals(id)){
                    pe.setEmployeeType(AvaStatusEnum.LIABLEAVA.getCode());
                }else if("1".equals(id)){
                    pe.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                    pe.setEmployeeTypeId(new Long(AvaStatusEnum.PRODAVA.getCode()));
                    pe.setEmployeeTypeText(AvaStatusEnum.PRODAVA.getText());
                }else if("2".equals(id)){
                    pe.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                    pe.setEmployeeTypeId(new Long(AvaStatusEnum.PROGAVA.getCode()));
                    pe.setEmployeeTypeText(AvaStatusEnum.PROGAVA.getText());
                }else if("3".equals(id)){
                    pe.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                    pe.setEmployeeTypeId(new Long(AvaStatusEnum.DEVEAVA.getCode()));
                    pe.setEmployeeTypeText(AvaStatusEnum.DEVEAVA.getText());
                }else if("4".equals(id)){
                    pe.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                    pe.setEmployeeTypeId(new Long(AvaStatusEnum.UEDAVA.getCode()));
                    pe.setEmployeeTypeText(AvaStatusEnum.UEDAVA.getText());
                }
                pe.setStatus(AvaStatusEnum.AVA.getCode());
                programEmployeeMapper.insert(pe);
            }
        }
        return true;
    }

    @Override
    public List<Program> inProgramId(String likeProgram){
        return programMapper.inProgramId(likeProgram);
    }

    @Override
    public Program getProgramId(long id){
        return programMapper.getProgramId(id);
    }
}
