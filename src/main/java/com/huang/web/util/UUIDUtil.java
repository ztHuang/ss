package com.huang.web.util;

import java.util.UUID;

/**
 * @Description UUID工具类
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
