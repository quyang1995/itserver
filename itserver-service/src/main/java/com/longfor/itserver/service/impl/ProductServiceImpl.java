package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.eds.helper.EDSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.enums.ProductStatusEnum;
import com.longfor.itserver.common.enums.PublicTypeEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.DateUtil;
import com.longfor.itserver.common.util.StringUtil;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.entity.ProductEmployeeChangeLog;
import com.longfor.itserver.entity.ps.PsProductCount;
import com.longfor.itserver.mapper.ProductEmployeeChangeLogMapper;
import com.longfor.itserver.mapper.ProductEmployeeMapper;
import com.longfor.itserver.mapper.ProductMapper;
import com.longfor.itserver.service.IProductService;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.util.AccountUitl;
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
	private EDSHelper edsHelper;
	@Autowired
	private ProductEmployeeChangeLogMapper productEmployeeChangeLogMapper;

	@Override
	public List<PsProductCount> searchList(Map map) {
		List<PsProductCount> list= productMapper.selectList(map);
		this.getPersonLiableList(list);
		return list;
	}

	@Override
	public List<Map> exportList(Map map) {
		List<Map> list= productMapper.exportList(map);
//		for (Map obj:list){
//			Object analyzingConditions=obj.get("analyzingConditions");
//			String anaName = "";
//			if(analyzingConditions!=null){
//				anaName = obj.get("analyzingConditions").toString().replace("1","地产系统相关")
//						.replace("2","商业系统相关").replace("3","数据系统相关")
//						.replace("4","冠寓系统相关").replace("5","养老系统相关")
//						.replace("6","产城系统相关").replace("7","基础中心系统相关");
//			}
//			obj.put("anaName",anaName);
//		}
		return list;
	}

	/**
	 * 产品责任人
	 * @param list
	 */
	private void getPersonLiableList(List<PsProductCount> list){
		for(PsProductCount model:list){
			ProductEmployee productEmployee  = new ProductEmployee();
			productEmployee.setEmployeeType(AvaStatusEnum.LIABLEAVA.getCode());
			productEmployee.setProductId(model.getId());
			model.setPersonLiableList(productEmployeeMapper.select(productEmployee));
		}
	}

	@Override
	public List<Product> productCountList(Map map){
		return productMapper.productCountList(map);
	}

	@Override
	public List<PsProductCount> searchLikeList(Map map) {
		List<PsProductCount> productList = productMapper.selectLikeList(map);
		this.getPersonLiableList(productList);
		return productList;
	}

	@Transactional
	@Override
	public Map<String, Object> addProduct(Map map) {
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		Product product = JSONObject.toJavaObject(jsonObject, Product.class);

		Product codeCheckProduct = new Product();
		//name唯一检查
		String productName = product.getName();
		codeCheckProduct.setName(productName);
		if(StringUtils.isBlank(productName) || productMapper.select(codeCheckProduct).size() > 0){
			return CommonUtils.getResultMapByBizEnum(BizEnum.E1037, productName);
		}

		//code唯一检查
		String code = this.generateProductNewCode();
		codeCheckProduct.setName(null);
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
		Integer accountType = AccountUitl.getAccountType(map);

		product.setLogo(jsonObject.getString("logo"));
		product.setContactAccountId1(jsonObject.getString("contactAccountId1"));
		product.setLabel(jsonObject.getString("label"));
		product.setPcUrl(jsonObject.getString("pcUrl"));
		product.setH5Url(jsonObject.getString("h5Url"));
		product.setIosUrl(jsonObject.getString("iosUrl"));
		product.setAndroidUrl(jsonObject.getString("androidUrl"));
		product.setLabelName(jsonObject.getString("labelName"));
		product.setWindowsUrl(jsonObject.getString("windowsUrl"));
		product.setMacUrl(jsonObject.getString("macUrl"));
		/* 接口人相关信息 */
		getAccountInfo(0, product, null);
		/* 添加产品 */
		product.setCreateTime(TimeUtils.getTodayByDateTime());
		product.setModifiedTime(TimeUtils.getTodayByDateTime());
		product.setAccountType(accountType);
		product.setNewCode(code);
		product.setCode(code);
        product.setIsTop("0");
		product.setAnalyzingConditions(jsonObject.getString("analyzingConditions"));
		int insert = productMapper.insert(product);
		/* 产品责任人 */
		String personLiableList = (String) map.get("personLiableList");
		getAccountInfo(1, product, personLiableList);
		/* 产品经理 */
		String productManagerList = (String) map.get("productManagerList");
		getAccountInfo(2, product, productManagerList);
		/* 项目经理 */
		String programManagerList = (String) map.get("programManagerList");
		getAccountInfo(3, product, programManagerList);
		/* 开发人员 */
		String developerList = (String) map.get("developerList");
		getAccountInfo(4, product, developerList);
		/* UED人员 */
		String uedList = (String) map.get("uedList");
		getAccountInfo(5, product, uedList);
		/* 测试人员 */
		String testList = (String) map.get("testingList");
		getAccountInfo(6, product, testList);
		/* 业务人员 */
		String businessList = (String) map.get("businessList");
		getAccountInfo(7, product, businessList);


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
			employeeChangeLog.setAccountType(accountType);
			productEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);
		}

		return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
	}

	/**
	 * 根据规则生成新的产品code
	 * 例：CP000001
	 * @return
	 */
	private String generateProductNewCode(){
		String newCode = productMapper.getNewCode();
		if (StringUtils.isBlank(newCode)) {
			newCode = "CP000001";
			return newCode;
		}
		Integer newNum = Integer.parseInt(newCode.substring(2,newCode.length()))+1;
		String newNumStr = newNum.toString();
		Integer j  = newNumStr.length();
		for(int i = 0; i< 6 - j; i++) {
			newNumStr = "0" + newNumStr;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("CP" + newNumStr);
		return sb.toString();
	}

	@Override
	public List<Product> searchIdList(String likeProduct) {
		return productMapper.selectIdList(likeProduct);
	}

	@Transactional
	@Override
	public boolean updateProduct(Map map) throws Exception{
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		Integer accountType = AccountUitl.getAccountType(map);
		Product newProduct = JSONObject.toJavaObject(jsonObject, Product.class);
		Product oldProduct = productMapper.selectByPrimaryKey(newProduct.getId());

		//先生成变动日志
		List<String> changeLogTextList = getChangeLogText(oldProduct, newProduct);

		oldProduct.setName(jsonObject.getString("name"));
		oldProduct.setAnalyzingConditions(jsonObject.getString("analyzingConditions"));
		oldProduct.setDescp(jsonObject.getString("descp"));
		oldProduct.setStatus(jsonObject.getInteger("status"));
		oldProduct.setContactAccountId(jsonObject.getString("contactAccountId"));
		oldProduct.setType(jsonObject.getInteger("type"));

		oldProduct.setContactAccountId1(jsonObject.getString("contactAccountId1"));
		oldProduct.setLabel(jsonObject.getString("label"));
		if(StringUtils.isNotBlank(jsonObject.getString("logo")))oldProduct.setLogo(jsonObject.getString("logo"));
		oldProduct.setPcUrl(jsonObject.getString("pcUrl"));
		oldProduct.setH5Url(jsonObject.getString("h5Url"));
		oldProduct.setIosUrl(jsonObject.getString("iosUrl"));
		oldProduct.setAndroidUrl(jsonObject.getString("androidUrl"));
		oldProduct.setLabelName(jsonObject.getString("labelName"));
		oldProduct.setWindowsUrl(jsonObject.getString("windowsUrl"));
		oldProduct.setMacUrl(jsonObject.getString("macUrl"));
		oldProduct.setIosVersion(jsonObject.getString("iosVersion"));
		oldProduct.setAndroidVersion(jsonObject.getString("androidVersion"));
		if(StringUtils.isNotBlank(jsonObject.getString("versionModifiedTime"))){
			oldProduct.setVersionModifiedTime(DateUtil.string2Date(jsonObject.getString("versionModifiedTime"),DateUtil.PATTERN_DATE));
		}

		/* 接口人相关信息 */
		getAccountInfo(0, oldProduct, null);
		/* 关联项目 */
		oldProduct.setLikeProgram(newProduct.getLikeProgram());
		oldProduct.setModifiedTime(TimeUtils.getTodayByDateTime());
		oldProduct.setAccountType(accountType);
		int update = productMapper.updateByPrimaryKey(oldProduct);// 更新产品

		/* 产品责任人 */
		String personLiableList = (String) map.get("personLiableList");
		deleteByParam(1, newProduct);
		getAccountInfo(1, newProduct, personLiableList);
		/* 产品经理 */
		String productManagerList = (String) map.get("productManagerList");
		deleteByParam(2, newProduct);
		getAccountInfo(2, newProduct, productManagerList);
		/* 项目经理 */
		String programManagerList = (String) map.get("programManagerList");
		getAccountInfo(3, newProduct, programManagerList);
		/* 开发人员 */
		String developerList = (String) map.get("developerList");
		getAccountInfo(4, newProduct, developerList);
		/* UED人员 */
		String uedList = (String) map.get("uedList");
		getAccountInfo(5, newProduct, uedList);
		/* 测试人员 */
		String testList = (String) map.get("testingList");
		getAccountInfo(6, newProduct, testList);
		/* 业务人员 */
		String businessList = (String) map.get("businessList");
		getAccountInfo(7, newProduct, businessList);

		/*添加日志*/
		for(String text : changeLogTextList){
			ProductEmployeeChangeLog employeeChangeLog = new ProductEmployeeChangeLog();
			employeeChangeLog.setProductId(newProduct.getId());
			employeeChangeLog.setActionChangeInfo(text);
			employeeChangeLog.setModifiedAccountId(newProduct.getModifiedAccountId());
			employeeChangeLog.setModifiedName(newProduct.getModifiedName());
			employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
			employeeChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
			employeeChangeLog.setAccountType(accountType);
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
	public List<Product> getListSort(Map map){
		return productMapper.getListSort(map);
	}

	public boolean getAccountInfo(int num, Product product, String str) {
		if (num == 0) {
			//功能建议反馈接口人
			AccountLongfor accountInfo =
					AccountUitl.getAccountByAccountTypes(product.getContactAccountId(),adsHelp,edsHelper);
			if (accountInfo != null) {
				product.setContactEmployeeCode(StringUtil.getLongValue(accountInfo.getPsEmployeeCode()));
				product.setContactEmployeeName(accountInfo.getName());
				product.setContactFullDeptPath(accountInfo.getPsDeptFullName());
			}
			//功能异常反馈接口人
			AccountLongfor accountInfo1 =
					AccountUitl.getAccountByAccountTypes(product.getContactAccountId1(),adsHelp,edsHelper);
			if (accountInfo1 != null) {
				product.setContactEmployeeCode1(StringUtil.getLongValue(accountInfo1.getPsEmployeeCode()));
				product.setContactEmployeeName1(accountInfo1.getName());
				product.setContactFullDeptPath1(accountInfo1.getPsDeptFullName());
			}
		} else {
			if (str != null && str != "") {
				String[] split = str.split(",");
				if (split != null) {
					for (int i = 1; i < split.length; i++) {
						String loginName = split[i];
						AccountLongfor accountInfo =
								AccountUitl.getAccountByAccountTypes(loginName,adsHelp,edsHelper);
						if (accountInfo != null) {
							ProductEmployee productEmployee = new ProductEmployee();
							productEmployee.setProductId(product.getId());
							productEmployee.setAccountId(loginName);
							productEmployee.setEmployeeCode(StringUtil.getLongValue(accountInfo.getPsEmployeeCode()));
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
							} else if (num == 6) {
								productEmployee.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
								productEmployee.setEmployeeTypeId(new Long(AvaStatusEnum.TESTINGAVA.getCode()));
								productEmployee.setEmployeeTypeText(AvaStatusEnum.TESTINGAVA.getText());
							} else if (num == 7) {
								productEmployee.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
								productEmployee.setEmployeeTypeId(new Long(AvaStatusEnum.BUSINESSAVA.getCode()));
								productEmployee.setEmployeeTypeText(AvaStatusEnum.BUSINESSAVA.getText());
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
		Integer accountType = AccountUitl.getAccountType(paramsMap);

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
			employeeChangeLog.setAccountType(accountType);
			productEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);
		}
		oldProduct.setStatus(status);
		oldProduct.setModifiedTime(TimeUtils.getTodayByDateTime());
		oldProduct.setAccountType(accountType);
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

	@Override
	public List<PsProductCount> productHui(Map<String,Object> map) {
		List<PsProductCount> list= productMapper.productHui(map);
		this.getPersonLiableList(list);
		return list;
	}

	@Override
	public int productHuiNum(Map map) {
		return productMapper.productHuiNum(map);
	}

	@Override
	public int getCountByLabelId(String label) {
		return productMapper.getCountByLabelId(label);
	}
}
