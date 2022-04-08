package com.juno.loginservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.loginservice.controller.vo.RequestLogin;
import com.juno.loginservice.service.GameUserService;
import com.juno.loginservice.service.UserService;
import com.juno.loginservice.service.vo.GameUserVo;
import com.juno.loginservice.service.vo.UserVo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class GameAuthFilter extends UsernamePasswordAuthenticationFilter {
    private final GameUserService gameUserService;
    private final Environment env;

    //로그인 요청
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            RequestLogin login = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            //인증정보 생성
            return getAuthenticationManager()
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    login.getUserId(),
                                    login.getPw(),
                                    new ArrayList<>() //권한
                            )
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //로그인 성공
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        GameUserVo user = gameUserService.getUserDetailByUserId(((User) authResult.getPrincipal()).getUsername());
        log.info("로그인 성공 = {}",user.toString());
        String token = Jwts.builder()
                .setSubject(user.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time")))) //파기일
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))    //암호화 알고리즘과 암호화 키값
                .compact();

        response.setHeader("token", token);
    }
}
