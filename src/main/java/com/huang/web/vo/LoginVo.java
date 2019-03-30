package com.huang.web.vo;

import com.huang.web.validator.IsMobile;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Description 页面传回数据
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */
@Data
@ToString
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
