package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author zhourui
 * @Date 2019/9/29 16:46
 */
@Slf4j
public class DateUtil {

    private final static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy年MM月dd日");

    public static Date formatDate(Date date) {
        try {
            return FORMAT.parse(FORMAT.format(date));
        } catch (ParseException e) {
            log.error("格式化日期失败!,{}", e);
            throw new RuntimeException(e);
        }
    }

    public static String getDateFormatStr(Date date) {
        return FORMAT.format(date);
    }

    /**
     * 格式化日期
     *
     * @Author zhourui
     */
    public static String formatDate(String format, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取日期年月日
     *
     * @Author zhourui
     */
    public static Map<String, String> getDate(Date date, List<String> varList) {
        Map<String, String> map = Maps.newHashMap();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);

        map.put(varList.get(0), String.valueOf(instance.get(Calendar.YEAR)));
        map.put(varList.get(1), String.valueOf(instance.get(Calendar.MONTH) + 1));
        map.put(varList.get(2), String.valueOf(instance.get(Calendar.DAY_OF_MONTH)));
        System.out.println(JSON.toJSONString(map));
        return map;
    }

    public static Date getDateAdd(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.DAY_OF_MONTH, 1);
        return instance.getTime();
    }

    public static Date getDateAddByNum(Date date, int num) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.DAY_OF_MONTH, num);
        return instance.getTime();
    }

    public static Date getDateAddByTerm(Date date, int term) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int num = 0;
        if (term == 12) {
            num = (int) (term * 30.5);
        } else if (term == 24) {
            num = (int) (term * 30.5) - 1;
        }
        instance.add(Calendar.DAY_OF_MONTH, num);
        return instance.getTime();
    }
}
