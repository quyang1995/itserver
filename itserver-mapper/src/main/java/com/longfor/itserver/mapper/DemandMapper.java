package com.longfor.itserver.mapper;

import java.util.List;
import java.util.Map;

import com.longfor.itserver.entity.Demand;
import com.longfor.itserver.mapper.base.BeeMapper;

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
}