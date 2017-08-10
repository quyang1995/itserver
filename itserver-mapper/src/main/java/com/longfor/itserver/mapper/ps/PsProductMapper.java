package com.longfor.itserver.mapper.ps;


import com.longfor.itserver.entity.ps.PsProductAll;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface PsProductMapper extends BeeMapper<PsProductAll> {
    List<PsProductAll> selectList(Map map);
    List<PsProductAll> selectLikeList(Map map);
    PsProductAll getById(Integer id);
}