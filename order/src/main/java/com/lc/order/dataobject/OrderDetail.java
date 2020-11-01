package com.lc.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单信息.
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
@Data
@Entity
public class OrderDetail {

    // 订单信息ID.
    @Id
    private String detailId;

    // 订单ID.
    private String orderId;

    // 商品ID.
    private String productId;

    // 商品名称.
    private String productName;

    // 商品单价.
    private BigDecimal productPrice;

    // 商品数量.
    private Integer productQuantity;

    // 商品小图.
    private String productIcon;

    // 创建时间.
    private Date createTime;

    // 修改时间.
    private Date updateTime;

}
