package com.huang.web.service;

import com.huang.web.dao.SSUserDao;
import com.huang.web.domain.SSUser;
import com.huang.web.result.CodeMsg;
import com.huang.web.util.MD5Util;
import com.huang.web.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */

@Service
public class SSUserService {
    @Autowired
    public SSUserDao ssUserDao;

    public SSUser getById(long id) {
        SSUser ssUser = ssUserDao.getById(id);
        return ssUser;
    }

    public CodeMsg login(LoginVo loginVo) {
        if (loginVo == null) {
            return CodeMsg.SERVER_ERROR;
        }
        String password = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        //判断数据库中是否存在
        SSUser isUser = getById(Long.valueOf(mobile));
        if (isUser == null) {
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        //验证密码
        String dbPass = isUser.getPassword();
        String saltDB = isUser.getSalt();

        String pass = MD5Util.formPassToDbPass(password, saltDB);
        System.out.println("页面拿的" + password);
        System.out.println("数据库的"+dbPass);
        System.out.println("盐"+saltDB);
        System.out.println("二次加密的"+pass);
        if (!dbPass.equals(pass)) {
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }
}
