### 一些相关问题的解决思路

1. redis中设值（set）可能存在覆盖key，可以加上前缀避免
2. JedisPoolFactory类如果放于RedisService类中会导致循环依赖
3. 两次MD5
   1. 用户端：pass = MD5（明文 + 固定Salt）
   2. 服务端：pass = MD5（用户输入 + 随机Salt）
4. id 可以使用snowflake算法

### 备忘
服务端把一个token写到cookie中，客户端在访问中携带cookie，服务端就可以根据cookie中的token找到对应的用户信息
每次访问都会改变cookie的生命周期

在一个Service中，通常只引入自己的Dao，如果需要引入其它的，也是引入其它的Service不推荐引入Dao，因为其它的service中可能存在缓存


在开发时为了方便，可以使用springboot自带的tomcat，也就是还是以jar的形式，到了打包上传到服务器时，要改成war，同时加上tomcat的依赖和plugins，以及启动类。
在项目目录下，使用命令：mvn clean package 即可打jar包，失败可以删除target再试。

在使用对象缓存时，如果发生用户数据的更新：
    首先更新数据库信息
    其次更新所有相关缓存信息redis，避免缓存不一致问题


当调用dao更新时，要修改哪个对象就新建一个，然后修改对应的值再调用dao方法即可；使用原先对象可能会影响性能。
    


