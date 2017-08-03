package com.longfor.itserver.filter.web;

import com.alibaba.fastjson.TypeReference;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import net.mayee.commons.helper.APIHelper;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * @author mayee
 *         Created on 2017/2/26 14:45
 * @version v1.0
 */
public class ParseParamsAPIFilter extends AccessControlFilter {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object obj) throws IOException, FileUploadException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding(ConfigConsts.UTF8);

        @SuppressWarnings("unchecked")
        Map<String, String> paramsMap = (Map<String, String>) request
                .getAttribute(ConfigConsts.REQ_PARAMS_MAP);

        String postContent = CommonUtils.getContent(req.getInputStream());
        if (StringUtils.isNotBlank(postContent)) {
            Map<String, String> tmpMap;
            try {
                tmpMap = toMap(postContent);
            } catch (Exception e) {
                return false;
            }
            //清理预留字段
            APIHelper.getInstance().cleanReservedName(tmpMap);
            paramsMap.putAll(tmpMap);
        } else {
            return false;
        }

        req.setAttribute(ConfigConsts.REQ_PARAMS_MAP, paramsMap);
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding(ConfigConsts.UTF8);
        CommonUtils.printWriteWithEnum(resp, BizEnum.E9994);
        return false;
    }


    private Map<String, String> toMap(String jsonStr) {
        return parseObject(jsonStr, new TypeReference<TreeMap<String, String>>() {
        });
    }

}
