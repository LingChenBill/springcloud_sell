package com.lc.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Hystrix控制器.
 * @description:
 * @author: lingchen
 * @date: 2020/11/22
 */
@RestController
public class HystrixController {

    /**
     * 获取商品列表.
     * hystrix的服务降级.
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/getProductList")
    public String getProductInfoList() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8090/product/listForOrder",
                Arrays.asList("157875196366160022"),
                String.class);
    }

    /**
     * getProductInfoList的fallback方法.
     * @return
     */
    private String fallback() {
        return "太拥挤了,请稍后再试~";
    }
}
