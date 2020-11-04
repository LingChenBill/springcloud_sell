package com.lc.product.common;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息输出接口.
 * @description:
 * @author: lingchen
 * @date: 2020/11/4
 */
@Data
public class ProductInfoOutput {

    /**
     * 商品ID.
     */
    private String productId;

    /**
     * 商品名称.
     */
    private String productName;

    /**
     * 单价.
     */
    private BigDecimal productPrice;

    /**
     * 库存.
     */
    private Integer productStock;

    /**
     * 描述.
     */
    private String productDescription;

    /**
     * 小图.
     */
    private String productIcon;

    /**
     * 商品状态,0正常1下架.
     */
    private Integer productStatus;

    /**
     * 类目编号.
     */
    private Integer categoryType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间.
     */
    private Date updateTime;
}
