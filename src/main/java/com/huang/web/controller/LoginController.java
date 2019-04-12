package com.huang.web.controller;

import com.huang.web.result.Result;
import com.huang.web.service.SSUserService;
import com.huang.web.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Description 登陆
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */

@Controller
@RequestMapping("/login")
@Api(value = "用户登陆的接口", tags = {"登陆的Controller"})
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SSUserService ssUserService;

    @ApiOperation(value = "用户登陆", notes = "用户登陆的接口")
    @GetMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        //log.info(loginVo.toString());

        //验证成功，登陆
        ssUserService.login(response, loginVo);
        return Result.success(true);
    }
}
