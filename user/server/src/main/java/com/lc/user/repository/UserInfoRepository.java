package com.lc.user.repository;

import com.lc.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户信息仓库接口.
 * @description:
 * @author: lingchen
 * @date: 2020/11/21
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    /**
     * 通过openid查找用户.
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}
