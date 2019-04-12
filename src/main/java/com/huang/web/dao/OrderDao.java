package com.huang.web.dao;

import com.huang.web.domain.OrderInfo;
import com.huang.web.domain.SSOrder;
import org.apache.ibatis.annotations.*;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.04.07
 * @Version 1.0
 */

@Mapper
public interface OrderDao {

    @Select("select * from ss_order where user_id=#{userId} and goods_id=#{goodsId}")
    SSOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") Long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    long insertOrder(OrderInfo orderInfo);

    @Insert("insert into ss_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    int insertSSOrder(SSOrder ssOrder);

    @Select("select * from order_info where id = #{orderId}")
    OrderInfo getOrderById(@Param("orderId") long orderId);
}
