package com.longfor.itserver.service;

import com.longfor.itserver.entity.ProductLabelType;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;


public interface IProductLabelTypeService extends IAdminService<ProductLabelType> {
    List<ProductLabelType> getLabelTypeList(Map<String,Object> map);

    int getLabelTypeCount(Map<String,Object> map);

    List<ProductLabelType> getLabelTree(Map<String,Object> map);

}
