package com.juno.loginservice.controller;

import com.juno.loginservice.api.CommonApi;
import com.juno.loginservice.api.CommonEnum;
import com.juno.loginservice.controller.code.UserCode;
import com.juno.loginservice.controller.vo.RequestGameUser;
import com.juno.loginservice.controller.vo.Token;
import com.juno.loginservice.entity.GameRole;
import com.juno.loginservice.entity.GameUserEntity;
import com.juno.loginservice.service.GameUserService;
import com.juno.loginservice.exception.UserException;
import com.juno.loginservice.service.vo.ResponseGameUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
@Slf4j
public class GameUserController {
    private final GameUserService gameUserService;

    //회원가입
    @PostMapping("/join")
    public ResponseEntity<CommonApi> join(@RequestBody @Valid RequestGameUser requestGameuser, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("game user 회원가입 잘못된 요청");
            throw new UserException(UserCode.BAD_REQUEST, bindingResult);
        }
        
        ResponseGameUser user = gameUserService.join(requestGameuser);
        CommonApi<Object> response = new CommonApi(CommonEnum.OK, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    //role 추가
    /*
     * 추후에 role 매개변수를 통해 role을 추가하는 메서드로 변경해서 사용할 것
     */
    @PostMapping("/role")
    public ResponseEntity<CommonApi> role(){
        GameRole role = new GameRole(null, "ROLE_ADMIN");
        GameRole gameRole = gameUserService.saveRole(role);
        CommonApi<Object> response = new CommonApi(CommonEnum.OK, gameRole);
        return ResponseEntity.ok().body(response);
    }

    //모든 game user만 조회해오는 로직
    //임시용
    @GetMapping("/user")
    public ResponseEntity<CommonApi> allUser(){
        List<GameUserEntity> allUser = gameUserService.getAllUser();
        CommonApi<Object> response = new CommonApi(CommonEnum.OK, allUser);
        return ResponseEntity.ok().body(response);
    }

    /*
     * refresh token 발급
     */
    @PostMapping("/token/refresh")
    public ResponseEntity<Token> tokenRefresh(HttpServletRequest req){
        Token token = gameUserService.refreshToken(req);
        return ResponseEntity.ok().body(token);
    }
}
