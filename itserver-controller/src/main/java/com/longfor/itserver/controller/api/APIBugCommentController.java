package com.longfor.itserver.controller.api;

import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.BugComment;
import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.entity.FeedBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value="/list" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map list(HttpServletRequest request, HttpServletResponse response){
        Map paramMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Long bugId = Long.valueOf((String)paramMap.get("bugId"));
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

        Map<String,Object> map= this.getBugCommentService().add(paramsMap);
        return map;
    }





    @RequestMapping(value="/appList" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map appList(HttpServletRequest request, HttpServletResponse response){
        Map paramMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Long feedBackId = Long.valueOf((String)paramMap.get("feedBackId"));

        FeedBack feedBack = this.getFeedBackService().selectById(feedBackId);
        Map map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        if(feedBack != null && feedBack.getType().equals(0)){
            BugComment bugComment = new BugComment();
            BugInfo bugInfo = new BugInfo();
            bugInfo.setFeedBackId(feedBackId);
            bugInfo = this.getBugInfoService().selectOne(bugInfo);
            if(bugInfo != null){
                bugComment.setBugId(bugInfo.getId());
                List bugCommentList = this.getBugCommentService().select(bugComment);
                map.put("bugCommentList",bugCommentList);
                return  map;
            }else{
                logger.info("通过反馈ID查询功能异常评论时从BUG表中获取数据失败！");
                map = CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
            }
        }else{
            map = CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
            logger.info("通过反馈ID查询功能异常评论时从feedBack表中获取数据失败！");
        }

        return  map;
    }

}
