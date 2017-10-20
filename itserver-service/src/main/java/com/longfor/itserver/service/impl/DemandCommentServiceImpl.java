package com.longfor.itserver.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.entity.Demand;
import com.longfor.itserver.entity.DemandComment;
import com.longfor.itserver.mapper.DemandCommentMapper;
import com.longfor.itserver.mapper.DemandMapper;
import com.longfor.itserver.service.IDemandCommentService;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.util.AccountUitl;
import net.mayee.commons.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wax
 *         Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("DemandCommentService")
public class DemandCommentServiceImpl extends AdminBaseService<DemandComment> implements IDemandCommentService {
    public  final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private DemandCommentMapper demandCommentMapper;

    @Autowired
    private ADSHelper adsHelper;
    @Autowired
    private DemandMapper demandMapper;


    @Override
    public Map<String, Object> add(Map<String,String> paramsMap){
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(paramsMap);
        DemandComment demandComment = JSONObject.toJavaObject(jsonObject,DemandComment.class);
        Integer accountType = Integer.parseInt(jsonObject.getString("accountType"));
        //回复人验证
        AccountLongfor accountLongfor =
                AccountUitl.getAccountByAccountType(accountType,demandComment.getAccountId(),adsHelper);
        if(accountLongfor == null) {
            return CommonUtils.getResultMapByBizEnum(BizEnum.E1017,"该回复人");
        }
        //bugId验证
        Long demandId = Long.parseLong(paramsMap.get("demandId"));
        Demand demand = demandMapper.selectByPrimaryKey(demandId);
        if(demand == null){
            return CommonUtils.getResultMapByBizEnum(BizEnum.E1017,"该需求");
        }

        //levelNum
        int levelNum = 1;
        DemandComment lastBugComment = demandCommentMapper.getMaxLevelNum(demandId);
        if(lastBugComment != null){
            levelNum = lastBugComment.getLevelNum() + 1;
        }

        if(accountLongfor!=null&&StringUtils.isNotBlank(accountLongfor.getPsEmployeeCode())){
            demandComment.setEmployeeCode(Long.parseLong(accountLongfor.getPsEmployeeCode()));
        }
        demandComment.setEmployeeName(accountLongfor.getName());
        demandComment.setFullDeptPath(accountLongfor.getPsDeptFullName());
        demandComment.setLevelNum(levelNum);
        demandComment.setParentId(0L);
        demandComment.setReplyType(0);
        demandComment.setStatus(AvaStatusEnum.AVA.getCode());
        demandComment.setCreateTime(TimeUtils.getTodayByDateTime());
        demandComment.setModifiedTime(TimeUtils.getTodayByDateTime());
        demandCommentMapper.insert(demandComment);

        return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);

    };
}
