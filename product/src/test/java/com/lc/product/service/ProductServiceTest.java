package com.lc.product.service;

import com.lc.product.ProductApplicationTests;
import com.lc.product.dataobject.ProductInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品服务类测试。
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
@Component
public class ProductServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productService.findUpAll();
        Assertions.assertTrue(list.size() > 0);
    }
}