package com.huang.web.redis;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */
public class SSUserKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;  //默认cookie为两天

    private SSUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SSUserKey token = new SSUserKey(TOKEN_EXPIRE, "tk");
}
