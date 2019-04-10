package com.huang.web.redis;

/**
 * @Description 商品列表的前缀信息和cookie时间
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */
public class GoodKey extends BasePrefix {

    public static final int GL_EXPIRE = 60;  //默认cookie为一分钟

    private GoodKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodKey getGoodList = new GoodKey(GL_EXPIRE,"gl");
}
