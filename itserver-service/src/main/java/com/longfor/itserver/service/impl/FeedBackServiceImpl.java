package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.eds.helper.EDSHelper;
import com.longfor.itserver.common.enums.*;
import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.entity.ps.PsFeedBackStatus;
import com.longfor.itserver.esi.impl.LongforServiceImpl;
import com.longfor.itserver.mapper.*;
import com.longfor.itserver.service.IFeedBackService;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.util.AccountUitl;
import jodd.props.Props;
import net.mayee.commons.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.*;

/**
 * @author wax Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("FeedBackService")
public class FeedBackServiceImpl extends AdminBaseService<FeedBack> implements IFeedBackService {

    @Autowired
    private FeedBackMapper feedBackMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private BugInfoMapper bugInfoMapper;

    @Autowired
    private DemandMapper demandMapper;

    @Autowired
    private ADSHelper adsHelper;

    @Autowired
    private EDSHelper edsHelper;

    @Autowired
    private BugChangeLogMapper bugChangeLogMapper;

    @Autowired
    private DemandChangeLogMapper demandChangeLogMapper;
    @Autowired
    private FeedBackFileMapper feedBackFileMapper;
    @Autowired
    private BugFileMapper bugFileMapper;
    @Autowired
    private DemandFileMapper demandFileMapper;
    @Autowired
    private LongforServiceImpl longforServiceImpl;


    @Override
    public List<FeedBack> feedBackList(Map map) {
        return feedBackMapper.feedBackList(map);
    }

    @Transactional
    @Override
    public Map<String, Object> addFeedBack(Map map) {
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        FeedBack feedBack = JSONObject.toJavaObject(json, FeedBack.class);
        /* 确认反馈渠道 */
        boolean hadAccount = true;
        AccountLongfor accountLongfor = new AccountLongfor();
        String modifiedAccountId = feedBack.getModifiedAccountId();
        String feedbackPhone = feedBack.getFeedbackPhone();

        Integer accountType = AccountUitl.getAccountType(map);

        //二者不可都为空
        if (StringUtils.isBlank(modifiedAccountId) && StringUtils.isBlank(feedbackPhone)) {
            return CommonUtils.getResultMapByBizEnum(BizEnum.E1201, "modifiedAccountId", "feedbackPhone");
        }
        if (StringUtils.isNotBlank(modifiedAccountId)) {
            //通过账户ID查询账户基本信息
            //获取发起人信息
            accountLongfor =
                    AccountUitl.getAccountByAccountType(accountType,feedBack.getModifiedAccountId(),adsHelper,edsHelper);
//            accountLongfor = adsHelper.getAccountLongforByLoginName(feedBack.getModifiedAccountId());
            if (accountLongfor == null) {//账户不存在
                return CommonUtils.getResultMapByBizEnum(BizEnum.E1001);
            }
            if(StringUtils.isNotBlank(accountLongfor.getPsEmployeeCode())){
                feedBack.setModifiedEmployeeCode(Long.parseLong(accountLongfor.getPsEmployeeCode()));
            }
            feedBack.setModifiedName(accountLongfor.getName());
            feedBack.setModifiedFullDeptPath(accountLongfor.getPsDeptFullName());

        } else if (StringUtils.isNotBlank(feedbackPhone)) { //外部反馈
            //如果是外部反馈，除了提供手机号，还要提供姓名
            if (StringUtils.isBlank(feedBack.getFeedbackName())) {
                return CommonUtils.getResultMapByBizEnum(BizEnum.E1203);
            }
            hadAccount = false;
        }

        //多余数据清空
        if(hadAccount){
            feedBack.setFeedbackPhone("");
            feedBack.setFeedbackName("");
        }else{
            feedBack.setModifiedAccountId("");
        }

        //查询产品信息得到接口人信息和产品名称
        Product obj = new Product();
        obj.setId(feedBack.getProductId());
        Product product = productMapper.selectOne(obj);
        feedBack.setName(product.getName());
        feedBack.setProductCode(product.getCode());
        //反馈类型：0=功能异常，1=功能建议
        if(feedBack.getType()==0){
            feedBack.setContactAccountId(product.getContactAccountId1());
            feedBack.setContactEmployeeCode(product.getContactEmployeeCode1());
            feedBack.setContactEmployeeName(product.getContactEmployeeName1());
            feedBack.setContactFullDeptPath(product.getContactFullDeptPath1());
        }
        if(feedBack.getType()==1){
            feedBack.setContactAccountId(product.getContactAccountId());
            feedBack.setContactEmployeeCode(product.getContactEmployeeCode());
            feedBack.setContactEmployeeName(product.getContactEmployeeName());
            feedBack.setContactFullDeptPath(product.getContactFullDeptPath());
        }
        feedBack.setCreateTime(TimeUtils.getTodayByDateTime());
        feedBack.setModifiedTime(TimeUtils.getTodayByDateTime());
        //状态
        feedBack.setStatus(FeedBackStatusEnum.PENDING.getCode());
        //合并BUG描述和复现步骤
        if (feedBack.getType().equals(0)) {
            String descp = "";
            descp += feedBack.getProblemDescp().substring(0, feedBack.getProblemDescp().length() <= 500 ? feedBack.getProblemDescp().length() : 500);
            descp += "<br>" + feedBack.getReproductionStep().substring(0, feedBack.getReproductionStep().length() <= 500 ? feedBack.getReproductionStep().length() : 500);
            feedBack.setProblemDescp(descp);
            feedBack.setReproductionStep(null);
        }
        if("4".equals((feedBack.getChannel()).toString())){
            feedBack.setType(0);
            feedBack.setAccountType(2);
        }else{
            feedBack.setAccountType(accountType);
        }
        feedBackMapper.insert(feedBack);

        //添加文件
        List<FeedBackFile> fileList = JSONArray.parseArray(json.getString("fileList"), FeedBackFile.class);
        if (fileList != null && fileList.size() > 0) {
            for (FeedBackFile file : fileList) {
                file.setFeedBackId(feedBack.getId());
                file.setCreateTime(TimeUtils.getTodayByDateTime());
            }
            feedBackFileMapper.insertList(fileList);
        }

        //0:新增BUG   1:新增需求
        if (feedBack.getType().equals(0)) {
            BugInfo bugInfo = new BugInfo();
            bugInfo.setFeedBackId(feedBack.getId());
            bugInfo.setRelationId(product.getId());
            bugInfo.setRelationType(1);
            bugInfo.setName(feedBack.getProblemTitle());
            bugInfo.setDescp(feedBack.getProblemDescp());
//			bugInfo.setReproductionStep(feedBack.getReproductionStep());
            bugInfo.setBrower(feedBack.getSysEnvironment());
            bugInfo.setBrowerInfo(feedBack.getBrowerInfo());
            bugInfo.setHopeDate(new Date());
            bugInfo.setChannel(feedBack.getChannel());

            //指派人
            bugInfo.setCallonAccountId(product.getContactAccountId1());
            bugInfo.setCallonEmployeeCode(product.getContactEmployeeCode1());
            bugInfo.setCallonEmployeeName(product.getContactEmployeeName1());
            bugInfo.setCallonFullDeptPath(product.getContactFullDeptPath1());

            //起草人
            if (hadAccount) {
                bugInfo.setDraftedAccountId(accountLongfor.getLoginName());
                if(StringUtils.isNotBlank(accountLongfor.getPsEmployeeCode())){
                    bugInfo.setDraftedEmployeeCode(Long.parseLong(accountLongfor.getPsEmployeeCode()));
                }
                bugInfo.setDraftedEmployeeName(accountLongfor.getName());
                bugInfo.setDraftedFullDeptPath(accountLongfor.getPsDeptFullName());
            }

            bugInfo.setLevel(BugLevelEnum.MID_LEVEL.getCode());
            bugInfo.setStatus(feedBack.getStatus());
            bugInfo.setModifiedAccountId(feedBack.getModifiedAccountId());
            bugInfo.setModifiedName(feedBack.getModifiedName());
            bugInfo.setCreateTime(TimeUtils.getTodayByDateTime());
            bugInfo.setModifiedTime(TimeUtils.getTodayByDateTime());
            bugInfo.setFeedbackPhone(feedBack.getFeedbackPhone());
            bugInfo.setFeedbackName(feedBack.getFeedbackName());
            bugInfo.setAccountType(feedBack.getAccountType());
            bugInfoMapper.insert(bugInfo);

            //添加文件
            List<BugFile> bugFileList = JSONArray.parseArray(json.getString("fileList"), BugFile.class);
            if (bugFileList != null && bugFileList.size() > 0) {
                for (BugFile file : bugFileList) {
                    file.setBugId(bugInfo.getId());
                    file.setCreateTime(TimeUtils.getTodayByDateTime());
                }
                bugFileMapper.insertList(bugFileList);
            }


            /*新增BUG消息提醒*/
            if(!modifiedAccountId.equals(bugInfo.getCallonAccountId())){
                Map paramMap = longforServiceImpl.param();
                Props props = JoddHelper.getInstance().getJoddProps();
                String openUrl = props.getValue("openUrl.bugPath");
                paramMap.put("ruser",bugInfo.getCallonAccountId());
                JSONObject paramMapCont = (JSONObject) paramMap.get("content");
                paramMapCont.put("topTitle","BUG提醒");
                paramMapCont.put("centerWords","您收到一条BUG：【"+ bugInfo.getName() +"】");
                paramMapCont.put("openUrl",openUrl + "?VIEWSHOW_NOHEAD&bugid="+bugInfo.getId()+"&isweb=true"+"&accountId="+bugInfo.getCallonAccountId());
                longforServiceImpl.msgcenter(paramMap);
            }

            //添加日志
            Map<String, Object> logMap = getChangeLog(null, bugInfo);
            @SuppressWarnings("unchecked")
            List<String> textList = (List<String>) logMap.get("logList");
            for (String log : textList) {
                BugChangeLog bugChangeLog = new BugChangeLog();
                bugChangeLog.setBugId(bugInfo.getId());
                bugChangeLog.setBefDescp(bugInfo.getDescp());
                bugChangeLog.setType((Integer) logMap.get("type"));
                bugChangeLog.setActionChangeInfo(log);
                bugChangeLog.setModifiedName(bugInfo.getModifiedName());
                bugChangeLog.setModifiedAccountId(bugInfo.getModifiedAccountId());
                bugChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
                bugChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
                bugChangeLog.setAccountType(accountType);
                bugChangeLogMapper.insert(bugChangeLog);
            }
        } else if (feedBack.getType().equals(1)) {
            Demand demand = new Demand();
            demand.setFeedBackId(feedBack.getId());
            demand.setRelationId(product.getId());
            demand.setRelationType(1);
            demand.setName(feedBack.getProblemTitle());
            demand.setDescp(feedBack.getProblemDescp());
            demand.setHopeDate(new Date());
            demand.setChannel(feedBack.getChannel());

            //指派人
            demand.setCallonAccountId(product.getContactAccountId());
            demand.setCallonEmployeeCode(product.getContactEmployeeCode());
            demand.setCallonEmployeeName(product.getContactEmployeeName());
            demand.setCallonFullDeptPath(product.getContactFullDeptPath());

            //起草人
            if (hadAccount) {
                demand.setDraftedAccountId(accountLongfor.getLoginName());
                if(StringUtils.isNotBlank(accountLongfor.getPsEmployeeCode())){
                    demand.setDraftedEmployeeCode(Long.parseLong(accountLongfor.getPsEmployeeCode()));
                }
                demand.setDraftedEmployeeName(accountLongfor.getName());
                demand.setDraftedFullDeptPath(accountLongfor.getPsDeptFullName());
            }
            demand.setLevel(DemandLevelEnum.MID_LEVEL.getCode());
            demand.setStatus(feedBack.getStatus());
            demand.setModifiedAccountId(feedBack.getModifiedAccountId());
            demand.setModifiedName(feedBack.getModifiedName());
            demand.setCreateTime(TimeUtils.getTodayByDateTime());
            demand.setModifiedTime(TimeUtils.getTodayByDateTime());
            demand.setFeedbackPhone(feedBack.getFeedbackPhone());
            demand.setFeedbackName(feedBack.getFeedbackName());
            demand.setAccountType(feedBack.getAccountType());
            demandMapper.insert(demand);
            //添加文件
            List<DemandFile> demamdFileList = JSONArray.parseArray(json.getString("fileList"), DemandFile.class);
            if(demamdFileList!= null && demamdFileList.size()>0) {
                for (DemandFile demandFile : demamdFileList) {
                    demandFile.setDemandId(demand.getId());
                    demandFile.setCreateTime(TimeUtils.getTodayByDateTime());
                }
                demandFileMapper.insertList(demamdFileList);
            }


            /*新增需求消息提醒*/
            if(!modifiedAccountId.equals(demand.getCallonAccountId())) {
                Map paramMap = longforServiceImpl.param();
                Props props = JoddHelper.getInstance().getJoddProps();
                String openUrl = props.getValue("openUrl.demandPath");
                paramMap.put("ruser", demand.getCallonAccountId());
                JSONObject paramMapCont = (JSONObject) paramMap.get("content");
                paramMapCont.put("topTitle", "需求提醒");
                paramMapCont.put("centerWords", "您收到一条需求：【" + demand.getName() + "】");
                paramMapCont.put("openUrl", openUrl + "?VIEWSHOW_NOHEAD&reqid=" + demand.getId() + "&isweb=true" + "&accountId=" + demand.getCallonAccountId());
                longforServiceImpl.msgcenter(paramMap);
            }

            //添加日志
            Map<String, Object> logMap = getChangeLog(demand, null);
            @SuppressWarnings("unchecked")
            List<String> textList = (List<String>) logMap.get("logList");
            for (String log : textList) {
                DemandChangeLog demandChangeLog = new DemandChangeLog();
                demandChangeLog.setDemandId(demand.getId());
                demandChangeLog.setBefDescp(demand.getDescp());
                demandChangeLog.setType((Integer) logMap.get("type"));
                demandChangeLog.setActionChangeInfo(log);
                demandChangeLog.setModifiedName(demand.getModifiedName());
                demandChangeLog.setModifiedAccountId(demand.getModifiedAccountId());
                demandChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
                demandChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
                demandChangeLog.setAccountType(accountType);
                demandChangeLogMapper.insert(demandChangeLog);
            }
        }

        return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
    }


    @Override
    public FeedBack getFeedBackId(long id) {
        return feedBackMapper.getFeedBackId(id);
    }

    @Override
    public Map countStatus(Map map) {
        Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        PsFeedBackStatus status = feedBackMapper.countStatus(map);
        resultMap.put("status", status);
        return resultMap;
    }


    public Map<String, Object> getChangeLog(Demand newDemand, BugInfo newBug) {
        Map<String, Object> map = new HashMap<>();
        List<String> textList = new ArrayList<String>();
        StringBuilder log = new StringBuilder();
        if (newBug == null && newDemand != null) {
            log.append(newDemand.getModifiedName()).
                    append(" 新增了需求信息");
        }
        if (newDemand == null && newBug != null) {
            log.append(newBug.getModifiedName()).
                    append(" 新增了bug信息");
        }
        textList.add(log.toString());
        map.put("type", 2);
        map.put("logList", textList);
        return map;
    }

}
