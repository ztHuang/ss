package com.huang.web.controller;

import com.huang.web.domain.SSUser;
import com.huang.web.rabbitmq.MQSender;
import com.huang.web.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 用来测试性能
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */
@Controller
@RequestMapping("/test")
public class UserController {

    @Autowired
    MQSender mqSender;

    /**
     * 用户列表
     * @return
     */
    @GetMapping("/info")
    @ResponseBody
    public Result<SSUser> info(SSUser ssUser){
        return Result.success(ssUser);
    }

    /**
     *
     * @return
     */
    @GetMapping("/mq")
    @ResponseBody
    public Result<String> mq(){
        mqSender.send("hello,rabbitmq");

        return Result.success("OK!!!!!!!");
    }
}
