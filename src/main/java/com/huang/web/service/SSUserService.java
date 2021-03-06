package com.huang.web.service;

import com.huang.web.dao.SSUserDao;
import com.huang.web.domain.SSUser;
import com.huang.web.exception.GlobleException;
import com.huang.web.redis.RedisService;
import com.huang.web.redis.SSUserKey;
import com.huang.web.result.CodeMsg;
import com.huang.web.util.MD5Util;
import com.huang.web.util.UUIDUtil;
import com.huang.web.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */

@Service
public class SSUserService {

    public final static String COOKI_NAME_TOKEN = "token";

    @Autowired
    public SSUserDao ssUserDao;
    @Autowired
    public RedisService redisService;

    public SSUser getById(long id) {
        //取缓存
        SSUser ssUser = redisService.get(SSUserKey.getById, "" + id, SSUser.class);
        if (ssUser != null) {
            return ssUser;
        }
        //如果缓存存在，直接返回；没有再从数据库查询，并存入缓存中
        ssUser = ssUserDao.getById(id);
        if (ssUser != null) {
            redisService.set(SSUserKey.getById, "" + id, ssUser);
        }
        return ssUser;
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobleException(CodeMsg.SERVER_ERROR);
        }
        //页面传来的已经加密过一次的密码
        String password = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        //判断数据库中是否存在
        SSUser isUser = getById(Long.valueOf(mobile));
        if (isUser == null) {
            throw new GlobleException(CodeMsg.MOBILE_NOT_EXIST);
        }

        //验证密码

        //数据库保存的密码，加密两次的
        String dbPass = isUser.getPassword();
        //第二次加密的盐
        String saltDB = isUser.getSalt();

        String pass = MD5Util.formPassToDbPass(password, saltDB);
        if (!dbPass.equals(pass)) {
            throw new GlobleException(CodeMsg.PASSWORD_ERROR);
        }

        // 生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, isUser);

        return true;
    }

    private void addCookie(HttpServletResponse response, String token, SSUser isUser) {
        redisService.set(SSUserKey.token, token, isUser);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(SSUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public SSUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        SSUser user = redisService.get(SSUserKey.token, token, SSUser.class);
        if (user != null) {
            //用户访问，生成新的cookie
            addCookie(response,token, user);
        }
        return user;
    }
}
