package com.juno.loginservice.controller;

import com.juno.loginservice.api.CommonApi;
import com.juno.loginservice.api.CommonEnum;
import com.juno.loginservice.controller.code.UserCode;
import com.juno.loginservice.controller.vo.RequestUser;
import com.juno.loginservice.service.UserService;
import com.juno.loginservice.service.exception.UserException;
import com.juno.loginservice.service.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<CommonApi<Object>> join(@Valid @RequestBody RequestUser user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error(bindingResult.toString());
            throw new UserException(UserCode.BAD_REQUEST, bindingResult);
        }

        ResponseUser responseUser = userService.join(user);
        CommonApi<Object> response = new CommonApi(CommonEnum.OK, responseUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
