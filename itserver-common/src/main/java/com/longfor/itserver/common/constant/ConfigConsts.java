package com.longfor.itserver.common.constant;

import java.io.File;
import java.security.Provider;
import java.util.regex.Pattern;

/**
 * @author mayee
 * Created on 2017/3/31 18:35
 *
 * @version v1.0
 */
public class ConfigConsts {

    //编码格式
    public static final String UTF8 = "UTF-8";
    public static final String GBK = "GBK";
    public static final String ISO_8859_1 = "ISO-8859-1";

    //附件存储地址  生产环境 /lhdata/itplus/upload    测试环境 /mnt/ITPlusnas/ITPlus
//    public static final String FILE_ADDRESS = "/lhdata/itplus/upload";

    //接口提交方式
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String OPTIONS = "OPTIONS";

    public static final String REQ_PARAMS_MAP = "REQ_PARAMS_MAP";
    public static final String REQ_ERR_MAP = "REQ_ERR_MAP";

    //自定义token名称
    public static final String TOKEN_NAME = "x-authentication-token";
    public static final String CHARSET = "UTF-8";
    public static final String MEDIA_TYPE = "application/json;charset=UTF-8";
    //占位符
    public final static String PLACE_HOLDER = "\\{\\?\\}";
//    public static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    //session存储账户信息
    public static final String SESSION_ACCOUNT_LONGFOR = "SESSION_ACCOUNT_LONGFOR";
    public static final String SESSION_EMPLOYEE = "SESSION_EMPLOYEE";
    public static final String SESSION_ORGANIZATION_LV1 = "SESSION_ORGANIZATION_LV1";

    public static final Pattern MOBILE_PHONE_NUMBER_PATTERN = Pattern
            .compile("^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9])[0-9]{8}$");
    public static final Pattern EMAIL_PATTERN = Pattern
            .compile("^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");

    public static final class ADMIN {

        public static final String ERR_VALIDATEIMG_REFRESH_TOO_MUCH = "ERR_VALIDATEIMG_REFRESH_TOO_MUCH";
        public static final String ERR_VALIDATEIMG_CHECK_FAIL = "ERR_VALIDATEIMG_CHECK_FAIL";
        public static final String ERR_APPLICATION_CONFIG = "ERR_APPLICATION_CONFIG";
        public static final String ERR_MOBILE = "ERR_MOBILE";
        public static final String ERR_BROWSER = "ERR_BROWSER";
        public static final String SESSIONKEY_LOGIN_LAST_TIME = "LOGIN_LAST_TIME";

        //验证码
        public static final String SESSIONKEY_LOGIN_VALIDATEIMG = "LOGIN_VALIDATEIMG";
        public static final String SESSIONKEY_LOGIN_VALIDATEIMG_VALUE = "LOGIN_VALIDATEIMG_VALUE";
        public static final String SESSIONKEY_REFRESH_NUM = "REFRESH_NUM";
        public static final String SESSIONKEY_VALIDATEIMG_LAST_TIME = "VALIDATEIMG_LAST_TIME";

        //屏幕锁定判断标记
        public static final int STATUS_LOCKSCREEN = 1;
        public static final int STATUS_UNLOCKSCREEN = 0;

        //登录密码错误锁定次数
        public static final String SESSIONKEY_LOGIN_PWD_FAIL_COUNT = "LOGIN_PWD_FAIL_COUNT";
        //登录密码锁定时间
        public static final String SESSIONKEY_LOGIN_PWD_LOCKED_TIME = "LOGIN_PWD_LOCKED_TIME";

        /* 默认图片验证码刷新次数最大 */
        public static final int DEF_VALIDATEIMG_MAX_REFRESH_NUM = 10;
        /* 默认图片验证码重置时间[单位：毫秒] */
        public static final long DEF_VALIDATEIMG_ACTION_TIME = 20000;
        /* 默认登录图片验证码保存路径 */
        public static final String DEF_VALIDATEIMG_SAVEPATH_LOGIN = "validate" + File.separator + "login" + File.separator;
    }

    public static final class programBpm {
        //提交立项-审批节点2
        public static final String apply_approval2_1 = "地产、基础架构、技术管理相关";
        public static final String apply_approval2_2 = "商业、冠寓、数据、养老相关";
        public static final String is_first = "新产品下的第一个项目";
    }
}
