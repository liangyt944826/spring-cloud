package com.example.mqconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 主题模式，与路由模式类似，不过主题有通配符，匹配的就会发送 #表示匹配所有
 */
@Service
@Slf4j
public class TopicExchangeConsumer {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "topicConsumer1"),
            //绑定的交换机类型为direct（路由模式），交换机名称为direct，默认为路由模式，所以不需要指定类型
            exchange = @Exchange(name = "topic", type = ExchangeTypes.TOPIC),
            //绑定对应的路由，满足路由才会发送到对应的队列
            key = {"china.#"}))
    public void consumer1(String msg){
        log.info("topic消费者1消费到消息：{}", msg);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "topicConsumer2"),
            //绑定的交换机类型为direct（路由模式），交换机名称为direct，默认为路由模式，所以不需要指定类型
            exchange = @Exchange(name = "topic", type = ExchangeTypes.TOPIC),
            key = {"#.news"}))
    public void consumer2(String msg){
        log.info("topic消费者2消费到消息：{}", msg);
    }
}
