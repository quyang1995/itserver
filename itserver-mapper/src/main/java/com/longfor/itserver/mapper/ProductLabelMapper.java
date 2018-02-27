package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.ProductLabel;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface ProductLabelMapper extends BeeMapper<ProductLabel> {
    List<ProductLabel> getLabelList(Map<String,Object> map);

    int getLabelCount(Map<String,Object> map);
}