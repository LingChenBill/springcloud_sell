package com.lc.order.message;

import com.lc.order.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * spring cloud stream rabbit接收类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/14
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

//    /**
//     * 消息接收器.
//     * @param message
//     */
//    @StreamListener(StreamClient.INPUT)
//    public void process(Object message) {
//        log.info("StreamReceiver: {}", message);
//    }

    /**
     * 消息接收器(接收对象OrderDto).
     * @param message
     */
    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.RECEIVED)
    public String processOrderDto(OrderDto message) {
        log.info("StreamReceiver: {}", message);
        return "Stream message received.";
    }

    /**
     * 消息消费反馈.
     * @param message
     */
    @StreamListener(StreamClient.RECEIVED)
    public void processReceived(String message) {
        log.info("Stream received: {}", message);
    }

}
