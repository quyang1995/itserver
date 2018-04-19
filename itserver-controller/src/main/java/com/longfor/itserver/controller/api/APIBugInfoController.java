package com.longfor.itserver.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.enums.BugStatusEnum;
import com.longfor.itserver.common.helper.DataPermissionHelper;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.BugFile;
import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ps.PsBugInfoDetail;
import com.longfor.itserver.entity.ps.PsBugTimeTask;
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
import java.util.List;
import java.util.Map;

/**
 * BUG
 *
 * @author wax
 * @create 2017/8/23 上午9:00
 *
 * @version v1.0
 **/
@RequestMapping(value = "/api/bug")
@Controller
public class APIBugInfoController extends BaseController {

	/**
	 * BUG列表
	 *
	 * @author lovex
	 * @create 2017/8/23 上午9:00
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map bugList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		/* 生成查询用Example */
		ELExample elExample = new ELExample(request, BugInfo.class);
		/* 查询数据 and admin权限判断 */
		String accountId = String.valueOf(paramsMap.get("accountId"));
		paramsMap.put("isAdmin", DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0");
		PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
		String status = paramsMap.get("status").toString();
		if(StringUtils.isNotBlank(status) && !"-1".equals(status)){
			String [] programStatusList = status.split(",");
			paramsMap.put("statusList",programStatusList);
		}
		List<PsBugInfoDetail> bugList = this.getBugInfoService().bugList(paramsMap);
		

		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("list", bugList);
		resultMap.put(APIHelper.PAGE_NUM, elExample.getPageNum());
		resultMap.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
		resultMap.put(APIHelper.TOTAL, new PageInfo(bugList).getTotal());
		return resultMap;
	}

	/**
	 * 导出BUG列表
	 *
	 * @author lovex
	 * @create 2017/8/23 上午9:00
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/export", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map bugExportList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		/* 查询数据 and admin权限判断 */
		String accountId = String.valueOf(paramsMap.get("accountId"));
		paramsMap.put("isAdmin", DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0");
		String status = paramsMap.get("status").toString();
		if(StringUtils.isNotBlank(status) && !"-1".equals(status)){
			String [] programStatusList = status.split(",");
			paramsMap.put("statusList",programStatusList);
		}
		List<PsBugInfoDetail> bugList = this.getBugInfoService().bugList(paramsMap);


		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("list", bugList);
		resultMap.put(APIHelper.TOTAL, new PageInfo(bugList).getTotal());
		return resultMap;
	}

