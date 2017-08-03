package com.longfor.itserver.mapper;

import com.longfor.itserver.mapper.base.BeeMapper;
import generator.warehouse.entity.Demand;
import generator.warehouse.entity.DemandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface DemandMapper extends BeeMapper<Demand> {
}