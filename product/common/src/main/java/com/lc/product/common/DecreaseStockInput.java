package com.lc.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 扣库存输入接口.
 * @description:
 * @author: lingchen
 * @date: 2020/11/4
 */
@Data
@AllArgsConstructor
public class DecreaseStockInput {

    /**
     * 商品ID.
     */
    private String productId;

    /**
     * 商品数量.
     */
    private Integer productQuantity;

}
