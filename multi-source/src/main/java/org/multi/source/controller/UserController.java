package org.multi.source.controller;

import org.multi.source.pojo.MsUser;
import org.multi.source.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

     @GetMapping("/save")
     public String save(){
          userService.save();
          return "success";
     }

    @GetMapping("/findById")
    public MsUser findById(Integer id){
        return userService.findById(id);
    }
}
