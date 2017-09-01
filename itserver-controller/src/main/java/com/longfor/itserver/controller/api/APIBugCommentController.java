package com.longfor.itserver.controller.api;

import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.BugComment;
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
public class APIBugCommentController extends BaseController {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 通过BUGID获取评论基本信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map list(HttpServletRequest request, HttpServletResponse response) {
        Map paramMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Long bugId = Long.valueOf((String) paramMap.get("bugId"));
        BugComment bugComment = new BugComment();

        bugComment.setBugId(bugId);
        List bugCommentList = this.getBugCommentService().select(bugComment);

        Map map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        map.put("bugCommentList", bugCommentList);

        return map;
    }

    /**
     * 添加BUG评论
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map add(HttpServletRequest request, HttpServletResponse response) {
        @SuppressWarnings("unchecked")
        Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        try {
            this.getBugCommentService().add(paramsMap);
            return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
        }
    }

}
