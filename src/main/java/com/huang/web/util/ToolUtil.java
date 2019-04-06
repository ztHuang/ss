package com.huang.web.util;

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
}
