package com.huang.web.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Description MD5工具类
 * @Author huangzt
 * @Date 2019.03.30
 * @Version 1.0
 */
public class MD5Util {
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    //固定的salt，存入于客户端
    private static final String SALT = "huang_12138";

    /**
     * 用户明文输入密码进行第一次加密
     * @param inputPass
     * @return
     */
    private static String inputPassFormPass(String inputPass) {
        //注意：这里要加上一个空字符串
        String password = "" + SALT.charAt(0) + SALT.charAt(5) + inputPass + SALT.charAt(10);
        return DigestUtils.md5Hex(password);
    }

    /**
     * 加入随机salt进行第二次加密
     * @param fromPass
     * @param salt
     * @return
     */
    public static String formPassToDbPass(String fromPass, String salt) {
        String password = SALT.charAt(0) + SALT.charAt(5) + fromPass + SALT.charAt(10);
        return DigestUtils.md5Hex(password);
    }

    /**
     * 整合上面两个方法所得
     * @param userpass 用户输入密码
     * @param saltDB 随机salt
     * @return
     */
    public static String inputPassToDbPass(String userpass, String saltDB) {
        return formPassToDbPass(inputPassFormPass(userpass), saltDB);
    }

    public static void main(String[] args) {
        System.out.println(inputPassFormPass("15915686069")); //902a990e60c73f815b90bb7d8cabb8cd
        System.out.println(inputPassToDbPass("15915686069","12138_huangzt")); //
    }
}
