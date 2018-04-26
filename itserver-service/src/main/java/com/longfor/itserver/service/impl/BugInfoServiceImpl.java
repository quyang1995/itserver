package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.eds.helper.EDSHelper;
import com.longfor.itserver.common.enums.*;
import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.common.util.StringUtil;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.entity.ps.PsBugInfoDetail;
import com.longfor.itserver.entity.ps.PsBugTimeTask;
import com.longfor.itserver.esi.impl.LongforServiceImpl;
import com.longfor.itserver.mapper.*;
import com.longfor.itserver.service.IBugInfoService;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.util.AccountUitl;
import jodd.props.Props;
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
    private EDSHelper edsHelper;
    @Autowired
    private BugChangeLogMapper bugChangeLogMapper;
    @Autowired
    private BugFileMapper bugFileMapper;
    @Autowired
    private FeedBackMapper feedBackMapper;
    @Autowired
    private LongforServiceImpl longforServiceImpl;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProgramMapper programMapper;
    @Autowired
    private ProgramEmployeeServiceImpl programEmployeeServiceImpl;

    /**
     * bug列表
     *
     * @param map
     * @return
     */
    @Override
    public List<PsBugInfoDetail> bugList(Map map) {
        List<PsBugInfoDetail> bugList = bugInfoMapper.bugList(map);
        for (PsBugInfoDetail bug:bugList) {
            this.setBugInfo(bug);
        }
        return bugList;
    }

    private void setBugInfo(PsBugInfoDetail bug){
        //归属产品/项目 名称
        String relationName = "";
        if (bug.getRelationType().equals(1) && bug.getRelationId()!=null) {
            Product prod = productMapper.selectByPrimaryKey(bug.getRelationId());
            if(prod!=null){
                relationName = prod.getName();
            }
        } else if (bug.getRelationType().equals(2) && bug.getRelationId()!=null) {
            Program prom = programMapper.selectByPrimaryKey(bug.getRelationId());
            if(prom!=null){
                relationName = prom.getName();
            }
        }
        bug.setRelationName(relationName);
		/* 责任人 */
		if(bug.getRelationId()!=null){
            Map map = new HashMap();
            map.put("programId", bug.getRelationId());
            map.put("employeeType", AvaStatusEnum.LIABLEAVA.getCode());
            List<ProgramEmployee> personLiableList = programEmployeeServiceImpl.selectTypeList(map);
            bug.setProductManagerList(personLiableList);
        }
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
        Integer accountType = AccountUitl.getAccountType(map);

        //获取发起人信息
        AccountLongfor draftedAccountLongfor =
                AccountUitl.getAccountByAccountType(accountType,bugInfo.getModifiedAccountId(),adsHelper,edsHelper);
        if (draftedAccountLongfor != null) {
            bugInfo.setDraftedAccountId(bugInfo.getModifiedAccountId());
            if(StringUtils.isNotBlank(draftedAccountLongfor.getPsEmployeeCode())){
                bugInfo.setDraftedEmployeeCode(Long.parseLong(draftedAccountLongfor.getPsEmployeeCode()));
            }
            bugInfo.setDraftedEmployeeName(draftedAccountLongfor.getName());
            bugInfo.setDraftedFullDeptPath(draftedAccountLongfor.getPsDeptFullName());
        }
        //获取指派人信息
        AccountLongfor callonAccountLongfor =
                AccountUitl.getAccountByAccountTypes(bugInfo.getCallonAccountId(),adsHelper,edsHelper);
        if (callonAccountLongfor != null) {
            if(StringUtils.isNotBlank(callonAccountLongfor.getPsEmployeeCode())){
                bugInfo.setCallonEmployeeCode(Long.parseLong(callonAccountLongfor.getPsEmployeeCode()));
            }
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

        /*新增BUG消息提醒*/
        if(!bugInfo.getDraftedAccountId().equals(bugInfo.getCallonAccountId())) {
            Map paramMap = longforServiceImpl.param();
            Props props = JoddHelper.getInstance().getJoddProps();
            String openUrl = props.getValue("openUrl.bugPath");
            paramMap.put("ruser", bugInfo.getCallonAccountId());
            JSONObject paramMapCont = (JSONObject) paramMap.get("content");
            paramMapCont.put("topTitle", "BUG提醒");
            paramMapCont.put("centerWords", "您收到一条BUG：【" + bugInfo.getName() + "】");
            paramMapCont.put("openUrl", openUrl + "?VIEWSHOW_NOHEAD&bugid=" + bugInfo.getId() + "&isweb=true" + "&accountId=" + bugInfo.getCallonAccountId());
            longforServiceImpl.msgcenter(paramMap);
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
            bugChangeLog.setAccountType(bugInfo.getAccountType());
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
        Integer accountType = AccountUitl.getAccountType(map);
        //获取指派人信息
        AccountLongfor callonAccountLongfor =
                AccountUitl.getAccountByAccountTypes(bugInfo.getCallonAccountId(),adsHelper,edsHelper);
        if (callonAccountLongfor != null) {
            if(StringUtils.isNotBlank(callonAccountLongfor.getPsEmployeeCode())){
                bugInfo.setCallonEmployeeCode(Long.parseLong(callonAccountLongfor.getPsEmployeeCode()));
            }
            bugInfo.setCallonEmployeeName(callonAccountLongfor.getName());
            bugInfo.setCallonFullDeptPath(callonAccountLongfor.getPsDeptFullName());
        }
        Map<String, Object> logMap = getChangeLog(selectOneBugInfo, bugInfo);

        //获取最后修改人
        AccountLongfor draftedAccountLongfor =
                AccountUitl.getAccountByAccountType(accountType,bugInfo.getModifiedAccountId(),adsHelper,edsHelper);
        if (draftedAccountLongfor != null) {
            bugInfo.setModifiedAccountId(bugInfo.getModifiedAccountId());
            bugInfo.setModifiedName(draftedAccountLongfor.getName());
            bugInfo.setModifiedTime(TimeUtils.getTodayByDateTime());
        }
        //获取指派人信息
        if (callonAccountLongfor != null) {
            selectOneBugInfo.setCallonAccountId(bugInfo.getCallonAccountId());
            if(StringUtils.isNotBlank(callonAccountLongfor.getPsEmployeeCode())){
                selectOneBugInfo.setCallonEmployeeCode(Long.parseLong(callonAccountLongfor.getPsEmployeeCode()));
            }
            selectOneBugInfo.setCallonEmployeeName(callonAccountLongfor.getName());
            selectOneBugInfo.setCallonFullDeptPath(callonAccountLongfor.getPsDeptFullName());
        }


        selectOneBugInfo.setName(bugInfo.getName());
        selectOneBugInfo.setDescp(bugInfo.getDescp());
        selectOneBugInfo.setBrower(bugInfo.getBrower());
        selectOneBugInfo.setBrowerInfo(bugInfo.getBrowerInfo());
        selectOneBugInfo.setRelationType(bugInfo.getRelationType());
        selectOneBugInfo.setRelationId(bugInfo.getRelationId());
        selectOneBugInfo.setLevel(bugInfo.getLevel());
        selectOneBugInfo.setHopeDate(bugInfo.getHopeDate());
        selectOneBugInfo.setLikeProduct(bugInfo.getLikeProduct());
        selectOneBugInfo.setLikeProgram(bugInfo.getLikeProgram());
        selectOneBugInfo.setAccountType(bugInfo.getAccountType());
        selectOneBugInfo.setModifiedTime(TimeUtils.getTodayByDateTime());
        bugInfoMapper.updateByPrimaryKey(selectOneBugInfo);

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
            bugChangeLog.setAccountType(bugInfo.getAccountType());
            logList.add(bugChangeLog);
        }
        if (logList.size() > 0) {
            bugChangeLogMapper.insertList(logList);
        }
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
                /*修改需求描述消息提醒*/
                if(!newBug.getModifiedAccountId().equals(newBug.getCallonAccountId())) {
                    Map paramMap = longforServiceImpl.param();
                    Props props = JoddHelper.getInstance().getJoddProps();
                    String openUrl = props.getValue("openUrl.bugPath");
                    paramMap.put("ruser", newBug.getCallonAccountId());
                    JSONObject paramMapCont = (JSONObject) paramMap.get("content");
                    paramMapCont.put("topTitle", "BUG提醒");
                    paramMapCont.put("centerWords", "您跟进的BUG发生变更：【" + newBug.getName() + "】");
                    paramMapCont.put("openUrl", openUrl + "?VIEWSHOW_NOHEAD&bugid=" + newBug.getId() + "&isweb=true" + "&accountId=" + newBug.getCallonAccountId());
                    longforServiceImpl.msgcenter(paramMap);
                }
            }
            if (    !Objects.equals(oldBug.getLevel(), newBug.getLevel())
                    || !Objects.equals(oldBug.getCallonAccountId(), newBug.getCallonAccountId())
                    ) {
                StringBuilder log = new StringBuilder();
                if (!Objects.equals(oldBug.getLevel(), newBug.getLevel())) {
                    if (StringUtils.isNotBlank(log)) {
                        log.append(",");
                    } else {
                        log.append(newBug.getModifiedName())
                                .append(" ");
                    }
                    log.append("将 严重程度 由[").
                            append(BugLevelEnum.getByCode(oldBug.getLevel()).getText()).
                            append("]更改为[").
                            append(BugLevelEnum.getByCode(newBug.getLevel()).getText()).
                            append("]");
                    textList.add(log.toString());
                }
                if (!Objects.equals(oldBug.getCallonAccountId(), newBug.getCallonAccountId())) {
                    if (StringUtils.isNotBlank(log)) {
                        log.append(",");
                    } else {
                        log.append(newBug.getModifiedName());
                    }
                    log.append("将 指派给 由[").
                            append(oldBug.getCallonEmployeeName()).
                            append("]更改为[").
                            append(newBug.getCallonEmployeeName()).
                            append("]");
                    textList.add(log.toString());

                    /*更新BUG指派给消息提醒*/
                    if(!newBug.getModifiedAccountId().equals(newBug.getCallonAccountId())) {
                        Map paramMap = longforServiceImpl.param();
                        Props props = JoddHelper.getInstance().getJoddProps();
                        String openUrl = props.getValue("openUrl.bugPath");
                        paramMap.put("ruser", newBug.getCallonAccountId());
                        JSONObject paramMapCont = (JSONObject) paramMap.get("content");
                        paramMapCont.put("topTitle", "BUG提醒");
                        paramMapCont.put("centerWords", "您收到一条BUG：【" + newBug.getName() + "】");
                        paramMapCont.put("openUrl", openUrl + "?VIEWSHOW_NOHEAD&bugid=" + newBug.getId() + "&isweb=true" + "&accountId=" + newBug.getCallonAccountId());
                        longforServiceImpl.msgcenter(paramMap);
                    }

                }

                map.put("type", 2);
            }

            if (!(Objects.equals(oldBug.getBrower(), newBug.getBrower())
                    && Objects.equals(oldBug.getLikeProduct(), newBug.getLikeProduct())
                    && Objects.equals(oldBug.getLikeProgram(), newBug.getLikeProgram())
                    && Objects.equals(oldBug.getCcAccount(), newBug.getCcAccount())
                    && Objects.equals(oldBug.getName(), newBug.getName())
                    &&Objects.equals(oldBug.getHopeDate(),newBug.getHopeDate())
                    && Objects.equals(oldBug.getRelationId(), newBug.getRelationId())
                    && Objects.equals(oldBug.getRelationType(), newBug.getRelationType()))) {
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

            /*更新需求状态消息提醒*/
            if(!newBug.getModifiedAccountId().equals(oldBug.getDraftedAccountId())) {
                Map paramMap = longforServiceImpl.param();
                Props props = JoddHelper.getInstance().getJoddProps();
                String openUrl = props.getValue("openUrl.bugPath");
                paramMap.put("ruser", oldBug.getDraftedAccountId());
                JSONObject paramMapCont = (JSONObject) paramMap.get("content");
                paramMapCont.put("topTitle", "BUG提醒");
                paramMapCont.put("centerWords", "您提交的BUG：【" + oldBug.getName() + "】处理状态从[" + DemandStatusEnum.getByCode(oldBug.getStatus()).getText() + "]变更为[" + DemandStatusEnum.getByCode(newBug.getStatus()).getText() + "]");
                paramMapCont.put("openUrl", openUrl + "?VIEWSHOW_NOHEAD&bugid=" + oldBug.getId() + "&isweb=true" + "&accountId=" + oldBug.getDraftedAccountId());
                longforServiceImpl.msgcenter(paramMap);
            }
        }

        else if (newBug.getCallonAccountId() != null&& !Objects.equals(oldBug.getCallonAccountId(), newBug.getCallonAccountId())) {
            log.append(newBug.getModifiedName())
                    .append(" 将 指派给 由[").
                    append(oldBug.getCallonEmployeeName()).
                    append("]更改为[").
                    append(newBug.getCallonEmployeeName()).
                    append("]");

            /*更新BUG指派给消息提醒*/
            if(!newBug.getModifiedAccountId().equals(newBug.getCallonAccountId())){
                Map paramMap = longforServiceImpl.param();
                Props props = JoddHelper.getInstance().getJoddProps();
                String openUrl = props.getValue("openUrl.bugPath");
                paramMap.put("ruser",newBug.getCallonAccountId());
                JSONObject paramMapCont = (JSONObject) paramMap.get("content");
                paramMapCont.put("topTitle","BUG提醒");
                paramMapCont.put("centerWords","您收到一条BUG：【"+ oldBug.getName() +"】");
                paramMapCont.put("openUrl",openUrl + "?VIEWSHOW_NOHEAD&bugid="+oldBug.getId()+"&isweb=true"+"&accountId="+newBug.getCallonAccountId());
                longforServiceImpl.msgcenter(paramMap);
            }
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
        Integer accountType = AccountUitl.getAccountType(paramsMap);
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
            bugChangeLog.setAccountType(accountType);
            bugChangeLogMapper.insert(bugChangeLog);
        }
        oldBug.setStatus(newBug.getStatus());
        oldBug.setModifiedTime(TimeUtils.getTodayByDateTime());
        oldBug.setAccountType(accountType);
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
        Integer accountType = AccountUitl.getAccountType(paramsMap);
        //更新前对象
        BugInfo oldBug = bugInfoMapper.selectByPrimaryKey(bugId);
        //指派人更改后对象 用于更新
//        BugInfo newBug = bugInfoMapper.selectByPrimaryKey(bugId);
        BugInfo newBug = new BugInfo();
        AccountLongfor accountLongfor = AccountUitl.getAccountByAccountTypes(callonAccountId,adsHelper,edsHelper);
        newBug.setCallonAccountId(callonAccountId);
        newBug.setCallonEmployeeCode(StringUtil.getLongValue(accountLongfor.getPsEmployeeCode()));
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
            bugChangeLog.setAccountType(accountType);
            bugChangeLogMapper.insert(bugChangeLog);
        }

        oldBug.setCallonAccountId(callonAccountId);
        oldBug.setCallonEmployeeCode(StringUtil.getLongValue(accountLongfor.getPsEmployeeCode()));
        oldBug.setCallonEmployeeName(accountLongfor.getName());
        oldBug.setCallonFullDeptPath(accountLongfor.getPsDeptFullName());
        oldBug.setModifiedTime(TimeUtils.getTodayByDateTime());
        oldBug.setAccountType(accountType);
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

    @Override
    public List<PsBugTimeTask> bugTask(){
        List<PsBugTimeTask> bugList = bugInfoMapper.bugTask();
        for(int i = 0; i < bugList.size(); i++){
            PsBugTimeTask bt = bugList.get(i);
            if(bt.getAmount() > 0){
                Map paramMap = longforServiceImpl.param();
                Props props = JoddHelper.getInstance().getJoddProps();
                String openUrl = props.getValue("openUrl.bugListPath");
                paramMap.put("ruser",bt.getCallonAccountId());
                JSONObject paramMapCont = (JSONObject) paramMap.get("content");
                paramMapCont.put("topTitle","BUG提醒");
                paramMapCont.put("centerWords","您还有"+ bt.getAmount() +"个未完成的BUG");
                paramMapCont.put("openUrl",openUrl);
                longforServiceImpl.msgcenter(paramMap);
            }
        }
        return bugList;
    }

}