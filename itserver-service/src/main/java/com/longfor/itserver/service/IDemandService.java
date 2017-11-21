package com.longfor.itserver.service;


import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.entity.Demand;
import com.longfor.itserver.entity.ps.PsDemandTimeTask;
import com.longfor.itserver.entity.ps.PsIndex;
import com.longfor.itserver.service.base.IAdminService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IDemandService extends IAdminService<Demand> {
	/**
	 * 获取分页list
	 * @param paramsMap
	 * @return
	 */
	public Map<String, Object> getPageDemandList(Map<String, Object>paramsMap,ELExample example);

	/**
	 * 获取所有list
	 * @param paramsMap
	 * @return
	 */
	public Map<String, Object> getExcelDemandList(Map<String, Object>paramsMap);

	/**
	 *  新增需求信息
	 * @param map
	 * @return
	 */
	boolean addDemand(Map map);

	/**
	 *  修改需求信息
	 * @param map
	 * @return
	 */
	Boolean updateDemand(Map map);

	/**
	 *  通过ID获取需求信息
	 * @param
	 * @return
	 */
	public Demand getDemandById(Long id);

	List<PsIndex> countPending(String id);

	/**\
	 * 更新需求状态
	 * @param paramsMap
	 * @return
	 */
	boolean updateStatus(Map<String,String> paramsMap);

	/**\
	 * 更新需求指派人
	 * @param paramsMap
	 * @return
	 */
	boolean updateCallon(Map<String,String> paramsMap);

	Map statusList(HttpServletRequest request,Map<String, String> paramsMap);

	/**
	 * 定时任务
	 * @return
	 */
	List<PsDemandTimeTask> demandTask();
}
