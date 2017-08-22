package com.longfor.itserver.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.Demand;
import com.longfor.itserver.entity.Product;
import net.mayee.commons.helper.APIHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 需求
 * @author mochengkun
 * Created by  on 2017/8/22.
 *
 * @version v1.0
 */
@RequestMapping(value="/api/demand")
@Controller
public class APIDemandController extends BaseController {
    /**
     * 需求系列
     *
     * @param response
     * @param request
     * @return map
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map demandList(HttpServletRequest request, HttpServletResponse response) {
          /*  获得已经验证过的参数map */
          Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
           /*获取查询用例*/
        ELExample elExample = new ELExample(request, Demand.class);
        /*查询数据*/
        Map<String, Object> map= this.getDemandService().getPageDemandList(paramsMap,elExample);
        return map;
    }
}
