package com.longfor.itserver.service.impl;

import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.entity.BugComment;
import com.longfor.itserver.mapper.BugCommentMapper;
import com.longfor.itserver.service.IBugCommentService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

/**
 * @author wax
 *         Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("BugCommentService")
public class BugCommentServiceImpl extends AdminBaseService<BugComment> implements IBugCommentService {

    @Autowired
    private BugCommentMapper bugCommentMapper;

    @Autowired
    private ADSHelper adsHelper;

    @Override
    public List<BugComment> list(BugComment bugComment) {
        List<BugComment> list = this.select(bugComment);
        return list;
    }

    @Override
    @Transactional
    public boolean add(Map paramsMap) {
        String accountId = (String)paramsMap.get("accountId");


        boolean flag =false;
        AccountLongfor accountLongfor =  adsHelper.getAccountLongforByLoginName(accountId);
        if(accountLongfor!=null) {
           // synchronized (this) {
                Long bugId =Long.valueOf((String)paramsMap.get("bugId"));
                Long parentId=Long.parseLong((String)paramsMap.get("parentId"));
                String content =(String)paramsMap.get("content");
                Long employeeCode = Long.parseLong(accountLongfor.getPsEmployeeCode());
                String employeeName = accountLongfor.getName();
                String fullDeptPath = accountLongfor.getPsDeptFullName();

                Integer levelNum = Integer.parseInt((String) paramsMap.get("levelNum"));
                String ip = (String) paramsMap.get("ip");
                Integer replyType = AvaStatusEnum.REPLY_ONE.getCode();
                Integer status = AvaStatusEnum.AVA.getCode();

                BugComment bugComment = new BugComment();

                bugComment.setBugId(bugId);
                bugComment.setParentId(parentId);
                bugComment.setContent(content);
                bugComment.setAccountId(accountId);
                bugComment.setEmployeeCode(employeeCode);
                bugComment.setEmployeeName(employeeName);
                bugComment.setFullDeptPath(fullDeptPath);
                bugComment.setLevelNum(levelNum);
                bugComment.setIp(ip);
                bugComment.setReplyType(replyType);
                bugComment.setStatus(status);

                flag = bugCommentMapper.add(bugComment);
           // }
        }
        return flag;
    }
}
