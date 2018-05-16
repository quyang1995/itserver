package com.longfor.itserver.controller.api;

import com.alibaba.fastjson.JSON;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.helper.DataPermissionHelper;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.DateUtil;
import com.longfor.itserver.common.vo.MoApprove.MoApproveListVo;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.ps.PsIndex;
import com.longfor.itserver.esi.MoApproveUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * PC首页统计
 *
 * @author wax
 * @create 2017/8/5 下午2:19
 *
 * @version v1.0
 **/
@RequestMapping(value = "/api/index")
@Controller
public class APIIndexController extends BaseController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * PC首页统计
	 *
	 * @author lovex
	 * @create 2017/8/5 下午2:25
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/analysis", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map indexAnaly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			String accountId = paramsMap.get("accountId").toString();
			/* 查询 */
			List<PsIndex> pendingList = this.getDemandService().countPending(accountId);
			String pending = "";
			String working = "";
			String cc = "0,0,0,";
			String favorites = "0,0,0,";
			for(PsIndex obj : pendingList){
				if(obj.getName().equals("pending")){
					pending += obj.getValue() + ",";
				}
				if(obj.getName().equals("working")){
					working += obj.getValue() + ",";
				}
			}
			//warningSum:进度预警包括红色跟黄色的
			int warningSum = this.getProgramWarningService().warningSum(accountId);
			//warningRemarksSum:风险备注数：一个项目添加两个算两个，关闭的不算
			int warningRemarksSum = this.getProgramWarningService().warningRemarksSum(accountId);
			//wSum:项目预警是进度预警和风险备注数总和
			int wSum = warningSum + warningRemarksSum;
			//待办
			MoApproveListVo moApproveListVo = MoApproveUtil.flowapiList("0","0",accountId,null,0,1);
			int dealtApprove = 0;
			if (moApproveListVo!=null) {
				dealtApprove = moApproveListVo.getTotal();
			}
			//我发起的
			MoApproveListVo myApproveListVo = MoApproveUtil.flowapiList("1","0",accountId,null,0,1);
			int myLaunchApprove = 0;
			if(myApproveListVo != null){
				myLaunchApprove = myApproveListVo.getTotal();
			}
			//待审批包括待办和我发起的
			int myApprove = dealtApprove + myLaunchApprove;

			resultMap.put("approve", myApprove + "," + dealtApprove + "," + myLaunchApprove);
			resultMap.put("warning", wSum + "," + warningSum + "," + warningRemarksSum);
			resultMap.put("pending", pending.substring(0,pending.length()-1));
			resultMap.put("working", working.substring(0,working.length()-1));
			resultMap.put("cc", cc.substring(0,cc.length()-1));
			resultMap.put("favorites", favorites.substring(0,favorites.length()-1));
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}


	/**
	 * 验证是否是admin
	 *
	 * @author lovex
	 * @create 2017/8/5 下午2:25
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/isAdmin", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map isAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {

		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		/* 查询数据 and admin权限判断 */
		String accountId = paramsMap.get("accountId").toString();
		resultMap.put("isAdmin", DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0");

		return resultMap;
	}

	/**
	 * bug 统计
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bugTj" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map bugTj(HttpServletRequest request){
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try {
			Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------bugTj:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
			//全部
			paramsMap.put("status",-1);
			resultMap.put("sum",this.getBugInfoService().bugTj(paramsMap));
			//待处理
			paramsMap.put("status",2);
			resultMap.put("pending",this.getBugInfoService().bugTj(paramsMap));
			//处理中
			paramsMap.put("status",3);
			resultMap.put("doing",this.getBugInfoService().bugTj(paramsMap));
			//已完成
			paramsMap.put("status",4);
			resultMap.put("finish",this.getBugInfoService().bugTj(paramsMap));
			//本周新增
			paramsMap.put("status",-1);
			paramsMap.put("createTime", DateUtil.date2String(DateUtil.getBeginDayOfWeek(),DateUtil.PATTERN_TIMESTAMP));
			resultMap.put("week",this.getBugInfoService().bugTj(paramsMap));
		} catch ( Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return  resultMap;
	}

	/**
	 * 需求统计
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/demandTj" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map demandTj(HttpServletRequest request){
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try {
			Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------demandTj:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
			//全部
			paramsMap.put("status",-1);
			resultMap.put("sum",this.getDemandService().demandTj(paramsMap));
			//待处理
			paramsMap.put("status",2);
			resultMap.put("pending",this.getDemandService().demandTj(paramsMap));
			//处理中
			paramsMap.put("status",3);
			resultMap.put("doing",this.getDemandService().demandTj(paramsMap));
			//已完成
			paramsMap.put("status",4);
			resultMap.put("finish",this.getDemandService().demandTj(paramsMap));
			//本周新增
			paramsMap.put("status",-1);
			paramsMap.put("createTime", DateUtil.date2String(DateUtil.getBeginDayOfWeek(),DateUtil.PATTERN_TIMESTAMP));
			resultMap.put("week",this.getDemandService().demandTj(paramsMap));
		} catch ( Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return  resultMap;
	}

}