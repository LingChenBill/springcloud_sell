package com.lc.product.repository;

import com.lc.product.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 类目仓库类。
 *
 * @description:
 * @author: lingchen
 * @date: 2020/10/31
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 获取类目type列表
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
