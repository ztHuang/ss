### 一些相关问题的解决思路

1. redis中设值（set）可能存在覆盖key，可以加上前缀避免
2. JedisPoolFactory类如果放于RedisService类中会导致循环依赖
3. 两次MD5
   1. 用户端：pass = MD5（明文 + 固定Salt）
   2. 服务端：pass = MD5（用户输入 + 随机Salt）
4. id 可以使用snowflake算法
5. 步骤
    1. html页面中，定义ajax在加载页面时，从后端由商品ID获取商品信息，再渲染出来
    2. 在服务端定义方法（Controller中定义）
    3. 在vo包中定义相对应的页面需要传输的对象
6. 时区问题
    1. UTC使用出错：UTC代表的是全球标准时间 ，但是我们使用的时间是北京时区也就是东八区，领先UTC八个小时。
    2. 更改为：url的时区使用中国标准时间。也是就 `serverTimezone=Asia/Shanghai`
    3. 防止乱码：url链接后面加上编码字符 `characterEncoding=utf8`
7. 并发时，库存数出现负数（超卖）
    1. 在调用SQL语句实现库存减1时，加入库存大于0的条件
    2. 用户可能发送两个相同的请求，秒杀到两个商品，这时，可以在秒杀订单中，将用户ID和商品ID加上唯一索引


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
    
Get / Post 区别（本质）：
    Get：幂等，不管怎样调用，结果都是一样的，不会对服务端数据产生影响
    Post：向服务端提交数据，服务端数据会发生变化

高并发：
    页面：缓存
    服务端：接口优化（思路：减少数据库的访问）
        redis预减库存减少数据库的访问
        内存标记减少redis访问
        请求先入队缓冲，异步下单，增强用户体验





