package com.lc.product.enums;

import lombok.Getter;

/**
 * 返回值枚举.
 * @description:
 * @author: lingchen
 * @date: 2020/11/3
 */
@Getter
public enum ResultEnum {

    PRODUCT_NO_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "商品库存有误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
