package com.lc.order.service;

import com.lc.order.dto.OrderDto;

/**
 * 订单服务类。
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
public interface OrderService {

    /**
     * 创建订单.
     * @param orderDto
     * @return
     */
    public OrderDto create(OrderDto orderDto);

    /**
     * 完结订单(只能卖家操作).
     * @param orderId
     * @return
     */
    public OrderDto finish(String orderId);
}
