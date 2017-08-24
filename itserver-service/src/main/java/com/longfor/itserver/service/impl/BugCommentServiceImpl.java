package com.longfor.itserver.service.impl;

import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.entity.BugComment;
import com.longfor.itserver.mapper.BugCommentMapper;
import com.longfor.itserver.service.IBugCommentService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public Map<String,Object> add(Map paramsMap) {
        String accountId = (String)paramsMap.get("accountId");
        Map map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
        AccountLongfor accountLongfor =  adsHelper.getAccountLongforByLoginName(accountId);

        if(accountLongfor == null) {
            map = CommonUtils.getResultMapByBizEnum(BizEnum.E1001);
            return map;
        }

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

        boolean flag = bugCommentMapper.add(bugComment);
        if(!flag) {
            return  CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
        }

        return map;
    }
}
