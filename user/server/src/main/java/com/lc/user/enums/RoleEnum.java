package com.lc.user.enums;

import lombok.Getter;

/**
 * 角色枚举.
 * @description:
 * @author: lingchen
 * @date: 2020/11/3
 */
@Getter
public enum RoleEnum {

    BUYER(1, "买家"),
    SELLER(2, "卖家")
    ;

    private Integer code;

    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
