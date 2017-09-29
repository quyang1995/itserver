package com.longfor.itserver.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.helper.DataPermissionHelper;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramEmployee;
import com.longfor.itserver.entity.ps.PsIndex;
import com.longfor.itserver.entity.ps.PsProgramDetail;
import net.mayee.commons.helper.APIHelper;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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

		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("pending", pending.substring(0,pending.length()-1));
        resultMap.put("working", working.substring(0,working.length()-1));
        resultMap.put("cc", cc.substring(0,cc.length()-1));
        resultMap.put("favorites", favorites.substring(0,favorites.length()-1));
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