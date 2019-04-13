package com.huang.web.redis;

import com.huang.web.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.29
 * @Version 1.0
 */

@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;


    /**
     * 取值
     * @param prefix 前缀
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        try (Jedis redis = jedisPool.getResource()){
            String realKey = prefix.getPrefix() + key;
            String str = redis.get(realKey);
            T t = ToolUtil.string2Bean(str,clazz);
            return t;
        }
    }

    /**
     * 设值
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        try (Jedis redis = jedisPool.getResource()){
            String s = ToolUtil.bean2String(value);
            if (StringUtils.isEmpty(s)) {
                return false;
            }
            //真正的key
            String realKey = prefix.getPrefix() + key;
            //过期标记
            int seconds = prefix.expireSeconds();
            if (seconds <= 0) {//永不过期
                redis.set(realKey, s);
            } else {//设置过期时间
                redis.setex(realKey, seconds, s);
            }
            return true;
        }
    }

    /**
     * 判断key是否存在
     * @param prefix
     * @param key
     * @return
     */
    public boolean exists(KeyPrefix prefix, String key) {
        try (Jedis redis = jedisPool.getResource()){
            String realKey = prefix.getPrefix() + key;
            return redis.exists(realKey);
        }
    }

    /**
     * 删除
     * @param prefix
     * @param key
     * @return
     */
    public boolean delete(KeyPrefix prefix, String key) {
        try (Jedis redis = jedisPool.getResource()){
            String realKey = prefix.getPrefix() + key;
            long ret = redis.del(realKey);
            return ret > 0;
        }
    }

    /**
     * INCR 命令会返回键 key 在执行加一操作之后的值
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long incr(KeyPrefix prefix, String key) {
        try (Jedis redis = jedisPool.getResource()){
            String realKey = prefix.getPrefix() + key;
            return redis.incr(realKey);
        }
    }

    /**
     * DECR 命令会返回键 key 在执行减一操作之后的值。
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decr(KeyPrefix prefix, String key) {
        try (Jedis redis = jedisPool.getResource()){
            String realKey = prefix.getPrefix() + key;
            return redis.decr(realKey);
        }
    }



}
