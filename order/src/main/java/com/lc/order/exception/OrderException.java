package com.lc.order.exception;

import com.lc.order.enums.ResultEnum;

/**
 * 订单异常处理类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
