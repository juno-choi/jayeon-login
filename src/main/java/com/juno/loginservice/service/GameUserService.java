package com.juno.loginservice.service;

import com.juno.loginservice.controller.vo.RequestGameUser;
import com.juno.loginservice.controller.vo.Token;
import com.juno.loginservice.domain.game.GameRole;
import com.juno.loginservice.domain.game.GameUserEntity;
import com.juno.loginservice.service.vo.GameUserVo;
import com.juno.loginservice.service.vo.ResponseGameUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GameUserService extends UserDetailsService {
    //user join
    ResponseGameUser join(RequestGameUser requestGameUser);
    //user role 저장
    GameRole saveRole(GameRole role);
    //user role 추가
    void addRoleToUser(String userId, String roleName);
    //user find
    GameUserVo getUserDetailByUserId(String userId);
    //all user find
    List<GameUserEntity> getAllUser();
    //token 재발급
    Token refreshToken(HttpServletRequest req);
}
