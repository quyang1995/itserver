package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.enums.BugStatusEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.entity.Demand;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.mapper.DemandMapper;
import com.longfor.itserver.service.IDemandService;
import com.longfor.itserver.service.base.AdminBaseService;

import net.mayee.commons.helper.APIHelper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wax Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("DemandService")
public class DemandServiceImpl extends AdminBaseService<Demand> implements IDemandService {
	@Autowired
	private DemandMapper demandMapper;
	@Autowired
	private ADSHelper adsHelper;

	/**
	 * 	新增需求信息
	 * @param map
	 * @return
	 */
	@Transactional
	@Override
	public boolean addDemand(Map map) {
		JSONObject json = (JSONObject) JSONObject.toJSON(map);
		Demand demand = JSONObject.toJavaObject(json, Demand.class);
		//获取状态信息(默认待处理)
		demand.setStatus(BugStatusEnum.PENDING.getCode());
		//获取指派人信息
		AccountLongfor draftedAccountLongfor = adsHelper.getAccountLongforByLoginName(demand.getDraftedAccountId());
		if(draftedAccountLongfor!=null){
			demand.setDraftedEmployeeCode(Long.parseLong(draftedAccountLongfor.getPsEmployeeCode()));
			demand.setDraftedEmployeeName(draftedAccountLongfor.getName());
			demand.setDraftedFullDeptPath(draftedAccountLongfor.getPsDeptFullName());
		}
		//获取发起人信息
		AccountLongfor callonAccountLongfor = adsHelper.getAccountLongforByLoginName(demand.getCallonAccountId());
		if (callonAccountLongfor!=null){
			demand.setCallonEmployeeName(callonAccountLongfor.getName());
			demand.setCallonEmployeeCode(Long.parseLong(callonAccountLongfor.getPsEmployeeCode()));
			demand.setCallonFullDeptPath(callonAccountLongfor.getPsDeptFullName());
		}

		demandMapper.insert(demand);
		return true;
	}

	/**
	 *  修改需求
	 * @param map
	 * @return
	 */
	@Transactional
	@Override
	public Boolean updateDemand(Map map) {
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		Demand demand = JSONObject.toJavaObject(jsonObject, Demand.class);
		Demand selectDemandOne = demandMapper.selectByPrimaryKey(demand.getId());
		if (selectDemandOne==null){
			return false;
		}
		//获取状态信息(默认处理中)
		demand.setStatus(BugStatusEnum.WORKING.getCode());
		//获取指派人信息
		AccountLongfor draftedAccountLongfor = adsHelper.getAccountLongforByLoginName(demand.getDraftedAccountId());
		if(draftedAccountLongfor!=null){
			demand.setDraftedEmployeeCode(Long.parseLong(draftedAccountLongfor.getPsEmployeeCode()));
			demand.setDraftedEmployeeName(draftedAccountLongfor.getName());
			demand.setDraftedFullDeptPath(draftedAccountLongfor.getPsDeptFullName());
		}
		//获取发起人信息
		AccountLongfor callonAccountLongfor = adsHelper.getAccountLongforByLoginName(demand.getCallonAccountId());
		if (callonAccountLongfor!=null){
			demand.setCallonEmployeeName(callonAccountLongfor.getName());
			demand.setCallonEmployeeCode(Long.parseLong(callonAccountLongfor.getPsEmployeeCode()));
			demand.setCallonFullDeptPath(callonAccountLongfor.getPsDeptFullName());
		}

		demandMapper.updateByPrimaryKey(demand);
		return true;
	}

	@Override
	public Demand getDemandById(Long id) {
			Demand demand = demandMapper.getDemandById(id);
		return demand;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getPageDemandList(Map<String, Object> paramsMap, ELExample elExample) {
		/* 查询数据 */
		PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
		List<Demand> demands = demandMapper.searchList(paramsMap);
		/* 返回数据 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("demandList", demands);
		resultMap.put(APIHelper.PAGE_NUM, elExample.getPageNum());
		resultMap.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
		resultMap.put(APIHelper.TOTAL, new PageInfo(demands).getTotal());
		return resultMap;
	}

}
