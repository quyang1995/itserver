package com.longfor.itserver.controller.api;

import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.helper.DataPermissionHelper;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.vo.MoApprove.MoApproveListVo;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.ps.PsIndex;
import com.longfor.itserver.esi.MoApproveUtil;
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
			int dealtApprove = moApproveListVo.getTotal();
			//我发起的
			MoApproveListVo myApproveListVo = MoApproveUtil.flowapiList("1","0",accountId,null,0,1);
			int myLaunchApprove = myApproveListVo.getTotal();
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

}