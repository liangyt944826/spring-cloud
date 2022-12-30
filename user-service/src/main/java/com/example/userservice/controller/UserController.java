package com.example.userservice.controller;

import entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("findById")
    public User findById(@RequestParam("userId") int userId){
        User user = new User();
        user.setUserId(userId);
        user.setAge(15);
        user.setName("啊哈");
        System.out.println(user);
        return user;
    }
}
