package com.lc.user.enums;

import lombok.Getter;

/**
 * 返回值枚举.
 * @description:
 * @author: lingchen
 * @date: 2020/11/3
 */
@Getter
public enum ResultEnum {

    LOGIN_FAIL(1, "登录失败"),
    ROLE_ERROR(2, "角色校验失败")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
