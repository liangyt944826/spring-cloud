package com.example.orderservice.controller;

import entity.Order;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${profile.name}")
    private String name;

    @RequestMapping("findById")
    public Order findById(@RequestParam("orderId") int orderId){
        Order order = new Order();
        order.setOrderId(orderId);
        order.setDate(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        order.setAmount(200);

        User body = restTemplate.getForEntity("http://user-service/user/findById?userId=" + 1, User.class).getBody();
        order.setUser(body);
        return order;
    }

    @GetMapping("name")
    public String name(){
        return name;
    }
}
