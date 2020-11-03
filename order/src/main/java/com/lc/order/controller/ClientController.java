package com.lc.order.controller;

import com.lc.order.client.ProductClient;
import com.lc.order.dataobject.ProductInfo;
import com.lc.order.dto.CartDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Templates;
import java.util.Arrays;
import java.util.List;

/**
 * RestTemplate调用服务的三种方式
 * @description:
 * @author: lingchen
 * @date: 2020/11/2
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProductClient productClient;

//    @Autowired
//    private RestTemplate restTemplate;

    @GetMapping("/product/msg")
    public String getProductMsg() {

        // 第一种方式:直接使用RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        String msg = restTemplate.getForObject("http://localhost:8080/msg", String.class);
        log.info("msg={}", msg);

        return msg;
    }

    @GetMapping("/product/msg2")
    public String getProductMsg2() {

        // 第二种方式:利用LoadBalancerClient, 获取url,使用RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");

        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";

        String msg = restTemplate.getForObject(url, String.class);
        log.info("msg={}", msg);

        return msg;
    }

//    @GetMapping("/product/msg3")
//    public String getProductMsg3() {
//
//        // 第二种方式:使用LoadBalanced注解,使用RestTemplate,直接使用微服务的名称.
//        String msg = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//        log.info("msg={}", msg);
//
//        return msg;
//    }

    /**
     * 通过FeignClient方式来调用商品微服务接口.
     * @return
     */
    @GetMapping("/getProductMsg")
    public String getProductClientMsg() {
        String response = productClient.productMsg();
        log.info("response={}", response);
        return response;
    }

    /**
     * 查询商品列表.
     * @return
     */
    @GetMapping("/getProductList")
    public String getProductList() {
        List<ProductInfo> productInfoList = productClient.listForOrder(Arrays.asList("157875196366162707"));
        log.info("response={}", productInfoList);
        return "OK";
    }

    /**
     * 扣库存.
     * @return
     */
    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock() {
        productClient.decreaseStock(Arrays.asList(new CartDto("157875196366162707", 3)));
        return "OK";
    }
}
