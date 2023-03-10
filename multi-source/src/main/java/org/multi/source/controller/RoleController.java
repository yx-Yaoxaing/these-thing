package org.multi.source.controller;


import org.multi.source.domain.Role;
import org.multi.source.pojo.MsUser;
import org.multi.source.service.RoleService;
import org.multi.source.service.UserService;
import org.multi.source.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role/save")
    public String save(){
        roleService.save();
        return "success";
    }

    @GetMapping("/role/findById")
    public Role findById(Integer id){
        return roleService.findById(id);
    }
}
