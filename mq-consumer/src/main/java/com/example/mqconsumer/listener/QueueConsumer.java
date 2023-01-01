package com.example.mqconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QueueConsumer {

    /**
     * 监听队列
     */
    @RabbitListener(queues = {"order"})
    public void testConsumer(String msg){
        log.info("消费者1消费消息，消息为：{}", msg);
    }

    /**
     * 监听队列
     */
    @RabbitListener(queues = {"order"})
    public void testConsumer1(String msg){
        log.info("消费者2消费消息，消息为：{}", msg);
    }
}
