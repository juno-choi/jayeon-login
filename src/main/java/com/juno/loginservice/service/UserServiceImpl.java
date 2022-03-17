package com.juno.loginservice.service;

import com.juno.loginservice.Entity.UserEntity;
import com.juno.loginservice.controller.vo.RequestUser;
import com.juno.loginservice.repository.UserRepository;
import com.juno.loginservice.service.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public ResponseUser join(RequestUser user) {
        UserEntity userEntity = UserEntity.builder()
                .userId(user.getUserId())
                .pw(passwordEncoder.encode(user.getPw()))
                .name(user.getName())
                .address1(user.getAddress1())
                .address2(user.getAddress2())
                .address3(user.getAddress3())
                .tel(user.getTel())
                .build();

        UserEntity save = userRepository.save(userEntity);

        return ResponseUser.builder().userId(save.getUserId()).build();
    }
}
