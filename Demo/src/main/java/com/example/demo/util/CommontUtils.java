package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhourui
 * @Date 2020/4/28 11:46
 */
@Slf4j
public class CommontUtils {

    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) {
        try {
            Map<String, Object> map = new HashMap();
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                map.put(fieldName, value);
            }
            return map;
        } catch (IllegalAccessException e) {
            log.error("对象转map失败!,{}, {}", JSON.toJSONString(obj), e);
            throw new RuntimeException(e);
        }
    }
}
