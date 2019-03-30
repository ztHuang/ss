package com.huang.web.controller;

import com.huang.web.result.CodeMsg;
import com.huang.web.result.Result;
import com.huang.web.service.SSUserService;
import com.huang.web.util.ValidatorUtil;
import com.huang.web.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 登陆
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SSUserService ssUserService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(LoginVo loginVo) {
        log.info(loginVo.toString());
        //参数校验
        String inputPassword = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        if (StringUtils.isEmpty(inputPassword)) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (StringUtils.isEmpty(mobile)) {
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if (!ValidatorUtil.isMobile(mobile)) {
            return Result.error(CodeMsg.MOBILE_ERROR);
        }

        //验证成功，登陆
        CodeMsg codeMsg = ssUserService.login(loginVo);
        if (codeMsg.getCode() == 0) {
            return Result.success(true);
        } else {
            return Result.error(codeMsg);
        }
    }
}
