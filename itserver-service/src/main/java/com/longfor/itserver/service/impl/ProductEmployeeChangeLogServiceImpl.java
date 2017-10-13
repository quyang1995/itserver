package com.longfor.itserver.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.entity.DemandChangeLog;
import com.longfor.itserver.entity.ProductEmployeeChangeLog;
import com.longfor.itserver.mapper.ProductEmployeeChangeLogMapper;
import com.longfor.itserver.service.IProductEmployeeChangeLogService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wax
 *         Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("ProductEmployeeChangeLogService")
public class ProductEmployeeChangeLogServiceImpl extends AdminBaseService<ProductEmployeeChangeLog> implements IProductEmployeeChangeLogService {

    @Autowired
    private ProductEmployeeChangeLogMapper productEmployeeChangeLogMapper;


    @Override
    public Map orderList(Map paramsMap) {

        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(paramsMap);

        Map<String,Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        ProductEmployeeChangeLog employeeChangeLog = JSONObject.toJavaObject(jsonObject,ProductEmployeeChangeLog.class);
        List<ProductEmployeeChangeLog> list = productEmployeeChangeLogMapper.orderList(employeeChangeLog);
        resultMap.put("list",list);

        return resultMap ;
    }

    @Override
    public  List<ProductEmployeeChangeLog> paraQuery(Map<String, String> paramsMap){
        return productEmployeeChangeLogMapper.paraQuery(paramsMap);
    }
}
