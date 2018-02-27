package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.ProductLabelType;
import com.longfor.itserver.mapper.base.BeeMapper;
import java.util.List;
import java.util.Map;

public interface ProductLabelTypeMapper extends BeeMapper<ProductLabelType> {
    List<ProductLabelType> getLabelTypeList(Map<String,Object> map);

    int getLabelTypeCount(Map<String,Object> map);
}