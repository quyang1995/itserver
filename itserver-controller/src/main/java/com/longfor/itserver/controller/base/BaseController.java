package com.longfor.itserver.controller.base;


import com.longfor.ads.helper.ADSHelper;
import com.longfor.eds.helper.EDSHelper;
import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.service.*;
import net.mayee.redis.RedisCache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BaseController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private IuserTableService userTableService;

    public IuserTableService getUserTableService() {
        return userTableService;
    }

    public RedisCache getRedisCache() {
        return redisCache;
    }

    protected final String getRealPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }

    protected final String getMessage(String key, String[] values, Locale locale) {
        ApplicationContext ac = WebApplicationContextUtils
                .getRequiredWebApplicationContext(request.getSession()
                        .getServletContext());
        if (locale == null) {
            locale = getSessionLocale();
        }
        return ac.getMessage(key, values, "Key Null", locale);
    }

    protected final Locale getSessionLocale() {
        return (Locale) request.getSession().getAttribute(
                SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
    }

    protected final void setSessionLocale(Locale locale) {
        request.getSession().setAttribute(
                SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
    }

    protected final void setDefaultSessionLocale() {
        if (request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME) == null) {
            String default_code = JoddHelper.getInstance().getJoddProps().getValue("system.default.language");
            setSessionLocale(new Locale(default_code.split("_")[0], default_code.split("_")[1]));
        }
    }

    protected final void setSessionLocale(String code) {
        request.getSession().setAttribute(
                SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
                new Locale(code.split("_")[0], code.split("_")[1]));
    }

    protected HttpServletRequest getRequest() {
        return request;
    }

    protected String getPath() {
        return request.getContextPath();
    }

    protected String getBasePath() {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + getPath() + "/";
    }

    protected Map<String, String> getDTSearchMap(HttpServletRequest request){
        Map<String, String> searchMap = new HashMap<>();

        String paramsStr = request.getParameter("SEARCH_PARAMS");
        if(StringUtils.isNotBlank(paramsStr)){
            String[] paramsList = paramsStr.split("\\&");
            for(String param : paramsList){
                String[] val = param.split("=");
                if(val.length == 2){
                    searchMap.put(val[0], val[1]);
                }
            }
        }

        return searchMap;
    }

}

