package com.lc.product.service;

import com.lc.product.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目服务类。
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
public interface CategoryService {

    /**
     * 获取类目type列表
     * @param categoryTypeList
     * @return
     */
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
