package com.lc.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ消息接收类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/9
 */
@Component
@Slf4j
public class MqReceiver {

    /**
     * myQueue消息队列接收.
     * @RabbitListener(queues = "myQueue"): 控制台要手动创建消息队列.
     *   错误:DeclarationException: Failed to declare queue(s):[myQueue]
     * @RabbitListener(queuesToDeclare = @Queue("myQueue")): 自动创建消息队列,不用控制台手动创建.
     * @param message
     */
//    @RabbitListener(queues = "myQueue")
    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void process(String message) {
        log.info("MqReceiver: {}", message);
    }
}
