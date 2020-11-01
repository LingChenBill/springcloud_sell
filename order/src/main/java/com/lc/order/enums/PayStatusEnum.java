package com.lc.order.enums;

import lombok.Getter;

/**
 * 支付状态。
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付完成"),
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
