package com.database.shard.controller;

import cn.hutool.core.lang.Snowflake;
import com.database.shard.mapper.OrderMapper;
import com.database.shard.pojo.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping("/add")
    public void addOrder(Long userId){
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(userId);
        orderDto.setId(new Snowflake(1L,1L).nextId());
        orderDto.setCreateTime(LocalDateTime.now());
        orderMapper.addOrder(orderDto);
    }

}
