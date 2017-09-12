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
import com.longfor.itserver.entity.ProductEmployeeChangeLog;
import com.longfor.itserver.mapper.ProductEmployeeChangeLogMapper;
import com.longfor.itserver.mapper.ProductEmployeeMapper;
import com.longfor.itserver.service.IProductEmployeeService;
import com.longfor.itserver.service.base.AdminBaseService;
import net.mayee.commons.TimeUtils;
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
    @Autowired
    private ProductEmployeeChangeLogMapper changeLogMapper;


    @Override
    public List<ProductEmployee> searchTypeList(Long productId, Integer employeeType, Long employeeTypeId) {
        return productEmployeeMapper.selectTypeList(productId,employeeType,employeeTypeId);
    }

    @Override
    public boolean delEmployee(ProductEmployee employee) {
        List<ProductEmployee>employeeList =  productEmployeeMapper.select(employee);
        //添加日志
        ProductEmployeeChangeLog changeLog = new ProductEmployeeChangeLog();
        StringBuilder log = new StringBuilder();
        log.append("[");
        log.append(employeeList.get(0).getEmployeeName());
        log.append("] 退出了产品组。");
        changeLog.setModifiedAccountId(employee.getAccountId());
        changeLog.setModifiedName(employeeList.get(0).getEmployeeName());
        changeLog.setProductId(employeeList.get(0).getProductId());
        changeLog.setActionChangeInfo(log.toString());
        changeLog.setCreateTime(TimeUtils.getTodayByDateTime());
        changeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
        changeLogMapper.insertUseGeneratedKeys(changeLog);

        //删除人员
        productEmployeeMapper.delete(employee);
        return true;
    }
}
