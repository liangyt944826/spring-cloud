package com.example.mqconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 路由模式，绑定队列中路由满足的才会发送到对应的队列
 */
@Service
@Slf4j
public class DirectExchangeConsumer {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "directConsumer1"),
            //绑定的交换机类型为direct（路由模式），交换机名称为direct，默认为路由模式，所以不需要指定类型
            exchange = @Exchange(name = "direct"),
            //绑定对应的路由，满足路由才会发送到对应的队列
            key = {"red", "black"}))
    public void consumer1(String msg){
        log.info("direct消费者1消费到消息：{}", msg);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "directConsumer2"),
            //绑定的交换机类型为direct（路由模式），交换机名称为direct，默认为路由模式，所以不需要指定类型
            exchange = @Exchange(name = "direct"),
            key = {"red", "blue"}))
    public void consumer2(String msg){
        log.info("direct消费者2消费到消息：{}", msg);
    }
}
