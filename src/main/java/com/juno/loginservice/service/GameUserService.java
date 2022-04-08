package com.juno.loginservice.service;

import com.juno.loginservice.controller.vo.RequestGameUser;
import com.juno.loginservice.service.vo.GameUserVo;
import com.juno.loginservice.service.vo.ResponseGameUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface GameUserService extends UserDetailsService {
    ResponseGameUser join(RequestGameUser requestGameUser);

    GameUserVo getUserDetailByUserId(String userId);
}
