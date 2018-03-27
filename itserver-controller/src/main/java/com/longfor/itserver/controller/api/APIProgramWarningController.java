package com.longfor.itserver.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.ProgramWarning;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * warning
 *
 *
 * @version v1.0
 **/
@RequestMapping(value = "/api/warning")
@Controller
public class APIProgramWarningController extends BaseController {

	/**
	 * warning列表
	 *
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			resultMap.put("list",this.getProgramWarningService().getListByWhere(paramsMap));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * warning新增
	 *
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map add(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			resultMap.put("data",this.getProgramWarningService().addProgramWarning(paramsMap));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * warning查询
	 *
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map get(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			resultMap.put("data",this.getProgramWarningService().selectById(Long.valueOf(paramsMap.get("id").toString())));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * warning修改
	 *
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map update(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			JSONObject json = (JSONObject) JSONObject.toJSON(paramsMap);
			ProgramWarning programWarning = JSONObject.toJavaObject(json, ProgramWarning.class);
			this.getProgramWarningService().updateByIdSelective(programWarning);
			resultMap.put("data",programWarning);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}


}