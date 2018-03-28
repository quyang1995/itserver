package com.longfor.itserver.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.*;
import com.longfor.itserver.common.helper.DataPermissionHelper;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.entity.ps.PsProgramDetail;
import com.longfor.itserver.service.util.AccountUitl;
import net.mayee.commons.helper.APIHelper;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
		Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		/* 生成查询用Example */
		ELExample elExample = new ELExample(request, Program.class);
		/* 查询数据 and admin权限判断 */
//		String accountId = paramsMap.get("accountId").toString();
//		paramsMap.put("isAdmin", DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0");
		paramsMap.put("isAdmin", "1");
		PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
//		elExample.getExample().setOrderByClause("convert(substr(name,1,1) using 'GBK') desc");
		String programStatus = paramsMap.get("programStatus").toString();
		if(StringUtils.isNotBlank(programStatus) && !"0".equals(programStatus)){
			String [] programStatusList = programStatus.split(",");
			paramsMap.put("programStatusList",programStatusList);
		}
		List<PsProgramDetail> programList = this.getProgramService().programList(paramsMap);

		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("list", programList);
		resultMap.put(APIHelper.PAGE_NUM, elExample.getPageNum());
		resultMap.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
		resultMap.put(APIHelper.TOTAL, new PageInfo(programList).getTotal());
		return resultMap;
	}

	/**
	 * 项目列表
	 *
	 * @author lovex
	 * @create 2017/8/5 下午2:25
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/list1", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map programList1(HttpServletRequest request, HttpServletResponse response){
		/* 参数 */
		Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			/* 生成查询用Example */
			ELExample elExample = new ELExample(request, Program.class);
			paramsMap.put("isAdmin", "1");
			PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
			String iSortCol = paramsMap.get("iSortCol").toString();
			String sSortDir = paramsMap.get("sSortDir").toString();
			if("name".equals(iSortCol) || "productName".equals(iSortCol) || "personLiable".equals(iSortCol)){
				elExample.getExample().setOrderByClause("convert(substr("+iSortCol+",1,1) using 'GBK') "+sSortDir);
			} else {
				elExample.getExample().setOrderByClause(iSortCol+" "+sSortDir);
			}
			CommonUtils.buildPageParams(paramsMap);
			String programStatus = paramsMap.get("programStatus").toString();
			if(StringUtils.isNotBlank(programStatus) && !"0".equals(programStatus)){
				String [] programStatusList = programStatus.split(",");
				paramsMap.put("programStatusList",programStatusList);
			}
			List<PsProgramDetail> programList = this.getProgramService().programList1(paramsMap);
			resultMap.put("list", programList);
			resultMap.put(APIHelper.PAGE_NUM, elExample.getPageNum());
			resultMap.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
			resultMap.put(APIHelper.TOTAL, new PageInfo(programList).getTotal());
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
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
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try {
			long id = Long.parseLong(paramsMap.get("id").toString());
			Object accountId = paramsMap.get("accountId");

//			PsProgramDetail program = (PsProgramDetail) this.getProgramService().getProgramId(id);
			PsProgramDetail program = (PsProgramDetail) this.getProgramService().getProgram(id);
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

			//验证当前人员权限********beg
			if (accountId != null && 0 == program.getType() ){
				String isAdmin = DataPermissionHelper.getInstance().isShowAllData(accountId.toString()) ? "1" : "0";
				//判断管理员角色，若为管理员，可以直接查看 0=非管理员，1=管理员
				if ("0".equals(isAdmin)) {
					map.put("accountId", accountId);
					map.remove("employeeType");
					List<ProgramEmployee> accountList = this.getProgramEmployeeService().selectTypeList(map);
					if (accountList == null || accountList.isEmpty()) {
						resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E1035, personLiableList.get(0).getEmployeeName());
						return resultMap;
					}
					map.remove("accountId");
				}
			}
			//验证当前人员权限********end

			/* 产品经理 */
			map.put("employeeType", AvaStatusEnum.MEMBERAVA.getCode());
			map.put("employeeTypeId", new Long(AvaStatusEnum.PRODAVA.getCode()));
			List<ProgramEmployee> programManagerList = this.getProgramEmployeeService().selectTypeList(map);
			program.setProductManagerList(programManagerList);
			/* 项目经理 */
			map.put("employeeTypeId", new Long(AvaStatusEnum.PROGAVA.getCode()));
			List<ProgramEmployee> productManagerList = this.getProgramEmployeeService().selectTypeList(map);
			program.setProgramManagerList(productManagerList);
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
			/* 运维人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.OPERATION.getCode()));
            List<ProgramEmployee> operationList = this.getProgramEmployeeService().selectTypeList(map);
            program.setOperationList(operationList);
            /* 运营人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.OPERATE.getCode()));
            List<ProgramEmployee> operateList = this.getProgramEmployeeService().selectTypeList(map);
            program.setOperateList(operateList);
			/*根據id获取项目快照list*/
			Map grayLevelMap = new HashMap();
			grayLevelMap.put("programId", new Long(id));
			List<ProgramApprovalSnapshot> productList =  this.getProgramApprovalSnapshotService().grayLevelList(grayLevelMap);
			this.setChangeStatus(program,productList);
			/* 灰度时间变更记录 */
			List<ProgramApprovalSnapshot> grayLevelList =  this.getGrayLevelList(productList);
			program.setGrayLevelList(grayLevelList);
			/*项目费用记录*/
			List<ProgramApprovalSnapshot> costRecordList =  this.costRecordList(productList);
			program.setCostRecordList(costRecordList);
			/*项目里程碑*/
			Map milepostMap = new HashMap();
			milepostMap.put("programId", new Long(id));
			List<ProgramApprovalSnapshot> milepostList =this.getProgramService().milepost(milepostMap);
			program.setMilepostList(milepostList);
			/* 返回报文 */
			resultMap.put("data", program);
		} catch (Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * 获取项目变更记录
	 *
	 * @author lovex
	 * @create 2017/8/5 下午2:25
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/getChangeList", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map getChangeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try {
			List<ProgramApprovalSnapshot> productList =  this.getProgramApprovalSnapshotService().grayLevelList(paramsMap);
			/* 灰度时间变更记录 */
			List<ProgramApprovalSnapshot> grayLevelList =  this.getGrayLevelList(productList);
			resultMap.put("data", grayLevelList);
		} catch (Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}

	private void setChangeStatus(PsProgramDetail program,List<ProgramApprovalSnapshot> productList){
		if (productList != null && !productList.isEmpty()) {
			ProgramApprovalSnapshot programApprovalSnapshot = productList.get(0);
			Integer changeStatus = programApprovalSnapshot.getProgramStatus();
			Integer changeApprovalStatus = programApprovalSnapshot.getApprovalStatus();
			program.setChangeStatus(changeStatus);
			program.setChangeApprovalStatus(changeApprovalStatus);
		}
	}

	/* 项目费用记录 */
	private List<ProgramApprovalSnapshot> costRecordList(List<ProgramApprovalSnapshot> productList){
		List<ProgramApprovalSnapshot> resultList = new ArrayList<ProgramApprovalSnapshot>();
		if (productList == null || productList.isEmpty()) {
			return null;
		}
		for(ProgramApprovalSnapshot model:productList){
			if (model.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
					&& (model.getProgramStatus()==ProgramStatusNewEnum.ZBSQ.getCode()
					|| model.getProgramStatus()==ProgramStatusNewEnum.XQBG.getCode())) {
//					发起人ID：model.getModifiedAccountId();
//					发起人：model.getModifiedName();
//					预估变更整体费用：model.getBidOverallCost();
//					预估人天：model.getBidOversingleCost()
				resultList.add(model);
			}
		}
		return  resultList;
	}

	/* 灰度时间变更记录 */
	private List<ProgramApprovalSnapshot> getGrayLevelList(List<ProgramApprovalSnapshot> productList){
		List<ProgramApprovalSnapshot> resultList = new ArrayList<ProgramApprovalSnapshot>();
		if (productList == null || productList.isEmpty()) {
			return null;
		}
		for(ProgramApprovalSnapshot model:productList){
			if (model.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
					&& (model.getProgramStatus()==ProgramStatusNewEnum.YQSX.getCode()
							|| model.getProgramStatus()==ProgramStatusNewEnum.XQBG.getCode())) {
//					发起人ID：model.getModifiedAccountId();
//					发起人：model.getModifiedName();
//					灰度时间：model.getGrayReleaseDate();
//					变更渠道：model.getProgramStatus();
				resultList.add(model);
			}
		}

		return  resultList;
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
		try{
			Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			//name唯一检查
			Program checkProgram = new Program();
			String programName = paramsMap.get("name").toString();
			checkProgram.setName(programName);
			if(StringUtils.isBlank(programName) || this.getProgramService().select(checkProgram).size() > 0){
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1038, programName);
			}
			this.getProgramService().addProgram(paramsMap);
			// 返回报文
			return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
		}catch (Exception e){
			e.printStackTrace();
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
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
	public Map programUpdate(HttpServletResponse response, HttpServletRequest request){
		try{
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
		}catch (Exception e){
			e.printStackTrace();
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
	}

	@RequestMapping(value = "/changeLog/list" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map changeLogList(HttpServletResponse response,HttpServletRequest request){
		Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			CommonUtils.buildPageParams(paramsMap);
			resultMap.put("list",this.getProgramEmployeeChangeLogService().orderLimitList(paramsMap));
			resultMap.put(APIHelper.PAGE_NUM, paramsMap.get("pageNum"));
			resultMap.put(APIHelper.PAGE_SIZE, paramsMap.get("pageSize"));
			resultMap.put(APIHelper.TOTAL, this.getProgramEmployeeChangeLogService().orderLimitTotal(paramsMap));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}

		return resultMap;
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
		try{
			Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			String programId = (String) paramsMap.get("programId");
			String accountId = (String) paramsMap.get("accountId");
			String accountType = String.valueOf(AccountUitl.getAccountType(paramsMap));
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
		}catch (Exception e){
			e.printStackTrace();
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
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
		try{
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
		}catch (Exception e){
			e.printStackTrace();
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
	}
	/**
	 * 导出项目列表
	 * @return
	 */
	@RequestMapping(value = "/exportProgramList" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map exportProgramList(HttpServletResponse response,HttpServletRequest request){
		Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			String programStatus = paramsMap.get("programStatus").toString();
			if(StringUtils.isNotBlank(programStatus) && !"0".equals(programStatus)){
				String [] programStatusList = programStatus.split(",");
				paramsMap.put("programStatusList",programStatusList);
			}
			resultMap.put("list",this.getProgramService().exportProgramList(paramsMap));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 定时任务 发送龙信小秘书提示流程节点信息
	 */
	//每三分钟触发一次
//	@Scheduled(cron = "0 0/3 * * * ?")
	//每天九点触发
	@Scheduled(cron = "0 0 9 ? * *")
	public void bugTask() throws Exception{
		try{
			this.getProgramService().programTask();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 为了项目列表按照预警天数排序，为了项目列表按照预警天数排序，为了项目列表按照预警天数排序
	 * @throws Exception
	 */
//	@Scheduled(cron = "0 28 11 ? * *")
	//每天凌晨1点执行一次
	@Scheduled(cron = "0 0 1 ? * *")
	public void warningDaysTask() throws Exception{
		try{
			this.getProgramService().warningDaysTask();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}