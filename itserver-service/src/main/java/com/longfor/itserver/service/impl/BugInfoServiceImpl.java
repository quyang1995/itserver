package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.BugStatusEnum;
import com.longfor.itserver.entity.BugChangeLog;
import com.longfor.itserver.entity.BugFile;
import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.mapper.BugChangeLogMapper;
import com.longfor.itserver.mapper.BugFileMapper;
import com.longfor.itserver.mapper.BugInfoMapper;
import com.longfor.itserver.service.IBugInfoService;
import com.longfor.itserver.service.base.AdminBaseService;
import net.mayee.commons.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    private BugFileMapper bugFileMapper;

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
        //获取发起人信息
        AccountLongfor draftedAccountLongfor = adsHelper.getAccountLongforByLoginName(bugInfo.getModifiedAccountId());
        if(draftedAccountLongfor != null){
            bugInfo.setDraftedAccountId(bugInfo.getModifiedAccountId());
            bugInfo.setDraftedEmployeeCode(Long.parseLong(draftedAccountLongfor.getPsEmployeeCode()));
            bugInfo.setDraftedEmployeeName(draftedAccountLongfor.getName());
            bugInfo.setDraftedFullDeptPath(draftedAccountLongfor.getPsDeptFullName());
        }
        //获取指派人信息
        AccountLongfor callonAccountLongfor = adsHelper.getAccountLongforByLoginName(bugInfo.getCallonAccountId());
        if(callonAccountLongfor != null){
            bugInfo.setCallonEmployeeCode(Long.parseLong(callonAccountLongfor.getPsEmployeeCode()));
            bugInfo.setCallonEmployeeName(callonAccountLongfor.getName());
            bugInfo.setCallonFullDeptPath(callonAccountLongfor.getPsDeptFullName());
        }
        bugInfo.setCreateTime(TimeUtils.getTodayByDateTime());
        bugInfo.setModifiedTime(TimeUtils.getTodayByDateTime());
        bugInfoMapper.insert(bugInfo);

        //添加文件
        List<BugFile> fileList = JSONArray.parseArray(json.getString("fileList"), BugFile.class);
        if(fileList!= null && fileList.size()>0) {
            for (BugFile file : fileList) {
                file.setBugId(bugInfo.getId());
                file.setCreateTime(TimeUtils.getTodayByDateTime());
            }
            bugFileMapper.insertList(fileList);
        }

        /*添加BUG修改日志*/

        Map<String,Object> logMap = getChangeLog(null,bugInfo);
        List<String> textList = (List)logMap.get("logList");
        for (String log:textList){
            BugChangeLog bugChangeLog = new BugChangeLog();
            bugChangeLog.setBugId(bugInfo.getId());
            bugChangeLog.setBefDescp(bugInfo.getDescp());
            bugChangeLog.setType((Integer)logMap.get("type"));
            bugChangeLog.setActionChangeInfo(log);
            bugChangeLog.setModifiedName(draftedAccountLongfor.getName());
            bugChangeLog.setModifiedAccountId(bugInfo.getModifiedAccountId());
            bugChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            bugChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
            bugChangeLogMapper.insert(bugChangeLog);
        }

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

        //获取发起人信息
        AccountLongfor draftedAccountLongfor = adsHelper.getAccountLongforByLoginName(bugInfo.getModifiedAccountId());
        if(draftedAccountLongfor != null){
            bugInfo.setDraftedAccountId(bugInfo.getModifiedAccountId());
            bugInfo.setDraftedEmployeeCode(Long.parseLong(draftedAccountLongfor.getPsEmployeeCode()));
            bugInfo.setDraftedEmployeeName(draftedAccountLongfor.getName());
            bugInfo.setDraftedFullDeptPath(draftedAccountLongfor.getPsDeptFullName());
        }
        //获取指派人信息
        AccountLongfor callonAccountLongfor = adsHelper.getAccountLongforByLoginName(bugInfo.getCallonAccountId());
        if(callonAccountLongfor != null){
            bugInfo.setCallonEmployeeCode(Long.parseLong(callonAccountLongfor.getPsEmployeeCode()));
            bugInfo.setCallonEmployeeName(callonAccountLongfor.getName());
            bugInfo.setCallonFullDeptPath(callonAccountLongfor.getPsDeptFullName());
        }

        /*修改文件*/
        BugFile bugFile = new BugFile();
        bugFile.setBugId(bugInfo.getId());
        bugFileMapper.delete(bugFile);
        List<BugFile> fileList = JSONArray.parseArray(json.getString("fileList"), BugFile.class);
        if(fileList!= null && fileList.size()>0) {
            for (BugFile file : fileList) {
                file.setBugId(bugInfo.getId());
                file.setCreateTime(TimeUtils.getTodayByDateTime());
            }
            bugFileMapper.insertList(fileList);
        }

        /*添加BUG修改日志*/

        Map<String,Object> logMap = getChangeLog(selectOneBugInfo,bugInfo);
        List<String> textList = (List)logMap.get("logList");
        List<BugChangeLog> logList = new ArrayList<>();
        for (String log:textList){
            BugChangeLog bugChangeLog = new BugChangeLog();
            bugChangeLog.setBugId(bugInfo.getId());
            bugChangeLog.setBefDescp(selectOneBugInfo.getDescp());
            bugChangeLog.setType((Integer)logMap.get("type"));
            bugChangeLog.setActionChangeInfo(log);
            bugChangeLog.setModifiedName(draftedAccountLongfor.getName());
            bugChangeLog.setModifiedAccountId(bugInfo.getModifiedAccountId());
            bugChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            bugChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
            logList.add(bugChangeLog);
        }
        bugChangeLogMapper.insertList(logList);
        bugInfo.setModifiedTime(TimeUtils.getTodayByDateTime());

        bugInfoMapper.updateByPrimaryKey(bugInfo);
        return true;
    }

    public Map getChangeLog(BugInfo  oldBug , BugInfo newBug) {
        Map<String,Object> map = new HashMap<>();
        List<String> textList = new ArrayList<String>();
        if(oldBug == null && newBug != null){
            StringBuilder log = new StringBuilder();
            log.append(newBug.getModifiedName()).
                    append("新增了bug信息");
            textList.add(log.toString());
            map.put("type",2);
            map.put("logList",textList);
            return map;
        }

        if(oldBug !=null && newBug != null){
            if(!Objects.equals(oldBug.getDescp(),newBug.getDescp())){
                StringBuilder log = new StringBuilder();
                log.append(newBug.getModifiedName()).
                        append("修改了bug描述信息");
                textList.add(log.toString());
                map.put("type",1);
            }
            if(!Objects.equals(oldBug.getStatus(),newBug.getStatus())||
                    !Objects.equals(oldBug.getLevel(),newBug.getLevel())){
                StringBuilder log = new StringBuilder();
                if (!Objects.equals(oldBug.getStatus(),newBug.getStatus())){

                    log.append(newBug.getModifiedName()).
                            append("将 状态 由[").
                            append(oldBug.getStatus()).
                            append("]更改为[").
                            append(newBug.getStatus()).
                            append("]");
                    textList.add(log.toString());
                }
                if(!Objects.equals(oldBug.getLevel(),newBug.getLevel())){
                    if (StringUtils.isNotBlank(log)){
                        log.append(",");
                    }else{
                        log.append(newBug.getModifiedName());
                    }
                    log.append("将 优先级 由[").
                            append(oldBug.getLevel()).
                            append("]更改为[").
                            append(newBug.getLevel()).
                            append("]");
                    textList.add(log.toString());
                }
                map.put("type",2);
            }

            if(!(Objects.equals(oldBug.getBrower(),newBug.getBrower())&&Objects.equals(oldBug.getLikeProduct(),newBug.getLikeProduct())
                    &&Objects.equals(oldBug.getLikeProgram(),newBug.getLikeProgram())&&Objects.equals(oldBug.getCcAccount(),newBug.getCcAccount())
                    &&Objects.equals(oldBug.getName(),newBug.getName())&&Objects.equals(oldBug.getRelationId(),newBug.getRelationId())
                    &&Objects.equals(oldBug.getRelationType(),newBug.getRelationType())&&Objects.equals(oldBug.getReproductionStep(),newBug.getReproductionStep()))){
                StringBuilder log = new StringBuilder();
                log.append(newBug.getModifiedName()).
                        append("修改了bug基础信息");
                    textList.add(log.toString());
                    map.put("type",2);
            }
            map.put("logList",textList);
        }
        return map;
    }
}
