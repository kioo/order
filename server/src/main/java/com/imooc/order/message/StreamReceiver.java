package com.imooc.order.message;

import com.imooc.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {

    /*@StreamListener("myMessage")
    public void process(Object message){
        log.info("StreamReceiver: {}", message);
    }*/

    /**
     * 接收orderDTO对象
     * @param message
     */
    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.INPUT2)
    public String process(OrderDTO message){
        log.info("StreamReceiver: {}", message);
        return "received.";
    }

    @StreamListener(StreamClient.INPUT2)
    public void process(String message){
        log.info("StreamReceiver2: {}", message);
    }
}
