package com.longfor.itserver.controller.api;

import com.alibaba.fastjson.JSON;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.enums.ProgramStatusNewEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.vo.programBpm.ApplyViewVo;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
	 * 查看立项申请
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

			ApplyViewVo applyViewVo = getProgramService().applyView(paramsMap,program);
			resultMap.put("data",applyViewVo);
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
}