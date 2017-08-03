package com.longfor.itserver.filter.web;

import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import net.mayee.commons.helper.APIHelper;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author mayee
 *         Created on 2017/2/24 17:24
 * @version v1.0
 */
public class configAPIFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding(ConfigConsts.UTF8);

        /* 接口地址检查 */
        String apiId = CommonUtils.getAPIId(req.getServletPath());
        /* api是否存在判断 */
        if (!APIHelper.getInstance().containsAPI(apiId)) {
            return false;
        }

        /* 参数容器 */
        Map<String, String> params = new TreeMap<String, String>();
        params.put(APIHelper.API_ID, apiId);

        /* 向后传递 */
        req.setAttribute(ConfigConsts.REQ_PARAMS_MAP, params);
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding(ConfigConsts.UTF8);
        CommonUtils.printWriteWithEnum(resp, BizEnum.E9999);
        return false;
    }


}
