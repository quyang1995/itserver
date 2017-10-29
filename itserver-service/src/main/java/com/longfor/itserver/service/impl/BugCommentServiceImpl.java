package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.entity.BugComment;
import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.mapper.BugCommentMapper;
import com.longfor.itserver.mapper.BugInfoMapper;
import com.longfor.itserver.service.IBugCommentService;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.util.AccountUitl;
import net.mayee.commons.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Service("BugCommentService")
public class BugCommentServiceImpl extends AdminBaseService<BugComment> implements IBugCommentService {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BugCommentMapper bugCommentMapper;
    @Autowired
    private BugInfoMapper bugInfoMapper;

    @Autowired
    private ADSHelper adsHelper;


    @Transactional
    @Override
    public Map<String,Object> add(Map<String,String> paramsMap) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(paramsMap);
        BugComment bugComment = JSONObject.toJavaObject(jsonObject,BugComment.class);
        Integer accountType = AccountUitl.getAccountType(paramsMap);
        //回复人验证
        AccountLongfor accountLongfor =
                AccountUitl.getAccountByAccountType(accountType,bugComment.getAccountId(),adsHelper);
        if(accountLongfor == null) {
            return CommonUtils.getResultMapByBizEnum(BizEnum.E1017,"该回复人");
        }
        //bugId验证
        Long bugId = Long.parseLong(paramsMap.get("bugId"));
        BugInfo bugInfo = bugInfoMapper.selectByPrimaryKey(bugId);
        if(bugInfo == null){
            return CommonUtils.getResultMapByBizEnum(BizEnum.E1017,"该BUG");
        }

        //levelNum
        int levelNum = 1;
        BugComment lastBugComment = bugCommentMapper.getMaxLevelNum(bugId);
        if(lastBugComment != null){
            levelNum = lastBugComment.getLevelNum() + 1;
        }

        //parentId验证
//        Long parentId = Long.parseLong(paramsMap.get("parentId"));
//        if(parentId != 0){
//            BugInfo parentBugInfo = bugInfoMapper.selectByPrimaryKey(parentId);
//            if(parentBugInfo == null){
//                return CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
//            }
//
//            //更新回复状态
//            BugComment bugCommentParent = new BugComment();
//            bugCommentParent.setId(parentId);
//            bugCommentParent.setReplyType(1);
//            bugCommentMapper.updateByPrimaryKeySelective(bugCommentParent);
//        }

        if(accountLongfor!=null && StringUtils.isNotBlank(accountLongfor.getPsEmployeeCode())){
            bugComment.setEmployeeCode(Long.parseLong(accountLongfor.getPsEmployeeCode()));
        }
        bugComment.setEmployeeName(accountLongfor.getName());
        bugComment.setFullDeptPath(accountLongfor.getPsDeptFullName());
        bugComment.setLevelNum(levelNum);
        bugComment.setParentId(0L);
        bugComment.setReplyType(0);
        bugComment.setStatus(AvaStatusEnum.AVA.getCode());
        bugComment.setCreateTime(TimeUtils.getTodayByDateTime());
        bugComment.setModifiedTime(TimeUtils.getTodayByDateTime());
        bugCommentMapper.insert(bugComment);

        return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
    }
}
