package com.lc.product.service.impl;

import com.lc.product.dataobject.ProductInfo;
import com.lc.product.dto.CartDto;
import com.lc.product.enums.ProductStatusEnum;
import com.lc.product.enums.ResultEnum;
import com.lc.product.exception.ProductException;
import com.lc.product.repository.ProductInfoRepository;
import com.lc.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 商品服务实现类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**
     * 查询所有在架商品.
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    /**
     * 查询商品列表.
     * @param productIdList
     * @return
     */
    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    /**
     * 扣库存.
     * @param cartDtoList
     */
    @Override
    public void decreaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto: cartDtoList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDto.getProductId());
            // 判断商品是否存在.
            if (!productInfoOptional.isPresent()) {
                log.error("[商品不存在], 商品={}", cartDto);
                throw new ProductException(ResultEnum.PRODUCT_NO_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (result < 0) {
                log.error("[库存有误], 商品库存={}", productInfo.getProductStock().toString());
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            // 更新库存.
            productInfo.setProductStock(result);
            productInfo.setUpdateTime(new Date());
            productInfoRepository.save(productInfo);
        }
    }
}
