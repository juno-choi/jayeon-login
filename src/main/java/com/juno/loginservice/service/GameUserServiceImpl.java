package com.juno.loginservice.service;

import com.google.common.collect.Lists;
import com.juno.loginservice.controller.code.UserCode;
import com.juno.loginservice.controller.vo.RequestGameUser;
import com.juno.loginservice.controller.vo.Token;
import com.juno.loginservice.domain.game.GameRole;
import com.juno.loginservice.domain.game.GameUserEntity;
import com.juno.loginservice.exception.CommonException;
import com.juno.loginservice.exception.JoinException;
import com.juno.loginservice.repository.GameRoleRepository;
import com.juno.loginservice.repository.GameUserRepository;
import com.juno.loginservice.service.vo.GameUserVo;
import com.juno.loginservice.service.vo.ResponseGameUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GameUserServiceImpl implements GameUserService{
    private final BCryptPasswordEncoder passwordEncoder;
    private final GameUserRepository gameUserRepository;
    private final GameRoleRepository gameRoleRepository;
    private final Environment env;

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
                .id(userEntity.getId())
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .pw(userEntity.getPw())
                .roles(userEntity.getRoles())
                .createdAt(userEntity.getCreatedAt())
                .build();

        return gameUserVo;
    }

    @Override
    public List<GameUserEntity> getAllUser() {
        return Lists.newArrayList(gameUserRepository.findAll());
    }

    @Override
    public Token refreshToken(HttpServletRequest req) {
        log.info("user token refresh");
        String authorization = req.getHeader(AUTHORIZATION);

        Token token = null;
        if(authorization != null && authorization.startsWith("Bearer ")){
            String refreshToken = authorization.replace("Bearer ", "");

            try {
                //refresh token parse
                Claims claims = Jwts.parser().setSigningKey(env.getProperty("token.secret"))
                        .parseClaimsJws(refreshToken).getBody();

                //refresh token에는 userId와 만기일만 담겨 있음
                String userId = claims.getSubject();
                GameUserEntity user = gameUserRepository.findByUserId(userId);

                //access token 재발급
                String accessToken = Jwts.builder()
                        .setSubject(user.getUserId())
                        .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.access-token.expiration_time")))) //파기일
                        .claim("roles", user.getRoles().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))    //암호화 알고리즘과 암호화 키값
                        .compact();
                token = new Token(accessToken, refreshToken);
            }catch (Exception e){
                //token이 유효하지 않을 경우 ex) refresh token의 정보가 잘못 되었거나 유효기간을 초과함
                log.error("user token refresh error : {}", e.getMessage());
                throw new CommonException("401", "token이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED);
            }
        }else{
            // 요청이 잘못됨
            String msg = "token 요청이 잘못 되었습니다.";
            log.error("user token refresh error : {}", msg);
            throw new CommonException("404", msg, HttpStatus.BAD_REQUEST);
        }
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        GameUserEntity user = gameUserRepository.findByUserId(userId);
        if(user == null) throw new UsernameNotFoundException("user가 존재하지 않습니다.");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(user.getUserId(), user.getPw(), true, true, true, true, authorities);
    }
}
