package org.multi.source.service.impl;


import org.multi.source.mapper.master.ScDataMapper;
import org.multi.source.mapper.slave.UserMapper;
import org.multi.source.pojo.MsUser;
import org.multi.source.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ScDataMapper scDataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class) //connection
    public void save() {
        // 另一个数据源
        scDataMapper.saveScData("uYxUuu");
        saveScData();
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveScData(){
        MsUser user = new MsUser();
        user.setUserName(UUID.randomUUID().toString());
        user.setLoginName(UUID.randomUUID().toString());
        user.setAge(new Random(50).nextInt()+1);
        // 一个数据源
        userMapper.save(user);
        //int i  = 1 /0;
    }



    @Override
    public MsUser findById(Integer id) {
       return userMapper.findById(id);
    }
}
