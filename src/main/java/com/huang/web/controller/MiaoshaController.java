package com.huang.web.controller;

import com.huang.web.domain.OrderInfo;
import com.huang.web.domain.SSOrder;
import com.huang.web.domain.SSUser;
import com.huang.web.result.CodeMsg;
import com.huang.web.service.GoodsService;
import com.huang.web.service.MiaoshaService;
import com.huang.web.service.OrderService;
import com.huang.web.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.04.07
 * @Version 1.0
 */

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @PostMapping("/do_miaosha")
    public String doMiaosha(Model model, SSUser ssUser, @RequestParam("goodsId") long goodsId){

        model.addAttribute("user", ssUser);
        if (ssUser == null) { //没有用户信息，跳转重新登陆
            return "login";
        }

        //判断是否库存不足
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        Integer stockCount = goods.getStockCount();
        if (stockCount <= 0) {  //库存不足，秒杀失败
            model.addAttribute("errorMsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }

        //判断是否重复秒杀
        SSOrder ssOrder = orderService.getMiaoshaOrderByUserIdGoodsId(ssUser.getId(), goodsId);
        if (ssOrder != null) { // miaoshaOrder应该为空的，新的；如果不是，则表示已经秒杀过，返回错误页面
            model.addAttribute("errorMsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }

        //下面为正常秒杀流程：减库存，下订单；这些可以封装在一起-MiaoshaService  =  一个事务
        OrderInfo orderInfo = miaoshaService.miaosha(ssUser, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }
}
