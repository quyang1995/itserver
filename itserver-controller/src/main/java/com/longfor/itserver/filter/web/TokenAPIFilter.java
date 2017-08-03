package com.longfor.itserver.filter.web;

import com.alibaba.fastjson.TypeReference;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.common.util.CommonUtils;
import io.jsonwebtoken.Claims;
import jodd.props.Props;
import net.mayee.commons.helper.APIHelper;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * @author mayee
 *         Created on 2017/2/24 17:41
 * @version v1.0
 */
public class TokenAPIFilter extends AccessControlFilter {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;

        @SuppressWarnings("unchecked")
        Map<String, String> paramsMap = (Map<String, String>) request
                .getAttribute(ConfigConsts.REQ_PARAMS_MAP);

        Props props = JoddHelper.getInstance().getJoddProps();
        if ("true".equals(props.getValue("system.token.use"))) {
            String token = getHeadersInfo(req).get(ConfigConsts.TOKEN_NAME);
            if(CommonUtils.TOKEN_LIST.contains(token)){
                paramsMap.put(APIHelper.TOKEN, token);
            }else{
                return false;
            }

//            try{
//                Claims claims = CommonUtils.parseJWT(token);
//                if(adminProjectService.selectById(Long.parseLong(claims.getId())) == null){
//                    return false;
//                }
//                paramsMap.put(APIHelper.PROJECT_ID, claims.getId());
//                paramsMap.put(APIHelper.SSO_ACCESS_KEY, claims.getSubject());


//                paramsMap.put(APIHelper.TOKEN, token);
//            }catch (Exception e){
//                return false;
//            }
        }

        request.setAttribute(ConfigConsts.REQ_PARAMS_MAP, paramsMap);
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding(ConfigConsts.UTF8);
        CommonUtils.printWriteWithEnum(resp, BizEnum.E9997);
        return false;
    }

    private Map<String, String> toMap(String jsonStr) {
        return parseObject(jsonStr, new TypeReference<TreeMap<String, String>>() {
        });
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

}
