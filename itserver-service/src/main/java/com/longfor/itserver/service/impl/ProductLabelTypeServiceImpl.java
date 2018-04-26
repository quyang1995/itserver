package com.longfor.itserver.service.impl;


import com.longfor.itserver.entity.ProductLabel;
import com.longfor.itserver.entity.ProductLabelType;
import com.longfor.itserver.mapper.ProductLabelMapper;
import com.longfor.itserver.mapper.ProductLabelTypeMapper;
import com.longfor.itserver.service.IProductLabelTypeService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jtx Created on 2018/2/27 下午3:15
 * @version v1.0
 */
@Service("ProductLabelTypeService")
public class ProductLabelTypeServiceImpl extends AdminBaseService<ProductLabelType> implements IProductLabelTypeService {
    @Autowired
    private ProductLabelTypeMapper productLabelTypeMapper;
    @Autowired
    private ProductLabelMapper productLabelMapper;

    @Override
    public List<ProductLabelType> getLabelTypeList(Map<String,Object> map) {
        return productLabelTypeMapper.getLabelTypeList(map);
    }

    @Override
    public int getLabelTypeCount(Map<String,Object> map) {
        return productLabelTypeMapper.getLabelTypeCount(map);
    }

    @Override
    public List<ProductLabelType> getLabelTree(Map<String,Object> map) {
        List<ProductLabelType> allLabelType = productLabelTypeMapper.getLabelTypeList(null);
        List<ProductLabel> allLabel = productLabelMapper.getLabelList(null);
        for (ProductLabelType labelType:allLabelType){
            List<ProductLabel> labels = new ArrayList<>();
            for (ProductLabel label:allLabel){
                if(label.getLabelTypeId()==labelType.getId()){
                    labels.add(label);
                }
            }
            labelType.setProductLabelList(labels);
        }
        return allLabelType;
    }
}