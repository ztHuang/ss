package com.huang.web.util;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

/**
 * @Description 常用工具类
 * @Author huangzt
 * @Date 2019.04.01
 * @Version 1.0
 */

public class ToolUtil {

    /**
     * long -> int
     * @param l
     * @return
     */
    public static final int long2Int(long l) {
        return new Long(l).intValue();
    }

    /**
     * long1 - long2   -->   int
     * @param l1
     * @param l2
     * @return
     */
    public static final int longSubtractLong2Int(long l1, long l2) {
        return new Long(Math.subtractExact(l1, l2)).intValue();
    }

    public static <T> String bean2String(T value) {
        //TODO 这里可能需要补充判断类型
        Class<?> valueClass = value.getClass();
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        if (valueClass == int.class || valueClass == Integer.class) {
            return value + "";
        } else if (valueClass == String.class) {
            return (String) value;
        } else if (valueClass == long.class || valueClass == Long.class) {
            return value + "";
        } else {
            return JSON.toJSONString(value);
        }
    }

    public static <T> T string2Bean(String str, Class<T> clazz) {
        //TODO 这里可能需要补充判断类型
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    public static void main(String[] args) {
        long l1 = 1555079280000L;
        long l2 = 1556614860000L;
        int i = longSubtractLong2Int(l2, l1) / 1000000;
        System.out.println(i);
    }
}
