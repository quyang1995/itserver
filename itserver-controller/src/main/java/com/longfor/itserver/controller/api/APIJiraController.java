package com.longfor.itserver.controller.api;

import com.alibaba.fastjson.JSON;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.util.JiraIssueHelp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping(value = "/api/jira")
@Controller
public class APIJiraController extends BaseController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * type=0:近90天创建的问题,
	 * type=1:近90天解决的问题
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/problem" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map problem(HttpServletRequest request){
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try {
			Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------problem:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
			resultMap.put("data",JiraIssueHelp.doJiraIssue(LOG,paramsMap.get("code").toString(),Integer.valueOf(paramsMap.get("type").toString())));
		} catch ( Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return  resultMap;
	}

}