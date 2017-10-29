package com.longfor.itserver.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sunyanhui on 2017/10/13.
 */
public class DateUtil {

    public static final String PATTERN_TIMESTAMP = "yyyy-MM-dd hh:mm:ss";

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String result = format.format(today);
        return result;
    }

    /***
     * 日期格式化
     * @return
     */
    public static Date string2Date(String date,String pattern) throws ParseException {
       SimpleDateFormat sdf = new SimpleDateFormat(pattern);
       return sdf.parse(date);
    }

    /***
     * 日期格式化
     * @return
     */
    public static String date2String(Date date,String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    public static void main(String[] args) {
        System.out.print(DateUtil.getPastDate(4));
    }
}
