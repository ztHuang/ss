package com.huang.web.controller;

import com.huang.web.domain.User;
import com.huang.web.redis.RedisService;
import com.huang.web.redis.UserKey;
import com.huang.web.result.CodeMsg;
import com.huang.web.result.Result;
import com.huang.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.26
 * @Version 1.0
 */
@Controller
@RequestMapping("result")
public class TestController {

    @Autowired
    public UserService userService;
    @Autowired
    public RedisService redisService;

    @RequestMapping("/success")
    @ResponseBody
    public Result<String> success() {
        return Result.success("成功连接");
    }

    @RequestMapping("/error")
    @ResponseBody
    public Result<String> error() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "tom");
        return "hello";
    }



    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {

        User user = userService.getById(1);

        return Result.success(user);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {

        User resultString = redisService.get(UserKey.getById,1 + "", User.class);

        return Result.success(resultString);
    }

    @RequestMapping("/redis/set")
        @ResponseBody
        public Result<Boolean> redisSet() {
        User user = new User(1,"hzt");
        boolean set = redisService.set(UserKey.getById, 1 + "", user);

        return Result.success(set);
        }

    @RequestMapping("/db/in")
    @ResponseBody
    public Result<User> insert() {

        userService.tx();
        User user = userService.getById(2);
        return Result.success(user);
    }

}
