package com.huang.web.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @Description 页面传回数据
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */
@Data
@ToString
public class LoginVo {
    private String mobile;
    private String password;
}
