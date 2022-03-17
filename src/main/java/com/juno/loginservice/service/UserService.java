package com.juno.loginservice.service;

import com.juno.loginservice.controller.vo.RequestUser;
import com.juno.loginservice.service.vo.ResponseUser;

public interface UserService {
    ResponseUser join(RequestUser user);
}
