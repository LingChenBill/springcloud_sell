package com.lc.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * spring cloud stream rabbitmq客户端.
 * @description:
 * @author: lingchen
 * @date: 2020/11/14
 */
public interface StreamClient {

    // 消息队列名称.
    String INPUT = "myStreamMessage";

    // 消息消费反馈.
    String RECEIVED = "myStreamReceived";

    // 消息消费.
    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.INPUT)
    MessageChannel output();

    // 消息消费反馈.
    @Input(StreamClient.RECEIVED)
    SubscribableChannel inputReceive();

    @Output(StreamClient.RECEIVED)
    MessageChannel outputReceive();

}
