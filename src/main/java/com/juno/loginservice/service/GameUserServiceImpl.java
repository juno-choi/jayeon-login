package com.juno.loginservice.service;

import com.google.common.collect.Lists;
import com.juno.loginservice.controller.code.UserCode;
import com.juno.loginservice.controller.vo.RequestGameUser;
import com.juno.loginservice.entity.GameRole;
import com.juno.loginservice.entity.GameUserEntity;
import com.juno.loginservice.exception.JoinException;
import com.juno.loginservice.repository.GameRoleRepository;
import com.juno.loginservice.repository.GameUserRepository;
import com.juno.loginservice.service.vo.GameUserVo;
import com.juno.loginservice.service.vo.ResponseGameUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GameUserServiceImpl implements GameUserService{
    private final BCryptPasswordEncoder passwordEncoder;
    private final GameUserRepository gameUserRepository;
    private final GameRoleRepository gameRoleRepository;

    @Override
    public ResponseGameUser join(RequestGameUser requestGameUser) {
        log.info("user join {}", requestGameUser.getName());
        //유효성 검사
        GameUserEntity user = gameUserRepository.findByUserId(requestGameUser.getUserId());
        if(user != null){
            throw new JoinException(UserCode.EXIST_USER);
        }

        GameUserEntity gameUserEntity = GameUserEntity.builder()
                .userId(requestGameUser.getUserId())
                .pw(passwordEncoder.encode(requestGameUser.getPw()))
                .name(requestGameUser.getName())
                .build();

        GameUserEntity save = gameUserRepository.save(gameUserEntity);
        addRoleToUser(save.getUserId(), "ROLE_ADMIN");
        ResponseGameUser responseGameUser = new ResponseGameUser(save.getUserId());

        return responseGameUser;
    }

    @Override
    public GameRole saveRole(GameRole role) {
        log.info("user save role {}",role.getName());
        return gameRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userId, String roleName) {
        log.info("user add user = {}, role = {}",userId, roleName);
        GameUserEntity user = gameUserRepository.findByUserId(userId);
        GameRole role = gameRoleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public GameUserVo getUserDetailByUserId(String userId) {
        log.info("getUserDetailByUserId {}",userId);
        GameUserEntity userEntity = gameUserRepository.findByUserId(userId);

        GameUserVo gameUserVo = GameUserVo.builder()
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .pw(userEntity.getPw())
                .createdAt(userEntity.getCreatedAt())
                .build();

        return gameUserVo;
    }

    @Override
    public List<GameUserEntity> getAllUser() {
        return Lists.newArrayList(gameUserRepository.findAll());
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        GameUserEntity user = gameUserRepository.findByUserId(userId);
        if(user == null) throw new UsernameNotFoundException("user가 존재하지 않습니다.");

        return new User(user.getUserId(), user.getPw(), true, true, true, true, new ArrayList<>());
    }
}
