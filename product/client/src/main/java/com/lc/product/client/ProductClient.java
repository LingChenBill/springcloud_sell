package com.lc.product.client;

import com.lc.product.common.DecreaseStockInput;
import com.lc.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 商品Feign客户端类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/3
 */
@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> cartDtoList);
}
