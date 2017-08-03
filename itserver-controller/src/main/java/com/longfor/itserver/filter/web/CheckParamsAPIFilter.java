package com.longfor.itserver.filter.web;

import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.util.CommonUtils;
import net.mayee.commons.helper.APIHelper;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author mayee
 *         Created on 2017/2/26 14:45
 * @version v1.0
 */
public class CheckParamsAPIFilter extends AccessControlFilter {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object obj) throws IOException, FileUploadException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding(ConfigConsts.UTF8);

        @SuppressWarnings("unchecked")
        Map<String, String> paramsMap = (Map<String, String>) request
                .getAttribute(ConfigConsts.REQ_PARAMS_MAP);

        /* 清理参数 */
        Map<String, String> cleanedParamsMap = APIHelper.getInstance().cleanParams(paramsMap);
        /* 根据接口xml文件，检查接口参数合法性 */
        Map<String, Object> checkResultMap = APIHelper.getInstance().checkAPIObject(cleanedParamsMap);
        if (!checkResultMap.isEmpty()) {
            req.setAttribute(ConfigConsts.REQ_ERR_MAP, checkResultMap);
            return false;
        }

        /* 分页参数转换 */
        APIHelper.getInstance().buildPageParams(cleanedParamsMap);

        req.setAttribute(ConfigConsts.REQ_PARAMS_MAP, cleanedParamsMap);
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding(ConfigConsts.UTF8);
        @SuppressWarnings("unchecked")
        Map<String, String> checkResultMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_ERR_MAP);
        CommonUtils.printWriteWithMap(resp, checkResultMap);
        return false;
    }

}
