package com.huang.web.controller;

import com.huang.web.domain.SSUser;
import com.huang.web.service.SSUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("/to_list")
    public String toList(Model model, HttpServletResponse response,
            //兼容，下面两者选一
            @CookieValue(value=SSUserService.COOKI_NAME_TOKEN, required=false) String cookieToken,
                         @RequestParam(value=SSUserService.COOKI_NAME_TOKEN, required=false) String paramToken){
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            //没有cookie，跳转到登陆
            System.out.println(cookieToken + "   :    " + paramToken);
            return "login";
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        SSUser ssuser = ssUserService.getByToken(response, token);
        model.addAttribute("user", ssuser);
        System.out.println("user:"+ssuser);
        return "good_list";
    }
}
