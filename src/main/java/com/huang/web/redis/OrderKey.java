package com.huang.web.redis;

/**
 * @Description 商品列表的前缀信息和cookie时间
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */
public class OrderKey extends BasePrefix {


    private OrderKey(String prefix) {
        super(prefix);
    }

    public static OrderKey getSSOrderByUidGid = new OrderKey("SSUG");
}
