package com.lc.order.message;

import com.lc.order.OrderApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * RabbitMQ消息发送测试.
 * @description:
 * @author: lingchen
 * @date: 2020/11/9
 */
@Component
class MqReceiverTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send() {
        amqpTemplate.convertAndSend("myQueue", "now: " + new Date());
    }
}