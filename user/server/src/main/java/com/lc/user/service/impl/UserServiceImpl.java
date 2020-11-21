package com.lc.user.service.impl;

import com.lc.user.dataobject.UserInfo;
import com.lc.user.repository.UserInfoRepository;
import com.lc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 通过openid查找用户.
     * @param openid
     * @return
     */
    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
