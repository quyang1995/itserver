package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.FeedBackStatusEnum;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.mapper.BugInfoMapper;
import com.longfor.itserver.mapper.DemandMapper;
import com.longfor.itserver.mapper.FeedBackMapper;
import com.longfor.itserver.mapper.ProductMapper;
import com.longfor.itserver.service.IFeedBackService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		//查询产品信息得到接口人信息和产品名称
		Product obj = new Product();
		obj.setId(feedBack.getProductId());
		Product product = productMapper.selectOne(obj);
		feedBack.setName(product.getName());
		feedBack.setContactAccountId(product.getContactAccountId());
		feedBack.setContactEmployeeCode(product.getContactEmployeeCode());
		feedBack.setContactEmployeeName(product.getContactEmployeeName());
		feedBack.setContactFullDeptPath(product.getContactFullDeptPath());
		//状态
		feedBack.setStatus(FeedBackStatusEnum.WORKING.getCode());
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
			bugInfo.setCallonAccountId(product.getContactAccountId());
			bugInfo.setCallonEmployeeCode(product.getContactEmployeeCode());
			bugInfo.setCallonEmployeeName(product.getContactEmployeeName());
			bugInfo.setCallonFullDeptPath(product.getContactFullDeptPath());
			bugInfo.setLevel(0);
			bugInfo.setStatus(feedBack.getStatus());
			bugInfo.setModifiedAccountId(feedBack.getModifiedAccountId());
			bugInfo.setModifiedName(feedBack.getModifiedName());
			bugInfoMapper.insert(bugInfo);
		}else if(feedBack.getType().equals(1)){
			Demand demand = new Demand();
			demand.setFeedBackId(feedBack.getId());
			demand.setRelationId(product.getId());
			demand.setRelationType(1);
			demand.setName(feedBack.getProblemTitle());
			demand.setDescp(feedBack.getProblemDescp());
			demand.setHopeDate(new Date());
			demand.setCallonAccountId(product.getContactAccountId());
			demand.setCallonEmployeeCode(product.getContactEmployeeCode());
			demand.setCallonEmployeeName(product.getContactEmployeeName());
			demand.setCallonFullDeptPath(product.getContactFullDeptPath());
			demand.setLevel(0);
			demand.setStatus(feedBack.getStatus());
			demand.setModifiedAccountId(feedBack.getModifiedAccountId());
			demand.setModifiedName(feedBack.getModifiedName());
			demandMapper.insert(demand);
		}
		return true;
	}

}
