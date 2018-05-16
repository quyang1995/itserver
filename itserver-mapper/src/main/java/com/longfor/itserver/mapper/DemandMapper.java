package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.Demand;
import com.longfor.itserver.entity.ps.PsDemandDetail;
import com.longfor.itserver.entity.ps.PsDemandTimeTask;
import com.longfor.itserver.entity.ps.PsIndex;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author shanxinqiang
 *
 */
public interface DemandMapper extends BeeMapper<Demand> {

	List<PsDemandDetail> searchList(Map<String, Object> paramsMap);
	boolean addDemand(Map map);

	Demand getDemandById(long id);

	boolean updateDemand(Map map);

	List<PsIndex> countPending(String id);

	List<Demand> statusList(Map paramsMap);

	List<PsDemandTimeTask> demandTask();

	/**
	 * 导出list（新）
	 * @param paramsMap
	 * @return
	 */
	List<Map<String,Object>> newExport(Map<String, Object> paramsMap);

	/**
	 * 移动统计
	 * @param paramsMap
	 * @return
	 */
	Integer demandTj(Map<String, Object> paramsMap);
}