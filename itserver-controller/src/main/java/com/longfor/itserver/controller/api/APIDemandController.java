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
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ps.PsBugInfoDetail;
import com.longfor.itserver.entity.ps.PsDemandDetail;
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
    public Map demandList(HttpServletRequest request, HttpServletResponse response) throws IOException {
          /*  获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
           /*获取查询用例*/
        ELExample elExample = new ELExample(request, Demand.class);
        /*查询数据*/
        Map<String, Object> map= this.getDemandService().getPageDemandList(paramsMap,elExample);
        return map;
    }

    /**
     *  添加需求
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public Map demandAdd(HttpServletRequest request,HttpServletResponse response)
            throws IOException, JSONException {
        //获取已经验证的参数map
        @SuppressWarnings("unchecked")
        Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        this.getDemandService().addDemand(paramsMap);
        //返回成功信息
        return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
    }

    /**
     *  修改需求
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public Map demandUpdate(HttpServletResponse response, HttpServletRequest request)
            throws IOException, JSONException {

		//获取已经验证的参数map
        @SuppressWarnings("unchecked")
        Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        this.getDemandService().updateDemand(paramsMap);
        // 返回成功信息
        return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_U);
    }

    /**
     *  通过ID获取需求信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public Map demandGet(HttpServletRequest request, HttpServletResponse response)throws IOException{
		//获得已经验证过的参数map
        @SuppressWarnings("unchecked")
        Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

        long id = Long.parseLong(paramsMap.get("id").toString());
        PsDemandDetail demand = (PsDemandDetail)this.getDemandService().getDemandById(id);
        //关联产品
        String likeProduct = demand.getLikeProduct().substring(1,demand.getLikeProduct().length());
        List<Product> product = this.getProductService().searchIdList(likeProduct);
        demand.setProductList(product);
        //关联项目
        String likeProgram = demand.getLikeProgram().substring(1,demand.getLikeProgram().length());
        List<Program> program = this.getProgramService().inProgramId(likeProgram);
        demand.setProgramList(program);
        //归属产品/项目
        String relationName = "";
        if(demand.getRelationType().equals("1")){
            Product prod = this.getProductService().selectById(demand.getRelationId());
            relationName = prod.getName();
        }else if(demand.getRelationType().equals("2")){
            Program prom = this.getProgramService().selectById(demand.getRelationId());
            relationName = prom.getName();
        }
        demand.setRelationName(relationName);
        //返回成功信息
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        resultMap.put("data", demand);
        return resultMap;
    }
}
