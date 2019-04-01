package com.huang.web.controller;

import com.huang.web.domain.SSUser;
import com.huang.web.service.SSUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 商品列表
     * @param model
     * @return
     */
    @RequestMapping("/to_list")
    public String List(Model model, SSUser ssUser){
        model.addAttribute("user", ssUser);
        return "good_list";
    }
}
