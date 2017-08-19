package com.longfor.itserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
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

/**
 * @author wax Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("DemandService")
public class DemandServiceImpl extends AdminBaseService<Demand> implements IDemandService {
	@Autowired
	private DemandMapper demandMapper;

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
