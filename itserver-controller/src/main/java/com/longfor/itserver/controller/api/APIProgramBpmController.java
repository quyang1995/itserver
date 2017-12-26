package com.longfor.itserver.controller.api;

import com.alibaba.fastjson.JSON;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.enums.ProgramStatusNewEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.vo.programBpm.ApplyViewVo;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramApprovalSnapshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 项目 -- 状态变更 bpm审批流程
 *
 **/
@RequestMapping(value = "/api/program/bpm")
@Controller
public class APIProgramBpmController extends BaseController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 查看立项申请（作废）
	 */
	@RequestMapping(value = "/applyView", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map applyView(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------applyView:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if(program.getProgramStatus() == ProgramStatusNewEnum.WLX.getCode())
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1302);

//			ApplyViewVo applyViewVo = getProgramService().applyView(paramsMap,program);
//			resultMap.put("data",applyViewVo);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 提交立项申请
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map programList(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------apply:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);

			getProgramService().apply(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 审核通过
	 */
	@RequestMapping(value = "/approvalPass", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map approvalPass(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------approvalPass:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);

			getProgramService().approvalPass(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 审核驳回
	 */
	@RequestMapping(value = "/approvalRebut", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map approvalRebut(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------approvalRebut:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);

			getProgramService().approvalRebut(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	private Program getProgram(Map<String, String> paramsMap){
		Long programId = Long.parseLong(paramsMap.get("programId"));
		Program program = getProgramService().getProgramId(programId);
		return program;
	}

	/**
	 * Demo评审
	 */
	@RequestMapping(value = "/demoReview", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map demoReview(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------demoReview:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);

			getProgramService().demoReview(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 招标文件
	 */
	@RequestMapping(value = "/tenderFile", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map tenderFile(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------tenderFile:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);

			getProgramService().tenderFile(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 中标通知
	 */
	@RequestMapping(value = "/bidNotice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map bidNotice(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------bidNotice:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);

			getProgramService().bidNotice(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 产品评审
	 */
	@RequestMapping(value = "/productReview", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map productReview(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------productReview:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);

			getProgramService().productReview(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 进入开发
	 */
	@RequestMapping(value = "/development", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map development(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------development:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);

			getProgramService().development(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 测试部署
	 */
	@RequestMapping(value = "/deploy", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map deploy(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------deploy:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);

			getProgramService().deploy(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 上线计划
	 */
	@RequestMapping(value = "/planOnline", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map planOnline(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------planOnline:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);

			getProgramService().planOnline(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 灰度发布
	 */
	@RequestMapping(value = "/release", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map release(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------release:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);

			getProgramService().release(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 *延期上线
	 */
	@RequestMapping(value = "/delay", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map delay(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------delay:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);

			getProgramService().delay(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 需求变更
	 */
	@RequestMapping(value = "/demandChange", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map demandChange(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------demandChange:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);

			getProgramService().demandChange(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 查看
	 */
	@RequestMapping(value = "/lookNodes", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map lookNodes(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------lookNodes:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			ProgramApprovalSnapshot programApprovalSnapshot = new ProgramApprovalSnapshot();
			programApprovalSnapshot.setProductId(Long.parseLong(paramsMap.get("productId")));
			programApprovalSnapshot.setProgramStatus(Integer.parseInt(paramsMap.get("programStatus")));
			List<ProgramApprovalSnapshot>  shotList= this.getProgramService().lookNodes(programApprovalSnapshot);
			resultMap.put("list",shotList);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}


}