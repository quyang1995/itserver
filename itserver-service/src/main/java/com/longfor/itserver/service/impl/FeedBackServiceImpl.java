package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.*;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.entity.ps.PsFeedBackStatus;
import com.longfor.itserver.mapper.BugInfoMapper;
import com.longfor.itserver.mapper.DemandMapper;
import com.longfor.itserver.mapper.FeedBackMapper;
import com.longfor.itserver.mapper.ProductMapper;
import com.longfor.itserver.service.IFeedBackService;
import com.longfor.itserver.service.base.AdminBaseService;
import net.mayee.commons.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


	@Override
	public List<FeedBack> feedBackList(Map map) {
		return feedBackMapper.feedBackList(map);
	}

    @Transactional
	@Override
	public boolean addFeedBack(Map map) {
		JSONObject json = (JSONObject) JSONObject.toJSON(map);
		FeedBack feedBack = JSONObject.toJavaObject(json, FeedBack.class);
		//通过账户ID查询账户基本信息
		AccountLongfor accountLongfor = adsHelper.getAccountLongforByLoginName(feedBack.getModifiedAccountId());
		if(accountLongfor != null){
			feedBack.setModifiedEmployeeCode(Long.parseLong(accountLongfor.getPsEmployeeCode()));
			feedBack.setModifiedName(accountLongfor.getName());
			feedBack.setModifiedFullDeptPath(accountLongfor.getPsDeptFullName());
		}
//		else{
//		    //账户不存在
//		    return false;
//        }
		//查询产品信息得到接口人信息和产品名称
		Product obj = new Product();
		obj.setId(feedBack.getProductId());
		Product product = productMapper.selectOne(obj);
		feedBack.setName(product.getName());
		feedBack.setContactAccountId(product.getContactAccountId());
		feedBack.setContactEmployeeCode(product.getContactEmployeeCode());
		feedBack.setContactEmployeeName(product.getContactEmployeeName());
		feedBack.setContactFullDeptPath(product.getContactFullDeptPath());
        feedBack.setCreateTime(TimeUtils.getTodayByDateTime());
        feedBack.setModifiedTime(TimeUtils.getTodayByDateTime());
		//状态
		feedBack.setStatus(FeedBackStatusEnum.PENDING.getCode());
		feedBackMapper.insert(feedBack);
		//0:新增BUG   1:新增需求
		if(feedBack.getType().equals(0)){
			BugInfo bugInfo = new BugInfo();
			bugInfo.setFeedBackId(feedBack.getId());
			bugInfo.setRelationId(product.getId());
			bugInfo.setRelationType(1);
			bugInfo.setName(feedBack.getProblemTitle());
			bugInfo.setDescp(feedBack.getProblemDescp());
			bugInfo.setReproductionStep(feedBack.getReproductionStep());
			bugInfo.setBrower(feedBack.getSysEnvironment());
			bugInfo.setHopeDate(new Date());
			bugInfo.setChannel(feedBack.getChannel());

            //指派人
			bugInfo.setCallonAccountId(product.getContactAccountId());
			bugInfo.setCallonEmployeeCode(product.getContactEmployeeCode());
			bugInfo.setCallonEmployeeName(product.getContactEmployeeName());
			bugInfo.setCallonFullDeptPath(product.getContactFullDeptPath());

			//起草人
			if(accountLongfor != null){
				bugInfo.setDraftedAccountId(accountLongfor.getLoginName());
				bugInfo.setDraftedEmployeeCode(Long.parseLong(accountLongfor.getPsEmployeeCode()));
				bugInfo.setDraftedEmployeeName(accountLongfor.getName());
				bugInfo.setDraftedFullDeptPath(accountLongfor.getPsDeptFullName());
			}

			bugInfo.setLevel(BugLevelEnum.HIGH_LEVEL.getCode());
			bugInfo.setStatus(feedBack.getStatus());
			bugInfo.setModifiedAccountId(feedBack.getModifiedAccountId());
			bugInfo.setModifiedName(feedBack.getModifiedName());
            bugInfo.setCreateTime(TimeUtils.getTodayByDateTime());
            bugInfo.setModifiedTime(TimeUtils.getTodayByDateTime());
			bugInfoMapper.insert(bugInfo);
		}else if(feedBack.getType().equals(1)){
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
			if(accountLongfor != null) {
				demand.setDraftedAccountId(accountLongfor.getLoginName());
				demand.setDraftedEmployeeCode(Long.parseLong(accountLongfor.getPsEmployeeCode()));
				demand.setDraftedEmployeeName(accountLongfor.getName());
				demand.setDraftedFullDeptPath(accountLongfor.getPsDeptFullName());
			}
			demand.setLevel(DemandLevelEnum.HIGH_LEVEL.getCode());
			demand.setStatus(feedBack.getStatus());
			demand.setModifiedAccountId(feedBack.getModifiedAccountId());
			demand.setModifiedName(feedBack.getModifiedName());
            demand.setCreateTime(TimeUtils.getTodayByDateTime());
            demand.setModifiedTime(TimeUtils.getTodayByDateTime());
			demandMapper.insert(demand);
		}
		return true;
	}


	@Override
	public FeedBack getFeedBackId(long id) {
		return feedBackMapper.getFeedBackId(id);
	}

	@Override
	public Map countStatus() {
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		PsFeedBackStatus  status = feedBackMapper.countStatus();
		resultMap.put("status",status);
		return resultMap;
	}
}
