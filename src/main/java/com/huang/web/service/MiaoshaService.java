package com.huang.web.service;

import com.huang.web.domain.OrderInfo;
import com.huang.web.domain.SSUser;
import com.huang.web.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Description 秒杀service
 * @Author huangzt
 * @Date 2019.04.07
 * @Version 1.0
 */

@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    /**
     * 减少库存，下订单，写入秒杀订单
     * @param user
     * @param goods
     * @return
     */
    @Transactional
    public OrderInfo miaosha(SSUser user, GoodsVo goods) {

        //减少库存
        goodsService.reduceStock(goods);

        //下订单，写入秒杀订单
        OrderInfo orderInfo = orderService.createOrder(user, goods);

        return orderInfo;
    }
}
