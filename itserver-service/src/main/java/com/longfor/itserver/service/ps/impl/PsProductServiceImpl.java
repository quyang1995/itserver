package com.longfor.itserver.service.ps.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.entity.ps.PsProduct;
import com.longfor.itserver.mapper.ProductEmployeeMapper;
import com.longfor.itserver.mapper.ProductMapper;
import com.longfor.itserver.mapper.ps.PsProductMapper;
import com.longfor.itserver.service.IProductService;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.ps.IPsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author wax
 *         Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("PsProductService")
public class PsProductServiceImpl extends AdminBaseService<PsProduct> implements IPsProductService {
    @Autowired
    private PsProductMapper psProductMapper;
    @Autowired
    private ProductEmployeeMapper productEmployeeMapper;
    @Override
    public List<PsProduct> searchList(Map map) {
        return psProductMapper.selectList(map);
    }

    @Override
    public List<PsProduct> searchLikeList(Map map) {
        return psProductMapper.selectLikeList(map);
    }

    @Override
    public PsProduct getById(Integer id) {
        return psProductMapper.getById(id);
    }


}
