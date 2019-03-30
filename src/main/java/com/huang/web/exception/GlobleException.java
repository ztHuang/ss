package com.huang.web.exception;

import com.huang.web.result.CodeMsg;
import lombok.Getter;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */
@Getter
public class GlobleException extends RuntimeException {

    private CodeMsg codeMsg;
    public GlobleException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }
}
