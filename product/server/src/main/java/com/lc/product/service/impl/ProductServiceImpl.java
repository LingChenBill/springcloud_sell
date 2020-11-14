package com.lc.product.service.impl;

import com.lc.product.common.DecreaseStockInput;
import com.lc.product.common.ProductInfoOutput;
import com.lc.product.dataobject.ProductInfo;
import com.lc.product.enums.ProductStatusEnum;
import com.lc.product.enums.ResultEnum;
import com.lc.product.exception.ProductException;
import com.lc.product.repository.ProductInfoRepository;
import com.lc.product.service.ProductService;
import com.lc.product.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private AmqpTemplate amqpTemplate;

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
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                }).collect(Collectors.toList());
    }

    /**
     * 扣库存, 并发送消息.
     * @param decreaseStockInputList
     */
    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        // 将库存DB操作完成后,统一发送消息.
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);

        // 转换消息.
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());

        // 发送消息.
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
    }

    /**
     * 扣库存-操作.
     * @param decreaseStockInputList
     */
    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput: decreaseStockInputList) {

            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(
                    decreaseStockInput.getProductId());
            // 判断商品是否存在.
            if (!productInfoOptional.isPresent()) {
                log.error("[商品不存在], 商品={}", decreaseStockInput);
                throw new ProductException(ResultEnum.PRODUCT_NO_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                log.error("[库存有误], 商品库存={}", productInfo.getProductStock().toString());
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            // 更新库存.
            productInfo.setProductStock(result);
            productInfo.setUpdateTime(new Date());
            productInfoRepository.save(productInfo);

            productInfoList.add(productInfo);
        }

        return productInfoList;
    }

}
