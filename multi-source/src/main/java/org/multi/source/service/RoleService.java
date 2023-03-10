package org.multi.source.service;

import org.multi.source.domain.Role;
import org.multi.source.pojo.MsUser;

public interface RoleService {
    void save();

    Role findById(Integer id);
}
