package org.multi.source.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface WRMapper {

    void saveW(String message);

    Map<Object,Object> findById(Integer id);

}
