package org.multi.source.service.impl;


import org.multi.source.mapper.UserMapper;
import org.multi.source.pojo.MsUser;
import org.multi.source.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save() {
        MsUser user = new MsUser();
        user.setUserName(UUID.randomUUID().toString());
        user.setLoginName(UUID.randomUUID().toString());
        user.setAge(new Random(50).nextInt()+1);
        userMapper.save(user);
    }

    @Override
    public MsUser findById(Integer id) {
       return userMapper.findById(id);
    }
}
