package com.juno.loginservice.controller;

import com.juno.loginservice.api.CommonResponse;
import com.juno.loginservice.controller.vo.RequestLogin;
import com.juno.loginservice.controller.vo.RequestUser;
import com.juno.loginservice.service.UserService;
import com.juno.loginservice.service.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<CommonResponse<Object>> join(@RequestBody RequestUser user){
        log.debug("user = {}", user.toString());
        ResponseUser responseUser = userService.join(user);
        CommonResponse<Object> response = CommonResponse.builder()
                .code("200")    //api 정의된 코드
                .msg("회원가입 정상실행")    //api 정의된 메세지
                .data(responseUser) //데이터
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
