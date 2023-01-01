package com.example.mqconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 广播模式，会发送给exchage绑定的所有队列
 */
@Service
@Slf4j
public class FanoutExchangeConsumer {

                                                            //绑定的队列名称叫 fanoutConsumer1
    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "fanoutConsumer1"),
        //绑定的交换机类型为FANOUT（广播模式），交换机名称为fanout
        exchange = @Exchange(name = "fanout", type = ExchangeTypes.FANOUT)))
    public void consumer1(String msg){
        log.info("fanout消费者1消费到消息：{}", msg);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "fanoutConsumer2"),
            exchange = @Exchange(name = "fanout", type = ExchangeTypes.FANOUT)))
    public void consumer2(String msg){
        log.info("fanout消费者2消费到消息：{}", msg);
    }
}
