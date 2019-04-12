package com.huang.web.controller;

import com.huang.web.domain.OrderInfo;
import com.huang.web.domain.SSUser;
import com.huang.web.result.CodeMsg;
import com.huang.web.result.Result;
import com.huang.web.service.GoodsService;
import com.huang.web.service.OrderService;
import com.huang.web.vo.GoodsVo;
import com.huang.web.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单的Controller
 *
 * @Author huangzt
 * @Date 2019.04.12
 * @Version 1.0
 */

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @GetMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> orderDetail(SSUser user, @RequestParam("orderId") long orderId){
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo orderInfo = orderService.getOrderById(orderId);
        if (orderInfo == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        Long goodsId = orderInfo.getGoodsId();
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);

        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setGoodsVo(goodsVo);
        orderDetailVo.setOrderInfo(orderInfo);

        return Result.success(orderDetailVo);
    }

}
