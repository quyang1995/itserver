package com.longfor.itserver.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.mapper.ProductEmployeeMapper;
import com.longfor.itserver.service.IProductEmployeeService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean addProductEmployee(Map map){
        /*修改后的产品人员*/
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(map);
        JSONArray personLiableList = (JSONArray)JSONArray.parse(jsonObject.getString("personLiableList"));
        JSONArray programManagerList = (JSONArray)JSONArray.parse(jsonObject.getString("programManagerList"));
        JSONArray productManagerList = (JSONArray)JSONArray.parse(jsonObject.getString("productManagerList"));
        JSONArray developerList = (JSONArray)JSONArray.parse(jsonObject.getString("developerList"));
        JSONArray uedList = (JSONArray)JSONArray.parse(jsonObject.getString("uedList"));
        String str = (String)map.get("id");
        /*产品相关人员*/
        List<ProductEmployee> productEmployees = productEmployeeMapper.
                selectTypeList(new Long(str), AvaStatusEnum.LIABLEAVA.getCode(), null);
        List list=new ArrayList();
        for (ProductEmployee pe:productEmployees){
            list.add(pe.getAccountId());
        }
        List<ProductEmployee> productEmployees1 = productEmployeeMapper.
                selectTypeList(new Long(str), AvaStatusEnum.MEMBERAVA.getCode(), new Long(AvaStatusEnum.PRODAVA.getCode()));
        List list1=new ArrayList();
        for (ProductEmployee pe:productEmployees1){
            list1.add(pe.getAccountId());
        }
        List<ProductEmployee> productEmployees2 = productEmployeeMapper.
                selectTypeList(new Long(str), AvaStatusEnum.MEMBERAVA.getCode(), new Long(AvaStatusEnum.PROGAVA.getCode()));
        List list2=new ArrayList();
        for (ProductEmployee pe:productEmployees2){
            list2.add(pe.getAccountId());
        }
        List<ProductEmployee> productEmployees3 = productEmployeeMapper.
                selectTypeList(new Long(str), AvaStatusEnum.MEMBERAVA.getCode(), new Long(AvaStatusEnum.DEVEAVA.getCode()));
        List list3=new ArrayList();
        for (ProductEmployee pe:productEmployees3){
            list3.add(pe.getAccountId());
        }
        List<ProductEmployee> productEmployees4 = productEmployeeMapper.
                selectTypeList(new Long(str), AvaStatusEnum.MEMBERAVA.getCode(), new Long(AvaStatusEnum.UEDAVA.getCode()));
        List list4=new ArrayList();
        for (ProductEmployee pe:productEmployees4){
            list4.add(pe.getAccountId());
        }
        StringBuilder sb=new StringBuilder();
        if(!(personLiableList.size()==list.size() && personLiableList.containsAll(list))){
            for(Object object:personLiableList){
                if (!list.contains(object)) {
                    /*调用ads获得人员信息*/
                    /*productEmployee表中进行add*/
                }
            }
            for (Object o:list){
                if(!personLiableList.contains(o)){
                    /*调用productEmployee表进行delete*/
                }
            }
            /*责任人变更信息*/
            sb.append("责任人变更");
        }
        if (sb.length()>=1){
            /*产品人员变更日志表进行add*/
        }
        return true;
    }
}
