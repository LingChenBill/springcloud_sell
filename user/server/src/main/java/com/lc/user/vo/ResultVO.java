package com.lc.user.vo;

import lombok.Data;

/**
 * http请求信息
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 具体内容
     */
    private T data;
}
