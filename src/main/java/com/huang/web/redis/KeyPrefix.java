package com.huang.web.redis;

/**
 * @Description 为避免重复key，redis设值时使用的前缀；区分模块
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */
public interface KeyPrefix {
    int expireSeconds();
    String getPrefix();
}
