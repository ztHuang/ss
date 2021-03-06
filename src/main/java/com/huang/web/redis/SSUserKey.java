package com.huang.web.redis;

/**
 * @Description 秒杀用户的前缀信息和cookie时间
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

    public static SSUserKey getById = new SSUserKey(0, "id"); // id为用户手机号码，一般不变，永久有效
}
