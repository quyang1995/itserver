package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.enums.BugLevelEnum;
import com.longfor.itserver.common.enums.BugStatusEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.entity.BugChangeLog;
import com.longfor.itserver.entity.BugFile;
import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.entity.FeedBack;
import com.longfor.itserver.mapper.BugChangeLogMapper;
import com.longfor.itserver.mapper.BugFileMapper;
import com.longfor.itserver.mapper.BugInfoMapper;
import com.longfor.itserver.mapper.FeedBackMapper;
import com.longfor.itserver.service.IBugInfoService;
import com.longfor.itserver.service.base.AdminBaseService;
import net.mayee.commons.TimeUtils;
import net.mayee.commons.helper.APIHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private FeedBackMapper feedBackMapper;

    /**
     * bug列表
     *
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
     *
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
     *
     * @param map
     * @return
     */
    @Override
    @Transactional
    public boolean addBug(Map map) {
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        BugInfo bugInfo = JSONObject.toJavaObject(json, BugInfo.class);
        bugInfo.setStatus(BugStatusEnum.PENDING.getCode());
        //获取发起人信息
        AccountLongfor draftedAccountLongfor = adsHelper.getAccountLongforByLoginName(bugInfo.getModifiedAccountId());
        if (draftedAccountLongfor != null) {
            bugInfo.setDraftedAccountId(bugInfo.getModifiedAccountId());
            bugInfo.setDraftedEmployeeCode(Long.parseLong(draftedAccountLongfor.getPsEmployeeCode()));
            bugInfo.setDraftedEmployeeName(draftedAccountLongfor.getName());
            bugInfo.setDraftedFullDeptPath(draftedAccountLongfor.getPsDeptFullName());
        }
        //获取指派人信息
        AccountLongfor callonAccountLongfor = adsHelper.getAccountLongforByLoginName(bugInfo.getCallonAccountId());
        if (callonAccountLongfor != null) {
            bugInfo.setCallonEmployeeCode(Long.parseLong(callonAccountLongfor.getPsEmployeeCode()));
            bugInfo.setCallonEmployeeName(callonAccountLongfor.getName());
            bugInfo.setCallonFullDeptPath(callonAccountLongfor.getPsDeptFullName());
        }
        bugInfo.setCreateTime(TimeUtils.getTodayByDateTime());
        bugInfo.setModifiedTime(TimeUtils.getTodayByDateTime());
        bugInfoMapper.insert(bugInfo);

        //添加文件
        String filelist = json.getString("fileList");
        if(StringUtils.isNotBlank(filelist)) {
            List<BugFile> fileList = JSONArray.parseArray(filelist, BugFile.class);
            if (fileList != null && fileList.size() > 0) {
                for (BugFile file : fileList) {
                    file.setBugId(bugInfo.getId());
                    file.setCreateTime(TimeUtils.getTodayByDateTime());
                }
                bugFileMapper.insertList(fileList);
            }
        }
        /*添加BUG修改日志*/

        Map<String, Object> logMap = getChangeLog(null, bugInfo);
        List<String> textList = (List) logMap.get("logList");
        for (String log : textList) {
            BugChangeLog bugChangeLog = new BugChangeLog();
            bugChangeLog.setBugId(bugInfo.getId());
            bugChangeLog.setBefDescp(bugInfo.getDescp());
            bugChangeLog.setType((Integer) logMap.get("type"));
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
     *
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
        bugInfo.setStatus(selectOneBugInfo.getStatus());

        //获取发起人信息
        AccountLongfor draftedAccountLongfor = adsHelper.getAccountLongforByLoginName(bugInfo.getModifiedAccountId());
//        if (draftedAccountLongfor != null) {
//            bugInfo.setDraftedAccountId(bugInfo.getModifiedAccountId());
//            bugInfo.setDraftedEmployeeCode(Long.parseLong(draftedAccountLongfor.getPsEmployeeCode()));
//            bugInfo.setDraftedEmployeeName(draftedAccountLongfor.getName());
//            bugInfo.setDraftedFullDeptPath(draftedAccountLongfor.getPsDeptFullName());
//        }
        //获取指派人信息
        AccountLongfor callonAccountLongfor = adsHelper.getAccountLongforByLoginName(bugInfo.getCallonAccountId());
        if (callonAccountLongfor != null) {
            bugInfo.setCallonEmployeeCode(Long.parseLong(callonAccountLongfor.getPsEmployeeCode()));
            bugInfo.setCallonEmployeeName(callonAccountLongfor.getName());
            bugInfo.setCallonFullDeptPath(callonAccountLongfor.getPsDeptFullName());
        }

        /*修改文件*/
        String filelist = json.getString("fileList");
        if(StringUtils.isNotBlank(filelist)) {
            List<BugFile> fileList = JSONArray.parseArray(filelist, BugFile.class);
            if (fileList != null && fileList.size() > 0) {
                for (BugFile file : fileList) {
                    file.setBugId(bugInfo.getId());
                    file.setCreateTime(TimeUtils.getTodayByDateTime());
                }
                bugFileMapper.insertList(fileList);
            }
        }

        /*添加BUG修改日志*/

        Map<String, Object> logMap = getChangeLog(selectOneBugInfo, bugInfo);
        List<String> textList = (List) logMap.get("logList");
        List<BugChangeLog> logList = new ArrayList<>();
        for (String log : textList) {
            BugChangeLog bugChangeLog = new BugChangeLog();
            bugChangeLog.setBugId(bugInfo.getId());
            bugChangeLog.setBefDescp(selectOneBugInfo.getDescp());
            bugChangeLog.setType((Integer) logMap.get("type"));
            bugChangeLog.setActionChangeInfo(log);
            bugChangeLog.setModifiedName(draftedAccountLongfor.getName());
            bugChangeLog.setModifiedAccountId(bugInfo.getModifiedAccountId());
            bugChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            bugChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
            logList.add(bugChangeLog);
        }
        if (logList.size() > 0) {
            bugChangeLogMapper.insertList(logList);
        }
        if("".equals(bugInfo.getDescp())){
            bugInfo.setDescp(selectOneBugInfo.getDescp());
        }
        bugInfo.setModifiedTime(TimeUtils.getTodayByDateTime());
        bugInfo.setCreateTime(selectOneBugInfo.getCreateTime());
        bugInfoMapper.updateByPrimaryKey(bugInfo);
        return true;
    }

    public Map getChangeLog(BugInfo oldBug, BugInfo newBug) {
        Map<String, Object> map = new HashMap<>();
        List<String> textList = new ArrayList<String>();
        if (oldBug == null && newBug != null) {
            StringBuilder log = new StringBuilder();
            log.append(newBug.getModifiedName()).
                    append(" 新增了bug信息");
            textList.add(log.toString());
            map.put("type", 2);
            map.put("logList", textList);
            return map;
        }

        if (oldBug != null && newBug != null) {
            if (!Objects.equals(oldBug.getDescp(), newBug.getDescp())) {
                StringBuilder log = new StringBuilder();
                log.append(newBug.getModifiedName()).
                        append(" 修改了bug描述信息");
                textList.add(log.toString());
                map.put("type", 1);
            }
            if (    !Objects.equals(oldBug.getLevel(), newBug.getLevel())
//                    ||
//                    !Objects.equals(oldBug.getStatus(), newBug.getStatus()) ||
//                    !Objects.equals(oldBug.getCallonAccountId(), newBug.getCallonAccountId())
                    ) {
                StringBuilder log = new StringBuilder();
//                if (!Objects.equals(oldBug.getStatus(), newBug.getStatus())) {
//
//                    log.append(newBug.getModifiedName()).
//                            append("将 状态 由[").
//                            append(BugStatusEnum.getByCode(oldBug.getStatus()).getText()).
//                            append("]更改为[").
//                            append(BugStatusEnum.getByCode(newBug.getStatus()).getText()).
//                            append("]");
//                    textList.add(log.toString());
//                }
                if (!Objects.equals(oldBug.getLevel(), newBug.getLevel())) {
                    if (StringUtils.isNotBlank(log)) {
                        log.append(",");
                    } else {
                        log.append(newBug.getModifiedName())
                                .append(" ");
                    }
                    log.append("将 优先级 由[").
                            append(BugLevelEnum.getByCode(oldBug.getLevel()).getText()).
                            append("]更改为[").
                            append(BugLevelEnum.getByCode(newBug.getLevel()).getText()).
                            append("]");
                    textList.add(log.toString());
                }
//                if (!Objects.equals(oldBug.getCallonAccountId(), newBug.getCallonAccountId())) {
//                    if (StringUtils.isNotBlank(log)) {
//                        log.append(",");
//                    } else {
//                        log.append(newBug.getModifiedName());
//                    }
//                    log.append("将 指派人 由[").
//                            append(oldBug.getCallonEmployeeName()).
//                            append("]更改为[").
//                            append(newBug.getCallonEmployeeName()).
//                            append("]");
//                    textList.add(log.toString());
//                }

                map.put("type", 2);
            }

            if (!(Objects.equals(oldBug.getBrower(), newBug.getBrower()) && Objects.equals(oldBug.getLikeProduct(), newBug.getLikeProduct())
                    && Objects.equals(oldBug.getLikeProgram(), newBug.getLikeProgram()) && Objects.equals(oldBug.getCcAccount(), newBug.getCcAccount())
                    && Objects.equals(oldBug.getName(), newBug.getName()) && Objects.equals(oldBug.getRelationId(), newBug.getRelationId())
                    && Objects.equals(oldBug.getRelationType(), newBug.getRelationType()) && Objects.equals(oldBug.getReproductionStep(), newBug.getReproductionStep()))) {
                StringBuilder log = new StringBuilder();
                log.append(newBug.getModifiedName()).
                        append(" 修改了bug基础信息");
                textList.add(log.toString());
                map.put("type", 2);
            }
            map.put("logList", textList);
        }
        return map;
    }

    public String statusLog(BugInfo oldBug, BugInfo newBug) {
            StringBuilder log = new StringBuilder();
            if (newBug.getStatus() != null && !Objects.equals(oldBug.getStatus(), newBug.getStatus())) {
                log.append(newBug.getModifiedName()).
                        append(" 将 状态 由[").
                        append(BugStatusEnum.getByCode(oldBug.getStatus()).getText()).
                        append("]更改为[").
                        append(BugStatusEnum.getByCode(newBug.getStatus()).getText()).
                        append("]");
            }

            else if (newBug.getCallonAccountId() != null&& !Objects.equals(oldBug.getCallonAccountId(), newBug.getCallonAccountId())) {
                log.append(newBug.getModifiedName())
                        .append(" 将 指派人 由[").
                        append(oldBug.getCallonEmployeeName()).
                        append("]更改为[").
                        append(newBug.getCallonEmployeeName()).
                        append("]");
            }

            return  log.toString();
        }

        @Override
        @Transactional
        public boolean updateStatus (Map < String, String > paramsMap){

            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(paramsMap);
            String modifiedAccountId = jsonObject.getString("modifiedAccountId");
            String modifiedName = jsonObject.getString("modifiedName");
            Long bugId = Long.valueOf(jsonObject.getString("bugId"));
            Integer status = Integer.valueOf(jsonObject.getString("status"));
            BugInfo oldBug = bugInfoMapper.selectByPrimaryKey(bugId);
            BugInfo newBug = new BugInfo();
            newBug.setModifiedAccountId(modifiedAccountId);
            newBug.setModifiedName(modifiedName);
            newBug.setStatus(status);
            /*添加BUG修改日志*/
            String log = statusLog(oldBug, newBug);
            if(StringUtils.isNotBlank(log)) {
                BugChangeLog bugChangeLog = new BugChangeLog();
                bugChangeLog.setBugId(oldBug.getId());
                bugChangeLog.setBefDescp(oldBug.getDescp());
                bugChangeLog.setType(2);
                bugChangeLog.setActionChangeInfo(log.toString());
                bugChangeLog.setModifiedName(modifiedName);
                bugChangeLog.setModifiedAccountId(modifiedAccountId);
                bugChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
                bugChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
                bugChangeLogMapper.insert(bugChangeLog);
            }
            oldBug.setStatus(newBug.getStatus());
            oldBug.setModifiedTime(TimeUtils.getTodayByDateTime());
            bugInfoMapper.updateByPrimaryKey(oldBug);
            FeedBack feedBack = feedBackMapper.selectByPrimaryKey(oldBug.getFeedBackId());
            //如果BUG有对应反馈异常  更新反馈异常的状态
            if (feedBack != null) {
                feedBack.setStatus(status);
                feedBack.setModifiedTime(TimeUtils.getTodayByDateTime());
                feedBackMapper.updateByPrimaryKey(feedBack);
            }
            return true;
        }


        @Override
        @Transactional
        public boolean updateCallon (Map < String, String > paramsMap){
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(paramsMap);
            String modifiedAccountId = jsonObject.getString("modifiedAccountId");
            String modifiedName = jsonObject.getString("modifiedName");
            Long bugId = Long.valueOf(jsonObject.getString("bugId"));
            String callonAccountId = jsonObject.getString("callonAccountId");
            //更新前对象
            BugInfo oldBug = bugInfoMapper.selectByPrimaryKey(bugId);
            //指派人更改后对象 用于更新
//        BugInfo newBug = bugInfoMapper.selectByPrimaryKey(bugId);
            BugInfo newBug = new BugInfo();
            AccountLongfor accountLongfor = adsHelper.getAccountLongforByLoginName(callonAccountId);
            newBug.setCallonAccountId(callonAccountId);
            newBug.setCallonEmployeeCode(Long.valueOf(accountLongfor.getPsEmployeeCode()));
            newBug.setCallonEmployeeName(accountLongfor.getName());
            newBug.setCallonFullDeptPath(accountLongfor.getPsDeptFullName());
            newBug.setModifiedName(modifiedName);
            newBug.setModifiedAccountId(modifiedAccountId);
        /*添加BUG修改日志*/
            String log = statusLog(oldBug, newBug);
            if(StringUtils.isNotBlank(log)) {
                BugChangeLog bugChangeLog = new BugChangeLog();
                bugChangeLog.setBugId(oldBug.getId());
                bugChangeLog.setBefDescp(oldBug.getDescp());
                bugChangeLog.setType(2);
                bugChangeLog.setActionChangeInfo(log.toString());
                bugChangeLog.setModifiedName(modifiedName);
                bugChangeLog.setModifiedAccountId(modifiedAccountId);
                bugChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
                bugChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
                bugChangeLogMapper.insert(bugChangeLog);
            }

            oldBug.setCallonAccountId(callonAccountId);
            oldBug.setCallonEmployeeCode(Long.valueOf(accountLongfor.getPsEmployeeCode()));
            oldBug.setCallonEmployeeName(accountLongfor.getName());
            oldBug.setCallonFullDeptPath(accountLongfor.getPsDeptFullName());
            oldBug.setModifiedTime(TimeUtils.getTodayByDateTime());
            bugInfoMapper.updateByPrimaryKey(oldBug);
            FeedBack feedBack = feedBackMapper.selectByPrimaryKey(oldBug.getFeedBackId());
            //如果 BUG有对应的反馈异常，更新反馈异常的信息
            if (feedBack != null) {
                feedBack.setContactAccountId(callonAccountId);
                feedBack.setContactEmployeeCode(oldBug.getCallonEmployeeCode());
                feedBack.setContactEmployeeName(oldBug.getCallonEmployeeName());
                feedBack.setContactFullDeptPath(oldBug.getCallonFullDeptPath());
                feedBack.setModifiedTime(TimeUtils.getTodayByDateTime());
                feedBackMapper.updateByPrimaryKey(feedBack);
            }

            return true;
        }

        @Override
        public Map statusList (HttpServletRequest request, Map < String, String > paramsMap){
         /* 生成查询用Example */
            ELExample elExample = new ELExample(request, BugInfo.class);
            PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);

            Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
            List<BugInfo> list = bugInfoMapper.statusList(paramsMap);
            resultMap.put("list", list);
            resultMap.put(APIHelper.PAGE_NUM, elExample.getPageNum());
            resultMap.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
            resultMap.put(APIHelper.TOTAL, new PageInfo(list).getTotal());
            return resultMap;
        }



}