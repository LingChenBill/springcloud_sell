package com.lc.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lc.order.utils.JsonUtil;
import com.lc.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品消息接收类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/14
 */
@Component
@Slf4j
public class ProductInfoReceiver {

    // redis中库存缓存key.
    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 商品:扣库存消息接收.
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        // message -> ProductInfoOutput
//        ProductInfoOutput productInfoOutput = (ProductInfoOutput) JsonUtil.fromJson(message, ProductInfoOutput.class);

        List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>) JsonUtil.fromJson(message,
                new TypeReference<List<ProductInfoOutput>>() {});
        log.info("从队列{}接收到消息 {}", "productInfo", productInfoOutputList);

        // 存储到redis中.
        for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()),
                    String.valueOf(productInfoOutput.getProductStock()));
        }
    }
}
