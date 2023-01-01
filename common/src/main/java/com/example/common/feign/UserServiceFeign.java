package com.example.common.feign;

import com.example.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("userservice")
public interface UserServiceFeign {

    @GetMapping("/user/findById")
    User findById(@RequestParam("userId") int userId);
}
