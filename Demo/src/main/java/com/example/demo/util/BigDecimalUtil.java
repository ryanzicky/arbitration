package com.example.demo.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * @Author zhourui
 * @Date 2020/6/1 17:30
 */
public class BigDecimalUtil {

    private static NumberFormat percent = null;

    /**
     * BigDecimal转百分比保留两位小数
     *
     * @Author zhourui
     */
    public static NumberFormat getPercent() {
        percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        return percent;
    }

    public static void main(String[] args){
        String format = BigDecimalUtil.getPercent().format(BigDecimal.valueOf(0.0970));
        System.out.println(format);
    }
}
