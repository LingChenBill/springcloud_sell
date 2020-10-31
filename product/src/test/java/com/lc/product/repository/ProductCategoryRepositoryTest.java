package com.lc.product.repository;

import com.lc.product.dataobject.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: lingchen
 * @date: 2020/10/31
 */
@SpringBootTest
class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * 获取类目type列表测试
     */
    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11, 22));
        Assertions.assertTrue(list.size() > 0);
    }
}