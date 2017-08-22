package com.longfor.itserver.controller.api;

import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.DemandComment;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.mayee.commons.helper.APIHelper;
import org.json.JSONException;
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
 * Created by wangs on 2017/8/22.
 */

@RequestMapping(value = "/api/demandComment")
@Controller
public class APIDemandCommentController extends BaseController{

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/list",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map list(HttpServletRequest request,HttpServletResponse response)  throws IOException, JSONException {

        Map paramMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

        logger.info(paramMap.toString());

        DemandComment demandComment = new DemandComment();
        String demandId=(String)paramMap.get("demandId");
        demandComment.setDemandId(Long.parseLong(demandId));
        List<DemandComment> demandCommentList =  this.getDemandCommentService().select(demandComment);

        Map <String,Object> map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        map.put("demandCommentList",demandCommentList);

        return map;
    }

//    @RequestMapping(value="/add",method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
//    @ResponseBody
//    public Map add(HttpServletRequest request,HttpServletResponse response){
//        Map paramMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
//        String accountId = (String)paramMap.get("accountId");
//        DemandComment demandComment = new DemandComment();
//        demandComment.setAccountId(accountId);
//
//
//        Map<String,Object> map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
//        return map;
//    }

}
