package com.longfor.itserver.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.enums.BugStatusEnum;
import com.longfor.itserver.common.enums.ProgramStatusEnum;
import com.longfor.itserver.common.helper.DataPermissionHelper;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.EmployeeType;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramEmployee;
import com.longfor.itserver.entity.ps.PsProgramDetail;
import net.mayee.commons.helper.APIHelper;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目
 *
 * @author wax
 * @create 2017/8/5 下午2:19
 *
 * @version v1.0
 **/
@RequestMapping(value = "/api/program")
@Controller
public class APIProgramController extends BaseController {

	/**
	 * 项目列表
	 *
	 * @author lovex
	 * @create 2017/8/5 下午2:25
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map programList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		/* 生成查询用Example */
		ELExample elExample = new ELExample(request, Program.class);
		/* 查询数据 and admin权限判断 */
		String accountId = paramsMap.get("accountId");
		paramsMap.put("isAdmin", DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0");
		PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
		List<Program> programList = this.getProgramService().programList(paramsMap);


		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("list", programList);
		resultMap.put(APIHelper.PAGE_NUM, elExample.getPageNum());
		resultMap.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
		resultMap.put(APIHelper.TOTAL, new PageInfo(programList).getTotal());
		return resultMap;
	}

	/**
	 * 通过产品ID获取项目列表
	 *
	 * @author lovex
	 * @create 2017/8/5 下午2:25
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/productIdList", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map productIdList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		/* 生成查询用Example */
		ELExample elExample = new ELExample(request, Program.class);
		/* 查询数据 and admin权限判断 */
		String accountId = paramsMap.get("accountId");
		paramsMap.put("isAdmin", DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0");
		PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
		List<Program> programList = this.getProgramService().productIdList(paramsMap);


		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("list", programList);
		resultMap.put(APIHelper.PAGE_NUM, elExample.getPageNum());
		resultMap.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
		resultMap.put(APIHelper.TOTAL, new PageInfo(programList).getTotal());
		return resultMap;
	}

