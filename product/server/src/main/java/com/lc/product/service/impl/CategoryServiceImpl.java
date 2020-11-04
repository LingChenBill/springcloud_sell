package com.lc.product.service.impl;

import com.lc.product.dataobject.ProductCategory;
import com.lc.product.repository.ProductCategoryRepository;
import com.lc.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目服务实现类。
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * 获取类目type列表
     * @param categoryTypeList
     * @return
     */
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
