package com.lc.product.repository;

import com.lc.product.dataobject.ProductInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * 商品信息测试类。
 *
 * @description:
 * @author: lingchen
 * @date: 2020/10/31
 */
@SpringBootTest
class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**
     * 查询所有在架的商品
     * @throws Exception
     */
    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> list = productInfoRepository.findByProductStatus(0);
        Assertions.assertTrue(list.size() > 0);
    }

    /**
     * 查询包含ID的商品.
     */
    @Test
    public void findByProductIdIn() {
        List<ProductInfo> list = productInfoRepository.findByProductIdIn(Arrays.asList("157875196366160022", "157875196366164846"));
        Assertions.assertTrue(list.size() > 0);
    }
}