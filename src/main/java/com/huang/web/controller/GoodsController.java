package com.huang.web.controller;

import com.huang.web.domain.SSUser;
import com.huang.web.service.GoodsService;
import com.huang.web.service.SSUserService;
import com.huang.web.util.ToolUtil;
import com.huang.web.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.util.List;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */
@Controller
@RequestMapping("/good")
public class GoodsController {

    @Autowired
    SSUserService ssUserService;

    @Autowired
    GoodsService goodsService;

    /**
     * 商品列表
     * @param model
     * @return
     */
    @GetMapping("/to_list")
    public String list(Model model, SSUser ssUser){
        model.addAttribute("user", ssUser);
        //查询商品列表，跳转到页面
        List<GoodsVo> goodsVos = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsVos);
        return "good_list";
    }

    /**
     * 商品详情
     * @param model
     * @param ssUser
     * @param goodsId
     * @return
     */
    @GetMapping("/to_detail/{goodsId}")
    public String detail(Model model, SSUser ssUser,@PathVariable("goodsId") long goodsId){
        model.addAttribute("user", ssUser);

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        //System.out.println(goods);

        //活动时间（毫秒）
        long startDate = goods.getStartDate().getTime();
        long endDate = goods.getEndDate().getTime();
        long nowDate = Instant.now().toEpochMilli();
        //活动状态
        int s_static = 0;
        int remainSeconds = 0;  //剩余时间（秒）
        if (nowDate < startDate) { //倒计时
            s_static = 0;
            remainSeconds = ToolUtil.longSubtractLong2Int(startDate, nowDate) / 1000000;
        } else if (nowDate > endDate) { //活动结束
            s_static = -1;
            remainSeconds = -1;
        } else { //秒杀进行中...
            s_static = 1;
            remainSeconds = 0;
        }
        model.addAttribute("s_static", s_static);
        model.addAttribute("remainSeconds", remainSeconds);

        return "good_detail";
    }

}
