package com.huang.web.result;

import lombok.Data;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.26
 * @Version 1.0
 */

@Data
public class CodeMsg {
    private int code;
    private String msg;

    //成功
    public static CodeMsg SUCCESS = new CodeMsg(0, "SUCCESS");
    //服务端异常
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    //参数异常
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    //登陆模块异常
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "session不存在或已经失效");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登陆密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "手机号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");
    ////商品模块异常
    //public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    ////订单模块异常
    //public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    ////秒杀模块异常
    //public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 带参数的msg
     * @param args
     * @return
     */
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }
}
