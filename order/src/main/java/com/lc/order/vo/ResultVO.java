package com.lc.order.vo;

import lombok.Data;

/**
 * 请求处理结果类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
