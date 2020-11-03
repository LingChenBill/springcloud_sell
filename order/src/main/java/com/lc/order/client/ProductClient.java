package com.lc.order.client;

import com.lc.order.dataobject.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/msg")
    public String productMsg();

    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);
}
