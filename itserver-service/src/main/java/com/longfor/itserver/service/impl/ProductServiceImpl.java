package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.util.DateUtil;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.entity.ProductEmployeeChangeLog;
import com.longfor.itserver.mapper.ProductEmployeeChangeLogMapper;
import com.longfor.itserver.mapper.ProductEmployeeMapper;
import com.longfor.itserver.mapper.ProductMapper;
import com.longfor.itserver.service.IProductService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	public List<Product> searchLikeList(Map map) {
		return productMapper.selectLikeList(map);
	}

	@Transactional
	@Override
	public boolean addProduct(Map map) {
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		Product product = JSONObject.toJavaObject(jsonObject, Product.class);
		String descp = product.getDescp();
		if (descp != null && descp.length() > 3000) {
			product.setDescp(descp.substring(0, 3000));
		}
		product.setStatus(Integer.parseInt(jsonObject.getString("productStatus")));
		product.setContactAccountId(jsonObject.getString("contactAccountId"));
		product.setLikeProgram(jsonObject.getString("likeProgram"));
		/* 接口人相关信息 */
		getAccountInfo(0, product, null);
		/* 添加产品 */
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

		return true;
	}

	@Override
	public List<Product> searchIdList(String likeProduct) {
		return productMapper.selectIdList(likeProduct);
	}

	@Transactional
	@Override
	public boolean updateProduct(Map map) {
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		Product product = JSONObject.toJavaObject(jsonObject, Product.class);
		Product selectOne = productMapper.selectByPrimaryKey(product.getId());
		selectOne.setName(jsonObject.getString("name"));
		selectOne.setDescp(jsonObject.getString("descp"));
		selectOne.setStatus(Integer.parseInt(jsonObject.getString("productStatus")));
		selectOne.setContactAccountId(jsonObject.getString("contactAccountId"));
		selectOne.setType(jsonObject.getInteger("type"));	
		/* 接口人相关信息 */
		getAccountInfo(0, selectOne, null);
		/* 关联项目 */
		selectOne.setLikeProgram(product.getLikeProgram());
				/*添加日志*/
		this.addLog(map);
		int update = productMapper.updateByPrimaryKey(selectOne);// 更新产品

		/* 产品责任人 */
		String personLiableList = (String) map.get("personLiableList");
		deleteByParam(1, product);
		getAccountInfo(1, product, personLiableList);
		/* 产品经理 */
		String programManagerList = (String) map.get("programManagerList");
		deleteByParam(2, product);
		getAccountInfo(2, product, programManagerList);
		/* 项目经理 */
		String productManagerList = (String) map.get("productManagerList");
		deleteByParam(3, product);
		getAccountInfo(3, product, productManagerList);
		/* 开发人员 */
		String developerList = (String) map.get("developerList");
		deleteByParam(4, product);
		getAccountInfo(4, product, developerList);
		/* UED人员 */
		String uedList = (String) map.get("uedList");
		deleteByParam(5, product);
		getAccountInfo(5, product, uedList);


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
							productEmployeeMapper.insert(productEmployee);
						}
					}
				}
			}
		}
		return true;
	}

	public boolean addLog(Map paramsMap){
		JSONObject jsonObject = (JSONObject)JSONObject.toJSON(paramsMap);
		ProductEmployeeChangeLog employeeChangeLog = JSONObject.toJavaObject(jsonObject,ProductEmployeeChangeLog.class);


		employeeChangeLog.setProductId(Long.valueOf(jsonObject.getString("id")));
		employeeChangeLog.setCreateTime(new Date());

		StringBuffer info = new StringBuffer();
		info.append(employeeChangeLog.getModifiedName());
		info.append("于");
		info.append(DateUtil.getCurrentTime(new Date()));
		info.append("更新了");
		info.append(jsonObject.getString("name"));
		info.append("的信息。");
		employeeChangeLog.setActionChangeInfo(info.toString());
		productEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);

		return true;
	}


}
