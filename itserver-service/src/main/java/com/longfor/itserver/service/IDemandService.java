package com.longfor.itserver.service;


import java.util.Map;

import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.entity.Demand;
import com.longfor.itserver.service.base.IAdminService;

public interface IDemandService extends IAdminService<Demand> {
	/**
	 * 获取分页list
	 * @param paramsMap
	 * @return
	 */
	public Map<String, Object> getPageDemandList(Map<String, Object>paramsMap,ELExample example);


}
