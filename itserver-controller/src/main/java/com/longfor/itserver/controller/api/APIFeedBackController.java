package com.longfor.itserver.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.entity.ps.PsFeedBackDetail;
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
 * 项目
 *
 * @author wax
 * @create 2017/8/5 下午2:19
 *
 * @version v1.0
 **/
@RequestMapping(value = "/api/feedBack")
@Controller
public class APIFeedBackController extends BaseController {

	/**
	 * 反馈列表
	 *
	 * @author lovex
	 * @create 2017/8/24 下午3:50
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map feedBackList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		/* 生成查询用Example */
		ELExample elExample = new ELExample(request, FeedBack.class);
		PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
		List<FeedBack> feedBackList = this.getFeedBackService().feedBackList(paramsMap);


		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("list", feedBackList);
		resultMap.put(APIHelper.PAGE_NUM, elExample.getPageNum());
		resultMap.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
		resultMap.put(APIHelper.TOTAL, new PageInfo(feedBackList).getTotal());
		return resultMap;
	}


	/**
	 * 新增反馈信息
	 *
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map feedBackAdd(HttpServletResponse response, HttpServletRequest request) throws IOException, JSONException {

		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		this.getFeedBackService().addFeedBack(paramsMap);
		// 返回报文
		return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
	}

	/**
	 * 通过ID获取反馈基本信息
	 *
	 * @author lovex
	 * @create 2017/8/5 下午2:25
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map feedBackGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		long id = Long.parseLong(paramsMap.get("id").toString());

		PsFeedBackDetail feedBack = (PsFeedBackDetail) this.getFeedBackService().getFeedBackId(id);
		if(feedBack.getType().equals(1)){
			Demand demand = new Demand();
			demand.setFeedBackId(feedBack.getId());
			demand = this.getDemandService().selectOne(demand);
			if(demand != null){
				DemandComment demandComment = new DemandComment();
				demandComment.setDemandId(demand.getId());
				List<DemandComment> demandCommentList = this.getDemandCommentService().select(demandComment);
				feedBack.setDemandCommentList(demandCommentList);
			}
		}else if(feedBack.getType().equals(0)){
			BugInfo bugInfo = new BugInfo();
			bugInfo.setFeedBackId(feedBack.getId());
			List<BugInfo> bugInfoList = this.getBugInfoService().select(bugInfo);
			if(bugInfoList.size() > 0){
				BugComment bugComment = new BugComment();
				bugComment.setBugId(bugInfoList.get(0).getId());
				List<BugComment> bugCommentList = this.getBugCommentService().select(bugComment);
				feedBack.setBugCommentList(bugCommentList);
			}
		}

		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("data", feedBack);
		return resultMap;
	}



	@RequestMapping(value = "/addComment" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map addComment(HttpServletRequest request , HttpServletResponse response){
		Map<String,Object> paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		Long feedBackId = Long.valueOf((String)paramsMap.get("feedBackId"));

		FeedBack feedBack =	this.getFeedBackService().selectById(feedBackId);
		if(feedBack != null){
			/*功能异常*/
			if(feedBack.getType().equals(0)){
				BugInfo bugInfo = new BugInfo();
				bugInfo.setFeedBackId(feedBackId);
				bugInfo = this.getBugInfoService().selectOne(bugInfo);
				if(bugInfo == null) return  CommonUtils.getResultMapByBizEnum(BizEnum.E9994);

				paramsMap.put("bugId",bugInfo.getId());
				return this.getBugCommentService().add(paramsMap);
			/*功能建议*/
			}else if(feedBack.getType().equals(1)){
				Demand demand = new Demand();
				demand.setFeedBackId(feedBackId);
				demand = this.getDemandService().selectOne(demand);
				if(demand == null ) return CommonUtils.getResultMapByBizEnum(BizEnum.E9994);

				paramsMap.put("demandId",demand.getId());
				return this.getDemandCommentService().add(paramsMap);
			}

			return CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
		}

		return  CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
	}

}