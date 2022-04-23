package com.juno.loginservice.service;

import com.juno.loginservice.controller.code.UserCode;
import com.juno.loginservice.entity.UserEntity;
import com.juno.loginservice.controller.vo.RequestUser;
import com.juno.loginservice.exception.CommonException;
import com.juno.loginservice.repository.UserRepository;
import com.juno.loginservice.exception.UserException;
import com.juno.loginservice.service.vo.ResponseUser;
import com.juno.loginservice.service.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public ResponseUser join(RequestUser user) {

        UserEntity findUser = userRepository.findByUserId(user.getUserId());
        if(findUser != null) throw new CommonException(UserCode.EXIST_USER.getCode(), UserCode.EXIST_USER.getMsg(), UserCode.EXIST_USER.getStatus());

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

    @Override
    public UserVo getUserDetailByUserId(String userId) {
        UserEntity user = userRepository.findByUserId(userId);

        return UserVo.builder()
                .userId(user.getUserId())
                .pw(user.getPw())
                .name(user.getName())
                .createdAt(user.getCreatedAt())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserId(userId);
        if(user == null) throw new UsernameNotFoundException("user가 존재하지 않습니다.");

        return new User(user.getUserId(), user.getPw(), true, true, true, true, new ArrayList<>());
    }
}
