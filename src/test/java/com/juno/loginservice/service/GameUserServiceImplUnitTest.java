package com.juno.loginservice.service;

import com.juno.loginservice.controller.vo.RequestGameUser;
import com.juno.loginservice.domain.game.GameRole;
import com.juno.loginservice.domain.game.GameUser;
import com.juno.loginservice.repository.GameRoleRepository;
import com.juno.loginservice.repository.GameUserRepository;
import com.juno.loginservice.repository.GameUserRoleMappingRepository;
import com.juno.loginservice.service.vo.ResponseGameUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameUserServiceImplUnitTest {
    @InjectMocks
    private GameUserServiceImpl gameUserService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private GameUserRepository gameUserRepository;
    @Mock
    private GameRoleRepository gameRoleRepository;
    @Mock
    private GameUserRoleMappingRepository gameUserRoleMappingRepository;
    @Mock
    private Environment environment;

    @Test
    @DisplayName("회원가입 테스트")
    void join() {
        //given
        RequestGameUser requestGameUser = new RequestGameUser("joinUser", "test1234", "test1234", "테스터");
        String encodePw = "암호화된 비밀번호";
        LocalDateTime date = LocalDateTime.now();
        GameUser gameUser = GameUser.builder()
                .gameUserId(1L)
                .userId(requestGameUser.getUserId())
                .name(requestGameUser.getName())
                .pw(encodePw)
                .updatedAt(date)
                .createdAt(date)
                .build();
        GameRole gameRole = GameRole.builder()
                .gameRoleId(1L)
                .name("USER")
                .build();

        given(passwordEncoder.encode(requestGameUser.getPw())).willReturn(encodePw);
        //when
        when(gameRoleRepository.findByName("USER")).thenReturn(gameRole);
        when(gameUserRepository.save(any())).thenReturn(gameUser);

        ResponseGameUser responseGameUser = gameUserService.join(requestGameUser);
        //then
        verify(gameUserRepository, times(1)).save(any());

        Assertions.assertThat(responseGameUser.getUserId()).isEqualTo(responseGameUser.getUserId());
    }
}