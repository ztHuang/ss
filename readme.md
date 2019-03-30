一些相关问题的解决思路
1、redis中设值（set）可能存在覆盖key，可以加上前缀避免
2、JedisPoolFactory类如果放于RedisService类中会导致循环依赖
3、两次MD5
    3.1、用户端：pass = MD5（明文 + 固定Salt）
    3.2、服务端：pass = MD5（用户输入 + 随机Salt）