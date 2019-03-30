package com.huang.web.exception;

import com.huang.web.result.CodeMsg;
import com.huang.web.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */

@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        if (e instanceof GlobleException) {
            GlobleException bindException = (GlobleException) e;
            return Result.error(bindException.getCodeMsg());
        }else if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            List<ObjectError> allErrors = bindException.getAllErrors();
            ObjectError objectError = allErrors.get(0);
            String defaultMessage = objectError.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(defaultMessage));
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
