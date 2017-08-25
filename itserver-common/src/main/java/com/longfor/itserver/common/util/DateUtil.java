package com.longfor.itserver.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangs on 2017/8/25.
 */
public class DateUtil {


    public static String currentFormat = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(currentFormat);
        return  simpleDateFormat.format(date);
    }


}
