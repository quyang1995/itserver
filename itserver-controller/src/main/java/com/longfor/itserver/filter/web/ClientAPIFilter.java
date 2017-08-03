package com.longfor.itserver.filter.web;

import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mayee
 *         Created on 2017/2/24 16:31
 * @version v1.0
 */
public class ClientAPIFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding(ConfigConsts.UTF8);

        /* 渠道检查 */
        String servletPath = req.getServletPath();
        if ((ConfigConsts.POST.equals(req.getMethod()) || ConfigConsts.OPTIONS.equals(req.getMethod())) && servletPath.startsWith("/api/")) {
            return true;
        }
        return false;

    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding(ConfigConsts.UTF8);
        CommonUtils.printWriteWithEnum(resp, BizEnum.E9999);
        return false;
    }


}
