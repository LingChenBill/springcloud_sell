package com.lc.product.service.impl;

import com.lc.product.dataobject.ProductInfo;
import com.lc.product.enums.ProductStatusEnum;
import com.lc.product.repository.ProductInfoRepository;
import com.lc.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务实现类。
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**
     * 查询所有在架商品
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
