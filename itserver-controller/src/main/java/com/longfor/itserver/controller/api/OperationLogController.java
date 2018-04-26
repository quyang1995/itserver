package com.longfor.itserver.controller.api;

import com.github.pagehelper.PageInfo;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.DateUtil;
import com.longfor.itserver.common.vo.BuddyAccount;
import com.longfor.itserver.common.vo.OperationLogVo;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.esi.impl.AdsServiceImpl;
import net.mayee.commons.helper.APIHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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
	public Map list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

			String operateTime = StringUtils.isBlank(paramsMap.get("operateTime"))
					? DateUtil.date2String(new Date(),"yyyy-MM-dd") : paramsMap.get("operateTime");
			String type = paramsMap.get("type");//操作类型：0-产品，1-项目，2-需求，3-bug
			int pageNum = Integer.parseInt(paramsMap.get("pageNum"));
			int pageSize = Integer.parseInt(paramsMap.get("pageSize"));

			paramsMap.put("operateTime",operateTime);

			BuddyAccount buddyAccount = AdsServiceImpl.buddyAccountGetByLoginName(paramsMap.get("loginName"));
			if(buddyAccount==null){
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1001);
			}

			List<OperationLogVo> list = new ArrayList();
			if("0".equals(type)){
				list.addAll(this.convertProduct2OperationLogVo(
						getProductEmployeeChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),PRODUCT_LIST,buddyAccount.getName()));
			}else if("1".equals(type)){
				list.addAll(this.convertProduct2OperationLogVo(
						getProgramEmployeeChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),PROGRAM_LIST,buddyAccount.getName()));
			}else if("2".equals(type)){
				list.addAll(this.convertProduct2OperationLogVo(
						getDemandChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),DEMAND_LIST,buddyAccount.getName()));
			}else if("3".equals(type)){
				list.addAll(this.convertProduct2OperationLogVo(
						getBugChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),BUG_LIST,buddyAccount.getName()));
			}else{
				list.addAll(this.convertProduct2OperationLogVo(
						getProductEmployeeChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),PRODUCT_LIST,buddyAccount.getName()));
				list.addAll(this.convertProduct2OperationLogVo(
						getProgramEmployeeChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),PROGRAM_LIST,buddyAccount.getName()));
				list.addAll(this.convertProduct2OperationLogVo(
						getDemandChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),DEMAND_LIST,buddyAccount.getName()));
				list.addAll(this.convertProduct2OperationLogVo(
						getBugChangeLogService().paraQuery(paramsMap),buddyAccount.getCompanyName(),BUG_LIST,buddyAccount.getName()));
			}
			Map<String, Object> map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
			map.put(APIHelper.TOTAL, new PageInfo(list).getTotal());
			if(list.size()>0){
				Collections.sort(list);
				if(list.size() > pageSize){
					try{
						list = list.subList((pageNum-1)*pageSize,pageNum*pageSize);
					}catch (Exception e){
						list = list.subList((pageNum-1)*pageSize,list.size());
					}

				}
			}
			map.put("list", list);

			map.put(APIHelper.PAGE_NUM, pageNum);
			map.put(APIHelper.PAGE_SIZE, pageSize);

			return map;
		} catch (Exception e){
			e.printStackTrace();
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
	}

	private <T> List<OperationLogVo> convertProduct2OperationLogVo
			(List<T> list,String companyName,String operateModule,String name) throws Exception{
		List<OperationLogVo> operationLogVoList = new ArrayList<>();
		for(Object objList:list){
			OperationLogVo tmpOperationLogVo = new OperationLogVo(companyName,operateModule);
			tmpOperationLogVo.setName(name);
			BeanUtils.copyProperties(tmpOperationLogVo,objList);
			operationLogVoList.add(tmpOperationLogVo);
		}
		return operationLogVoList;
	}
}