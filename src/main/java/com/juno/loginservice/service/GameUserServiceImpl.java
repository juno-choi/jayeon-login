package com.juno.loginservice.service;

import com.juno.loginservice.controller.vo.RequestGameUser;
import com.juno.loginservice.entity.GameUserEntity;
import com.juno.loginservice.repository.GameUserRepository;
import com.juno.loginservice.service.vo.GameUserVo;
import com.juno.loginservice.service.vo.ResponseGameUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GameUserServiceImpl implements GameUserService{
    private final BCryptPasswordEncoder passwordEncoder;
    private final GameUserRepository gameUserRepository;

    @Override
    public ResponseGameUser join(RequestGameUser requestGameUser) {

        GameUserEntity gameUserEntity = GameUserEntity.builder()
                .userId(requestGameUser.getUserId())
                .pw(passwordEncoder.encode(requestGameUser.getPw()))
                .name(requestGameUser.getName())
                .build();

        GameUserEntity save = gameUserRepository.save(gameUserEntity);
        ResponseGameUser responseGameUser = new ResponseGameUser(save.getUserId());

        return responseGameUser;
    }

    @Override
    public GameUserVo getUserDetailByUserId(String userId) {
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
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        GameUserEntity user = gameUserRepository.findByUserId(userId);
        if(user == null) throw new UsernameNotFoundException("user가 존재하지 않습니다.");

        return new User(user.getUserId(), user.getPw(), true, true, true, true, new ArrayList<>());
    }
}
