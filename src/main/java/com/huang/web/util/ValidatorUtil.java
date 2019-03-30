package com.huang.web.util;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * @Description 验证工具类
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */
public class ValidatorUtil {

    private static final Pattern mobile_pattern = Pattern.compile("^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$");

    /**
     * 是否为有效的手机号码
     * @param src
     * @return
     */
    public static boolean isMobile(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        return mobile_pattern.matcher(src).matches();
    }

}
