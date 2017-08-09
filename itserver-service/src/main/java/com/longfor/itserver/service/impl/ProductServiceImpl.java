package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
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
    @Autowired
    private ADSHelper adsHelp;

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
        product.setStatus(Integer.parseInt(jsonObject.getString("productStatus")));
        product.setContactAccountId(jsonObject.getString("contactAccountId"));
        /*接口人相关信息*/
        getAccountInfo(0,product,null);
        /*关联项目*/
        JSONArray parse = (JSONArray)JSONObject.parse(product.getLikeProgram());
        String str="";
        for(Object o:parse){
            str += "," + o;
        }
        product.setLikeProgram(str);
        int insert = productMapper.insert(product);
        /*产品责任人*/
        JSONArray personLiableList = (JSONArray)JSONArray.parse(jsonObject.getString("personLiableList"));
        getAccountInfo(1,product,personLiableList);
        /*产品经理*/
        JSONArray programManagerList = (JSONArray)JSONArray.parse(jsonObject.getString("programManagerList"));
        getAccountInfo(2,product,programManagerList);
        /*项目经理*/
        JSONArray productManagerList = (JSONArray)JSONArray.parse(jsonObject.getString("productManagerList"));
        getAccountInfo(3,product,personLiableList);
        /*开发人员*/
        JSONArray developerList = (JSONArray)JSONArray.parse(jsonObject.getString("developerList"));
        getAccountInfo(4,product,personLiableList);
        /*UED人员*/
        JSONArray uedList = (JSONArray)JSONArray.parse(jsonObject.getString("uedList"));
        getAccountInfo(5,product,personLiableList);

        return true;
    }

    @Override
    public List<Product> searchIdList(String likeProduct) {
        return productMapper.selectIdList(likeProduct);
    }

    public void getAccountInfo(int num,Product product,JSONArray array){
        if (num == 0){
            AccountLongfor accountInfo = adsHelp.getAccountLongforByLoginName(product.getContactAccountId());
            if(accountInfo != null) {
                product.setContactEmployeeCode(Long.parseLong(accountInfo.getPsEmployeeCode()));
                product.setContactEmployeeName(accountInfo.getName());
                product.setContactFullDeptPath(accountInfo.getPsDeptFullName());
            }
        }else {
            for(int i=0;i<array.size();i++){
                String loginName = array.get(i).toString();
                AccountLongfor accountInfo = adsHelp.getAccountLongforByLoginName(loginName);
                if(accountInfo != null){
                    ProductEmployee productEmployee = new ProductEmployee();
                    productEmployee.setProductId(product.getId());
                    productEmployee.setAccountId(loginName);
                    productEmployee.setEmployeeCode(Long.parseLong(accountInfo.getPsEmployeeCode()));
                    productEmployee.setEmployeeName(accountInfo.getName());
                    productEmployee.setFullDeptPath(accountInfo.getPsDeptFullName());
                    productEmployee.setStatus(AvaStatusEnum.AVA.getCode());
                    if (num == 1){
                        productEmployee.setEmployeeType(AvaStatusEnum.LIABLEAVA.getCode());
                    }else if(num == 2){
                        productEmployee.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                        productEmployee.setEmployeeTypeId(new Long(AvaStatusEnum.PRODAVA.getCode()));
                        productEmployee.setEmployeeTypeText(AvaStatusEnum.PRODAVA.getText());
                    }else if(num == 3){
                        productEmployee.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                        productEmployee.setEmployeeTypeId(new Long(AvaStatusEnum.PROGAVA.getCode()));
                        productEmployee.setEmployeeTypeText(AvaStatusEnum.PROGAVA.getText());
                    }else if (num == 4){
                        productEmployee.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                        productEmployee.setEmployeeTypeId(new Long(AvaStatusEnum.DEVEAVA.getCode()));
                        productEmployee.setEmployeeTypeText(AvaStatusEnum.DEVEAVA.getText());
                    }else if (num == 5){
                        productEmployee.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                        productEmployee.setEmployeeTypeId(new Long(AvaStatusEnum.UEDAVA.getCode()));
                        productEmployee.setEmployeeTypeText(AvaStatusEnum.UEDAVA.getText());
                    }
                    productEmployeeMapper.insert(productEmployee);
                }
            }
        }
    }
}
