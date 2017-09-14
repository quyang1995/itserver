package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.enums.ProductStatusEnum;
import com.longfor.itserver.common.enums.PublicTypeEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.entity.ProductEmployeeChangeLog;
import com.longfor.itserver.mapper.ProductEmployeeChangeLogMapper;
import com.longfor.itserver.mapper.ProductEmployeeMapper;
import com.longfor.itserver.mapper.ProductMapper;
import com.longfor.itserver.service.IProductService;
import com.longfor.itserver.service.base.AdminBaseService;
import net.mayee.commons.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author wax Created on 2017/8/3 下午7:15
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
	@Autowired
	private ProductEmployeeChangeLogMapper productEmployeeChangeLogMapper;

	@Override
	public List<Product> searchList(Map map) {
		return productMapper.selectList(map);
	}

	@Override
	public List<Product> productCountList(Map map){
		return productMapper.productCountList(map);
	}

	@Override
	public List<Product> searchLikeList(Map map) {
		return productMapper.selectLikeList(map);
	}

	@Transactional
	@Override
	public Map<String, Object> addProduct(Map map) {
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		Product product = JSONObject.toJavaObject(jsonObject, Product.class);

		//code唯一检查
        String code = product.getCode();
        Product codeCheckProduct = new Product();
        codeCheckProduct.setCode(code);
        if(StringUtils.isBlank(code) || productMapper.select(codeCheckProduct).size() > 0){
            return CommonUtils.getResultMapByBizEnum(BizEnum.E1028, code);
        }

		String descp = product.getDescp();
		if (descp != null && descp.length() > 500) {
			product.setDescp(descp.substring(0, 500));
		}
		product.setStatus(Integer.parseInt(jsonObject.getString("status")));
		product.setContactAccountId(jsonObject.getString("contactAccountId"));
		product.setLikeProgram(jsonObject.getString("likeProgram"));
		/* 接口人相关信息 */
		getAccountInfo(0, product, null);
		/* 添加产品 */
		product.setCreateTime(TimeUtils.getTodayByDateTime());
		product.setModifiedTime(TimeUtils.getTodayByDateTime());
		int insert = productMapper.insert(product);
		/* 产品责任人 */
		String personLiableList = (String) map.get("personLiableList");
		getAccountInfo(1, product, personLiableList);
		/* 产品经理 */
		String programManagerList = (String) map.get("programManagerList");
		getAccountInfo(2, product, programManagerList);
		/* 项目经理 */
		String productManagerList = (String) map.get("productManagerList");
		getAccountInfo(3, product, productManagerList);
		/* 开发人员 */
		String developerList = (String) map.get("developerList");
		getAccountInfo(4, product, developerList);
		/* UED人员 */
		String uedList = (String) map.get("uedList");
		getAccountInfo(5, product, uedList);

        //先生成变动日志
        List<String> changeLogTextList = getChangeLogText(null, product);
        /*添加日志*/
        for(String text : changeLogTextList){
            ProductEmployeeChangeLog employeeChangeLog = new ProductEmployeeChangeLog();
            employeeChangeLog.setProductId(product.getId());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setActionChangeInfo(text);
            employeeChangeLog.setModifiedAccountId(product.getModifiedAccountId());
            employeeChangeLog.setModifiedName(product.getModifiedName());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
            productEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);
        }

		return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
	}

	@Override
	public List<Product> searchIdList(String likeProduct) {
		return productMapper.selectIdList(likeProduct);
	}

	@Transactional
	@Override
	public boolean updateProduct(Map map) {
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		Product newProduct = JSONObject.toJavaObject(jsonObject, Product.class);
		Product oldProduct = productMapper.selectByPrimaryKey(newProduct.getId());

		//先生成变动日志
        List<String> changeLogTextList = getChangeLogText(oldProduct, newProduct);

        oldProduct.setName(jsonObject.getString("name"));
        oldProduct.setDescp(jsonObject.getString("descp"));
        oldProduct.setStatus(jsonObject.getInteger("status"));
        oldProduct.setContactAccountId(jsonObject.getString("contactAccountId"));
        oldProduct.setType(jsonObject.getInteger("type"));
		/* 接口人相关信息 */
		getAccountInfo(0, oldProduct, null);
		/* 关联项目 */
        oldProduct.setLikeProgram(newProduct.getLikeProgram());
        oldProduct.setModifiedTime(TimeUtils.getTodayByDateTime());
		int update = productMapper.updateByPrimaryKey(oldProduct);// 更新产品

		/* 产品责任人 */
		String personLiableList = (String) map.get("personLiableList");
		deleteByParam(1, newProduct);
		getAccountInfo(1, newProduct, personLiableList);
		/* 产品经理 */
		String programManagerList = (String) map.get("programManagerList");
		deleteByParam(2, newProduct);
		getAccountInfo(2, newProduct, programManagerList);
		/* 项目经理 */
		String productManagerList = (String) map.get("productManagerList");
		deleteByParam(3, newProduct);
		getAccountInfo(3, newProduct, productManagerList);
		/* 开发人员 */
		String developerList = (String) map.get("developerList");
		deleteByParam(4, newProduct);
		getAccountInfo(4, newProduct, developerList);
		/* UED人员 */
		String uedList = (String) map.get("uedList");
		deleteByParam(5, newProduct);
		getAccountInfo(5, newProduct, uedList);

		/*添加日志*/
        for(String text : changeLogTextList){
            ProductEmployeeChangeLog employeeChangeLog = new ProductEmployeeChangeLog();
            employeeChangeLog.setProductId(newProduct.getId());
            employeeChangeLog.setActionChangeInfo(text);
            employeeChangeLog.setModifiedAccountId(newProduct.getModifiedAccountId());
            employeeChangeLog.setModifiedName(newProduct.getModifiedName());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
            productEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);
        }

		return true;
	}
   /**
    * 删除操作
    * @param type
    * @param product
    */
	private void deleteByParam(int type, Product product) {
		ProductEmployee productEmployee = new ProductEmployee();
		productEmployee.setEmployeeType(type);
		productEmployee.setProductId(product.getId());
		productEmployeeMapper.delete(productEmployee);

	}

	/**
	 * 查询所有数据A-Z排序
	 */
	public List<Product> getListSort(){
		return productMapper.getListSort();
	}

	public boolean getAccountInfo(int num, Product product, String str) {
		if (num == 0) {
			AccountLongfor accountInfo = adsHelp.getAccountLongforByLoginName(product.getContactAccountId());
			if (accountInfo != null) {
				product.setContactEmployeeCode(Long.parseLong(accountInfo.getPsEmployeeCode()));
				product.setContactEmployeeName(accountInfo.getName());
				product.setContactFullDeptPath(accountInfo.getPsDeptFullName());
			}
		} else {
			if (str != null && str != "") {
				String[] split = str.split(",");
				if (split != null) {
					for (int i = 1; i < split.length; i++) {
						String loginName = split[i];
						AccountLongfor accountInfo = adsHelp.getAccountLongforByLoginName(loginName);
						if (accountInfo != null) {
							ProductEmployee productEmployee = new ProductEmployee();
							productEmployee.setProductId(product.getId());
							productEmployee.setAccountId(loginName);
							productEmployee.setEmployeeCode(Long.parseLong(accountInfo.getPsEmployeeCode()));
							productEmployee.setEmployeeName(accountInfo.getName());
							productEmployee.setFullDeptPath(accountInfo.getPsDeptFullName());
							productEmployee.setStatus(AvaStatusEnum.AVA.getCode());
							if (num == 1) {
								productEmployee.setEmployeeType(AvaStatusEnum.LIABLEAVA.getCode());
							} else if (num == 2) {
								productEmployee.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
								productEmployee.setEmployeeTypeId(new Long(AvaStatusEnum.PRODAVA.getCode()));
								productEmployee.setEmployeeTypeText(AvaStatusEnum.PRODAVA.getText());
							} else if (num == 3) {
								productEmployee.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
								productEmployee.setEmployeeTypeId(new Long(AvaStatusEnum.PROGAVA.getCode()));
								productEmployee.setEmployeeTypeText(AvaStatusEnum.PROGAVA.getText());
							} else if (num == 4) {
								productEmployee.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
								productEmployee.setEmployeeTypeId(new Long(AvaStatusEnum.DEVEAVA.getCode()));
								productEmployee.setEmployeeTypeText(AvaStatusEnum.DEVEAVA.getText());
							} else if (num == 5) {
								productEmployee.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
								productEmployee.setEmployeeTypeId(new Long(AvaStatusEnum.UEDAVA.getCode()));
								productEmployee.setEmployeeTypeText(AvaStatusEnum.UEDAVA.getText());
							}
							productEmployee.setCreateTime(TimeUtils.getTodayByDateTime());
							productEmployee.setModifiedTime(TimeUtils.getTodayByDateTime());
							productEmployeeMapper.insert(productEmployee);
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	@Transactional
	public boolean updateStatus(Map paramsMap) {
		Long productId =  Long.parseLong((String)paramsMap.get("productId"));
		int status =  Integer.parseInt((String)paramsMap.get("status"));
		String modifiedName = (String)paramsMap.get("modifiedName");
		String modifiedAccountId = (String)paramsMap.get("modifiedAccountId");

		Product oldProduct =  productMapper.selectByPrimaryKey(productId);
		String text = statusLog(oldProduct , paramsMap);
		if(StringUtils.isNotBlank(text)){
			ProductEmployeeChangeLog employeeChangeLog = new ProductEmployeeChangeLog();
			employeeChangeLog.setProductId(productId);
			employeeChangeLog.setActionChangeInfo(text);
			employeeChangeLog.setModifiedAccountId(modifiedAccountId);
			employeeChangeLog.setModifiedName(modifiedName);
			employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
			employeeChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
			productEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);
		}
		oldProduct.setStatus(status);
		productMapper.updateByPrimaryKey(oldProduct);
		return true;
	}

	private List<String> getChangeLogText(Product oldProduct, Product newProduct){
	    List<String> textList = new ArrayList<>();

	    if(oldProduct == null && newProduct != null){
            StringBuilder sb = new StringBuilder();
            sb.append(newProduct.getModifiedName())
                    .append(" 创建产品");
            textList.add(sb.toString());
            return textList;
        }
		//判断状态code是否有效
        if((Objects.nonNull(ProductStatusEnum.getByCode(newProduct.getStatus())) && !Objects.equals(oldProduct.getStatus(), newProduct.getStatus()))
                || !Objects.equals(oldProduct.getType(), newProduct.getType())){
            StringBuilder sb = new StringBuilder();
            if(!Objects.equals(oldProduct.getStatus(), newProduct.getStatus())){
                sb.append(newProduct.getModifiedName())
                        .append(" 将 产品状态 从 [")
                        .append(ProductStatusEnum.getByCode(oldProduct.getStatus()).getText())
                        .append("] 更新为 [")
                        .append(ProductStatusEnum.getByCode(newProduct.getStatus()).getText())
                        .append("] ");
            }
            if(!Objects.equals(oldProduct.getType(), newProduct.getType())){
                if(StringUtils.isNotBlank(sb.toString())){
                    sb.append(",");
                } else {
                    sb.append(newProduct.getModifiedName());
                }
                sb.append(" 将 公开性 从 [")
                        .append(PublicTypeEnum.getByCode(oldProduct.getType()).getText())
                        .append("] 更新为 [")
                        .append(PublicTypeEnum.getByCode(newProduct.getType()).getText())
                        .append("]");
            }

            textList.add(sb.toString());
        }

        if(!Objects.equals(oldProduct.getName(), newProduct.getName())
                || !Objects.equals(oldProduct.getContactAccountId(), newProduct.getContactAccountId())
                || !Objects.equals(oldProduct.getLikeProgram(), newProduct.getLikeProgram())
                || !Objects.equals(oldProduct.getDescp(), newProduct.getDescp())){
            StringBuilder sb = new StringBuilder();
            sb.append(newProduct.getModifiedName())
                    .append(" 修改了产品基础信息");
            textList.add(sb.toString());
        }

        return textList;
    }

    //产品状态修改日志
    private String statusLog(Product oldProduct,Map paramsMap){

		Integer status = null;
		//判断是否为更改状态
		if(StringUtils.isNotBlank((String)paramsMap.get("status"))){
			status =  Integer.parseInt((String)paramsMap.get("status"));
		}
		String modifiedName = (String)paramsMap.get("modifiedName");
		StringBuilder sb = new StringBuilder();
		if(status != null && ProductStatusEnum.getByCode(status) != null && !Objects.equals(oldProduct.getStatus(), status)){
			sb.append(modifiedName)
					.append(" 将 产品状态 从 [")
					.append(ProductStatusEnum.getByCode(oldProduct.getStatus()).getText())
					.append("] 更新为 [")
					.append(ProductStatusEnum.getByCode((status)).getText())
					.append("] ");
		}
		return  sb.toString();
	}
}
