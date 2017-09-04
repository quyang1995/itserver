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

    @Override
    public boolean delEmployee(Map paramsMap) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(paramsMap);
        ProductEmployee employee = JSONObject.toJavaObject(jsonObject,ProductEmployee.class);
//        ELExample example  = new ELExample(request, ProductEmployee.class);
//        productEmployeeMapper.deleteByExample();


//        Product product = this.getProductService().selectById(paramsMap.get("productId"));
//
//        ELExample elExample = new ELExample(request,ProductEmployee.class);
//        List<ProductEmployee> personLiableList = this.getProductEmployeeService().searchTypeList(new Long((String) paramsMap.get("productId")),AvaStatusEnum.LIABLEAVA.getCode(),null);
////        List<ProductEmployee> employeeList =  this.getProductEmployeeService().searchByELExample(elExample);
//        List<ProductEmployee> employeeList =  this.getProductEmployeeService().select();
//        List<ProductEmployee> delList = new ArrayList<>();
//        boolean flag = false;
//        int count = personLiableList.size();
//        for (ProductEmployee employee:employeeList) {
//            //将接口人排除
//            if (employee.getAccountId().equals(product.getContactAccountId())){
//                continue;
//            }
//            //将责任人至少留下一个
//            if(count > 0){
//                for (ProductEmployee liable:personLiableList ) {
//                    if(liable.getAccountId().equals(employee.getAccountId())&&employee.getEmployeeType().equals(2)) {
//                        if(count != 1) {
//                            delList.add(employee);
//                        }
//                        count--;
//                    }
//                }
//            }
//            //其他人员可以删除
//            delList.add(employee);
//        }
//        //可删除人员数小于1 返回请求拒绝
//        if(employeeList.size() < 1) return  CommonUtils.getResultMapByBizEnum(BizEnum.E9998);
//        flag = true;
//        if(flag){
//            for (ProductEmployee p: delList) {
//                this.getProductEmployeeService().delete(p);
//            }
//        }
        return false;
    }



//        @Override
//    public boolean delEmployee(HttpServletRequest request) {
//
//        ELExample example  = new ELExample(request, ProductEmployee.class);
//        productEmployeeMapper.deleteByExample(example);
//
//        return true;
//    }


}
