package com.longfor.itserver.service;

import com.longfor.itserver.entity.ProductLabel;
import com.longfor.itserver.entity.ProductLabelType;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;


public interface IProductLabelService extends IAdminService<ProductLabel> {
    List<ProductLabel> getLabelList(Map<String,Object> map);

    int getLabelCount(Map<String,Object> map);
}
