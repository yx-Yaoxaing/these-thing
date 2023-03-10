package org.multi.source.service;

import org.multi.source.pojo.MsUser;
import org.springframework.stereotype.Service;

public interface UserService {
    void save();

    MsUser findById(Integer id);
}
