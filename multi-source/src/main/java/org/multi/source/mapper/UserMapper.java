package org.multi.source.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.multi.source.multi.annotation.DB;
import org.multi.source.pojo.MsUser;

@Mapper
public interface UserMapper {

    void save(MsUser user);

    @DB("slave1")
    MsUser findById(Integer id);

}
