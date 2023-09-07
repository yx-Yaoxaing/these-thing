package org.multi.source.service.impl;

import org.multi.source.domain.Role;
import org.multi.source.repository.RoleDao;
import org.multi.source.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

//    @Autowired(required = false)
//    private RoleDao roledao;


    @Override
    public void save() {
        Role role = new Role();
        role.setRoleName(UUID.randomUUID().toString());
       // roledao.save(role);
    }

    @Override
    public Role findById(Integer id) {
        //return roledao.findById(id).orElse(new Role());
        return null;
    }
}
