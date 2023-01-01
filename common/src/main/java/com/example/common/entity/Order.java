package com.example.common.entity;

import lombok.Data;

@Data
public class Order {

    private int orderId;

    private String date;

    private int amount;

    private User user;
}
