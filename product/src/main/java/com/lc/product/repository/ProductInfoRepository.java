package com.lc.product.repository;

import com.lc.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品仓库类.
 *
 * @description:
 * @author: lingchen
 * @date: 2020/10/31
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 查询所有在架的商品.
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    /**
     * 查询对应ID的商品.
     * @param productIdList
     * @return
     */
    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
