package com.huang.web.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description Redis配置文件
 * @Author huangzt
 * @Date 2019.03.29
 * @Version 1.0
 */

@Component
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfig {

    private String host;
    private int port;
    private int timeout;
    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int pollMaxWait;


}