	/**
	 * 通过ID获取BUG基本信息
	 *
	 * @author lovex
	 * @create 2017/8/5 下午2:25
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map bugGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		long id = Long.parseLong(paramsMap.get("id").toString());
		PsBugInfoDetail bugInfo = (PsBugInfoDetail) this.getBugInfoService().getBugId(id);
		if(bugInfo != null) {
			// 关联产品
			if(bugInfo.getLikeProduct() != null && !"".equals(bugInfo.getLikeProduct())){
				String likeProduct = bugInfo.getLikeProduct().substring(1, bugInfo.getLikeProduct().length());
				List<Product> product = this.getProductService().searchIdList(likeProduct);
				bugInfo.setProductList(product);
			}
			// 关联项目
			if(bugInfo.getLikeProgram() != null && !"".equals(bugInfo.getLikeProgram())) {
				String likeProgram = bugInfo.getLikeProgram().substring(1, bugInfo.getLikeProgram().length());
				List<Program> program = this.getProgramService().inProgramId(likeProgram);
				bugInfo.setProgramList(program);
			}
			//归属项目/产品
			String relationName = "";
			if (bugInfo.getRelationType().equals(1)) {
				Product prod = this.getProductService().selectById(bugInfo.getRelationId());
				relationName = prod.getName();
			} else if (bugInfo.getRelationType().equals(2)) {
				Program prog = this.getProgramService().selectById(bugInfo.getRelationId());
				relationName = prog.getName();
			}
			bugInfo.setRelationName(relationName);

			//bug相关文件信息
			BugFile file = new BugFile();
			file.setBugId(bugInfo.getId());
			List<BugFile> fileList  = this.getBugFileService().select(file);
			bugInfo.setBugFileList(fileList);


			/* 返回报文 */
			Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
			resultMap.put("data", bugInfo);
			return resultMap;
		}
		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("data", bugInfo);
		return resultMap;
	}

	/**
	 * 新增BUG
	 *
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map bugAdd(HttpServletResponse response, HttpServletRequest request) throws IOException, JSONException {
		try{
			Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			this.getBugInfoService().addBug(paramsMap);
			// 返回报文
			return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
		}catch (Exception e){
			e.printStackTrace();
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
	}

	/**
	 * 修改BUG
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map bugUpdate(HttpServletResponse response, HttpServletRequest request){
		try{
			/* 获得已经验证过的参数map */
			Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

            /*更新操作*/
			this.getBugInfoService().updateBug(paramsMap);
			// 返回报文
			return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_U);
		}catch (Exception e){
			e.printStackTrace();
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
	}


	@RequestMapping(value = "/changeLog/list",method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map changeLoglist(HttpServletRequest request,HttpServletResponse response){

		Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		return this.getBugChangeLogService().orderLimitList(paramsMap);
	}

	/**
	 * 变更状态
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update/status" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map updateStatus(HttpServletRequest request ,HttpServletResponse response){
		try{
			Map<String, String> paramsMap = (Map<String, String>)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			//状态值有效性验证
			int code = Integer.parseInt(paramsMap.get("status"));
			BugStatusEnum bugStatusEnum = BugStatusEnum.getByCode(code);
			if(bugStatusEnum == null){
				return CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
			}

			this.getBugInfoService().updateStatus(paramsMap);
			Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
			resultMap.put("newStatusText", bugStatusEnum.getText());
			return resultMap;
		}catch (Exception e){
			e.printStackTrace();
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
	}

	/**
	 * 变更指派人
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update/callon" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map updateCallon(HttpServletRequest request ,HttpServletResponse response){
		try{
			Map<String, String> paramsMap = (Map<String, String>)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			//人员信息有效性验证
			AccountLongfor accountLongfor =
					AccountUitl.getAccountByAccountTypes(paramsMap.get("callonAccountId"),getAdsHelper(),getEdsHelper());
			if(accountLongfor == null){
				return CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
			}

			this.getBugInfoService().updateCallon(paramsMap);
			Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
			resultMap.put("newCallonEmployeeText", accountLongfor.getName());
			return resultMap;
		}catch (Exception e){
			e.printStackTrace();
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}

	}

	/**
	 * 根据产品/项目ID 获取 待处理/处理中的BUG
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/statusList" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map statusList(HttpServletRequest request ,HttpServletResponse response){
		Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);


		return this.getBugInfoService().statusList(request,paramsMap);
	}

	/**
	 * 根据BUGID 获取相关BUG文件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getFile" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map getFile(HttpServletRequest request , HttpServletResponse response){
		Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		BugFile result = this.getBugFileService().selectById(Long.valueOf((String)paramsMap.get("id")));
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("data",result);
		return  resultMap;
	}

	@RequestMapping(value = "/delete/file" , method = RequestMethod.POST , produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map deleteFile(HttpServletRequest request , HttpServletResponse response){
		Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		this.getBugFileService().deleteById(Long.parseLong((String)paramsMap.get("id")));

		return  CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_D);
	}

	/**
	 * 定时任务 超过1天（24小时）未处理／处理中：通知被指派人
	 */
	@Scheduled(cron = "0 0 9 ? * *")
	public void bugTask(){
		this.getBugInfoService().bugTask();
		System.out.println("Annotation：is show run");
	}
}