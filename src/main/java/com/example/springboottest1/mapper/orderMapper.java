package com.example.springboottest1.mapper;

import com.example.springboottest1.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface orderMapper {
    List<Order> orderSearchbyuser_buy(@Param("user_buy") String user_buy);
    List<Order> orderSearchbyuser_sold(@Param("user_sold") String user_sold);
    int addorder(@Param("partsname") String partsname, @Param("quantity") int quantity, @Param("price") double price, @Param("user_buy") String user_buy, @Param("user_sold") String user_sold);
}
