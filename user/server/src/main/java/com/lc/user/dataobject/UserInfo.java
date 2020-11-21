package com.lc.user.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @description:
 * @author: lingchen
 * @date: 2020/11/21
 */
@Data
@Entity
public class UserInfo {

    @Id
    private String id;

    // 用户姓名.
    private String username;

    // 用户密码.
    private String password;

    // 微信openid.
    private String openid;

    // 角色:1买家2卖家.
    private Integer role;

    // 创建时间.
    private Date createTime;

    // 修改时间.
    private Date updateTime;
}
