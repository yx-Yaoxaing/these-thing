package org.multi.source.controller;

import org.multi.source.mapper.WRMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WRController {


    @Autowired
    private WRMapper wRMapper;

    @GetMapping("/w")
    public void saveW(String message){
        wRMapper.saveW(message);
    }

    @GetMapping("/r")
    public Map<Object, Object> r(Integer id){
        return wRMapper.findById(id);
    }

}
