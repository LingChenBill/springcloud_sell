package com.lc.order.dto;

import com.lc.order.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单Dto
 * @description: OrderMaster和OrderDetail是一对多的关系。
 * @author: lingchen
 * @date: 2020/11/1
 */
@Data
public class OrderDto {

    // 订单ID.
    private String orderId;

    // 买家名字.
    private String buyerName;

    // 买家电话.
    private String buyerPhone;

    // 买家地址.
    private String buyerAddress;

    // 买家微信openid.
    private String buyerOpenid;

    // 订单总金额.
    private BigDecimal orderAmount;

    // 订单状态，默认为新下单.
    private Integer orderStatus;

    // 支付状态，默认未支付.
    private Integer payStatus;

    // 订单信息列表.
    private List<OrderDetail> orderDetailList;
}
