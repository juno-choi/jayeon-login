package com.juno.loginservice.service;

import com.juno.loginservice.controller.vo.RequestGameUser;
import com.juno.loginservice.service.vo.ResponseGameUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class GameUserServiceImplInteTest {
    @Autowired
    private GameUserService gameUserService;

    @Test
    @DisplayName("회원가입 테스트")
    void join() {
        //given
        String joinUser = UUID.randomUUID().toString().substring(0,10);
        RequestGameUser user = new RequestGameUser(joinUser, "test1234", "test1234", "테스터");
        //when
        ResponseGameUser join = gameUserService.join(user);
        //then
        assertThat(join.getUserId()).isEqualTo(joinUser);
    }
}