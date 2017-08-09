package com.longfor.itserver.controller.base;


import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.service.*;
import com.longfor.itserver.service.ps.IPsProductService;
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
import java.util.regex.Pattern;

public class BaseController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    public static final Pattern EMAIL_PATTERN = Pattern
            .compile("^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
    public static final Pattern MOBILE_PHONE_NUMBER_PATTERN = Pattern
            .compile("^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9])[0-9]{8}$");
    public static final Pattern LOGIN_NAME_PATTERN = Pattern
            .compile("^([a-zA-Z0-9]{4,20})$");
    public static final Pattern LOGIN_PASSWORD_PATTERN = Pattern
            .compile("^([a-zA-Z0-9!@#$%^*_]{6,16})$");

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private IBugChangeLogService bugChangeLogService;
    @Autowired
    private IBugCommentService bugCommentService;
    @Autowired
    private IBugInfoService bugInfoService;
    @Autowired
    private IDemandChangeLogService demandChangeLogService;
    @Autowired
    private IDemandCommentService demandCommentService;
    @Autowired
    private IDemandService demandService;
    @Autowired
    private IEmployeeTypeService employeeTypeService;
    @Autowired
    private IProductCommentService productCommentService;
    @Autowired
    private IProductEmployeeChangeLogService productEmployeeChangeLogService;
    @Autowired
    private IProductEmployeeService productEmployeeService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IProgramCommentService programCommentService;
    @Autowired
    private IProgramEmployeeChangeLogService programEmployeeChangeLogService;
    @Autowired
    private IProgramEmployeeService programEmployeeService;
    @Autowired
    private IProgramService programService;


    @Autowired
    private IPsProductService psProductService;

    public IPsProductService getPsProductService() {
        return psProductService;
    }
    public IBugChangeLogService getBugChangeLogService() {
        return bugChangeLogService;
    }

    public IBugCommentService getBugCommentService() {
        return bugCommentService;
    }

    public IBugInfoService getBugInfoService() {
        return bugInfoService;
    }

    public IDemandChangeLogService getDemandChangeLogService() {
        return demandChangeLogService;
    }

    public IDemandCommentService getDemandCommentService() {
        return demandCommentService;
    }

    public IDemandService getDemandService() {
        return demandService;
    }

    public IEmployeeTypeService getEmployeeTypeService() {
        return employeeTypeService;
    }

    public IProductCommentService getProductCommentService() {
        return productCommentService;
    }

    public IProductEmployeeChangeLogService getProductEmployeeChangeLogService() {
        return productEmployeeChangeLogService;
    }

    public IProductEmployeeService getProductEmployeeService() {
        return productEmployeeService;
    }

    public IProductService getProductService() {
        return productService;
    }

    public IProgramCommentService getProgramCommentService() {
        return programCommentService;
    }

    public IProgramEmployeeChangeLogService getProgramEmployeeChangeLogService() {
        return programEmployeeChangeLogService;
    }

    public IProgramEmployeeService getProgramEmployeeService() {
        return programEmployeeService;
    }

    public IProgramService getProgramService() {
        return programService;
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