	/**
	 * 项目列表返回最新20条
	 *
	 * @author lovex
	 * @create 2017/8/5 下午2:25
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/limitList", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map programLimitList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		List<Program> programList = this.getProgramService().programLimitList(paramsMap);
		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("list", programList);
		return resultMap;
	}

	/**
	 * 通过ID获取项目基本信息
	 *
	 * @author lovex
	 * @create 2017/8/5 下午2:25
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map programGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		long id = Long.parseLong(paramsMap.get("id").toString());

		PsProgramDetail program = (PsProgramDetail) this.getProgramService().getProgramId(id);
		if(program.getLikeProduct() != null && !"".equals(program.getLikeProduct())){
			String likeProduct = program.getLikeProduct().substring(1, program.getLikeProduct().length());
			// 关联产品
			List<Product> product = this.getProductService().searchIdList(likeProduct);
			program.setProductList(product);
		}

		Map map = new HashMap();
		map.put("programId", new Long(id));
		/* 产品相关人员 */
		/* 责任人 */
		map.put("employeeType", AvaStatusEnum.LIABLEAVA.getCode());
		List<ProgramEmployee> personLiableList = this.getProgramEmployeeService().selectTypeList(map);
		program.setPersonLiableList(personLiableList);
		/* 产品经理 */
		map.put("employeeType", AvaStatusEnum.MEMBERAVA.getCode());
		map.put("employeeTypeId", new Long(AvaStatusEnum.PRODAVA.getCode()));
		List<ProgramEmployee> programManagerList = this.getProgramEmployeeService().selectTypeList(map);
		program.setProgramManagerList(programManagerList);
		/* 项目经理 */
		map.put("employeeTypeId", new Long(AvaStatusEnum.PROGAVA.getCode()));
		List<ProgramEmployee> productManagerList = this.getProgramEmployeeService().selectTypeList(map);
		program.setProductManagerList(productManagerList);
		/* 开发人员 */
		map.put("employeeTypeId", new Long(AvaStatusEnum.DEVEAVA.getCode()));
		List<ProgramEmployee> developerList = this.getProgramEmployeeService().selectTypeList(map);
		program.setDeveloperList(developerList);
		/* UED人员 */
		map.put("employeeTypeId", new Long(AvaStatusEnum.UEDAVA.getCode()));
		List<ProgramEmployee> uedList = this.getProgramEmployeeService().selectTypeList(map);
		program.setUedList(uedList);
		/* 测试人员 */
		map.put("employeeTypeId", new Long(AvaStatusEnum.TESTINGAVA.getCode()));
		List<ProgramEmployee> testingList = this.getProgramEmployeeService().selectTypeList(map);
		program.setTestingList(testingList);
		/* 业务人员 */
		map.put("employeeTypeId", new Long(AvaStatusEnum.BUSINESSAVA.getCode()));
		List<ProgramEmployee> businessList = this.getProgramEmployeeService().selectTypeList(map);
		program.setBusinessList(businessList);

		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("data", program);
		return resultMap;
	}

	/**
	 * 新增项目列表
	 *
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map programAdd(HttpServletResponse response, HttpServletRequest request) throws IOException, JSONException {

		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		this.getProgramService().addProgram(paramsMap);
		// 返回报文
		return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
	}

	/**
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map programUpdate(HttpServletResponse response, HttpServletRequest request)
			throws IOException, JSONException {

		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		/* 责任人 */
		Map map = new HashMap();
		map.put("programId", paramsMap.get("id"));
		boolean isAllow = false;
		map.put("employeeType", AvaStatusEnum.LIABLEAVA.getCode());
		List<ProgramEmployee> personLiableList = this.getProgramEmployeeService().selectTypeList(map);
		if(!"".equals(paramsMap.get("modifiedAccountId"))){
			for(ProgramEmployee programEmployee : personLiableList){
				if(programEmployee.getAccountId().equals(paramsMap.get("modifiedAccountId"))){
					isAllow = true;
					break;
				}
			}
		}else {
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9993,"modifiedAccountId");
		}
		if(isAllow){
            /*更新操作*/
			this.getProgramService().updateProgram(paramsMap);
			// 返回报文
			return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_U);
		}else{
			// 返回报文
			return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
		}
	}

	@RequestMapping(value = "/changeLog/list" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map changeLogList(HttpServletResponse response,HttpServletRequest request){

		Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		return this.getProgramEmployeeChangeLogService().orderLimitList(paramsMap);
	}




	/**
	 * 根据accountId productId 删除产品相关人员
	 *
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/delEmployee", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map delEmp(HttpServletRequest request, HttpServletResponse response) {
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		String programId = (String) paramsMap.get("programId");
		String accountId = (String) paramsMap.get("accountId");
		String accountType = (String) paramsMap.get("accountType");
		ProgramEmployee employee = new ProgramEmployee();
		employee.setAccountId(accountId);
		employee.setProgramId(Long.valueOf(programId));
		employee.setEmployeeType(AvaStatusEnum.LIABLEAVA.getCode());
		ProgramEmployee persionaLiable = this.getProgramEmployeeService().selectOne(employee);
		if (persionaLiable != null) {
			//是责任人
			ProgramEmployee programEmployee = new ProgramEmployee();
			programEmployee.setProgramId(Long.valueOf(programId));
			programEmployee.setEmployeeType(AvaStatusEnum.LIABLEAVA.getCode());
			int persionaLiableCount =  this.getProgramEmployeeService().selectCount(programEmployee);
			if (persionaLiableCount > 1) {
				//删除当前用户
				this.getProgramEmployeeService().delEmployee(employee,accountType);
			} else {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1027, " 唯一责任人");
			}
		} else {
			//删除成员
			employee.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
			this.getProgramEmployeeService().delEmployee(employee,accountType);
		}

		return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
	}


	/**
	 * 通过产品ID获取相关项目列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/productIdAllList",method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map productIdAllList(HttpServletRequest request,	HttpServletResponse response){
		Map parsmsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		List list = this.getProgramService().productIdAllList(parsmsMap);
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("list",list);
		return resultMap;
	}


	@RequestMapping(value = "/update/status",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map updateStatus(HttpServletRequest request,HttpServletResponse response){
		Map paramsMap= (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		//状态值有效性验证
		int code = Integer.parseInt((String) paramsMap.get("status"));
		ProgramStatusEnum programStatusEnum = ProgramStatusEnum.getByCode(code);
		if(programStatusEnum != null){
			this.getProgramService().updateStatus(paramsMap);
			Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_U);
			resultMap.put("newStatusText", programStatusEnum.getText());
			return resultMap;
		}else{
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
		}
	}
}