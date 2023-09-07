package org.multi.source.mapper.master;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScDataMapper {
    void saveScData(String name);
}
