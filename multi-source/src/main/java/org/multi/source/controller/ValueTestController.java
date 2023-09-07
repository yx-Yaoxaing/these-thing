package org.multi.source.controller;

import org.multi.source.mapper.slave.UserMapper;
import org.multi.source.pojo.MsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ValueTestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/value/test")
    public MsUser test(String userName,Integer age){

        MsUser user = userMapper.findByUserNameAndAge(userName, age);
        return user;
    }

    @GetMapping("/value/list/test")
    public List<MsUser> test(String[] arr){
        List<String> list = Arrays.asList(arr);
        List<MsUser> user = userMapper.findByUserNames(list);
        return user;
    }

    @GetMapping("/value/list2/test")
    public List<MsUser> test2(String[] arr,Integer age){
        List<String> list = Arrays.asList(arr);
        List<MsUser> user = userMapper.findByUserNamesAndAge(list,age);
        return user;
    }


}
