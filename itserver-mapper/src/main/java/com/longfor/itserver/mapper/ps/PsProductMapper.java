package com.longfor.itserver.mapper.ps;


import com.longfor.itserver.entity.ps.PsProduct;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface PsProductMapper extends BeeMapper<PsProduct> {
    List<PsProduct> selectList(Map map);
    List<PsProduct> selectLikeList(Map map);
    PsProduct getById(Integer id);
}