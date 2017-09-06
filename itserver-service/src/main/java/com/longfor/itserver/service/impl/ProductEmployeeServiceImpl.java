package com.longfor.itserver.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.mapper.ProductEmployeeMapper;
import com.longfor.itserver.service.IProductEmployeeService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wax
 *         Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("ProductEmployeeService")
public class ProductEmployeeServiceImpl extends AdminBaseService<ProductEmployee> implements IProductEmployeeService {
    @Autowired
    private ProductEmployeeMapper productEmployeeMapper;
    @Override
    public List<ProductEmployee> searchTypeList(Long productId, Integer employeeType, Long employeeTypeId) {
        return productEmployeeMapper.selectTypeList(productId,employeeType,employeeTypeId);
    }

}
