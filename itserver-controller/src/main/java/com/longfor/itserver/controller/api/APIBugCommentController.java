package com.longfor.itserver.controller.api;

import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.BugComment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by wangs on 2017/8/23.
 */
@RequestMapping(value = "/api/bugComment")
@Controller
public class APIBugCommentController extends BaseController{


    @RequestMapping(value="/list" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map list(HttpServletRequest request, HttpServletResponse response){
        Map paramMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Long bugId = Long.parseLong((String)paramMap.get("bugId"));
        BugComment bugComment = new BugComment();

        bugComment.setBugId(bugId);
        List bugCommentList = this.getBugCommentService().select(bugComment);

        Map map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        map.put("bugCommentList",bugCommentList);

        return  map;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map add(HttpServletRequest request , HttpServletResponse response){
        Map paramsMap =   (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

        boolean flag = this.getBugCommentService().add(paramsMap);

        Map map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
        if(!flag){
            map = CommonUtils.getResultMapByBizEnum(BizEnum.E1001);
        }
        return map;
    }

}
