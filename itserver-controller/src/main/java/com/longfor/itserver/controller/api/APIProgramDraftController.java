package com.longfor.itserver.controller.api;

import com.alibaba.fastjson.JSON;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ps.PsProgramDraftDetail;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RequestMapping(value = "/api/programDraft")
@Controller
public class APIProgramDraftController extends BaseController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 新增项目草稿
	 *
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map programAdd(HttpServletResponse response, HttpServletRequest request){
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		try{
			LOG.info("------add:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
			//name唯一检查
			Program checkProgram = new Program();
			String programName = paramsMap.get("name").toString();
			checkProgram.setName(programName);
			if(StringUtils.isBlank(programName) || this.getProgramService().select(checkProgram).size() > 0){
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1038, programName);
			}
			this.getProgramDraftService().addProgramDraft(paramsMap);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 项目节点申请（草稿）
	 *
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/applyNode", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map applyNode(HttpServletResponse response, HttpServletRequest request){
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		try{
			LOG.info("------add:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
			this.getProgramDraftService().applyNode(paramsMap);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 删除项目草稿
	 *
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map programDelete(HttpServletResponse response, HttpServletRequest request){
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		try{
			LOG.info("------add:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
			this.getProgramDraftService().deleteProgramDraft(paramsMap);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 获取项目草稿
	 *
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map programGet(HttpServletResponse response, HttpServletRequest request){
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		try{
			LOG.info("------get:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
			PsProgramDraftDetail programDraft = this.getProgramDraftService().getProgramDraftDetail(paramsMap);
			resultMap.put("data",programDraft);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}
}