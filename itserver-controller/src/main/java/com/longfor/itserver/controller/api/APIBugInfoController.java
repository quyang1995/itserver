package com.longfor.itserver.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ps.PsBugInfoDetail;
import net.mayee.commons.helper.APIHelper;
import org.json.JSONException;
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
		PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
		List<BugInfo> bugList = this.getBugInfoService().bugList(paramsMap);
		

		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("list", bugList);
		resultMap.put(APIHelper.PAGE_NUM, elExample.getPageNum());
		resultMap.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
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
			if (bugInfo.getRelationType().equals("1")) {
				Product prod = this.getProductService().selectById(bugInfo.getRelationId());
				relationName = prod.getName();
			} else if (bugInfo.getRelationType().equals("2")) {
				Program prog = this.getProgramService().selectById(bugInfo.getRelationId());
				relationName = prog.getName();
			}
			bugInfo.setRelationName(relationName);
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

		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		this.getBugInfoService().addBug(paramsMap);
		// 返回报文
		return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
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
	public Map bugUpdate(HttpServletResponse response, HttpServletRequest request)
			throws IOException, JSONException {

		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		this.getBugInfoService().updateBug(paramsMap);

		// 返回报文
		return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_U);
	}

}