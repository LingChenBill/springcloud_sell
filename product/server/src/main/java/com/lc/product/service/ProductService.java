package com.lc.product.service;

import com.lc.product.common.DecreaseStockInput;
import com.lc.product.common.ProductInfoOutput;
import com.lc.product.dataobject.ProductInfo;

import java.util.List;

/**
 * 商品服务类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
public interface ProductService {

    /**
     * 查询所有在架商品.
     * @return
     */
    public List<ProductInfo> findUpAll();

    /**
     * 查询商品列表.
     * @param productIdList
     * @return
     */
    public List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存.
     * @param cartDtoList
     */
    public void decreaseStock(List<DecreaseStockInput> cartDtoList);

}
