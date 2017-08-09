package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.mapper.ProductEmployeeMapper;
import com.longfor.itserver.mapper.ProductMapper;
import com.longfor.itserver.service.IProductService;
import com.longfor.itserver.service.base.AdminBaseService;
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
@Service("ProductService")
public class ProductServiceImpl extends AdminBaseService<Product> implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductEmployeeMapper productEmployeeMapper;
    @Override
    public List<Product> searchList(Map map) {
        return productMapper.selectList(map);
    }

    @Override
    public List<Product> searchLikeList(Map map) {
        return productMapper.selectLikeList(map);
    }
    @Transactional
    @Override
    public boolean addProduct(Map map) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(map);
        Product product = JSONObject.toJavaObject(jsonObject, Product.class);
        /*关联产品*/
        JSONArray parse = (JSONArray)JSONObject.parse(product.getLikeProgram());
        String str="";
        for(Object o:parse){
            str += "," + o;
        }
        product.setLikeProgram(str);
        int insert = productMapper.insert(product);

        /*添加产品人员*/
        List<ProductEmployee> productEmployeeList =
                JSONArray.parseArray(jsonObject.get("productEmployeeList").toString(), ProductEmployee.class);
        for (ProductEmployee productEmployee : productEmployeeList){
            ProductEmployee productEmployee1 = JSONObject.toJavaObject((JSONObject) JSONObject.
                    toJSON(productEmployee), ProductEmployee.class);
            productEmployee1.setProductId(product.getId());
            productEmployeeMapper.insert(productEmployee1);
        }
        return true;
    }
}
