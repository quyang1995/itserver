package com.longfor.itserver.service;


import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.entity.Demand;
import com.longfor.itserver.entity.ps.PsDemandDetail;
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
	 Map<String, Object> getPageDemandList(Map<String, Object>paramsMap,ELExample example);

	/**
	 * 获取所有list
	 * @param paramsMap
	 * @return
	 */
	 Map<String, Object> getExcelDemandList(Map<String, Object>paramsMap);

	/**
	 * 导出list（新）
	 * @param paramsMap
	 * @return
	 */
	List<Map<String,Object>> newExport(Map<String, Object>paramsMap);

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
	 Demand getDemandById(Long id);

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

	/**
	 * 删除项目或产品相关Demand，relationType=1：产品，relationType=2：项目
	 * @param id
	 * @param relationType
	 */
	void deleteDemand(Long id,Integer relationType);
}
