package com.longfor.itserver.service.impl;


import com.longfor.itserver.entity.ProductLabel;
import com.longfor.itserver.mapper.ProductLabelMapper;
import com.longfor.itserver.service.IProductLabelService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jtx Created on 2018/2/27 下午3:15
 * @version v1.0
 */
@Service("ProductLabelService")
public class ProductLabelServiceImpl extends AdminBaseService<ProductLabel> implements IProductLabelService {
    @Autowired
    private ProductLabelMapper productLabelMapper;
    @Override
    public List<ProductLabel> getLabelList(Map<String,Object> map) {
        return productLabelMapper.getLabelList(map);
    }

    @Override
    public int getLabelCount(Map<String,Object> map) {
        return productLabelMapper.getLabelCount(map);
    }
}