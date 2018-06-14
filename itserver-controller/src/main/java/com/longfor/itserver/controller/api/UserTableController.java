package com.longfor.itserver.controller.api;

import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CacheConsts;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.userTable;
import net.mayee.redis.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/api/userTable")
@Controller
public class UserTableController extends BaseController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisCache redisCache;

	/**
	  * @Author:qy
	  * @Date: 2018/6/13 15:18
	  * @Description:查询所有数据
	  */
	@RequestMapping(value = "/userList" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map userList(HttpServletRequest request){
		//获取参数
		Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		String key = CacheConsts.CAHCE_PERMISSIONS_REDIS;
		try {
			//ridis 测试已ok
			List<userTable> userTableList = redisCache.getListCache(key, userTable.class);
			if(userTableList!=null && userTableList.size()>0){
				resultMap.put("list", userTableList);
			}else{
				//查询数据哈哈哈哈
				List<userTable> userTables = this.getUserTableService().userList();
				redisCache.putListCacheWithExpireTime(key, userTables, RedisCache.CAHCETIME_600S);
			}
			LOG.info("查询成功!");
		} catch ( Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return  resultMap;
	}
	/**
	  * @Author:qy
	  * @Date: 2018/6/13 15:18
	  * @Description:新增数据
	  */
	@RequestMapping(value = "/insetUser" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map insetUser(HttpServletRequest request){
		//获取参数
		Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		Map resultMap;
		try {
			//新增数据
			resultMap = this.getUserTableService().insetUser(paramsMap);
		} catch ( Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return  resultMap;
	}

}