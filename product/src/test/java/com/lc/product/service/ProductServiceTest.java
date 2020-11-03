package com.lc.product.service;

import com.lc.product.ProductApplicationTests;
import com.lc.product.dataobject.ProductInfo;
import com.lc.product.dto.CartDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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

    @Test
    public void findList() {
        List<ProductInfo> list = productService.findList(Arrays.asList("157875196366162707", "157875196366164846"));
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    public void decreaseStock() {
        CartDto cartDto = new CartDto("157875196366162707", 3);
        productService.decreaseStock(Arrays.asList(cartDto));
    }
}