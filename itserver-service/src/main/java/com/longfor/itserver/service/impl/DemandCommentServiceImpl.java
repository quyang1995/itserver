package com.longfor.itserver.service.impl;


import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.entity.DemandComment;
import com.longfor.itserver.mapper.DemandCommentMapper;
import com.longfor.itserver.service.IDemandCommentService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wax
 *         Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("DemandCommentService")
public class DemandCommentServiceImpl extends AdminBaseService<DemandComment> implements IDemandCommentService {

    @Autowired
    DemandCommentMapper demandCommentMapper;

    @Autowired
    private ADSHelper adsHelper;

    @Override
    public List<DemandComment> getListById(DemandComment demandComment) {

        List<DemandComment> demandCommentList= demandCommentMapper.selectListById(demandComment);

        return demandCommentList;
    }

    @Override
    public boolean add(Map paramMap)throws NullPointerException{
        String accountId = (String)paramMap.get("accountId");
        String demandId = (String)paramMap.get("demandId");
        String parentId = (String)paramMap.get("parentId");
        String content = (String)paramMap.get("content");
        String levelNum =(String)paramMap.get("levelNum");
        String ip = (String)paramMap.get("ip");
        int replyType = AvaStatusEnum.REPLY_ONE.getCode();
        int status = AvaStatusEnum.AVA.getCode();

        DemandComment demandComment = new DemandComment();
        demandComment.setDemandId(Long.parseLong(demandId));
        demandComment.setAccountId(accountId);
        demandComment.setParentId(Long.parseLong(parentId));
        demandComment.setContent(content);
        demandComment.setLevelNum(Integer.parseInt(levelNum));
        demandComment.setIp(ip);
        demandComment.setReplyType(replyType);
        demandComment.setStatus(status);
        AccountLongfor accountLongfor =  adsHelper.getAccountLongforByLoginName(demandComment.getAccountId());
        boolean flag = false;
        if(accountLongfor!= null) {
            demandComment.setEmployeeCode(Long.parseLong(accountLongfor.getPsEmployeeCode()));
            demandComment.setEmployeeName(accountLongfor.getName());
            demandComment.setFullDeptPath(accountLongfor.getPsDeptFullName());

            synchronized (this){
                int count =  demandCommentMapper.add(demandComment);
                if(count==1)
                    flag = true;
            }
        }
        return flag;
    };
}
