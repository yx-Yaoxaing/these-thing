package com.database.shard.mapper;

import com.database.shard.pojo.OrderDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    void addOrder(OrderDto order);

}
