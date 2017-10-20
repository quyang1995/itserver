package com.longfor.itserver.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by sunyanhui on 2017/10/19.
 */
public class StringUtil {

    public static Long getLongValue(String str){
        if(StringUtils.isBlank(str))return null;
        return Long.valueOf(str);
    }
}
