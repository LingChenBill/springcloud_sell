package com.lc.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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
     * @RabbitListener(bindings = @QueueBinding: 自动创建, Exchange和Queue绑定.
     * @param message
     */
//    @RabbitListener(queues = "myQueue")
//    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        log.info("MqReceiver: {}", message);
    }

    /**
     * 数码消息队列,接收消息.
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void processComputer(String message) {
        log.info("Computer Receiver: {}", message);
    }

    /**
     * 水果消息队列,接收消息.
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitOrder")
    ))
    public void processFruit(String message) {
        log.info("Fruit Receiver: {}", message);
    }

}
