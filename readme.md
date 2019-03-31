### 一些相关问题的解决思路

1. redis中设值（set）可能存在覆盖key，可以加上前缀避免
2. JedisPoolFactory类如果放于RedisService类中会导致循环依赖
3. 两次MD5
   1. 用户端：pass = MD5（明文 + 固定Salt）
   2. 服务端：pass = MD5（用户输入 + 随机Salt）


### 备忘
服务端把一个token写到cookie中，客户端在访问中携带cookie，服务端就可以根据cookie中的token找到对应的用户信息
每次访问都会改变cookie的生命周期