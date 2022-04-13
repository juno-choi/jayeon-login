package com.juno.loginservice.controller;

import com.juno.loginservice.api.CommonApi;
import com.juno.loginservice.api.CommonEnum;
import com.juno.loginservice.controller.code.UserCode;
import com.juno.loginservice.controller.vo.RequestGameUser;
import com.juno.loginservice.service.GameUserService;
import com.juno.loginservice.exception.UserException;
import com.juno.loginservice.service.vo.ResponseGameUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
@Slf4j
public class GameUserController {
    private final GameUserService gameUserService;

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
}
