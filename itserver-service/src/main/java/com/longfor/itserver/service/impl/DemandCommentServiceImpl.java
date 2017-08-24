package com.longfor.itserver.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.entity.DemandComment;
import com.longfor.itserver.mapper.DemandCommentMapper;
import com.longfor.itserver.service.IDemandCommentService;
import com.longfor.itserver.service.base.AdminBaseService;
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
    DemandCommentMapper demandCommentMapper;

    @Autowired
    private ADSHelper adsHelper;


    @Override
    public Map<String, Object> add(Map paramMap){
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(paramMap);
        DemandComment demandComment = JSONObject.toJavaObject(jsonObject,DemandComment.class);

        AccountLongfor accountLongfor =  adsHelper.getAccountLongforByLoginName(demandComment.getAccountId());

        Map<String, Object> map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
        if(accountLongfor == null) {
            map = CommonUtils.getResultMapByBizEnum(BizEnum.E1001);
            return map;
        }

        demandComment.setReplyType(AvaStatusEnum.REPLY_ONE.getCode());
        demandComment.setStatus(AvaStatusEnum.AVA.getCode());
        demandComment.setEmployeeCode(Long.parseLong(accountLongfor.getPsEmployeeCode()));
        demandComment.setEmployeeName(accountLongfor.getName());
        demandComment.setFullDeptPath(accountLongfor.getPsDeptFullName());

        int count =  demandCommentMapper.insert(demandComment);
        if(count !=1 ){
            logger.info("需求评论表新增评论异常");
            map = CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
        }
        return map;
    };
}
