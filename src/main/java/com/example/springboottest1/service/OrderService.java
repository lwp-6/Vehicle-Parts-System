package com.example.springboottest1.service;

import com.example.springboottest1.entity.Order;
import com.example.springboottest1.mapper.orderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {
    @Autowired
    private orderMapper ordermapper;
    public List<Order> orderSearchbyuser_buy(String user_buy)
    {
        return ordermapper.orderSearchbyuser_buy(user_buy);
    }
    public List<Order> orderSearchbyuser_sold(String user_sold)
    {
        return ordermapper.orderSearchbyuser_sold(user_sold);
    }
    public int addorder(String partsname, int quantity, double price, String user_buy, String user_sold)
    {
        return ordermapper.addorder(partsname, quantity, price, user_buy, user_sold);
    }
}

