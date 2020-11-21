package com.lc.user.constant;

/**
 * Cookie的常量.
 * @description:
 * @author: lingchen
 * @date: 2020/11/21
 */
public interface CookieConstant {

    String TOKEN = "token";

    String OPENID = "openid";

    /**
     * 过期时间(单位:s).
     */
    Integer expire = 7200;
}
