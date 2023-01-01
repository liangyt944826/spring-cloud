package com.example.orderservice.controller;

import com.example.common.entity.Order;
import com.example.common.entity.User;
import com.example.common.feign.UserServiceFeign;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("order")
/**
 * 动态刷新配置
 */
@RefreshScope
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserServiceFeign userServiceFeign;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${profile.name}")
    private String name;

    /**
     * feign远程调用方法
     * @return
     */
    @RequestMapping("findById")                               // 获取gateway中filter中添加的requestHeader
    public Order findById(@RequestParam("orderId") int orderId, @RequestHeader("X-Request-red") String color){
        System.out.println(color);
        Order order = new Order();
        order.setOrderId(orderId);
        order.setDate(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        order.setAmount(200);

        User user = userServiceFeign.findById(1);
        order.setUser(user);
        return order;
    }

    /**
     * nacos配置中心配置的name测试是否读取成功，还可以动态刷新
     * @return
     */
    @GetMapping("name")
    public String name(){
        return name;
    }

    /**
     * 发送到rabbitmq中的队列方法
     * @return
     */
    @GetMapping("queueMq")
    public String mq(@RequestParam("msg") String msg){
        rabbitTemplate.convertAndSend("order", msg);
        return "ok";
    }

    /**
     * 发送到rabbitmq中的广播交换机
     * @return
     */
    @GetMapping("fanout")
    public String fanout(@RequestParam("msg")String msg){
        rabbitTemplate.convertAndSend("fanout", "", msg);
        return "ok";
    }

    /**
     * 发送到rabbitmq中的路由交换机
     * @return
     */
    @GetMapping("direct")
    public String direct(@RequestParam("key") String key, @RequestParam("msg") String msg){
        rabbitTemplate.convertAndSend("direct", key, msg);
        return "ok";
    }

    /**
     * 发送到rabbitmq中的主题交换机
     * @return
     */
    @GetMapping("topic")
    public String topic(@RequestParam("key") String key, @RequestParam("msg") String msg){
        rabbitTemplate.convertAndSend("topic", key, msg);
        return "ok";
    }
}
