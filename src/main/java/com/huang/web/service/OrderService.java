package com.huang.web.service;

import com.huang.web.dao.OrderDao;
import com.huang.web.domain.OrderInfo;
import com.huang.web.domain.OrderInfoStatus;
import com.huang.web.domain.SSOrder;
import com.huang.web.domain.SSUser;
import com.huang.web.redis.OrderKey;
import com.huang.web.redis.RedisService;
import com.huang.web.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @Description 订单service
 * @Author huangzt
 * @Date 2019.04.07
 * @Version 1.0
 */

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    RedisService redisService;

    /**
     * 根据UserId和GoodsId获取秒杀订单
     * @param userId
     * @param goodsId
     * @return
     */
    public SSOrder getMiaoshaOrderByUserIdGoodsId(Long userId, long goodsId) {
        /**
         * 性能优化，不通过数据库，直接从缓存中拿
         * return orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
         */
        //缓存中找秒杀订单
        return redisService.get(OrderKey.getSSOrderByUidGid, "" + userId + "_" + goodsId, SSOrder.class);
    }

    /**
     * 创建订单
     * @param user
     * @param goods
     * @return
     */
    @Transactional
    public OrderInfo createOrder(SSUser user, GoodsVo goods) {

        //订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getSsPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setUserId(user.getId());
        orderInfo.setStatus(OrderInfoStatus.NEW_UNPAID.getCode());

        //返回一个订单id用于写入秒杀订单
        long orderId = orderDao.insertOrder(orderInfo);

        //秒杀订单
        SSOrder ssOrder = new SSOrder();
        ssOrder.setGoodsId(goods.getId());
        ssOrder.setOrderId(orderId);
        ssOrder.setUserId(user.getId());

        orderDao.insertSSOrder(ssOrder);

        //将秒杀订单存入缓存中
        redisService.set(OrderKey.getSSOrderByUidGid, "" + user.getId() + "_" + goods.getId(), ssOrder);

        return orderInfo;
    }

    public OrderInfo getOrderById(long orderId) {

        return orderDao.getOrderById(orderId);

    }
}
