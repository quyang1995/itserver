package com.longfor.itserver.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.DateUtil;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.common.vo.BuddyAccount;
import com.longfor.itserver.common.vo.OperationLogVo;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.DemandChangeLog;
import com.longfor.itserver.esi.impl.AdsServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 * 操作日志
 */
@RequestMapping(value = "/api/operationLog")
@Controller
public class OperationLogController extends BaseController {

	public static final String PRODUCT_LIST = "产品列表";
	public static final String BUG_LIST = "BUG列表";
	public static final String PROGRAM_LIST = "项目列表";
	public static final String DEMAND_LIST = "需求列表";
	/***
	 * 操作日志列表
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map bugList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		String operateTime = StringUtils.isBlank(paramsMap.get("operateTime"))
				? DateUtil.getPastDate(30) : paramsMap.get("operateTime");//查询30天内日志
		String type = paramsMap.get("type");//操作类型：0-产品，1-项目，2-需求，3-bug
		PageHelper.startPage(Integer.parseInt(paramsMap.get("pageNum")), Integer.parseInt(paramsMap.get("pageSize")), true);

		paramsMap.put("operateTime",operateTime);

		BuddyAccount buddyAccount = AdsServiceImpl.buddyAccountGetByLoginName(paramsMap.get("loginName"));
		if(buddyAccount==null){
			return CommonUtils.getResultMapByBizEnum(BizEnum.E1001);
		}

		List list = new ArrayList();
		try{
			if(StringUtils.isNotBlank(type)){//只需查询一张日志表
				if(type.equals("0")){
					list.addAll(this.convertProduct2OperationLogVo(
							getProductEmployeeChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),PRODUCT_LIST));
				}else if(type.equals("1")){
					list.addAll(this.convertProduct2OperationLogVo(
							getProgramEmployeeChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),PROGRAM_LIST));
				}else if(type.equals("2")){
					list.addAll(this.convertProduct2OperationLogVo(
							getDemandChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),DEMAND_LIST));
				}else if(type.equals("3")){
					list.addAll(this.convertProduct2OperationLogVo(
							getBugChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),BUG_LIST));
				}
			}
//			else{
//				list.addAll(this.convertProduct2OperationLogVo(
//						getProductEmployeeChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),PRODUCT_LIST));
//				list.addAll(this.convertProduct2OperationLogVo(
//						getProgramEmployeeChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),PROGRAM_LIST));
//				list.addAll(this.convertProduct2OperationLogVo(
//						getDemandChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),DEMAND_LIST));
//				list.addAll(this.convertProduct2OperationLogVo(
//						getBugChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),BUG_LIST));
//			}
		} catch (Exception e){
			e.printStackTrace();
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}

        /*返回数据*/
		Map<String, Object> map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		map.put("list", list);
		map.put(net.mayee.commons.helper.APIHelper.PAGE_NUM, Integer.parseInt(paramsMap.get("pageNum")));
		map.put(net.mayee.commons.helper.APIHelper.PAGE_SIZE, Integer.parseInt(paramsMap.get("pageSize")));
		map.put(net.mayee.commons.helper.APIHelper.TOTAL, new PageInfo(list).getTotal());
		return map;
	}

	private <T> List<OperationLogVo> convertProduct2OperationLogVo
			(List<T> list,String companyName,String operateModule) throws Exception{
		List<OperationLogVo> operationLogVoList = new ArrayList<>();
		for(Object objList:list){
			OperationLogVo tmpOperationLogVo = new OperationLogVo(companyName,operateModule);
			BeanUtils.copyProperties(tmpOperationLogVo,objList);
			operationLogVoList.add(tmpOperationLogVo);
		}
		return operationLogVoList;
	}
}