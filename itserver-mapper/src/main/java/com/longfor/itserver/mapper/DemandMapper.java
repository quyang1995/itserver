package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.Demand;
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

	List<Demand> searchList(Map<String, Object> paramsMap);
	boolean addDemand(Map map);

	Demand getDemandById(long id);

	boolean updateDemand(Map map);

	List<PsIndex> countPending(String id);

	List<Demand> statusList(Demand demand);
}