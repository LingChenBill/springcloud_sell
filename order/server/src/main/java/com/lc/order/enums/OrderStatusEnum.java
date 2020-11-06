package com.lc.order.enums;

import lombok.Getter;

/**
 * 订单状态。
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}