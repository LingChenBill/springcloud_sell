package com.lc.order.controller;

import com.lc.order.dto.OrderDto;
import com.lc.order.message.StreamClient;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Spring cloud stream rabbitmq消息控制器.
 * @description:
 * @author: lingchen
 * @date: 2020/11/14
 */
@RestController
@RequestMapping("stream")
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    /**
     * 发送消息.
     */
    @GetMapping("/sendMessage")
    public void process() {
        String message = "now: " + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }

    /**
     * 发送消息.
     */
    @GetMapping("/sendOrderDto")
    public void processOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId("1000001001");
        streamClient.output().send(MessageBuilder.withPayload(orderDto).build());
    }
}
