package com.lc.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 扣商品库存dto.
 * @description:
 * @author: lingchen
 * @date: 2020/11/3
 */
@Data
@AllArgsConstructor
public class CartDto {

    /**
     * 商品ID.
     */
    private String productId;

    /**
     * 商品数量.
     */
    private Integer productQuantity;

}
