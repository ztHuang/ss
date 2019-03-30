package com.huang.web.result;

import lombok.Data;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.26
 * @Version 1.0
 */

@Data
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    /**
     * 定义成功的私有构造，code和msg固定
     * @param data
     */
    private Result(T data) {
        this.code = 0;
        this.msg = "SUCCESS";
        this.data = data;
    }

    /**
     * 定义失败的私有构造，code和msg自定义
     * @param codeMsg
     */
    private Result(CodeMsg codeMsg) {
        if (codeMsg == null) {
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    /**
     * 成功时调用
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    /**
     * 失败时调用
     * @param codeMsg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<>(codeMsg);
    }
}
