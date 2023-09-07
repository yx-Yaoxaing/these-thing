package org.multi.source.mapper.slave;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.multi.source.multi.annotation.DB;
import org.multi.source.pojo.MsUser;

import java.util.List;

@Mapper
public interface UserMapper {

    @DB("slave1")
    void save(MsUser user);



















    @DB("slave1")
    MsUser findById(Integer id);

    MsUser findByUserNameAndAge(String userName, Integer age);

    List<MsUser> findByUserNames(List<String > userNameList);

    List<MsUser> findByUserNamesAndAge(List<String > userNameList,Integer age);
}
