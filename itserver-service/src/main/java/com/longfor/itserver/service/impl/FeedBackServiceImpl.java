package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.entity.FeedBack;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramEmployee;
import com.longfor.itserver.mapper.FeedBackMapper;
import com.longfor.itserver.mapper.ProductMapper;
import com.longfor.itserver.service.IFeedBackService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private ADSHelper adsHelper;

	@Override
	public boolean addFeedBack(Map map) {
		JSONObject json = (JSONObject) JSONObject.toJSON(map);
		FeedBack feedBack = JSONObject.toJavaObject(json, FeedBack.class);
		//通过账户ID查询账户基本信息
		AccountLongfor accountLongfor = adsHelper.getAccountLongforByLoginName(feedBack.getModifiedAccountId());
		feedBack.setContactAccountId(accountLongfor.getLoginName());
		feedBack.setModifiedEmployeeCode(Long.parseLong(accountLongfor.getPsEmployeeCode()));
		feedBack.setModifiedName(accountLongfor.getName());
		feedBack.setModifiedFullDeptPath(accountLongfor.getPsDeptFullName());
		//查询产品信息得到接口人信息和产品名称
		Product obj = new Product();
		obj.setId(feedBack.getProductId());
		Product product = productMapper.selectOne(obj);
		feedBack.setName(product.getName());
		feedBack.setContactAccountId(product.getContactAccountId());
		feedBack.setContactEmployeeCode(product.getContactEmployeeCode());
		feedBack.setContactEmployeeName(product.getContactEmployeeName());
		feedBack.setContactFullDeptPath(product.getContactFullDeptPath());
		feedBackMapper.insert(feedBack);

		return true;
	}

}
