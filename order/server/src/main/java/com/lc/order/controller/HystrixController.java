package com.lc.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Hystrix控制器.
 * @description: 单个访问配置服务降级.
 *      全局配置服务降级.
 * @author: lingchen
 * @date: 2020/11/22
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    /**
     * 获取商品列表.
     * hystrix的服务降级.
     * 单个方法的fallback.
     * 全局的defaultFallback.
     * 配置过期时间:HystrixCommandProperties类里查找.
     * # 服务熔断:
     *   http://localhost:8081/getProductList?number=1
     *   http://localhost:8081/getProductList?number=2
     * 在bootstrap.yml中配置时,  @HystrixCommand 注解要加上.
     * @return
     */
//    @HystrixCommand(fallbackMethod = "fallback")
    // 过期时间配置.
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    // 服务熔断
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),                     // 熔断开启.
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),        // 时钟状态.
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),  // 时间窗口
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")       // 阈值百分比触发.
//    })
    @HystrixCommand
    @GetMapping("/getProductList")
    public String getProductInfoList(@RequestParam("number") Integer number) {

        if (number % 2 == 0) {
            return "circuit break success!";
        }

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

    /**
     * 默认的服务降级:defaultFallback方法.
     * @return
     */
    private String defaultFallback() {
        return "默认提示: 太拥挤了,请稍后再试~";
    }
}
