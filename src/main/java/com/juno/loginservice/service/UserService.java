package com.juno.loginservice.service;

import com.juno.loginservice.controller.vo.RequestUser;
import com.juno.loginservice.service.vo.ResponseUser;
import com.juno.loginservice.service.vo.UserVo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    ResponseUser join(RequestUser user);

    UserVo getUserDetailByUserId(String userId);
}
