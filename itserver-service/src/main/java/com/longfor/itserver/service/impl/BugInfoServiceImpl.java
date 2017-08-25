package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BugStatusEnum;
import com.longfor.itserver.common.util.DateUtil;
import com.longfor.itserver.entity.BugChangeLog;
import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.mapper.BugChangeLogMapper;
import com.longfor.itserver.mapper.BugInfoMapper;
import com.longfor.itserver.mapper.ProgramMapper;
import com.longfor.itserver.service.IBugInfoService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wax
 *         Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("BugInfoService")
public class BugInfoServiceImpl extends AdminBaseService<BugInfo> implements IBugInfoService {

    @Autowired
    private BugInfoMapper bugInfoMapper;
    @Autowired
    private ADSHelper adsHelper;
    @Autowired
    private BugChangeLogMapper bugChangeLogMapper;

    /**
     * bug列表
     * @param map
     * @return
     */
    @Override
    public List<BugInfo> bugList(Map map) {
        List<BugInfo> bugList = bugInfoMapper.bugList(map);
        return bugList;
    }

    /**
     * 根据ID获取bug基本信息
     * @param id
     * @return
     */
    @Override
    public BugInfo getBugId(long id) {
        BugInfo bugInfo = bugInfoMapper.getBugId(id);
        return bugInfo;
    }


    /**
     * 新增BUG
     * @param map
     * @return
     */
    @Override
    public boolean addBug(Map map) {
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        BugInfo bugInfo = JSONObject.toJavaObject(json, BugInfo.class);
        bugInfo.setStatus(BugStatusEnum.PENDING.getCode());
        //获取指派人信息
        AccountLongfor draftedAccountLongfor = adsHelper.getAccountLongforByLoginName(bugInfo.getDraftedAccountId());
        if(draftedAccountLongfor != null){
            bugInfo.setDraftedEmployeeCode(Long.parseLong(draftedAccountLongfor.getPsEmployeeCode()));
            bugInfo.setDraftedEmployeeName(draftedAccountLongfor.getName());
            bugInfo.setDraftedFullDeptPath(draftedAccountLongfor.getPsDeptFullName());
        }
        //获取发起人信息
        AccountLongfor callonAccountLongfor = adsHelper.getAccountLongforByLoginName(bugInfo.getCallonAccountId());
        if(callonAccountLongfor != null){
            bugInfo.setCallonEmployeeCode(Long.parseLong(callonAccountLongfor.getPsEmployeeCode()));
            bugInfo.setCallonEmployeeName(callonAccountLongfor.getName());
            bugInfo.setCallonFullDeptPath(callonAccountLongfor.getPsDeptFullName());
        }
        bugInfoMapper.insert(bugInfo);
        return true;
    }

    /**
     * 修改BUG
     * @param map
     * @return
     */
    @Override
    @Transactional
    public boolean updateBug(Map map) {
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        BugInfo bugInfo = JSONObject.toJavaObject(json, BugInfo.class);
        BugInfo selectOneBugInfo = bugInfoMapper.selectByPrimaryKey(bugInfo.getId());
        if (null == selectOneBugInfo) {
            return false;
        }
        bugInfo.setStatus(BugStatusEnum.PENDING.getCode());
        //获取指派人信息
        AccountLongfor draftedAccountLongfor = adsHelper.getAccountLongforByLoginName(bugInfo.getDraftedAccountId());
        if(draftedAccountLongfor != null){
            bugInfo.setDraftedEmployeeCode(Long.parseLong(draftedAccountLongfor.getPsEmployeeCode()));
            bugInfo.setDraftedEmployeeName(draftedAccountLongfor.getName());
            bugInfo.setDraftedFullDeptPath(draftedAccountLongfor.getPsDeptFullName());
        }
        //获取发起人信息
        AccountLongfor callonAccountLongfor = adsHelper.getAccountLongforByLoginName(bugInfo.getCallonAccountId());
        if(callonAccountLongfor != null){
            bugInfo.setCallonEmployeeCode(Long.parseLong(callonAccountLongfor.getPsEmployeeCode()));
            bugInfo.setCallonEmployeeName(callonAccountLongfor.getName());
            bugInfo.setCallonFullDeptPath(callonAccountLongfor.getPsDeptFullName());
        }
        /*添加BUG修改日志*/
        addLog(map);
        bugInfoMapper.updateByPrimaryKey(bugInfo);
        return true;
    }


    public boolean addLog(Map paramsMap) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(paramsMap);
        BugChangeLog bugChangeLog = JSONObject.toJavaObject(jsonObject ,BugChangeLog.class);
        bugChangeLog.setBugId(Long.valueOf(jsonObject.getString("id")));
        BugInfo bugInfo =  bugInfoMapper.selectByPrimaryKey(bugChangeLog.getBugId());
        bugChangeLog.setType(2);
        if(!bugInfo.getDescp().equals(jsonObject.getString("descp"))) bugChangeLog.setType(1);
        bugChangeLog.setBefDescp(jsonObject.getString("descp"));
        String logInfo = jsonObject.getString("modifiedName")+ " 在 "+ DateUtil.getCurrentTime(new Date()) +" 更新BUG信息" ;
        bugChangeLog.setActionChangeInfo(logInfo);
        bugChangeLog.setCreateTime(new Date());
        bugChangeLogMapper.insertUseGeneratedKeys(bugChangeLog);
        return true;
    }
}
