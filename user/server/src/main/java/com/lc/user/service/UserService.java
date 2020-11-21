package com.lc.user.service;

import com.lc.user.dataobject.UserInfo;

/**
 * 用户信息服务接口.
 * @description:
 * @author: lingchen
 * @date: 2020/11/21
 */
public interface UserService {

    /**
     * 通过openid查找用户.
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}
